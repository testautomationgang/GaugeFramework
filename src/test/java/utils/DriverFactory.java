package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    WebDriver driver;

    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>()  {
        @Override
        protected WebDriver initialValue() {
            return driver;
        }
    };

    private static DriverFactory driverFactory = new DriverFactory();

    public static DriverFactory getInstance(){
        return driverFactory;
    }

    public WebDriver getDriver(){
        return (WebDriver) webDriver.get();
    }

    void setDriver(WebDriver driver){
        webDriver.set(driver);
    }

    // Get a new WebDriver Instance.
    public WebDriver createDriver() {

        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME": browser;

        switch (browser.toUpperCase()) {
            case "IE":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                return driver;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            case "CHROME":
            default:
                WebDriverManager.chromedriver().setup();

	            ChromeOptions options = new ChromeOptions();
	            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
	                options.addArguments("--headless");
	                options.addArguments("--disable-gpu");
	            }

                options.addArguments("--start-maximized");
                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	            return driver;
        }
    }

    public void closeDriver(){
        webDriver.get().quit();
    }
}
