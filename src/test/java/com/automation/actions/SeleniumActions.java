package com.automation.actions;

import com.automation.init.PageInit;
import com.automation.utils.DatastoreFactory;
import com.automation.utils.DriverFactory;
import com.thoughtworks.gauge.Gauge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumActions {

    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    private static Logger logger = LogManager.getLogger(SeleniumActions.class);
    private static WebDriverWait wait = new WebDriverWait(driver
            , 15);

    /**
     * @method: click -> This method is used to click on the element provided
     * This method handles exceptions like: StaleElementReferenceException,
     * ElementClickInterceptedException
     * @param element
     */
    public static void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException toe) {
            //Used Javascript Executor as sometimes the click is received by some Ads
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
            logger.info("Trying using Javascript Executor");
        }catch (TimeoutException e){
            element.click();//simply retry
            logger.error("Error occurred while clicking element");
        }catch (Exception e){
            logger.error("Error occurred while clicking element");
            e.printStackTrace();
        }
    }

    /**
     * This method
     * @param element
     * @param text
     */
    public static void enterText(WebElement element,String text){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
            logger.debug("Entered text: " + text);

        }catch (StaleElementReferenceException se){
            element.sendKeys(text);
        }catch (Exception e){
            logger.error("Error occurred: ",e);
        }
    }

    public static void uploadFile(WebElement element, String filePath){

        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(filePath);
            logger.debug("Entered text: " + filePath);

        }catch (StaleElementReferenceException se){
            element.sendKeys(filePath);
        }catch (Exception e){
            logger.error("Error occurred: ",e);
        }
    }

    /**
     *
     * @param accept
     * @return
     */
    protected String acceptOrRejectAlertAndGetText(boolean accept){
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        logger.info("Alert text is: " + text);
        Gauge.writeMessage("Alert text is: " + text);
        if(accept){
            alert.accept();
            logger.info("Alert accepted...");
        }else {
            alert.dismiss();
            logger.info("Alert rejected...");
        }

        return text;
    }

    protected String getTextFromElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        return text;
    }

    protected void getTextFromElementAndStoreAgainstVariable(WebElement element, String variable){
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.info("Text fetched from Element is: " + text);
        DatastoreFactory.addValueToDataStoreAgainstKey(variable, text);

    }

    protected void selectDropdownByValue(WebElement element, String value){
        try {
            Select select = new Select(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            select.selectByValue(value);
            logger.info("Selecting from dropdown having value: " + value);
        }catch (Exception e){
            logger.error("Value not present in dropdown");
            e.printStackTrace();
        }
    }

    protected void selectDropdownByVisibleText(WebElement element,String text){
        Select select = new Select(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        select.selectByVisibleText(text);
        logger.info("Selecting value from dropdown by text: " + text);
    }

    /**
     * The below method used to select value from dropdown using index number
     * @param element
     * @param index
     */
    protected void selectDropdownByIndex(WebElement element, int index){
        try {
            Select select = new Select(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            select.selectByIndex(index);
            logger.info("Selecting value from dropdown at index: " + index);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("Error occurred while selecting value from dropdown" , e);

        }
    }
}
