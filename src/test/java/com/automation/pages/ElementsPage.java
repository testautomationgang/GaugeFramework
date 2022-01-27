package com.automation.pages;

import com.automation.actions.MouseActions;
import com.automation.init.PageInit;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ElementsPage extends PageInit {

    @FindBy(xpath = "//div[@class='header-wrapper']//div[contains(text(),'Elements')]")
    private WebElement elementsDiv;

    @FindBy(how = How.XPATH, using = "//div/ul[@class='menu-list']/li/span[contains(text(),'Text Box')]")
    private WebElement textBoxElement;

    @FindBy(xpath = "//div/ul[@class='menu-list']/li/span[contains(text(),'Check Box')]")
    private WebElement checkBoxElement;

    @FindBy(xpath = "//span[contains(text(),'Buttons')]")
    private WebElement buttons;

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickBtn;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickBtn;



    public void clickElementsSection(){
        click(elementsDiv);
    }

    public void clickTextBox(){
        click(textBoxElement);
    }

    public void clickCheckBox(){
        click(checkBoxElement);
    }

    public void doubleClickOnButton(){
        MouseActions.doubleClick(doubleClickBtn);
    }

    public void rightClickOnButton(){
        MouseActions.rightClick(rightClickBtn);
    }

    public void clickOnButtonSection(){
        click(buttons);
    }
}
