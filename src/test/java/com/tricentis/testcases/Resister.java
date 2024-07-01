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
import com.tricentis.dataprovider.ResisterDataProvider;
import com.tricentis.pageobjects.HomePage;
import com.tricentis.pageobjects.ResisterPage;
import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class Resister extends Base {
	WebDriver driver;
	MyXLSReader excelReader;
	
	HomePage homePage;
	ResisterPage resisterPage;

	@BeforeMethod
	public void statUp() {
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!DataUtil.isRunnable(excelReader, "Resister", "TestCases")) {
			throw new SkipException("Data(Register Testcase) is not runnable.....!!skip!!");
		}
		driver = openBrowserAndRunApplicationURL();
		homePage = new HomePage(driver);
		homePage.clickOnResisterButton();
		resisterPage = new ResisterPage(driver);

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
	@Test  (dataProvider = "dataOfRegisterWithMandFields", dataProviderClass = ResisterDataProvider.class)
	public void registerWithMandFieldsOnly(HashMap<String, String>hMapRegisterWithMandField) {
		if (hMapRegisterWithMandField.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		
		resisterPage.selectRadioButtonByValue(hMapRegisterWithMandField.get("Gender"));
		resisterPage.enterFirstName(hMapRegisterWithMandField.get("First name"));
		resisterPage.enterLastName(hMapRegisterWithMandField.get("Last name"));
		resisterPage.enterEmail(hMapRegisterWithMandField.get("Email"));
		resisterPage.enterPassword(hMapRegisterWithMandField.get("Password"));
		resisterPage.enterConfPass(hMapRegisterWithMandField.get("Confirm password"));
		
		resisterPage.clickOnResisterButton();
	}
	
	
	@Test  (dataProvider = "dataOfRegisterWithoutPasswords", dataProviderClass = ResisterDataProvider.class)
	public void registerWithoutPasswords(HashMap<String, String>hMapRegisterWithoutPasswords) {
		if (hMapRegisterWithoutPasswords.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		
		resisterPage.selectRadioButtonByValue(hMapRegisterWithoutPasswords.get("Gender"));
		resisterPage.enterFirstName(hMapRegisterWithoutPasswords.get("First name"));
		resisterPage.enterLastName(hMapRegisterWithoutPasswords.get("Last name"));
		resisterPage.enterEmail(hMapRegisterWithoutPasswords.get("Email"));
		
		resisterPage.clickOnResisterButton();
		
		Assert.assertEquals(hMapRegisterWithoutPasswords.get("ErrMsgPass"), resisterPage.getValidationRemarkPassword());
		Assert.assertEquals(hMapRegisterWithoutPasswords.get("ErrMsgConPass"), resisterPage.getValidationRemarkConfPassword());
	}
	
	
	@Test  (dataProvider = "dataOfRegisterWithoutFirstname", dataProviderClass = ResisterDataProvider.class)
	public void registerWithoutFirstName(HashMap<String, String>hMapRegisterWithoutFirstName) {
		if (hMapRegisterWithoutFirstName.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		
		resisterPage.selectRadioButtonByValue(hMapRegisterWithoutFirstName.get("Gender"));
		//resisterPage.enterFirstName(hMapResisterWithoutFirstName.get("First name"));
		resisterPage.enterLastName(hMapRegisterWithoutFirstName.get("Last name"));
		resisterPage.enterEmail(hMapRegisterWithoutFirstName.get("Email"));

		resisterPage.enterPassword(hMapRegisterWithoutFirstName.get("Password"));
		resisterPage.enterConfPass(hMapRegisterWithoutFirstName.get("Confirm password"));
		
		resisterPage.clickOnResisterButton();
		
		Assert.assertEquals(hMapRegisterWithoutFirstName.get("ErrMsgFirstName"), resisterPage.getValidationRemarkFirstName());
	}
	@Test (dataProvider = "dataOfRegisterWithAlreadyUsedEmailAddress", dataProviderClass = ResisterDataProvider.class)
	public void registerWithAlreadyUsedEmailAddress(HashMap<String, String>hMapRegisterWithAlreadyUsedEmail) {
		if (hMapRegisterWithAlreadyUsedEmail.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		
		resisterPage.selectRadioButtonByValue(hMapRegisterWithAlreadyUsedEmail.get("Gender"));
		resisterPage.enterFirstName(hMapRegisterWithAlreadyUsedEmail.get("First name"));
		resisterPage.enterLastName(hMapRegisterWithAlreadyUsedEmail.get("Last name"));
		resisterPage.enterEmail(hMapRegisterWithAlreadyUsedEmail.get("Email"));
		resisterPage.enterPassword(hMapRegisterWithAlreadyUsedEmail.get("Password"));
		resisterPage.enterConfPass(hMapRegisterWithAlreadyUsedEmail.get("Confirm password"));
		
		resisterPage.clickOnResisterButton();
		
		Assert.assertEquals(hMapRegisterWithAlreadyUsedEmail.get("ErrMsgAlreadyUsedEmail"), resisterPage.getValidationRemarkForAlreadyUsedEmail());
		
	}
	
	
	
	
	
}
