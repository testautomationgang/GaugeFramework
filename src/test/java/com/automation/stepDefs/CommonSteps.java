package com.automation.stepDefs;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.automation.utils.ExecutionHooks;
import com.thoughtworks.gauge.datastore.DataStore;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps {

    Logger logger = LogManager.getLogger(CommonSteps.class);

    @Step("Open the browser and launch application URL")
    public void gotoGetStartedPage() throws InterruptedException {
        String app_url = System.getenv("APP_URL");
        ExecutionHooks.webDriver.get(app_url + "/");
    }

    @Step("User navigate to URL <url>")
    public void navigateToURL(String url) throws InterruptedException {
        //String app_url = System.getenv("APP_URL");
        ExecutionHooks.webDriver.navigate().to(url);
    }

    @Step("User wait for <timeInSeconds> seconds")
    public void implementWait(long timeInSeconds) throws InterruptedException {
        try{
            Thread.sleep(timeInSeconds * 1000);
        }catch (InterruptedException ie){
            System.err.println("Exception occurred in wait ...");
        }
    }

    /**
     * To capture the screenshot.
     * This feature is already there in Gauge
     */
    @Step("User captures the screenshot")
    public void captureScreen() {
      Gauge.captureScreenshot();
    }

    @Step("User fetch the value of <key> from user property file")
    public void fetchValueOfProperty(String key)  {
        String value = System.getenv(key);
        logger.debug("Value fetched from property for key: {} is {} " ,key ,value);
        Gauge.writeMessage("Value fetched from property for key: "+key +" is: "+ value);

    }

    @Step("Add value <value> with respect to key <key> into data store")
    public void addData(String value, String key){
        ScenarioDataStore.put(key,value);
        Gauge.writeMessage("Added value: "+value +" for key:" + key + " in data store");
    }

    @Step("Get value of key <key> from data store")
    public void getData(String key){
        ScenarioDataStore.get(key);
        Gauge.writeMessage("Value fetched from data store ");
    }


}
