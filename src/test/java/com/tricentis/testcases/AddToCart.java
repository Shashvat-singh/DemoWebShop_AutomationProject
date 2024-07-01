
package com.tricentis.testcases;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tricentis.base.Base;
import com.tricentis.dataprovider.AddToCartDataProvider;
import com.tricentis.pageobjects.HomePage;
import com.tricentis.pageobjects.LoginPage;
import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class AddToCart extends Base {
	WebDriver driver;
	MyXLSReader excelReader;

	HomePage homePage;
	LoginPage loginPage;

	@BeforeMethod
	public void statUp() {
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!DataUtil.isRunnable(excelReader, "AddToCart", "TestCases")) {
			throw new SkipException("Data(Add To Cart Testcase) is not runnable.....!!skip!!");
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

	@Test(dataProvider = "dataForVerifyAddButtonIsVisibleOnProduct", dataProviderClass = AddToCartDataProvider.class)
	public void verifyAddButtonIsVisibleOnProduct(HashMap<String, String> hMapAddBtnVisibleOnProduct) {
		if (hMapAddBtnVisibleOnProduct.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		// Land on Login Page and click on login button after enter valid login id and
		// password.
		loginPage.sendTextToEmailField(hMapAddBtnVisibleOnProduct.get("Valid Username"));
		loginPage.sendTextToPasswordField(hMapAddBtnVisibleOnProduct.get("Valid Password"));
		loginPage.clickOnLoginButton();

		// After successfully login go to feature product list at home page and verify
		// the add to cart button is visible.
		int NoOfFeatureProduct = homePage.numberOfFeaturedProd();
		int NoOfAddToCartBtn = homePage.numberOfAddToCartBtn();

		boolean NoOfProductIsEqualToNoOfAddToCartBtn;
		if (NoOfFeatureProduct == NoOfAddToCartBtn) {
			NoOfProductIsEqualToNoOfAddToCartBtn = true;
		} else {
			NoOfProductIsEqualToNoOfAddToCartBtn = false;
		}

		String expectedResult = hMapAddBtnVisibleOnProduct.get("ExpectedResult");
		boolean convertedExpectedResult = false;
		if (expectedResult.equalsIgnoreCase("Success")) {
			convertedExpectedResult = true;
		} else if (expectedResult.equalsIgnoreCase("Fail")) {
			convertedExpectedResult = false;
		}

		Assert.assertEquals(NoOfProductIsEqualToNoOfAddToCartBtn, convertedExpectedResult);

	}
	@Test (dataProvider = "dataSuccessMsgForAddToCartProd", dataProviderClass = AddToCartDataProvider.class)
	public void successMsgForAddToCartProd(HashMap<String, String>hMapMsgFrAddToCart) {
		if (hMapMsgFrAddToCart.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Data is not runnable.....!!skip!!");
		}
		// Land on Login Page and click on login button after enter valid login id and
		// password.
		loginPage.sendTextToEmailField(hMapMsgFrAddToCart.get("Valid Username"));
		loginPage.sendTextToPasswordField(hMapMsgFrAddToCart.get("Valid Password"));
		loginPage.clickOnLoginButton();
		
		
		
		
		

	}
}
