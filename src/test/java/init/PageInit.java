package com.automation.init;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.automation.utils.DriverFactory;

public class PageInit {

    private static WebDriver driver = DriverFactory.getInstance().getDriver();
    private static Logger logger = LogManager.getLogger(PageInit.class);
    private static WebDriverWait wait = new WebDriverWait(driver
            , 15);


    public static void click(WebDriver driver, WebElement element) {
        try {
            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (StaleElementReferenceException sere) {
                // simply retry finding the element in the refreshed DOM
                element.click();
        }catch (TimeoutException toe) {
            logger.error("Element not clickable" + element.getAttribute("name"));
        }
        }

    /**
     *
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






}
