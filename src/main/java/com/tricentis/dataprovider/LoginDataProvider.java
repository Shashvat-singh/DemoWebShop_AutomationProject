package com.tricentis.dataprovider;

import org.testng.annotations.DataProvider;

import com.tricentis.util.DataUtil;
import com.tricentis.util.MyXLSReader;

public class LoginDataProvider {
	MyXLSReader excelReader;
	@DataProvider
	public Object dataForLoginWithValidCredentials() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ValiLoginTestData", "Login");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

	@DataProvider
	public Object dataForLoginWithInvalidCredentials() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "InvalidCredentials", "Login");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

	@DataProvider
	public Object dataForLoginWithValidEmailInvalidPassword() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "ValidEmailInvalidPassword", "Login");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

	@DataProvider
	public Object dataForLoginWithInalidEmailValidPassword() {
		Object data = null;
		try {
			excelReader = new MyXLSReader("src\\test\\resources\\TestData.xlsx");
			data = DataUtil.getTestData(excelReader, "InalidEmailValidPassword", "Login");
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return data;
	}

}
