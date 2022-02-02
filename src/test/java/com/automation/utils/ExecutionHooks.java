package com.automation.utils;

import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;

public class ExecutionHooks {

    // Holds the WebDriver instance
    public static WebDriver webDriver;

    // Initialize a webDriver instance of required browser

    @BeforeSuite
    public void initializeDriver(){
        //Database pool connection
    }

    // BeforeSpec hook is used to instantiate the webDriver
    @BeforeSpec
    public void beforeSpec(){
        webDriver = DriverFactory.getInstance().getDriver();
        Gauge.writeMessage("Initiating driver ...");
    }

    @AfterSpec
    public void afterSpec(ExecutionContext context){
        DriverFactory.getInstance().quitDriver();
        System.out.println("=========== Spec file execution complete ===========");

    }

    @AfterScenario
    public void afterScenario(ExecutionContext context){
        if(context.getCurrentScenario().getIsFailing()){
            //Take screenshot
            Gauge.captureScreenshot();
            //Capture Network Logs
            DriverFactory.getInstance().captureNetworkLogs();
            //Attach network log file
            Gauge.writeMessage("Network Logs: "+"<a href=\"../test.txt\" download>Download</a>");
        }
    }

    @ContinueOnFailure
    public void onFailure(){

    }

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }

}
