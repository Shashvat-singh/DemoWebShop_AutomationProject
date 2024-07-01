package com.tricentis.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tricentis.base.Base;
import com.tricentis.pageobjects.ForgotPasswordPage;
import com.tricentis.pageobjects.HomePage;
import com.tricentis.pageobjects.LoginPage;

public class ForgotPassword extends Base{
	WebDriver driver;
	ForgotPasswordPage forgotPasswordPage;

	@BeforeMethod
	public void statUp() {
		driver = openBrowserAndRunApplicationURL();
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLoginButton();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOntheForgotPassword();
		forgotPasswordPage = new ForgotPasswordPage(driver);
	}



	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test
	public void verifyRecoveryOfPasswordWithInvalidEmailText() {
		forgotPasswordPage.sendTextToYourEmailAddressField("shashvat500@gmail.com");
		forgotPasswordPage.clickOnRecoveryButton();
	}

}
