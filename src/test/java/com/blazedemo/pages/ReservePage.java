package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class ReservePage {
	WebDriver driver;
	
	// Locator for Choose Flight Button
	@FindBy(xpath="(//input[@class=\"btn btn-small\"])[2]")
	private WebElement chooseFlight;

	// Constructor
	public ReservePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	// Method to click on Choose Flight Button
	public void selectFlight() {
		chooseFlight.click();
	}
	
	// Method to verify navigation to Purchase Page
	public void verifyNavigationToPurchasePage() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String partialUrl = driver.getCurrentUrl();
	System.out.println("Current URL is : " + partialUrl);
	
	Assert.assertTrue(partialUrl.contains("purchase"),"Navigate to Flight Purchase Page Failed!");
	System.out.println("Successfully navigated to Flight Purchase Page!");
	System.out.println("---------------------------------------------");
	
	
}
}