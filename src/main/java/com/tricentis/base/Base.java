package com.tricentis.base;

import java.io.File;


import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tricentis.pageobjects.HomePage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	WebDriver driver;
	Properties propConfig;

	
	
	public WebDriver openBrowserAndRunApplicationURL() {
		propConfig = new Properties();
		File fileConfig = new File("src\\test\\resources\\config.properties");
		try {
			FileInputStream fisConfig = new FileInputStream(fileConfig);
			propConfig.load(fisConfig);
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		String browserName = propConfig.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromiumdriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			System.out.println("The desired browser is not found. Please mention available browser on this machine....");
		}

		

		if (propConfig.get("browserSize").equals("M")) {
			driver.manage().window().maximize();
		} else if (propConfig.get("browserSize").equals("m")) {
			driver.manage().window().minimize();
		} else if (propConfig.get("browserSize").equals("F")) {
			driver.manage().window().fullscreen();
		} else {
			System.out.println("Plese define the correct window size...!!!");
		}

		
		driver.manage().deleteAllCookies();
		driver.get(propConfig.getProperty("url"));
		
		return driver;
	}

	


}
