package com.automation.stepDefs;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import static com.automation.utils.CommonUtils.*;

public class AssertionSteps {

    @Step("User verify that <expected> is equal to <actual>")
    public static void verifyEquals(String expected,String actual) {
        try {
            expected = checkIfVariableThenGetValue(expected);
            actual = checkIfVariableThenGetValue(actual);
            Assert.assertEquals(expected, actual);
            Gauge.writeMessage("Assertion Passed. Expected text: " + expected + " ,Actual text: " + actual);
        } catch (AssertionError error) {
            Gauge.writeMessage("Assertion failed, Expected text: " + expected + "Actual text: " + actual);
        } finally {
            //Take screenshot in both Pass/ Fail
            Gauge.captureScreenshot();
        }
    }



    @Step("User verify that <actual> contains text <expected>")
    public void textContains(String actual,String expected){
        expected = checkIfVariableThenGetValue(expected);
        actual = checkIfVariableThenGetValue(actual);
        if(actual != null && actual.contains(expected)){
            Gauge.writeMessage("Values matched. Expected text: " + expected + "Actual text: " + actual);
        }else {
            Gauge.writeMessage("Assertion failed. Expected text: " + expected + "Actual text: " + actual);
            Assert.fail();
        }
        Gauge.captureScreenshot();
    }
}
