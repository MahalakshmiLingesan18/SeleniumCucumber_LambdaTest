package com.lambdatest.stepdefinition;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.lambdatest.pageobjects.DragDropSlider;
import com.lambdatest.utilities.BaseUtils;
import com.lambdatest.webdriverutility.DriverManager;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DragDropSliderStepDefinition {
	
	private DragDropSlider dragDropSlider = new DragDropSlider();
	
	WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
	
	@Given("Users navigates to the LambdaTest Selenium Playground - Drag & Drop Slider")
	public void users_navigates_to_the_lambda_test_selenium_playground_drag_drop_slider() {
		String appUrl = BaseUtils.getProperty("url");
		DriverManager.getDriver().get(appUrl);
	}
	
	@When("User clicks on Drag & Drop Slider hyperlink")
	public void user_clicks_on_drag_drop_slider_hyperlink() {
		dragDropSlider.clickDragDropSlider();
	}

	@And("User drags the Default value 15 slider to {int}")
	public void user_drags_the_default_value_slider_to(Integer targetValue) {
		dragDropSlider.setSliderTo(targetValue);
		
		// Wait until the slider shows the correct value
		explicitWait.until((ExpectedCondition<Boolean>) driver -> 
	        (dragDropSlider.getRangeFromSlider()) == targetValue
	    );
	}

	@Then("The range value of Default value 15 slider should show {int}")
	public void the_range_value_of_default_value_slider_should_show(Integer expectedRange) {
		// Wait until the output shows the correct value
		explicitWait.until(ExpectedConditions.textToBePresentInElementValue(dragDropSlider.getOutputRangeElement(), String.valueOf(expectedRange)));

	    // Final assertion
	    Integer actualDisplayed = dragDropSlider.getOutputRange();
	    Assert.assertEquals(actualDisplayed, expectedRange, "Slider output mismatch!");
	    System.out.println("Slider output is now: " + actualDisplayed);
	}

}
