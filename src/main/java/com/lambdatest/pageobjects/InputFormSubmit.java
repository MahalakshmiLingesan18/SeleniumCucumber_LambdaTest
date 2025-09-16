package com.lambdatest.pageobjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.lambdatest.webdriverutility.DriverManager;

public class InputFormSubmit {
	
	public InputFormSubmit() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	@FindBy(xpath="//a[text()='Input Form Submit']")
	private WebElement inputFormSubmit;
	
	@FindBy(xpath="//button[text()='Submit']")
	private WebElement submit;
	
	@FindBy(how=How.ID, using="name")
	private WebElement nameTextBox;
	
	@FindBy(xpath="//input[@id='inputEmail4']")
	private WebElement emailTextBox;
	
	@FindBy(xpath="//input[@id='inputPassword4']")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//input[@id='company']")
	private WebElement companyTextBox;
	
	@FindBy(xpath="//input[@id='websitename']")
	private WebElement websiteTextBox;
	
	@FindBy(xpath="//select[@name='country']")
	private WebElement countryDropdown;
	
	@FindBy(xpath="//input[@id='inputCity']")
	private WebElement cityTextBox;
	
	@FindBy(xpath="//input[@id='inputAddress1']")
	private WebElement address1TextBox;
	
	@FindBy(xpath="//input[@id='inputAddress2']")
	private WebElement address2TextBox;
	
	@FindBy(xpath="//input[@id='inputState']")
	private WebElement stateTextBox;
	
	@FindBy(xpath="//input[@id='inputZip']")
	private WebElement zipCodeTextBox;
	
	@FindBy(xpath="//p[@class='success-msg hidden']")
	private WebElement successMessage;
	
	WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
	
	public void clickInputFormSubmit() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(inputFormSubmit)).click();
	}
	
	public void clickSubmit() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(submit)).click();
	}
	
	public String validateError() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		String validationMessage = (String) js.executeScript(
			    "return arguments[0].validationMessage;", explicitWait.until(ExpectedConditions.visibilityOf(nameTextBox)));
		return validationMessage;
	}
	
	public void enterName(String name) {
		explicitWait.until(ExpectedConditions.visibilityOf(nameTextBox)).sendKeys(name);
	}
	
	public void enterEmail(String email) {
		explicitWait.until(ExpectedConditions.visibilityOf(emailTextBox)).sendKeys(email);
	}
	
	public void enterPassword(String password) {
		explicitWait.until(ExpectedConditions.visibilityOf(passwordTextBox)).sendKeys(password);
	}
	
	public void enterCompany(String company) {
		explicitWait.until(ExpectedConditions.visibilityOf(companyTextBox)).sendKeys(company);
	}
	
	public void enterWebsite(String website) {
		explicitWait.until(ExpectedConditions.visibilityOf(websiteTextBox)).sendKeys(website);
	}
	
	public void selectCountry(String country) {
		Select select = new Select(countryDropdown);
		select.selectByVisibleText(country);
	}
	
	public void enterCity(String city) {
		explicitWait.until(ExpectedConditions.visibilityOf(cityTextBox)).sendKeys(city);
	}
	
	public void enterAddress1(String address1) {
		explicitWait.until(ExpectedConditions.visibilityOf(address1TextBox)).sendKeys(address1);
	}
	
	public void enterAddress2(String address2) {
		explicitWait.until(ExpectedConditions.visibilityOf(address2TextBox)).sendKeys(address2);
	}
	
	public void enterState(String state) {
		explicitWait.until(ExpectedConditions.visibilityOf(stateTextBox)).sendKeys(state);
	}
	
	public void enterZipCode(String zipcode) {
		explicitWait.until(ExpectedConditions.visibilityOf(zipCodeTextBox)).sendKeys(zipcode);
	}
	
	public String messageAfterSubmitting() {
		return explicitWait.until(ExpectedConditions.visibilityOf(successMessage)).getText();
	}

}
