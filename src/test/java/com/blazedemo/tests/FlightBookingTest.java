package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.ScreenshotUtil;

public class FlightBookingTest extends BaseTest {
	
	// This test method is designed to book a flight on the BlazeDemo website.	
	 // Using DataProvider to run the test with multiple sets of data
	
	@Test(dataProvider="userData",dataProviderClass=com.blazedemo.utils.DataProviderClass.class) 
	 public void testBookingflight(String name,String address,String city,String state,String zipCode,String cardType,String CCNumber,String nameOnCard) 
	 {	
			System.out.println("Starting the Flight Booking Test...");
			// Print the test data for the current iteration
			System.out.println("Test executing for user : " + name);
			System.out.println("--------------------------------------------------");
			// Create an object of HomePage and perform actions
			HomePage home = new HomePage(driver);
			
			// Verify the application URL
			String actUrl = home.getAppURL();
			Assert.assertTrue(actUrl.contains("blazedemo"), "Application URL is incorrect!");
			System.out.println("Application URL is correct! : " + actUrl);			
			
			// Select Departure and Destination Cities and click on Find Flights
			home.chooseDepartureCity("Boston");
			home.chooseDestinationCity("New York");			
			home.clickFindFlights();
			home.verifyNavigateToSelectFlightPage();
			
			// Create an object of ReservePage and perform actions
			ReservePage reserve = new ReservePage(driver);
			reserve.selectFlight();
			reserve.verifyNavigationToPurchasePage();

			// Print the test data for the current iteration
			System.out.println("UserName is  : " + name);
			System.out.println("--------------------------------------------------");

			// Create an object of PurchasePage and perform actions
			PurchasePage purchase = new PurchasePage(driver);
			purchase.enterPassengerDetails(name, address, city, state, zipCode, cardType, CCNumber, nameOnCard);
			
			//capture screenshot of the Purchase page after filling details and before clicking on Purchase Flight
			ScreenshotUtil.getScreenshot(driver, "PurchasePage_AfterClickingPurchaseFlight");
			
			purchase.clickPurchaseFlight();
			

			// create an object of ConfirmationPage and perform actions
			ConfirmationPage confirm = new ConfirmationPage(driver);
			String Message = confirm.getConfirmationMessage();
			System.out.println("Confirmation Message is : " + Message);
			Assert.assertTrue(Message.contains("Thank you"), " Flight Booking Failed!");
			System.out.println("Flight Booking Test Passed Successfully!");
			
			// Capture screenshot of the Confirmation page
			ScreenshotUtil.getScreenshot(driver, "ConfirmationPage");
			System.out.println("--------------------------------------------------");
  
  }
  
}

