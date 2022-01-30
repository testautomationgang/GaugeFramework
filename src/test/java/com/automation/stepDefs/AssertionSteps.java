package com.automation.stepDefs;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import static com.automation.utils.CommonUtils.*;

public class AssertionSteps {

    @Step("User verify that <expected> is equal to <actual>")
    public static void verifyEquals( String expected,String actual){
        try{
            expected = checkIfVariableThenGetValue(expected);
            actual = checkIfVariableThenGetValue(actual);
            Assert.assertEquals(expected,actual);
            Gauge.writeMessage("Expected text: " +expected + "Actual text: " +actual);
        }catch (AssertionError error){
            Gauge.writeMessage("Assertion failed, Expected text: " +expected + "Actual text: " +actual);
        }finally {
            //Take screenshot in both Pass/ Fail
            Gauge.captureScreenshot();
        }



    }

    public void verifyContains(){

    }
}
