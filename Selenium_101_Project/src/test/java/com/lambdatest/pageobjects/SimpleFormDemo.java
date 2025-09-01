package com.lambdatest.pageobjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.webdriverutility.DriverManager;

public class SimpleFormDemo {
	
	public SimpleFormDemo() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	@FindBy(xpath="//a[text()='Simple Form Demo']")
	private WebElement simpleFormDemo;
	
	@FindBy(xpath="//input[@placeholder='Please enter your Message']")
	private WebElement messageTextBox;
	
	@FindBy(how=How.ID, using="showInput")
	private WebElement getCheckedValue;
	
	@FindBy(xpath="//div[@id='user-message']/p[@id='message']")
	private WebElement yourMessage;
	
	WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
	
	public void clickSimpleFormDemo() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(simpleFormDemo)).click();
	}
	
	public void enterMessage(String message) {
		System.out.println("Entering message: " + message);
		explicitWait.until(ExpectedConditions.visibilityOf(messageTextBox)).sendKeys(message);
	}
	
	public void clickGetCheckedValue() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(getCheckedValue)).click();
	}
	
	public String displayedMessage() {
		return explicitWait.until(ExpectedConditions.visibilityOf(yourMessage)).getText();
	}

}
