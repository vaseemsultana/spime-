package com.api.test;

import com.api.util.Env;
import org.testng.annotations.BeforeClass;

public class BaseWrapperTest {

    public String sessionToken;


    //Enables to run the setUp when TestNG groups are used
    @BeforeClass(alwaysRun = true)
    protected final void beforeClass() {
        Env.setUp();
          }
  }





