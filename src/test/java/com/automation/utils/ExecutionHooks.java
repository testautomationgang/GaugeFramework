package com.automation.utils;

import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;

public class ExecutionHooks {

    // Holds the WebDriver instance
    public static WebDriver webDriver;

    // Initialize a webDriver instance of required browser

    @BeforeSuite
    public void initializeDriver(){

    }

    // BeforeSpec hook is used to instantiate the webDriver
    @BeforeSpec
    public void beforeSpec(){
        webDriver = DriverFactory.getInstance().getDriver();
        Gauge.writeMessage("Initiating driver ...");
    }

    @AfterSpec
    public void afterSpec(){
        DriverFactory.getInstance().quitDriver();
    }

    @ContinueOnFailure
    public void onFailure(){
        DriverFactory.getInstance().captureNetworkLogs();
    }

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }

}
