package com.wallethub.base.base;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver;
	protected static Properties prop = null;
	

	//Launch Browser as a first step
	@BeforeSuite
	public static void getDriver() {
		try {
			setDriver("CHROME");

		} catch (Exception e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Method for Setting up the driver and browser
	public static void setDriver(String browser) throws Exception {
		switch (browser.toUpperCase()) {
		case "CHROME":
			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			// 1-Allow, 2-Block, 0-default
			options.setExperimentalOption("prefs", prefs);
			driver = new ChromeDriver(options);

			/*
			 * case "FIREFOX": WebDriverManager.firefoxdriver().setup(); driver = new
			 * FirefoxDriver();
			 */
		}
		readProperties();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}

	//Set up for reading config.properties file
	public static void readProperties() {

		prop = new Properties();

		try {

			String currentDir = System.getProperty("user.dir");
			String configProperties = currentDir + "\\src\\main\\resources\\config.properties";
			//System.out.println(configProperties);

			InputStream input = new FileInputStream(configProperties);
			prop.load(input);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String getPropertyValue(String key) {

		return prop.getProperty(key);
	}

	//Tearing down the browser once tests are completed
	@AfterSuite
	public static void quitBrowser() {
		// driver.quit();
	}
}
