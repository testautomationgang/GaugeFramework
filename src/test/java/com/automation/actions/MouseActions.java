package com.automation.actions;

import com.automation.utils.DriverFactory;
import com.automation.utils.ExecutionHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MouseActions {

    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    private static WebDriverWait wait = new WebDriverWait(driver,10);
    private static Logger logger = LogManager.getLogger(MouseActions.class);

    public static void doubleClick(WebElement element) {
        //Instantiating Actions class
        Actions act = new Actions(driver);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            act.doubleClick(element).build().perform();
        }catch (Exception e){
            logger.error("Error occurred while clicking element");
            e.printStackTrace();
        }
    }

    public static void rightClick(WebElement element) {
        //Instantiating Actions class
        Actions act = new Actions(driver);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            act.contextClick(element).perform();
        } catch (StaleElementReferenceException sere) {
            // simply retry finding the element in the refreshed DOM
            act.contextClick(element).perform();
        }catch (Exception e){
            logger.error("Error occurred while right clicking element", e);
            e.printStackTrace();
            //throw new Exception("e");
        }
    }


}
