🧪 Selenium Cucumber TestNG Framework

This project is a Selenium WebDriver test automation framework built from scratch using Java, Cucumber (BDD), and TestNG.
It supports cross-browser testing on LambdaTest Cloud Grid with features like:

✅ Cucumber BDD Scenarios
✅ TestNG Runner with parallel execution
✅ Page Object Model (POM) design
✅ Excel-driven test data
✅ Configurable with config.properties
✅ Logging with Log4j2
✅ Cloud execution on LambdaTest

📂 Project Structure
selenium-cucumber-testng/
│── src/
│   ├── main/java/com/lambdatest/webdriverutility/
│   │   ├── CapabilityFactory.java
│   │   ├── Driver.java
│   │   └── DriverManager.java
│   │
│   └── test/java/com/lambdatest/
│       ├── pageobjects/
│       │   ├── DragDropSliderPage.java
│       │   ├── InputFormSubmit.java
│       │   └── SimpleFormDemo.java
│       │
│       ├── stepdefinition/
│       │   ├── DragDropSliderStepDefinition.java
│       │   ├── InputFormSubmitStepDefinition.java
│       │   ├── SimpleFormDemoStepDefinition.java
│       │   └── Hooks.java
│       │
│       ├── utilities/
│       │   └── BaseUtils.java
│       │
│       ├── runner/
│       │   └── TestRunner.java
│       │
│       └── resources/
│           ├── FeatureFiles/
│           │   ├── drag&DropSlider.feature
│           │   ├── InputFormSubmit.feature
│           │   └── SimpleFormDemo.feature
│           │
│           ├── PropertiesFile/
│           │   └── config.properties
│           │
│           ├── TestData/
│           │   └── InputFormData.xlsx
│── pom.xml
│── testng.xml
└── README.md

⚙️ Setup
1️⃣ Prerequisites
Java 11+ (Java 21 supported ✅)
Maven 3+
TestNG plugin (for IDE)
IntelliJ IDEA / Eclipse

2️⃣ Clone the repo
git clone https://github.com/<your-username>/selenium-cucumber-testng.git
cd selenium-cucumber-testng

3️⃣ Install dependencies
mvn clean install

🛠️ Configuration
🔑 LambdaTest Credentials

Set your LambdaTest username & access key as environment variables:

1️⃣(bash)
export LT_USERNAME="your-username"
export LT_ACCESS_KEY="your-access-key"

2️⃣(Windows CMD)
set LT_USERNAME=your-username
set LT_ACCESS_KEY=your-access-key

⚙️ config.properties
url=https://www.lambdatest.com/selenium-playground
browser=chrome
version=latest
platform=Windows 11

▶️ Running Tests
Run all tests (default config)
mvn clean test

Run with TestNG suite
mvn clean test -DsuiteXmlFile=testng.xml

Run a specific feature file
mvn test -Dcucumber.features="src/test/resources/FeatureFiles/drag&DropSlider.feature"

📊 Reports
Cucumber JSON Report → target/cucumber-reports/CucumberTestReport.json
Can be integrated with ExtentReports / Allure for richer reports.

🧩 Example Feature
Feature: Input Form Submit feature

  Scenario: Validating success message after filling the form
    Given User navigates to the LambdaTest Selenium Playground - Input Form Submit
    When User fills the form using data row 0
    And User clicks Submit button
    Then A success message "Thanks for contacting us, we will get back to you shortly" should be displayed

📌 Key Highlights
DriverManager Pattern → Thread-safe WebDriver management.
Configurable Browsers → via config.properties or testng.xml.
Excel Data Driven → form input pulled from InputFormData.xlsx.
Cloud Execution → Seamless integration with LambdaTest.

🚀 Next Enhancements
 Add Allure/Extent reporting
 Integrate with CI/CD (GitHub Actions / Jenkins)
 Add tagging for Smoke/Regression test selection
 Support parallel browser matrix from testng.xml

✨ With this framework, you can build scalable, maintainable, and CI/CD-ready Selenium tests with ease.
