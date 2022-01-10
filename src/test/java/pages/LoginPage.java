package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverFactory;

public class LoginPage {

    private final WebDriver webDriver;

    @FindBy(name = "login")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    public LoginPage(){
        this.webDriver = DriverFactory.getInstance().getDriver();
        PageFactory.initElements(webDriver, this);
    }

    public void login(String userName, String pwd){
        username.sendKeys(userName,pwd);
        password.sendKeys(pwd);
    }


}