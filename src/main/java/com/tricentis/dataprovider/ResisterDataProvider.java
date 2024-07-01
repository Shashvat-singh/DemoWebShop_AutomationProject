package com.tricentis.dataprovider;

import org.testng.annotations.DataProvider;

import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class ResisterDataProvider {
	MyXLSReader excelReader;
	@DataProvider
	public Object[][] dataOfRegisterWithMandFields(){
		Object[][] data = null;
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "ValidRegisterCredential", "Resister");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
	
	@DataProvider
	public Object[][] dataOfRegisterWithoutPasswords(){
		Object[][] data = null;
		try {
        excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "RegisterWithoutPasswords", "Resister");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
		
	}
	
	@DataProvider
	public Object[][] dataOfRegisterWithoutFirstname(){
		Object[][] data = null;
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "RegisterWithoutFirstname", "Resister");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
		
	}
	
	@DataProvider
	public Object[][] dataOfRegisterWithAlreadyUsedEmailAddress(){
		Object[][] data = null;
		try {
		excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
		data = DataUtil.getTestData(excelReader, "RegisterWithAlreadyUsedEmail", "Resister");
		}catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}
}
