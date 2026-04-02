package com.blazedemo.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class HomePage {
private	WebDriver driver;
	
	//Locators
	//Departure City Dropdown
	@FindBy(name="fromPort")
private	WebElement depCity;
	
	// Destination City DropDown
	@FindBy(name="toPort")
private	WebElement desCity;
	
	// Find Flight Button
	@FindBy(xpath="//input[@value=\"Find Flights\"]")
private	WebElement findFlights;
	
	// Constructor
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	// Methods
	// Method to get the current URL of the application
	public String getAppURL() {
		return driver.getCurrentUrl();
		
	}
		
	// Method to get Departure City
		public void chooseDepartureCity(String fromCity) {
		Select select = new Select(depCity);
		List<WebElement>alldepCities = select.getOptions();
		System.out.println("Total number of Departure Cities are : "+alldepCities.size());
		
		for(WebElement city:alldepCities) {
			System.out.println((city.getText()));
			if(city.getText().contains(fromCity)){
				city.click();
				break;
			}
		}
		System.out.println("---------------------------------------------");
	}
	
	// Method to get Destination City	
		public void chooseDestinationCity(String toCity) {
			Select select = new Select(desCity);
			List<WebElement>alldesCities=select.getOptions();
		 	System.out.println("Total number of Destination Cities are : "+alldesCities.size());	
			
		 	for(WebElement city:alldesCities) {
				System.out.println((city.getText()));
				if(city.getText().contains(toCity)){
					city.click();
					break;
				}
			}
		 	System.out.println("---------------------------------------------");
		}
				
		// Method to click on Find Flights Button
		public void clickFindFlights() {
			findFlights.click();
			
		}	
		
		// Method to verify navigation to Select Flight Page
		public void verifyNavigateToSelectFlightPage() {
			String currentUrl = driver.getCurrentUrl();
			System.out.println("Current URL is : " + currentUrl);
			Assert.assertTrue(currentUrl.contains("reserve"), "Navigation to Select Flight Page failed.");
			System.out.println("Successfully navigated to Select Flight Page."); 
			System.out.println("--------------------------------------------------------");
		
		}

		
}


