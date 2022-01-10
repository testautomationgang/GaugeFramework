package stepDefs;

import com.thoughtworks.gauge.Step;
import pages.LoginPage;

public class LoginSteps {

    LoginPage loginPage = new LoginPage();

    @Step("Login using userid <username> and password <passwd>")
    public void doLogin(String username, String passwd){
        //Call login method of Login Page
        loginPage.login(username,passwd);
    }
}
