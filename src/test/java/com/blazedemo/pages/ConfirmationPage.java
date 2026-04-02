package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {
	WebDriver driver;
	
	@FindBy(xpath="//h1") 
  private WebElement ConfirmationMsg;
	
	// Constructor
	public ConfirmationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	
	// Method to get the Confirmation Message
	public String getConfirmationMessage() {
		return ConfirmationMsg.getText();
	}

}
