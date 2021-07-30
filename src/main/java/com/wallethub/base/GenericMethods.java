package com.wallethub.base;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericMethods {

	public WebDriver driver;

	// Method for Launch App Url
	public void launchUrl(String applicationUrl) throws Exception {
		driver.get(applicationUrl);
	}

	// Method for Clicking the Element
	public void click(WebElement element) {
		element.click();
	}

	// Method for sending the text in Text box
	public void sendText(WebElement element, String text) {
		element.sendKeys(text);
	}

	// Method for checking the Visibility of Element in the page
	public boolean waitForElementVisible(WebElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(element));
			if (!element.isDisplayed()) {
				System.out.println("Element is not Identified");
				return false;
			} else {
				return true;
			}
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException f) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;

	}

	public boolean waitUntilElementExists(By element) {
		try {
			boolean isPresent = driver.findElements(element).size() > 0;
			if (isPresent) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean waitUntilElementDisplayed(final WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver arg0) {
				try {
					webElement.isDisplayed();
					return true;
				} catch (TimeoutException e) {
					return false;
				} catch (NoSuchElementException e) {
					return false;
				} catch (StaleElementReferenceException f) {
					return false;
				} catch (Exception f) {
					return false;
				}
			}
		};
		wait.until(elementIsDisplayed);
		return false;
	}

	// Method for generating random String and also setting the length of string
	// that is required
	public String randomString(final int length) {
		String aToZ = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int randIndex = r.nextInt(aToZ.length());
			res.append(aToZ.charAt(randIndex));
		}

		return res.toString();
	}

}
