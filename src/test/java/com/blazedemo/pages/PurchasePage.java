package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class PurchasePage {
	WebDriver driver;
	
	@FindBy(id="inputName") 								private WebElement name;
	@FindBy(id="address") 									private WebElement address;
	@FindBy(id="city") 										private WebElement city;
	@FindBy(id="state") 									private WebElement state;
	@FindBy(id="zipCode")									private  WebElement zipCode;
	@FindBy(id="cardType") 									private WebElement cardType;
	@FindBy(id="creditCardNumber") 							private WebElement CCNumber;
	@FindBy(id="nameOnCard")								private  WebElement nameOnCard;
	@FindBy(id="rememberMe") 								private WebElement rememberMe;
	@FindBy(xpath="//input[@class=\"btn btn-primary\"]") 	private WebElement purchaseFlight;
	
	// Constructor
	public PurchasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
	}
	
	// Method to fill the form and purchase the flight
	public void enterPassengerDetails(String uname, String uaddress, String ucity, String ustate, String uzipCode, String ucardType, String uCCNumber,String unameOnCard) {
		name.sendKeys(uname);
		address.sendKeys(uaddress);
		city.sendKeys(ucity);
		state.sendKeys(ustate);
		zipCode.sendKeys(uzipCode);
		Select Ctype = new Select(cardType);
		Ctype.selectByVisibleText(ucardType);
		CCNumber.sendKeys(uCCNumber);
		//Month.sendKeys(month);
		//Year.sendKeys(year);
		nameOnCard.sendKeys(unameOnCard);
		
	}
	
	// Method to click on Purchase Flight Button
	public void clickPurchaseFlight() {
		rememberMe.click();
		purchaseFlight.click();
		
		String nextpageUrl = driver.getCurrentUrl();
		System.out.println("Current URL is : " + nextpageUrl);
		Assert.assertTrue(nextpageUrl.contains("confirmation"),"Purchase Flight Failed!");
		System.out.println("Navigate to confirmation page Successfully!");
		//System.out.println("Successful message text is :"+driver.findElement(By.tagName("h1")).getText());
	}

}
