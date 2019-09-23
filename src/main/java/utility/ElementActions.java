package utility;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementActions {
	WebDriver driver;
	JavascriptExecutor executor;
	WebDriverWait wait;
	WebElement element;
	List<WebElement> elements;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60);
	}

	/* WebDriver function to locate a UI element. */
	public WebElement findElement(By by) {
		element = driver.findElement(by);
		return element;
	}

	/*
	 * Wait function to wait for a particular UI element until it is visible and
	 * click-able. Maximum wait time 60 secs.
	 */
	public void waitUntilVisibilityLocated(By by) {
		wait.until(
				ExpectedConditions.elementToBeClickable(wait.until(ExpectedConditions.visibilityOfElementLocated(by))));
	}

	/*
	 * Function to check weather a particular element is present on UI or not.
	 */
	public boolean checkElementPresence(By by) {
		boolean presence = false;

		if (driver.findElements(by).size() > 0) {
			presence = true;
		}
		return presence;
	}

	/* Function to click on a UI element. */
	public void click() {
		element.click();
	}

	/* Function to click on a UI element using java-script. */
	public void click_JS() {
		executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	/* Function to empty a text-field. */
	public void clearField() {
		element.clear();
	}

	/* Function to enter a particular value in a text-field. */
	public void sendKeys(String text) {
		element.sendKeys(text);
	}

	/* Function to get text present inside a particular UI element. */
	public String getElementText() {
		return element.getText();
	}

	/* Function to get text present inside a List of similar web elements. */
	public List<String> getElementsText(List<WebElement> list_Of_Elements) {
		List<String> texts = new ArrayList<String>();
		for (WebElement i : list_Of_Elements) {
			texts.add(i.getText());
		}
		return texts;
	}

	/* Function to select a value from a drop-down based upon visible text. */
	public void selectValueFromDropdown(String dropdown_Value) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(dropdown_Value);
	}

	/* Function to get title of a particular web-page. */
	public String getTitle() {
		return driver.getTitle();
	}

	/* function to get list of elements existing for a particular locator */
	public List<WebElement> findElements(By by) {
		return driver.findElements(by);
	}
}
