package com.automation.pages;

import com.automation.init.PageInit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.automation.utils.DriverFactory;

public class LoginPage extends PageInit {


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




    public void login(String userName, String pwd){
        enterText(username,userName);
        enterText(password,pwd);
        click(loginBtn);
    }

    public void logout(){
        click(logoutBtn);
    }




}
