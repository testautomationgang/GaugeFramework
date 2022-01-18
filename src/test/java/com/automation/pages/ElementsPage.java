package com.automation.pages;

import com.automation.init.PageInit;
import com.automation.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ElementsPage extends PageInit {
    //private final WebDriver webDriver;

    @FindBy(xpath = "//div[@class='header-wrapper']//div[contains(text(),'Elements')]")
    private WebElement elementsDiv;

    @FindBy(how = How.XPATH, using = "//div/ul[@class='menu-list']/li/span[contains(text(),'Text Box')]")
    private WebElement textBoxElement;

    @FindBy(xpath = "//div/ul[@class='menu-list']/li/span[contains(text(),'Check Box')]")
    private WebElement checkBoxElement;



    public void clickElementsSection(){
        click(elementsDiv);
    }

    public void clickTextBox(){
        click(textBoxElement);
    }

    public void clickCheckBox(){
        click(checkBoxElement);
    }
}
