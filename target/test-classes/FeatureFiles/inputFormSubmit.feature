Feature: Input Form Submit feature
  To validate error and success messages on form submission
  
  Scenario: Validating the messages before and after filling the fields from Excel data
  Given User navigates to the LambdaTest Selenium Playground - Input Form Submit
  When User clicks on Input Form Submit hyperlink
  And User clicks Submit button without filling the form
  Then An error message "Please fill out this field." should be displayed
  And User fills the form using data row 0
  And User clicks Submit button
  Then A success message "Thanks for contacting us, we will get back to you shortly." should be displayed
  
