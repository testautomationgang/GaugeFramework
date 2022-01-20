package com.automation.init;

import com.thoughtworks.gauge.Gauge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utils.DriverFactory;

public class PageInit {

    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    private static Logger logger = LogManager.getLogger(PageInit.class);
    private static WebDriverWait wait = new WebDriverWait(driver
            , 15);

    public PageInit(){
        logger.info("Parent class constructor called..");
        PageFactory.initElements(DriverFactory.getInstance().getDriver(), this);
    }


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
        } catch (StaleElementReferenceException sere) {
                // simply retry finding the element in the refreshed DOM
                element.click();
        }catch (ElementClickInterceptedException toe) {
            //Used Javascript Executor as sometimes the click is received by some Ads
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
            logger.info("Trying using Javascript Executor");
        }catch (Exception e){
            logger.error("Error occurred while clicking element");
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






}
