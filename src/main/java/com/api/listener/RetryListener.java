package com.api.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class RetryListener implements IAnnotationTransformer {

    public RetryListener() {
    }

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        Class<? extends IRetryAnalyzer> retry = annotation.getRetryAnalyzerClass();
        retry = annotation.getRetryAnalyzerClass();

        if ("org.testng.internal.annotations.DisabledRetryAnalyzer".equals(retry.getName())) {
            annotation.setRetryAnalyzer(RetryFailedTestCases.class);
        }
    }
}