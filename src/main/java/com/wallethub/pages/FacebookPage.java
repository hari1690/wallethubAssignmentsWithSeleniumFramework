package com.wallethub.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.wallethub.base.GenericMethods;

public class FacebookPage extends GenericMethods {

	@FindBy(xpath = "//input[@data-testid='royal_email']")
	public WebElement fbUsername;

	@FindBy(xpath = "//input[@data-testid='royal_pass']")
	public WebElement fbPassword;

	@FindBy(xpath = "//button[@data-testid='royal_login_button']")
	public WebElement fbLoginButton;

	@FindBy(xpath = "//span[contains(text(),'on your mind')]")
	public WebElement fbStatusBar;

	@FindBy(xpath = "//a[@aria-label='Facebook']")
	public WebElement fblogo;

	@FindBy(xpath = "//div[starts-with(@aria-label, 'What')]")
	public WebElement fbStatusPopupTextArea;

	@FindBy(xpath = "//div[@aria-label='Post']")
	public WebElement fbPostButton;

	@FindBy(xpath = "//div[contains(text(),'status update is identical')]")
	public WebElement postIdenticalError;

	@FindBy(xpath = "//div[@role='feed']/div[@data-pagelet='FeedUnit_0']//div[contains(text(),'Hello World')]")
	public WebElement postedStatus;

	@FindBy(xpath = "//span[contains(text(),'Welcome to Facebook')]")
	public WebElement welcomeMessage;
	
    By postIdenticalErrorfield = By.xpath("//div[contains(text(),'status update is identical')]");
    
	public FacebookPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method for logging into Facebook web application
	public void fbLogin(String fbEmail, String fbPwd) {
		try {
			launchUrl("http://fb.com");
			System.out.println("Launched URL");
			waitForElementVisible(fbUsername);
			sendText(fbUsername, fbEmail);
			sendText(fbPassword, fbPwd);
			System.out.println("Username and Password entered");
			click(fbLoginButton);
			try {
				Assert.assertTrue(welcomeMessage.isDisplayed());

				// If the message is displayed
				System.out.println("Logged in Successfully");

			} catch (Exception e) {

				// If the message is not displayed
				System.out.println("Login Failed");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Method for posting the status on Facebook
	public void statusPost(String StatusMessage) throws InterruptedException {
		try {
			waitForElementVisible(fblogo);
			fblogo.click();
			fbStatusBar.click();
			fbStatusPopupTextArea.sendKeys(StatusMessage);
			fbPostButton.click();
			boolean postIdenticalErrorCheck = waitUntilElementExists(postIdenticalErrorfield);
			boolean postedStatusCheck=waitForElementVisible(postedStatus);
			if (postIdenticalErrorCheck) {
				System.out.println(
						"Same Status already posted. Please check.- Status Identical Error is displayed and not allowing the user to post status. Try Again later!");
			} else if(postedStatusCheck){
				//Assert.assertEquals(postedStatus.getText(), StatusMessage);
					System.out.println("Status posted successfully");
			}
		} catch (Exception e) {
			System.out.println("Unable to post status. Please check");
			e.printStackTrace();
		}
	}
}
