package com.wallethub.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.wallethub.base.base.BaseTest;
import com.wallethub.pages.WalletHubReviewPage;

public class WalletHubReviewVerification extends BaseTest {

	WalletHubReviewPage whReviewPage;

	@Test
	public void postStatus() throws Exception {
		whReviewPage = new WalletHubReviewPage(driver);
		whReviewPage.walletHubLogin(prop.getProperty("walletHubUrl"),prop.getProperty("walletHubUsername"),prop.getProperty("walletHubPassword"));

		// Verify if user is adding review for the first time or updating existing
		// review and add the review details accordingly
		boolean reviewCheck = whReviewPage.hoverOverOnstars();
		boolean testCaseCheck = false;
		if (reviewCheck) {
			System.out.println("Posting a fresh Review!!");
			whReviewPage.clickon4thStar();
			testCaseCheck=whReviewPage.healthInsuranceReview(prop.getProperty("walletHubUrl"));
		} else {
			System.out.println("Updating Review as already review has been submitted!!");
			testCaseCheck=whReviewPage.updateReview();
		}
		Assert.assertTrue(testCaseCheck,"Review Added Successfully - Passed");

	}

}
