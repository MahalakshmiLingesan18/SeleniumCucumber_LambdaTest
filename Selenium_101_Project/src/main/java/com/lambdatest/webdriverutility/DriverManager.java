package com.lambdatest.webdriverutility;

import java.util.Objects;

import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverManager {
	
	// Private constructor to prevent instantiation
	private DriverManager() {
	}
	
	// ThreadLocal variable to store driver instance per thread
	private static final ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	// Setter - assigns driver for current thread
	public static void setDriver(RemoteWebDriver driverInstance) {
	    if (Objects.nonNull(driverInstance)) { //prevents assigning null
	      driver.set(driverInstance);
	    }
	}
	
	// Getter - retrieves driver for current thread
	public static RemoteWebDriver getDriver() {
	    return driver.get();
	}
	
	// Unload - removes driver reference from ThreadLocal
	public static void unload() {
	    driver.remove();
	}

}
