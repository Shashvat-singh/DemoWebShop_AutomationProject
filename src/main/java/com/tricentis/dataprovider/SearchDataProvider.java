package com.tricentis.dataprovider;

import org.testng.annotations.DataProvider;

import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class SearchDataProvider {

	MyXLSReader excelReader;
	
	@DataProvider 
	public Object dataForVerifyErrAlertForSearchProductWithoutSearchKeyword() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ErrAlertForSearchProductWithoutSearchKeyword", "Search");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	@DataProvider
	public Object dataForVerifyProductSearchFunOnHomePage() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ProductSearchFunOnHomePage", "Search");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	@DataProvider
	public Object dataForVerifyProductSearchFunOnSearchPage() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ProductSearchFunOnSearchPage", "Search");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	@DataProvider
	public Object dataForErrAlertForProductSearchFunOnSearchPage() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ErrAlertForProductSearchFunOnSearchPage", "Search");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	@DataProvider
	public Object dataForVerifyAdvancedSearchCategoryWiseFunc() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "AdvancedSearchCategoryWiseFunc", "Search");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
}
