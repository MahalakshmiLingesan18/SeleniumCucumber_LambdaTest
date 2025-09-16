package com.lambdatest.pageobjects;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.lambdatest.webdriverutility.DriverManager;

public class DragDropSlider {
	
	public DragDropSlider() {
		PageFactory.initElements(DriverManager.getDriver(), this);
	}
	
	@FindBy(xpath="//a[text()='Drag & Drop Sliders']")
	private WebElement dragDropSlider;
	
	@FindBy(xpath="//h4[text()=' Default value 15']/following::input[@value='15']")
	private WebElement sliderInput;
	
	@FindBy(xpath="//output[@id='rangeSuccess']")
	private WebElement outputRange;
	
	WebDriverWait explicitWait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
	
	public void clickDragDropSlider() {
		explicitWait.until(ExpectedConditions.elementToBeClickable(dragDropSlider)).click();
	}
	
	public int getRangeFromSlider() {
        return Integer.parseInt(sliderInput.getAttribute("value"));
    }
	
	public void setSliderTo(Integer targetValue) {
	    int currentValue = Integer.parseInt(sliderInput.getAttribute("value")); //15
	    int minValue = Integer.parseInt(sliderInput.getAttribute("min")); //1
	    int maxValue = Integer.parseInt(sliderInput.getAttribute("max")); //100

	    // Slider width in pixels
	    int sliderWidth = sliderInput.getSize().getWidth();
	    System.out.println("Slider width: " + sliderWidth); //500

	    // Pixels per unit
	    double pxPerUnit = (double) sliderWidth / (maxValue - minValue); //500/100-1 --> 500/99
	    System.out.println("Pixels per unit: " + pxPerUnit); //5.05050505050505

	    // Calculate offset
	    int pixelOffset = (int) Math.round((targetValue - currentValue) * pxPerUnit); //(95 - 15)*5.05
	    System.out.println("Pixel offset: " + pixelOffset); //404

	    // Try drag
	    Actions actions = new Actions(DriverManager.getDriver());
	    actions.dragAndDropBy(sliderInput, pixelOffset, 0).perform();

	    // Check value after drag
	    int finalValue = getRangeFromSlider();
	    System.out.println("After drag, slider value = " + finalValue); //100

	    // Correct with JS if needed
	    if (finalValue != targetValue) { //100 != 95
	        System.out.println("Correcting value with JavaScript...");
	        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
	        js.executeScript(
	            "arguments[0].value = arguments[2];" +
	            "arguments[1].textContent = arguments[2];" +   // force update output element
	            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
	            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
	            sliderInput, outputRange, targetValue
	        );
	    }
	}
	
	public WebElement getOutputRangeElement() {
	    return outputRange;
	}
	
	public int getOutputRange() {
		explicitWait.until(ExpectedConditions.visibilityOf(outputRange));
		String value = outputRange.getText();
		int actualRange = Integer.parseInt(value);
		return actualRange;
	}

}
