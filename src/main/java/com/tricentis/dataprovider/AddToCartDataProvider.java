package com.tricentis.dataprovider;

import org.testng.annotations.DataProvider;

import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class AddToCartDataProvider {
	MyXLSReader excelReader;
	
	@DataProvider
	public Object[][] dataForVerifyAddButtonIsVisibleOnProduct() {
		Object[][] data = null;
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "verifyAddButtonIsVisibleOnProduct", "AddToCart");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return data;
	}
	
	@DataProvider
	public Object[][] dataSuccessMsgForAddToCartProd() {
		Object[][] data = null;
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "successMsgForAddToCartProd", "AddToCart");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
}
