package stepDefs;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import utils.ExecutionHooks;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;

public class CommonSteps {
    @Step("Open the browser and launch application URL")
    public void gotoGetStartedPage() throws InterruptedException {
        String app_url = System.getenv("APP_URL");
        ExecutionHooks.webDriver.get(app_url + "/");
    }

    @Step("User wait for <timeInSeconds> seconds")
    public void implementWait(long timeInSeconds) throws InterruptedException {
        try{
            Thread.sleep(timeInSeconds * 1000);
        }catch (InterruptedException ie){
            System.err.println("Exception occurred in wait ...");
        }
    }

    @Step("User captures the screenshot")
    public void captureScreen() {
      Gauge.captureScreenshot();
    }
}
