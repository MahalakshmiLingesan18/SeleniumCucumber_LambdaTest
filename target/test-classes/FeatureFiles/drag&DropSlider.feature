Feature: Drag & Drop Slider feature

Scenario: Validating the range value of Default value 15 slider shows the same value as per the slider
Given Users navigates to the LambdaTest Selenium Playground - Drag & Drop Slider
When User clicks on Drag & Drop Slider hyperlink
And User drags the Default value 15 slider to 95
Then The range value of Default value 15 slider should show 95