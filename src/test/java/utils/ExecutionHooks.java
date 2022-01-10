package utils;

import com.thoughtworks.gauge.*;
import org.openqa.selenium.WebDriver;

public class ExecutionHooks {

    // Holds the WebDriver instance
    public static WebDriver webDriver;

    // Initialize a webDriver instance of required browser

    @BeforeSuite
    public void initializeDriver(){
        webDriver = DriverFactory.getInstance().getDriver();
    }

    // BeforeSpec hook is used to instantiate the webDriver
    @BeforeSpec
    public void beforeSpec(){
        Gauge.writeMessage("Initiating driver ...");
    }

    @AfterSpec
    public void afterSpec(){
        webDriver.quit();
    }

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }

}
