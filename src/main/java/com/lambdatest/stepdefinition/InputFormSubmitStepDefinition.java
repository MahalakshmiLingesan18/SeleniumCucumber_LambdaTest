package com.lambdatest.stepdefinition;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import com.lambdatest.pageobjects.InputFormSubmit;
import com.lambdatest.utilities.BaseUtils;
import com.lambdatest.webdriverutility.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InputFormSubmitStepDefinition {
	
	private InputFormSubmit inputFormSubmit = new InputFormSubmit();
	
	@Given("User navigates to the LambdaTest Selenium Playground - Input Form Submit")
	public void users_navigates_to_the_lambda_test_selenium_playground_input_form_submit() {
		String appUrl = BaseUtils.getProperty("url");
		DriverManager.getDriver().get(appUrl);
	}

	@When("User clicks on Input Form Submit hyperlink")
	public void user_clicks_on_input_form_submit_hyperlink() {
		inputFormSubmit.clickInputFormSubmit();
	}

	@And("User clicks Submit button without filling the form")
	public void user_clicks_submit_button_without_filling_the_form() {
		inputFormSubmit.clickSubmit();
	}

	@Then("An error message {string} should be displayed") 
	public void an_error_message_stating_should_be_displayed(String expectedErrorMessage) {
		Assert.assertEquals(inputFormSubmit.validateError(), expectedErrorMessage);
	}

	@And("User fills the form using data row {int}")
	public void user_fills_the_form_using_data_row(Integer rowIndex) {
	    List<Map<String, String>> testData = BaseUtils.readExcel("src/test/resources/testdata/InputFormSubmit.xlsx", "Input");
	    Map<String, String> data = testData.get(rowIndex);

	    inputFormSubmit.enterName(data.get("name"));
	    inputFormSubmit.enterEmail(data.get("email"));
	    inputFormSubmit.enterPassword(data.get("password"));
	    inputFormSubmit.enterCompany(data.get("company"));
	    inputFormSubmit.enterWebsite(data.get("website"));
	    inputFormSubmit.selectCountry(data.get("country"));
	    inputFormSubmit.enterCity(data.get("city"));
	    inputFormSubmit.enterAddress1(data.get("address1"));
	    inputFormSubmit.enterAddress2(data.get("address2"));
	    inputFormSubmit.enterState(data.get("state"));
	    inputFormSubmit.enterZipCode(data.get("zipcode"));
	}

//	@And("User enters email as {string}")
//	public void user_enters_email_as(String email) {
//		inputFormSubmit.enterEmail(email);
//	}
//
//	@And("User enters password as {string}")
//	public void user_enters_password_as(String password) {
//		inputFormSubmit.enterPassword(password);
//	}
//
//	@And("User enters company as {string}")
//	public void user_enters_company_as(String company) {
//		inputFormSubmit.enterCompany(company);
//	}
//
//	@And("User enters website as {string}")
//	public void user_enters_website_as(String website) {
//		inputFormSubmit.enterWebsite(website);
//	}
//
//	@And("User chooses country as {string}")
//	public void user_chooses_country_as(String country) {
//		inputFormSubmit.selectCountry(country);
//	}
//
//	@And("User enters city as {string}")
//	public void user_enters_city_as(String city) {
//		inputFormSubmit.enterCity(city);
//	}
//
//	@And("User enters address1 as {string}")
//	public void user_enters_address1_as(String address1) {
//		inputFormSubmit.enterAddress1(address1);
//	}
//
//	@And("User enters address2 as {string}")
//	public void user_enters_address2_as(String address2) {
//		inputFormSubmit.enterAddress2(address2);
//	}
//
//	@And("User enters state as {string}")
//	public void user_enters_state_as(String state) {
//		inputFormSubmit.enterState(state);
//	}
//
//	@And("User enters zipcode as {string}")
//	public void user_enters_zipcode_as(String zipcode) {
//		inputFormSubmit.enterZipCode(zipcode);
//	}

	@And("User clicks Submit button")
	public void user_clicks_submit_button() {
		inputFormSubmit.clickSubmit();
	}

	@Then("A success message {string} should be displayed")
	public void success_message(String expectedMessage) {
		Assert.assertEquals(inputFormSubmit.messageAfterSubmitting(), expectedMessage);
	}

}
