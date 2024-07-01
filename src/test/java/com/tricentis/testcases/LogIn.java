package com.tricentis.testcases;

import java.util.HashMap;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tricentis.base.Base;
import com.tricentis.dataprovider.LoginDataProvider;
import com.tricentis.pageobjects.AccountPage;
import com.tricentis.pageobjects.HomePage;
import com.tricentis.pageobjects.LoginPage;
import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class LogIn extends Base {
	WebDriver driver;
	MyXLSReader excelReader;

	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod 
	public void statUp() {
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!DataUtil.isRunnable(excelReader, "Login", "TestCases")) {
			throw new SkipException("Data(Login Testcase) is not runnable.....!!skip!!");
		}
		
		driver = openBrowserAndRunApplicationURL();
		homePage = new HomePage(driver);
		
		homePage.clickOnLoginButton();
		loginPage = new LoginPage(driver);
	}
	

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "dataForLoginWithValidCredentials", dataProviderClass = LoginDataProvider.class)
	public void verifyLoginWithValidCredentials(HashMap<String, String> hMapValidData) {
		if (hMapValidData.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}

		loginPage.sendTextToEmailField(hMapValidData.get("Username"));
		loginPage.sendTextToPasswordField(hMapValidData.get("Password"));

		// Just for checking the read data......
		System.out.println("The readed password from excel :" + hMapValidData.get("Password"));

		loginPage.clickOnLoginButton();
		
		System.out.println("running code successfully...");

		homePage.clickOnUserNameButton();
		accountPage = new AccountPage(driver);

		String expectedResult = hMapValidData.get("ExpectedResult");
		boolean convertedExpectedResult = false;
		if (expectedResult.equalsIgnoreCase("Success")) {
			convertedExpectedResult = true;
		} else if (expectedResult.equalsIgnoreCase("Fail")) {
			convertedExpectedResult = false;
		}

		Assert.assertEquals(accountPage.verifyLoginStatus(hMapValidData.get("Username")), convertedExpectedResult);
	}

	@Test(dataProvider = "dataForLoginWithInvalidCredentials", dataProviderClass = LoginDataProvider.class)
	public void verifyLoginWithInvalidCredentials(HashMap<String, String> hMapInvalidCredentials) {
		if (hMapInvalidCredentials.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}

		loginPage.sendTextToEmailField(hMapInvalidCredentials.get("Username"));
		loginPage.sendTextToPasswordField(hMapInvalidCredentials.get("Password"));
		loginPage.clickOnLoginButton();
		
		String errorMsg01 = hMapInvalidCredentials.get("ValidationError01");
		String errorMsg02 = hMapInvalidCredentials.get("ValidationError02");
		Assert.assertEquals(errorMsg01, "Login was unsuccessful. Please correct the errors and try again.");
		Assert.assertEquals(errorMsg02, "No customer account found");
	}


	@Test(dataProvider = "dataForLoginWithValidEmailInvalidPassword", dataProviderClass = LoginDataProvider.class)
	public void verifyLoginWithValidEmailInvalidPassword(HashMap<String, String> hMapValidEmailInvalidPassword) {
		if (hMapValidEmailInvalidPassword.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}

		loginPage.sendTextToEmailField(hMapValidEmailInvalidPassword.get("Username"));
		loginPage.sendTextToPasswordField(hMapValidEmailInvalidPassword.get("Password"));
		loginPage.clickOnLoginButton();
		
		Assert.assertEquals(hMapValidEmailInvalidPassword.get("ValidationError01"), loginPage.validationOneForLoginWithValidEmailInvalidPass());
		Assert.assertEquals(hMapValidEmailInvalidPassword.get("ValidationError02"), loginPage.validationSecoundForLoginWithValidEmailInvalidPass());
	}

	@Test(dataProvider = "dataForLoginWithInalidEmailValidPassword", dataProviderClass = LoginDataProvider.class)
	public void verifyLoginWithInalidEmailValidPassword(HashMap<String, String> hMapInvalidEmailValidPassword) {
		if (hMapInvalidEmailValidPassword.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}

		loginPage.sendTextToEmailField(hMapInvalidEmailValidPassword.get("Username"));
		loginPage.sendTextToPasswordField(hMapInvalidEmailValidPassword.get("Password"));
		loginPage.clickOnLoginButton();
		
		Assert.assertEquals(hMapInvalidEmailValidPassword.get("ValidationError01"), "Login was unsuccessful. Please correct the errors and try again.");
		Assert.assertEquals(hMapInvalidEmailValidPassword.get("ValidationError02"), "The credentials provided are incorrect");
	}

}
