package com.wallethub.pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.wallethub.base.GenericMethods;

public class WalletHubReviewPage extends GenericMethods {

	@FindBy(xpath = "//*[@id='web-app']/header/div/nav[1]/span")
	public WebElement WHLoginButton;

	@FindBy(xpath = "//span[@class='w-icon-wallet']")
	public WebElement WHLogo;

	@FindBy(xpath = "//div[@class='btns']")
	public WebElement JoinButton;

	@FindBy(xpath = "//input[@ng-model='fields.email']")
	public WebElement Email;

	@FindBy(xpath = "//input[@ng-model='fields.password']")
	public WebElement Password;

	@FindBy(xpath = "//button[@data-hm-tap='doLogin($event);']")
	public WebElement LoginButton;

	@FindBy(xpath = "//div[@class='review-stat-box']/div/review-star/div[@class='rating-box-wrapper']")
	public WebElement fullHover;

	@FindBy(xpath = "//div[@class='review-stat-box']/div/review-star/div[@class='rating-box-wrapper']/*[4]")
	public WebElement fourthStar;

	@FindBy(xpath = "//div[@class=\"dropdown second\"]")
	public WebElement InsuranceTypeForReview;

	@FindBy(xpath = "//*/ul/li[contains(text(),'Health Insurance')]")
	public WebElement HealthInsuranceType;

	@FindBy(xpath = "//div[@class='android']/textarea")
	public WebElement textArea;

	@FindBy(xpath = "//div[@class='sub-nav-ct']/*[contains(text(),'Submit')]")
	public WebElement submitButton;

	@FindBy(xpath = "//section[@id='reviews-section']/div[@role='listbox']/span[@role='option']")
	public WebElement reviewSortingDropdown;

	@FindBy(xpath = "//span[@role='option']/*[1]")
	public WebElement FirstOptionMostrecent;

	@FindBy(xpath = "//span[@class='rvtab-ci-name'][contains(text(),'Your Review')]")
	public WebElement YourReview;

	@FindBy(xpath = "//button[text()='Write a Review']")
	public WebElement writeAReviewButton;

	@FindBy(xpath = "//h3[text()='Your Rating']")
	public WebElement myReviewHeader;

	@FindBy(xpath = "//h3[text()='Your Rating']/following-sibling::review-star//*[4]")
	public WebElement review4thStar;

	@FindBy(xpath = "//h3[text()='Your Rating']/following-sibling::div//textarea[@name='reviewcomment']")
	public WebElement reviewCommentBox;

	@FindBy(xpath = "//button//span[text()='Submit']")
	public WebElement reviewSubmitButton;

	@FindBy(xpath = "//span[text()=' Your Review']//following::review-star[1]//*[5]//*[@fill='#e4e9eb']")
	public WebElement lastRating;

	@FindBy(xpath = "//span[text()=' Your Review']//following::div[@itemprop='description']")
	public WebElement updatedReviewText;

	public WalletHubReviewPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	By fullHoverField = By.xpath("//div[@class='review-stat-box']/div/review-star/div[@class='rating-box-wrapper']");
	// Method for logging into Wallethub web application
	public void walletHubLogin(String url, String email, String password) {
		try {
			launchUrl(url);
			System.out.println("Launched URL");
			waitForElementVisible(WHLoginButton);
			WHLoginButton.click();	
			sendText(Email, email);
			sendText(Password, password);
			System.out.println("Email and Password details sent");
			click(LoginButton);
			System.out.println("Login button clicked");
			waitForElementVisible(writeAReviewButton);
			System.out.println("Home page opened");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Method for hovering over the review stars
	public boolean hoverOverOnstars() {
		boolean check = false;
		try {
			check = waitUntilElementExists(fullHoverField);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return check;
	}

	// Method for hovering over on fourth star and click
	public void clickon4thStar() {
		try {
		//waitForElementVisible(fullHover);
		Actions builder = new Actions(driver);
		builder.moveToElement(fullHover).moveToElement(fourthStar).click().perform();
		System.out.println("4th start clicked successfully");
		} catch (Exception e) {
			System.out.println("4th start was not clicked properly");
			e.printStackTrace();
		}
	}

	// Method for posting up the Health insurance review for the first time login
	// user
	public boolean healthInsuranceReview(String url) {
		boolean reviewAdditionCheck = false;
		try {

			waitForElementVisible(InsuranceTypeForReview);
			System.out.println("Choosing the Insurance Type Review");
			InsuranceTypeForReview.click();
			waitForElementVisible(HealthInsuranceType);
			HealthInsuranceType.click();
			System.out.println("Health Insurance type selected");
			waitForElementVisible(textArea);
			String reviewComment = randomString(200);
			textArea.sendKeys(reviewComment);
			System.out.println("Review comment added in the text area");
			System.out.println(reviewComment);
			waitForElementVisible(submitButton);
			submitButton.click();
			System.out.println("Review submitted");
			//waitForElementVisible(JoinButton);
			launchUrl(url);
//			waitForElementVisible(reviewSortingDropdown);
//			reviewSortingDropdown.click();
//			System.out.println("review Sorting Dropdown");
//			FirstOptionMostrecent.click();
//			System.out.println("MostRecent");
			boolean verifyReviewAddedCheck = waitForElementVisible(YourReview);
			if (verifyReviewAddedCheck) {
				System.out.println("Review added successfully and is displayed in the review feed");
				reviewAdditionCheck = true;
			} else {
				System.out.println("Review is not present in the review feed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reviewAdditionCheck;
	}

	// Method for updating the review for the existing user
	public boolean updateReview() {
		boolean reviewFinalCheck = false;
		boolean retrieveRatingResult = false, retrieveCommentsResults = false;
		String descriptionText = randomString(200);
		try {
			writeAReviewButton.click();
			boolean headerCheck = waitForElementVisible(myReviewHeader);
			if (headerCheck) {
				review4thStar.click();
				reviewCommentBox.clear();

				reviewCommentBox.sendKeys(descriptionText);
				reviewSubmitButton.click();

				// Verifying if the Updated review rating is entered properly
				boolean ratingValue = false;
				for (int i = 1; i <= 4; i++) {
					boolean checkRatingStarColour = waitForElementVisible(
							driver.findElement(By.xpath("//span[text()=' Your Review']//following::review-star[1]//*["
									+ i + "]//*[@fill='#4ae0e1']")));
					if (!checkRatingStarColour) {
						ratingValue = false;
						break;
					} else {
						ratingValue = true;
					}

				}
				boolean lastRatingColourCheck = waitForElementVisible(lastRating);
				if (lastRatingColourCheck) {
					ratingValue = true;
				} else {
					ratingValue = false;
				}
				if (ratingValue) {
					System.out.println("Rating details are entered properly");
					retrieveRatingResult = true;
				} else {
					System.out.println("Rating details are not entered properly");
					retrieveRatingResult = false;
				}
			}

			// Verifying if the Updated review description is entered properly
			String updatedReviewTextValue = updatedReviewText.getText();

			// todo: change the manual addition of texts
			if (updatedReviewTextValue.equals(descriptionText)) {
				System.out.println("Description details are entered properly");
				retrieveCommentsResults = true;
			} else {
				System.out.println("Description details are not entered properly");
				retrieveCommentsResults = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (retrieveRatingResult == true && retrieveCommentsResults == true) {
			reviewFinalCheck = true;
		} else
			reviewFinalCheck = false;
		return retrieveCommentsResults;

	}

}
