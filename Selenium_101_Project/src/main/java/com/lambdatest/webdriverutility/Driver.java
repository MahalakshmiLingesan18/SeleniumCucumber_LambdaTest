package com.lambdatest.webdriverutility;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.remote.RemoteWebDriver;

public final class Driver {
	
	// Private constructor to prevent instantiation
	private Driver() {
	}
	
	//Initializes the WebDriver instance if it doesn’t already exist
	public static void initDriver(RemoteWebDriver driver) throws Exception {
		
	    if (Objects.isNull(DriverManager.getDriver())) {
	      try {
	        DriverManager.setDriver(driver); //Saves the given driver into DriverManager
	        RemoteWebDriver remoteDriver = DriverManager.getDriver();
            remoteDriver.manage().window().maximize();
            remoteDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // use explicit waits in POM
	      } catch (Exception e) {
	        throw new Exception("Driver initialization failed. Check capabilities or Grid URL. Root cause: "+ e.getMessage(), e);
	      }
	    }
	}
	
	//Cleanly close and remove the driver when a test finishes
	 public static void quitDriver() {
		    if (Objects.nonNull(DriverManager.getDriver())) {
		      DriverManager.getDriver().quit(); //shuts down the browser session
		      DriverManager.unload(); //removes the driver reference from the ThreadLocal so memory is freed, and no stale sessions remain
		    }
     }

}
