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

    @Step("Login using userid <userid> and password <pswd> from property file")
    public void doLoginUsingProperty(String userid, String pswd){
        //Call login method of Login Page
        String uname = System.getenv(userid);
        String pwd = System.getenv(pswd);
        loginPage.login(uname,pwd);
    }

    @Step("User Logout")
    public void doLogout(){
        loginPage.logout();
    }

    @Step("User expands elements section & tests double click button")
    public void doubleclick(){
        loginPage.doubleclickbtn();
    }
}
