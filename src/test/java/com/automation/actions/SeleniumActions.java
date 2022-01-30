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

import java.util.Iterator;
import java.util.Set;

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

    /**
     * selectDropdownByVisibleText : Select value from dropdown using visible text
     * @param element
     * @param text
     */
    protected void selectDropdownByVisibleText(WebElement element,String text){
        try {
            Select select = new Select(element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            select.selectByVisibleText(text);
            logger.info("Selecting value from dropdown by text: " + text);
        }catch (TimeoutException te){
            logger.error("Timeout occurred while waiting for dropdown to be clickable");
        }catch (Exception e){
            logger.error("Exception occurred: ",e);
            e.printStackTrace();
        }
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

    /**
     * Method: switchToChildWindow()
     * This method is used to switch to the child window
     * First it fetches the parent window id, then get all windows id
     * then it compares with current window. If both window id is different, then switch to window
     */
    protected void switchToChildWindow(){
        // It will return the parent window name as a String
        String parent=driver.getWindowHandle();
        Set<String> s=driver.getWindowHandles();
        // Now iterate using Iterator
        Iterator<String> it= s.iterator();
        while(it.hasNext()) {
            String childWindow=it.next();
            if(!parent.equals(childWindow)) {
                wait.until(ExpectedConditions.numberOfWindowsToBe(2));
                driver.switchTo().window(childWindow);
                System.out.println("Child window title: "+ driver.switchTo().window(childWindow).getTitle());
                logger.info("Switched to child window having id: " + childWindow);
            }
            //switch to the parent window
            driver.switchTo().window(parent);
        }
    }

    protected String getParentWindowId(){
        String parent=driver.getWindowHandle();
        logger.info("Parent window id is: " +parent);
        return parent;
    }

    protected void switchToParentWindow(String windowId){
        driver.switchTo().window(windowId);
        logger.info("Switch to parent window having windowId: "+ windowId);
    }

    protected void closeChildWindow(){
        DriverFactory.getInstance().closeDriver();
    }
}
