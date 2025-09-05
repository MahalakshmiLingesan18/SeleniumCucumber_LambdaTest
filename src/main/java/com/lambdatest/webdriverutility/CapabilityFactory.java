package com.lambdatest.webdriverutility;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class CapabilityFactory {
	
	// prevent instantiation
	private CapabilityFactory() {
	}
	
	/**
     * Builds capabilities for LambdaTest execution based on browser/version/platform
     */
    public static MutableCapabilities getCapabilities(String browser, String version, String platform) {
    	
    	if (browser == null) {
            throw new IllegalArgumentException("browser is null");
        }
    	
        MutableCapabilities capabilities;

        switch (browser.toLowerCase()) {
        
        case "chrome":
            ChromeOptions chromeOptions = new ChromeOptions();
            if (version != null && !version.isBlank()) chromeOptions.setBrowserVersion(version);
            if (platform != null && !platform.isBlank()) chromeOptions.setPlatformName(platform);
            chromeOptions.setCapability("LT:Options", getLtOptions());
            capabilities = chromeOptions;
            break;

        case "firefox":
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            if (version != null && !version.isBlank()) firefoxOptions.setBrowserVersion(version);
            if (platform != null && !platform.isBlank()) firefoxOptions.setPlatformName(platform);
            firefoxOptions.setCapability("LT:Options", getLtOptions());
            capabilities = firefoxOptions;
            break;

        case "edge":
        case "microsoftedge":
            EdgeOptions edgeOptions = new EdgeOptions();
            if (version != null && !version.isBlank()) edgeOptions.setBrowserVersion(version);
            if (platform != null && !platform.isBlank()) edgeOptions.setPlatformName(platform);
            edgeOptions.setCapability("LT:Options", getLtOptions());
            capabilities = edgeOptions;
            break;
            
        default:
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        return capabilities;
    }

    /**
     * Defines common LambdaTest options that apply across browsers
     */
    private static Map<String, Object> getLtOptions() {
        Map<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("build", "Selenium_Certification");
        ltOptions.put("project", "Selenium_Project_Using_Cucumber");
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        ltOptions.put("video", true);
        ltOptions.put("visual", true);
        ltOptions.put("w3c", true);
        ltOptions.put("selenium_version", "4.10.0");
        ltOptions.put("accessibility", true);
        return ltOptions;
    }

}
