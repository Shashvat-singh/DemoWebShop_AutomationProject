package com.tricentis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {
	WebDriver driver;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@href ='/customer/info']")
	private WebElement userNameButtonAfterLogin;

	public boolean verifyLoginStatus(String value) {
		String userNameText = userNameButtonAfterLogin.getText();
		boolean LoginStatus;
		try {
			LoginStatus = userNameText.equalsIgnoreCase(value);
		} catch (Throwable e) {
			LoginStatus = false;
		}
		return LoginStatus;
	}

}
