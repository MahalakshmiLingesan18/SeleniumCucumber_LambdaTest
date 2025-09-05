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
		
		if (driver == null) {
            throw new IllegalArgumentException("Provided RemoteWebDriver is null");
        }
		
		try {
	        DriverManager.setDriver(driver); //Saves the given driver into DriverManager
	        RemoteWebDriver remoteDriver = DriverManager.getDriver();
	        
	        if (remoteDriver == null) {
                throw new IllegalStateException("DriverManager.getDriver() returned null after setDriver()");
            }
	        
	        try {
                remoteDriver.manage().window().maximize();
            } catch (Exception e) {
                // maximize might fail on some cloud VMs; continue anyway
                System.out.println("WARN: could not maximize window: " + e.getMessage());
            }
            // You intentionally used 0 to encourage explicit waits. Keep or change as needed.
            remoteDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	      } catch (Exception e) {
	        throw new Exception("Driver initialization failed. Check capabilities or Grid URL. Root cause: "+ e.getMessage(), e);
	      }
		
	}
	
	//Cleanly close and remove the driver when a test finishes
	 public static void quitDriver() {
		 try {
	            RemoteWebDriver drv = DriverManager.getDriver();
	            if (Objects.nonNull(drv)) {
	            	drv.quit(); //shuts down the browser session
	            	DriverManager.unload(); //removes the driver reference from the ThreadLocal so memory is freed, and no stale sessions remain
	  		    }     
		 } catch (Exception e) {
	            System.err.println("ERROR in quitDriver(): " + e.getMessage());
	     }
     }
}
