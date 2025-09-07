# 🧪 Selenium Cucumber TestNG Framework  

This project is a **Selenium WebDriver test automation framework** built from scratch using **Java, Cucumber (BDD), and TestNG**.  
It supports **cross-browser testing on LambdaTest Cloud Grid** with features like:  

- ✅ Cucumber BDD Scenarios  
- ✅ TestNG Runner with parallel execution  
- ✅ Page Object Model (POM) design  
- ✅ Excel-driven test data  
- ✅ Configurable with `config.properties`  
- ✅ Logging with Log4j2  
- ✅ Cloud execution on LambdaTest  

---

## ✅ Run on Gitpod  

Click below to launch this repo in Gitpod:  

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/MahalakshmiLingesan18/Selenium_LambdaTest)  

---
## 📑 Table of Contents  
- [📦 Install dependencies](#-install-dependencies)  
- [🛠️ Configuration](#️-configuration)  
  - [🔑 LambdaTest Credentials](#-lambdatest-credentials)  
  - [⚙️ config.properties](#️-configproperties)  
- [▶️ Running Tests](#️-running-tests)  
- [📊 Reports](#-reports)  
- [🧩 Example Feature](#-example-feature)  
- [📌 Key Highlights](#-key-highlights)  
- [🚀 Next Enhancements](#-next-enhancements)  

---

## 📂 Project Structure  

<!-- Light mode image -->
![Project Structure Light](assets/project-structure-light.png#gh-light-mode-only)

<!-- Dark mode image -->
![Project Structure Dark](assets/project-structure-dark.png#gh-dark-mode-only)

---

## ⚙️ Setup  

### 📦 Prerequisites  
- Java **11+** (Java 21 supported ✅)  
- Maven **3+**  
- TestNG plugin (for IDE)  
- IntelliJ IDEA / Eclipse  

---

### 📦 Clone the repo  
(bash)
git clone https://github.com/MahalakshmiLingesan18/SeleniumCucumber_LambdaTest.git
cd SeleniumCucumber_LambdaTest

---

## 📦 Install dependencies
(bash)
mvn clean install

---

## 🛠️ Configuration

### 🔑 LambdaTest Credentials  

Set your LambdaTest username & access key as environment variables:

**macOS/Linux (bash):**
(bash)
export LT_USERNAME="your-username"
export LT_ACCESS_KEY="your-access-key"

**Windows (CMD):**
(cmd)
set LT_USERNAME=your-username
set LT_ACCESS_KEY=your-access-key

---

### ⚙️ config.properties
(properties)
url=https://www.lambdatest.com/selenium-playground
browser=chrome
version=latest
platform=Windows 11

---

## ▶️ Running Tests  

**Run all tests (default config):**  
(bash)
mvn clean test

---

**Run with TestNG suite:**  
(bash)
mvn clean test -DsuiteXmlFile=testng.xml

---

**Run a specific feature file:**  
(bash)
mvn test -Dcucumber.features="src/test/resources/FeatureFiles/drag&DropSlider.feature"

---

## 📊 Reports  

- **Cucumber JSON Report** → `target/cucumber-reports/CucumberTestReport.json`  
- Can be integrated with **ExtentReports** / **Allure** for richer reports.

---

## 🧩 Example Feature  

(gherkin)
Feature: Input Form Submit feature

  Scenario: Validating success message after filling the form
    Given User navigates to the LambdaTest Selenium Playground - Input Form Submit
    When User fills the form using data row 0
    And User clicks Submit button
    Then A success message "Thanks for contacting us, we will get back to you shortly" should be displayed

---

## 📌 Key Highlights  

- **DriverManager Pattern** → Thread-safe WebDriver management  
- **Configurable Browsers** → via `config.properties` or `testng.xml`  
- **Excel Data Driven** → form input pulled from `InputFormData.xlsx`  
- **Cloud Execution** → Seamless integration with **LambdaTest**  

---

## 🚀 Next Enhancements  

- Add **Allure / Extent reporting**  
- Integrate with **CI/CD (GitHub Actions / Jenkins)**  
- Add **tagging** for Smoke / Regression test selection  
- Support **parallel browser matrix** from `testng.xml`  

---

✨ With this framework, you can build **scalable, maintainable, and CI/CD-ready Selenium tests** with ease. 
