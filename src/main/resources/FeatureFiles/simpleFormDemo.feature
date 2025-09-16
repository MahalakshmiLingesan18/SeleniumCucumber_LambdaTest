Feature: Simple Form Demo feature

Scenario: Validating the same text message is displayed in the Your Message: section.
Given User navigates to the LambdaTest Selenium Playground - Simple Form Demo
When User clicks on Simple Form Demo hyperlink
And User enters "Welcome to LambdaTest" in the text box
And User clicks on Get Checked Value button
Then The same text message "Welcome to LambdaTest" should be displayed in Your Message: section
