package com.api.test;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Sample extends BaseWrapperTest {
    private static String client_id = "40e1d19b-319f-42a1-b7a0-890843bb8952";
    private static String client_secret = "a0cc1ea897a64445896da5e037dd712e";
    private static String redirect_uri = "https://stage.id.trimblecloud.com/oauth/token";
    public static String sessionToken;

    @Test
    public static void Test001() {
        sessionToken = given().contentType("application/x-www-form-urlencoded; charset=utf-8").
                formParam("scope", "Reseller-Api")
                .formParam("grant_type", "client_credentials").formParam("redirect_uri", redirect_uri)
                .formParam("client_id", client_id).formParam("client_secret", client_secret).when()
                .post(redirect_uri).then().assertThat().statusCode(HttpStatus.SC_OK).extract()
                .jsonPath().get("access_token");
           ExtractableResponse<Response> response = given().contentType("application/x-www-form-urlencoded; charset=utf-8").
                formParam("scope", "Reseller-Api")
                .formParam("grant_type", "client_credentials").formParam("redirect_uri", redirect_uri)
                .formParam("client_id", client_id).formParam("client_secret", client_secret).when()
                .post(redirect_uri).then().assertThat().statusCode(HttpStatus.SC_OK).extract();
    }
}

