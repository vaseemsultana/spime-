package com.api.util;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import java.io.IOException;
import java.util.PropertyResourceBundle;

public class Env {
    private static PropertyResourceBundle resourceBundle;
    public static String logBug;
    public static String Url;
      static {
        try {
            String resourceFileName = "Test.properties";
            if (System.getProperty("env") != null) {
                switch (System.getProperty("env").toLowerCase()) {
                    case "ci":
                        resourceFileName = "CI.properties";
                        break;
                    case "docker":
                        resourceFileName = "Docker.properties";
                        break;
                    case "demo":
                        resourceFileName = "Demo.properties";
                        break;
                    case "dev":
                        resourceFileName = "Dev.properties";
                        break;
                    default:
                    case "test":
                        //the default file is the test (t1) instance
                        break;
                }
            }
            resourceBundle = new PropertyResourceBundle(ClassLoader.getSystemResourceAsStream(resourceFileName));
            }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(1); //can't run without proper environment settings
        }
    }
    public static String getEnvProperty(String key) {
        return resourceBundle.getString(key);
    }

    public static void setUp() {
        Url = getEnvProperty("url");
        System.out.println("Env var: " + System.getProperty("logbug"));
        if (System.getProperty("logbug") != null) {
            logBug = System.getProperty("logbug");
        } else {
            System.setProperty("logbug", "false");
            logBug = "false";
        }
        RestAssured.useRelaxedHTTPSValidation();
        if (Url != null) {
            RestAssured.baseURI = Url;
        } else {
            RestAssured.baseURI = getEnvProperty("url");
        }
         RestAssured.registerParser("text/plain", Parser.JSON);
      }
}
