package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {
	
	WebDriver driver;
	
	@FindBy(id = "firstNameInput")
	private WebElement firstName;
	
	@FindBy(id = "lastNameInput")
	private WebElement lastName;
	
	@FindBy(id = "addressLine1Input")
	private WebElement Address;
	
	@FindBy(id = "provinceInput")
	private WebElement State;
	
	@FindBy(id = "postCodeInput")
	private WebElement PostalCode;
	
	@FindBy(id = "checkout-shipping-continue")
	private WebElement SubmitBtn;
	
	// Constructor to initialize the WebElement
	public CheckoutPage(WebDriver driver) {
		// Initialize the WebDriver and WebElements using PageFactory
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Method to fill in the shipping address details and submit the form
	public void fillShippingDetails(String fName,String LName,String address,String state,String postalcode) {
		firstName.sendKeys(fName);
		lastName.sendKeys(LName);
		Address.sendKeys(address);
		State.sendKeys(state);
		PostalCode.sendKeys(postalcode);
		SubmitBtn.click();
		
	}
	
	// Assertion to verify that the order was successfully placed
	public void verifyOrderConfirmation() {
		String expectedMsg = driver.findElement(By.xpath("//a[text()=\"StackDemo\"]")).getText();
		Assert.assertTrue(expectedMsg.contains("StackDemo"), "Form submission was not successful!");
		System.out.println(driver.findElement(By.id("confirmation-message")).getText());
	}
	

}
