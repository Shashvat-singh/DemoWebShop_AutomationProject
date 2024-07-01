package com.tricentis.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResisterPage {
	WebDriver driver;

	@FindBy(xpath = "//div[@class='gender']/input")
	private List<WebElement> rdo_Gender;
	@FindBy(id = "FirstName")
	private WebElement txt_FirstName;
	@FindBy(id = "LastName")
	private WebElement txt_LastName;
	@FindBy(id = "Email")
	private WebElement txt_Email;
	@FindBy(id = "Password")
	private WebElement txt_Password;
	@FindBy(id = "ConfirmPassword")
	private WebElement txt_ConPass;
	
	@FindBy(id = "register-button")
	private WebElement register_btn;
	
	
	@FindBy(xpath = "//span[@class='field-validation-error']/span[@for='FirstName']")
	private WebElement validationRemarkFirstName;
	
	@FindBy(xpath = "//div[@class='validation-summary-errors']/ul/li")
	private WebElement validationRemarkForAlreadyUsedEmail;
	
	@FindBy(xpath = "//span[@class='field-validation-error']/span[@for='Password']")
	private WebElement validationRemarkPassword;
	
	@FindBy(xpath = "//span[@class='field-validation-error']/span[@for='ConfirmPassword']")
	private WebElement validationRemarkConfPassword;
	
	

	public ResisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectRadioButtonByValue(String GenderValue) {
		for (WebElement radioButton : rdo_Gender) {
			if (radioButton.getAttribute("value").equals(GenderValue)) {
				radioButton.click();
				break;
			}
		}
	}
	
	public void enterFirstName(String FirstNameValue) {
		txt_FirstName.clear();
		txt_FirstName.sendKeys(FirstNameValue);
	}
	public void enterLastName(String LastNameValue) {
		txt_LastName.clear();
		txt_LastName.sendKeys(LastNameValue);
	}
	public void enterEmail(String Email) {
		txt_Email.clear();
		txt_Email.sendKeys(Email);
	}
	public void enterPassword(String Pass) {
		txt_Password.clear();
		txt_Password.sendKeys(Pass);
	}
	public void enterConfPass(String ConPass) {
		txt_ConPass.clear();
		txt_ConPass.sendKeys(ConPass);
	}
	public void clickOnResisterButton() {
		register_btn.click();
	}
	
	
	public String getValidationRemarkFirstName() {
		String validationFirstName = validationRemarkFirstName.getText();
		return validationFirstName;
	}
	public String getValidationRemarkForAlreadyUsedEmail() {
		String validationAlreadyUsedEmail = validationRemarkForAlreadyUsedEmail.getText();
		return validationAlreadyUsedEmail;
	}
	
	public String getValidationRemarkPassword() {
		String validationPassword = validationRemarkPassword.getText();
		return validationPassword;
	}
	public String getValidationRemarkConfPassword() {
		String validationConfPassword = validationRemarkConfPassword.getText();
		return validationConfPassword;
	}

}
