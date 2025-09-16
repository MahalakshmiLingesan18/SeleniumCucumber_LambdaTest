package com.lambdatest.stepdefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.lambdatest.utilities.BaseUtils;
import com.lambdatest.webdriverutility.Driver;
import com.lambdatest.webdriverutility.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	@Before
    public void updateName(Scenario scenario) {
        RemoteWebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                driver.executeScript("lambda-name=" + scenario.getName());
            } catch (Exception e) {
                System.err.println("Failed to set LambdaTest name: " + e.getMessage());
            }
        }
    }
	
	@After
    public void closeBrowser(Scenario scenario) {
        RemoteWebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            try {
                driver.executeScript("lambda-status=" + (scenario.isFailed() ? "failed" : "passed")); //Updates the status of the test scenario in LambdaTest cloud
                String sessionId = driver.getSessionId().toString();
                System.out.println("LambdaTest Session ID: " + sessionId);
                System.out.println("LambdaTest Dashboard: https://automation.lambdatest.com/logs/?sessionId=" + sessionId);
                
                //Takes screenshot of the failed test scenario
                if (scenario.isFailed()) {
                    String screenshotPath = BaseUtils.captureScreenshot(DriverManager.getDriver(), scenario.getName());
                    scenario.log("Screenshot saved at: " + screenshotPath);
                    scenario.attach(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES),
                            "image/png", "Failure Screenshot");
                }
            } catch (Exception e) {
                System.err.println("Failed to update LambdaTest status: " + e.getMessage());
            } finally {
                Driver.quitDriver();
            }
        }
    }
	
	@AfterStep
	public void uponFail(Scenario scenario) {
		if (scenario.isFailed()) {
			byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "error");
		}
	}

}
