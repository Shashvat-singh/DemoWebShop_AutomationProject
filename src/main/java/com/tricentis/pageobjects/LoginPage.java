package com.tricentis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage {
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "Email")
	private WebElement emailField;
	@FindBy(id = "Password")
	private WebElement passwordField;
	@FindBy(id = "RememberMe")
	private WebElement rememberMeCheckbox;
	@FindBy(xpath = "//a[text()='Forgot password?']")
	private WebElement forgotPassword;
	@FindBy(xpath = "//input[@class='button-1 login-button' and @value='Log in']")
	private WebElement loginButton;
	
	@FindBy (xpath = "//div[@class='validation-summary-errors']/span")
	private WebElement loginMsgError01;
	@FindBy (xpath = "//div[@class=\"validation-summary-errors\"]/ul/li")
	private WebElement loginMsgError02;
	
	
	@FindBy(xpath = "//a[@href ='/customer/info']")
	private WebElement userNameButtonAfterLogin;
	
	
	public void sendTextToEmailField(String emailText) {
		emailField.clear();
		emailField.sendKeys(emailText);
	}
	
	public void sendTextToPasswordField(String passwordText) {
		passwordField.clear();
		passwordField.sendKeys(passwordText);
	}
	
	public void checkingTheRememberMeCheckBox() {
		rememberMeCheckbox.click();
	}
	
	public ForgotPasswordPage clickOntheForgotPassword() {
		forgotPassword.click();
		return new ForgotPasswordPage(driver);
	}
	
	public HomePage clickOnLoginButton() {
		loginButton.click();
		return new HomePage(driver);
	}
	
	public AccountPage clickOnUserNameButtonAfterLogin() {
		userNameButtonAfterLogin.click();
		return new AccountPage(driver);
	}
	
	public String validationOneForLoginWithValidEmailInvalidPass() {
		return loginMsgError01.getText();
	}
	
	public String validationSecoundForLoginWithValidEmailInvalidPass() {
		return loginMsgError02.getText();
	}
	
	
	

}
