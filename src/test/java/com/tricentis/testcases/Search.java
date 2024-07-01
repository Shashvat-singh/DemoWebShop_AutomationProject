package com.tricentis.testcases;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tricentis.base.Base;
import com.tricentis.dataprovider.SearchDataProvider;
import com.tricentis.pageobjects.HomePage;
import com.tricentis.pageobjects.SearchPage;
import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class Search extends Base {
	WebDriver driver;
	MyXLSReader excelReader;
	HomePage homePage;
	SearchPage searchPage;

	@BeforeMethod
	public void startUp() {
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (!DataUtil.isRunnable(excelReader, "Resister", "TestCases")) {
			throw new SkipException("Data(Login Testcase) is not runnable.....!!skip!!");
		}
		
		driver = openBrowserAndRunApplicationURL();

	}

	//@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test(dataProvider = "dataForVerifyErrAlertForSearchProductWithoutSearchKeyword", dataProviderClass = SearchDataProvider.class)
	public void verifyErrAlertForSearchProductWithoutSearchKeyword(HashMap<String, String> hMapErrAlertForSearchProductWithoutSearchKeyword) {
		if (hMapErrAlertForSearchProductWithoutSearchKeyword.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Search Data is not runnable.....!!skip!!");
		}
		
		homePage = new HomePage(driver);
		homePage.clickOnTheSearchBtn();
		System.out.println(homePage.getAlertText());
		Assert.assertEquals(homePage.getAlertText(), hMapErrAlertForSearchProductWithoutSearchKeyword.get("Alert"));
	}

	@Test(dataProvider = "dataForVerifyProductSearchFunOnHomePage", dataProviderClass = SearchDataProvider.class)
	public void verifyProductSearchFunOnHomePage(HashMap<String, String> hMapProductSearchFunOnHomePage) {
		if (hMapProductSearchFunOnHomePage.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Search Data is not runnable.....!!skip!!");
		}
		
		homePage = new HomePage(driver);
		homePage.enterProductDescInSearchBox(hMapProductSearchFunOnHomePage.get("Search"));
		homePage.clickOnTheSearchBtn();
		searchPage = new SearchPage(driver);
		Assert.assertTrue(searchPage.searchedProductPageVisibility());
	}

	@Test(dataProvider = "dataForVerifyProductSearchFunOnSearchPage", dataProviderClass = SearchDataProvider.class)
	public void verifyProductSearchFunOnSearchPage(HashMap<String, String> hMapProductSearchFunOnSearchPage) {
		if (hMapProductSearchFunOnSearchPage.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Search Data is not runnable.....!!skip!!");
		}
		
		homePage = new HomePage(driver);
		homePage.enterProductDescInSearchBox(hMapProductSearchFunOnSearchPage.get("Search1"));
		homePage.clickOnTheSearchBtn();
		searchPage = new SearchPage(driver);
		searchPage.searchProductThroughSearchInputField(hMapProductSearchFunOnSearchPage.get("Search2"));
		Assert.assertTrue(searchPage.searchedProductPageVisibility());
	}
	
	@Test (dataProvider = "dataForErrAlertForProductSearchFunOnSearchPage", dataProviderClass = SearchDataProvider.class)
	public void verifyErrAlertForProductSearchFunOnSearchPage(HashMap<String, String> hMapErrAlertForProductSearchFunOnSearchPage) {
		if (hMapErrAlertForProductSearchFunOnSearchPage.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Search Data is not runnable.....!!skip!!");
		}
		
		homePage = new HomePage(driver);
		homePage.enterProductDescInSearchBox(hMapErrAlertForProductSearchFunOnSearchPage.get("Search1"));
		homePage.clickOnTheSearchBtn();
		searchPage = new SearchPage(driver);
		searchPage.searchNothingThroughSearchInputField();
		System.out.println(searchPage.gettingErrAlertForSearchNothingThruSearchBoxOfSearchPage());
		
		String expectedResult = hMapErrAlertForProductSearchFunOnSearchPage.get("ExpectedResult");
		boolean convertedExpectedResult = false;
		if (expectedResult.equalsIgnoreCase("Fail")) {
			convertedExpectedResult = false;
		}else if (expectedResult.equalsIgnoreCase("Success")) {
			convertedExpectedResult = true;
		}
		
		String alertTxt = searchPage.gettingErrAlertForSearchNothingThruSearchBoxOfSearchPage();
		Assert.assertEquals(searchPage.isDisplayErrAlertForSearchNothingThruSearchBoxOfSearchPage(), convertedExpectedResult);
		//Assert.assertEquals(searchPage.gettingErrAlertForSearchNothingThruSearchBoxOfSearchPage(), hMapErrAlertForProductSearchFunOnSearchPage.get("Alert"));
	}
	
	@Test(dataProvider = "dataForVerifyAdvancedSearchCategoryWiseFunc", dataProviderClass = SearchDataProvider.class)
	public void verifyAdvancedSearchCategoryWiseFunc(HashMap<String, String>hMapAdvancedSearchCategoryWiseFunc) {
		if (hMapAdvancedSearchCategoryWiseFunc.get("Runmode").equalsIgnoreCase("N")) {
			throw new SkipException("Search Data is not runnable.....!!skip!!");
		}
		
		homePage = new HomePage(driver);
		homePage.enterProductDescInSearchBox(hMapAdvancedSearchCategoryWiseFunc.get("Search1"));
		homePage.clickOnTheSearchBtn();
		try {
		Thread.sleep(1000);
		}catch (Throwable e) {
			// TODO: handle exception
			System.out.print("Thread........!!");
		}
		searchPage = new SearchPage(driver);
		searchPage.checkAdvancedSearchChkBox();
		searchPage.selectCategoryDrop(hMapAdvancedSearchCategoryWiseFunc.get("Search2"));
		
	
		
		
		
		
	}

}
