package com.automation.stepDefs;

import com.thoughtworks.gauge.Step;
import com.automation.pages.LoginPage;


public class LoginSteps {

    LoginPage loginPage;// = new LoginPage();

    @Step("User opens Login page")
    public void openPage(){
        loginPage = new LoginPage();
    }

    @Step("Login using userid <username> and password <passwd>")
    public void doLogin(String username, String passwd){
        //Call login method of Login Page
        loginPage.login(username,passwd);
    }

    @Step("Login using user from property file")
    public void doLoginUsingProperty(){
        //Call login method of Login Page
        String uname = System.getenv("USERNAME");
        String pwd = System.getenv("PASSWORD");
        loginPage.login(uname,pwd);
    }
}
