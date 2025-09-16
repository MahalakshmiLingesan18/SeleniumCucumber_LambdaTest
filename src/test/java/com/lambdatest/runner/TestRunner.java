package com.lambdatest.runner;

import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lambdatest.utilities.BaseUtils;
import com.lambdatest.webdriverutility.CapabilityFactory;
import com.lambdatest.webdriverutility.Driver;
import com.lambdatest.webdriverutility.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/main/resources/FeatureFiles", glue = {"com.lambdatest.stepdefinition"}, plugin = {"pretty", "json:target/cucumber-reports/CucumberTestReport.json", "html:target/cucumber-reports/cucumber.html", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class TestRunner extends AbstractTestNGCucumberTests {
	
	private TestNGCucumberRunner testNGCucumberRunner; //allows Cucumber features to be executed as TestNG tests
	
	@BeforeClass(alwaysRun = true) //Runs once before all tests in this class
	public void setUpCucumber() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass()); //Initializes testNGCucumberRunner for this test class (this.getClass())
	}
	
	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true) //Runs before each test scenario
	@Parameters({"browser", "version", "platform"})
	public void setUpClass(@Optional("") String browser,
		                   @Optional("") String version,
		                   @Optional("") String platform) throws Exception {
		
		String username = System.getenv("LT_USERNAME");
        String accesskey = System.getenv("LT_ACCESS_KEY");
        
        if (username == null || accesskey == null) {
            throw new RuntimeException("LambdaTest credentials not set in environment variables");
        }
        
        //Fallback to config.properties if no TestNG params
        if (browser.isEmpty()) browser = BaseUtils.getProperty("browser");
        if (version.isEmpty()) version = BaseUtils.getProperty("version");
        if (platform.isEmpty()) platform = BaseUtils.getProperty("platform");
        
        String gridURL = "https://" + username + ":" + accesskey + "@hub.lambdatest.com/wd/hub";
        System.out.println("Connecting to LambdaTest Grid...");
        
        //Creates a RemoteWebDriver pointing to LambdaTest’s Selenium Grid Hub and establishes a session on the cloud environment
		RemoteWebDriver remoteWebdriver = new RemoteWebDriver(new URL(gridURL), CapabilityFactory.getCapabilities(browser, version, platform)); // helper class for capabilities
		
		//initializes the driver which is put into a ThreadLocal<RemoteWebDriver> wrapper (DriverManager pattern) 
		//so each scenario/thread gets its own isolated driver and also parallel tests won’t clash
		Driver.initDriver(remoteWebdriver); 
		
		//retrieves the WebDriver instance for the current thread and prints the session ID for debugging 
		System.out.println("LT Session ID: " + DriverManager.getDriver().getSessionId());
	}
	
	@DataProvider(parallel = true)
	public Object[][] features() {
		return testNGCucumberRunner.provideScenarios(); //Each Cucumber scenario will be treated as a TestNG test case
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		testNGCucumberRunner.finish(); //Cleans up the Cucumber runner and make sure no memory leaks happen
	}
	
}
