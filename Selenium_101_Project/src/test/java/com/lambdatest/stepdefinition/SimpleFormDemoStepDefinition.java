package com.lambdatest.stepdefinition;

import org.testng.Assert;

import com.lambdatest.pageobjects.SimpleFormDemo;
import com.lambdatest.utilities.BaseUtils;
import com.lambdatest.webdriverutility.DriverManager;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SimpleFormDemoStepDefinition {
	
	private SimpleFormDemo simpleFormDemo = new SimpleFormDemo();
	
	@Given("User navigates to the LambdaTest Selenium Playground - Simple Form Demo")
	public void user_navigates_to_the_lambda_test_selenium_playground_simple_form_demo() {
		String appUrl = BaseUtils.getProperty("url");
		DriverManager.getDriver().get(appUrl);
	}

	@When("User clicks on Simple Form Demo hyperlink")
	public void user_clicks_on_simple_form_demo_hyperlink() {
		simpleFormDemo.clickSimpleFormDemo();
		Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("simple-form-demo"), "User is not on Simple Form Demo page!");
	}

	@And("User enters {string} in the text box")
	public void user_enters_in_the_text_box(String message) {
		simpleFormDemo.enterMessage(message);
	}

	@And("User clicks on Get Checked Value button")
	public void user_clicks_on_get_checked_value_button() {
		simpleFormDemo.clickGetCheckedValue();
	}

	@Then("The same text message {string} should be displayed in Your Message: section")
	public void the_same_text_message_should_be_displayed_in_your_message_section(String expectedMessage) {
		Assert.assertEquals(simpleFormDemo.displayedMessage(), expectedMessage, "Displayed message mismatch!");
	}

}
