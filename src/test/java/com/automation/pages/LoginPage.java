package com.automation.pages;

import com.automation.init.PageInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.DriverFactory;

public class LoginPage extends PageInit {

    private final WebDriver webDriver;

    @FindBy(id = "userName")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login")
    private WebElement loginBtn;

    @FindBy(xpath = "//*[contains(text(),'Log out')]")
    private WebElement logoutBtn;

    @FindBy(xpath = "//div[contains(text(),'Elements')]")
    private WebElement elements;

    @FindBy(xpath = "//span[contains(text(),'Buttons')]")
    private WebElement buttons;

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleclickbtn;

    public LoginPage(){
        this.webDriver = DriverFactory.getInstance().getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void login(String userName, String pwd){
        //username.sendKeys(userName,pwd);
        //password.sendKeys(pwd);
        enterText(username,userName);
        enterText(password,pwd);
        click(loginBtn);



    }

    public void logout(){click(logoutBtn);

    }

    public void doubleclickbtn(){
        click(elements);
        click(buttons);
        doubleclick(doubleclickbtn);
    }


}
