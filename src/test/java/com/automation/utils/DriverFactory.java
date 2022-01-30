package com.automation.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;


public class DriverFactory {

    WebDriver driver;
    Logger logger = LogManager.getLogger(DriverFactory.class);

    //Thread local is used here for thread safe, i.e variables that can only be read and written by the same thread
    private ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>()  {
        @Override
        protected WebDriver initialValue() {
            return createDriver();
        }
    };

    //creating object of DriverFactory
    private static DriverFactory driverFactory = new DriverFactory();

    //To get the instance of DriverFactory from other class
    public static DriverFactory getInstance(){
        return driverFactory;
    }

    //To get the driver instance
    public WebDriver getDriver(){
        return (WebDriver) webDriver.get();
    }

    void setDriver(WebDriver driver){
        webDriver.set(driver);
    }

    /**
     * Method: createDriver
     *
     * @return driver instance
     */
    public WebDriver createDriver() {

        String browser = System.getenv("BROWSER");
        browser = (browser == null) ? "CHROME": browser;
        logger.debug("Browser selected is: " + browser);

        switch (browser.toUpperCase()) {
            case "IE":
                //
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                return driver;
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                return driver;
            case "CHROME":
            default:
                /*
                 * checks for the latest version of the specified WebDriver binary.
                 * If the binaries are not present on the machine
                 * then it will download the WebDriver binaries.
                 */
                ChromeOptions options = new ChromeOptions();
                LoggingPreferences logPrefs = new LoggingPreferences();
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                options.setCapability("goog:loggingPrefs", logPrefs );
                WebDriverManager.chromedriver().setup();
                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE_PATH);


	            if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
	                options.addArguments("--headless");
	                options.addArguments("--disable-gpu");
	            }

                //options.addArguments("--start-maximized");
                //To accept the SSL certification error
                options.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(options);
                //putting implicit wait of 5 seconds
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().maximize();
                //driver.get("https://www.youtube.com/");
                //captureNetworkLogs();
	            return driver;
        }
    }

    /**
     * To close the driver
     */
    public void closeDriver(){
        webDriver.get().close();
        logger.debug("Driver closed successfully...");
    }

    public void quitDriver(){
        webDriver.get().quit();
        logger.debug("Driver quit successfully...");
    }

    /**
     * captureNetworkLogs
     * The below method will capture network logs based on XHR type
     */
    public void captureNetworkLogs(){
        List<LogEntry> entries = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
        System.out.println(entries.size() + " " + LogType.PERFORMANCE + " log entries found");

        for (LogEntry entry : entries) {
            JSONObject json = new JSONObject(entry.getMessage());
            JSONObject message = json.getJSONObject("message");
            //System.out.println(message.toString());
            String method = message.getString("method");
            if (method != null && "Network.responseReceived".equals(method)) {
                JSONObject params = message.getJSONObject("params");
                JSONObject response = params.getJSONObject("response");
                String messageUrl = response.getString("url");
                String type = params.getString("type");
                int status = response.getInt("status");
                if(type.equalsIgnoreCase("xhr")){
                    System.out.println("Response returned for :" + messageUrl + " is : " + status);
                    System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + message.toString());
                }
            }

        }
    }

    /*public static void main(String[] args) {
        getInstance().getDriver();
        getInstance().driver.get("https://www.google.co.in/");
        getInstance().captureNetworkLogs();
        getInstance().quitDriver();
    }*/
}
