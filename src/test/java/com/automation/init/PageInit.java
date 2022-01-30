package com.automation.init;

import com.automation.actions.SeleniumActions;
import com.thoughtworks.gauge.Gauge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utils.DriverFactory;

public class PageInit extends SeleniumActions {

    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    private static Logger logger = LogManager.getLogger(PageInit.class);
    private static WebDriverWait wait = new WebDriverWait(driver
            , 15);

    public PageInit(){
        logger.info("Parent class constructor called..");
        PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
    }

    public void raiseExceptionAndStopExecution(Exception e){
        DriverFactory.getInstance().captureNetworkLogs();
        Gauge.captureScreenshot();
        Assert.fail(e.getMessage());
    }

}
