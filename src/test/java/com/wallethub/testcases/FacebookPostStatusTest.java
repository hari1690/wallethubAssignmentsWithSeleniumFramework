package com.wallethub.testcases;

import org.testng.annotations.Test;

import com.wallethub.base.base.BaseTest;
import com.wallethub.pages.FacebookPage;

public class FacebookPostStatusTest extends BaseTest {

	FacebookPage fbloginpage;

	@Test
	public void postStatus() throws Exception {
		fbloginpage = new FacebookPage(driver);
		fbloginpage.fbLogin(prop.getProperty("fbUsername"),prop.getProperty("fbPassword"));
		fbloginpage.statusPost(prop.getProperty("fbStatus"));
	}

}
