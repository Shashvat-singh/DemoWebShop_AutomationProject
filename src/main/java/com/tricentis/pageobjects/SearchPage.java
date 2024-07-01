package com.tricentis.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SearchPage {
	WebDriver driver;

	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='page search-page']")
	private WebElement searchedProductPage;
	@FindBy(id = "Q")
	private WebElement searchKeywordInputField;
	@FindBy(xpath = "//div[@class='buttons']//input[@type='submit' and @value='Search']")
	private WebElement searchBtnOnSearchPage;
	@FindBy(id = "As")
	private WebElement advancedSearchChkBox;
	@FindBy (xpath = "//div[@class='search-results']/strong")
	private WebElement errAlertForSearchNothingThruSearchBoxOfSearchPage;
	@FindBy (id = "Cid")
	private WebElement categoryDrop;

	public boolean searchedProductPageVisibility() {
		return searchedProductPage.isDisplayed();
	}
	
	public void searchProductThroughSearchInputField(String value) {
		searchKeywordInputField.clear();
		searchKeywordInputField.sendKeys(value);
		searchBtnOnSearchPage.click();
		
	}
	public void searchNothingThroughSearchInputField() {
		searchKeywordInputField.clear();
		searchBtnOnSearchPage.click();
		
	}
	
	public void checkAdvancedSearchChkBox() {
		advancedSearchChkBox.click();
	}
	
	public String gettingErrAlertForSearchNothingThruSearchBoxOfSearchPage() {
		return errAlertForSearchNothingThruSearchBoxOfSearchPage.getText();
	}
	
	public boolean isDisplayErrAlertForSearchNothingThruSearchBoxOfSearchPage() {
		return errAlertForSearchNothingThruSearchBoxOfSearchPage.isDisplayed();
	}
	
	public  void selectCategoryDrop(String value) {
		Select selectCatDrop = new Select(categoryDrop);
		//selectCatDrop.selectByValue(value);
		selectCatDrop.selectByVisibleText(value);
		
	}
	
	
}
