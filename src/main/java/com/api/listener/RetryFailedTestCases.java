package com.api.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Kept a count that  incrementing upon failures of each given test
 * Modified to use getFailedInvocationNumbers to make compatible with tests
 * that use dataproviders, as a manual count is reset to zero each time the test is re-initialized by the data provider.
 * Code copied from UI Automation code */

public class RetryFailedTestCases implements IRetryAnalyzer {
    private static final int MAX_RETRIES = 1;
    private int retries;

    //This method will be called every time a test fails. Will return TRUE if a test fails and need to be retried,
    // else it returns FALSE
    public boolean retry(ITestResult result) {
        int dataProviderRetries = result.getMethod().getFailedInvocationNumbers().size();
        if (dataProviderRetries != 0) {
            retries = dataProviderRetries;
        } else {
            retries++;
        }
        if (retries <= MAX_RETRIES) {
            System.out.println("Retrying: " + result.getName() + ", retry count is " + (retries));
            return true;
        }
        return false;
    }
}
