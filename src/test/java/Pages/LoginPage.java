package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {
	
	WebDriver driver;
	
	// WebElements with locators for the login page
	@FindBy(xpath = "//span[@id='signin']") 
	private WebElement SignIn;
	
	@FindBy(xpath = "//div[text()='Select Username']")
	private WebElement SelectUsername;
	
	@FindBy(xpath = "//div[text()='demouser']")
	private WebElement userName;
	
	@FindBy(xpath="//div[text()='Select Password']")
	private WebElement SelectPassword;
	
	@FindBy(xpath = "//div[text()='testingisfun99']")
	private WebElement password;
	
	@FindBy(id = "login-btn")
	private WebElement LoginBtn;
	
	// Constructor to initialize the WebElements
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	// Method to get the current URL of the application
	public void getAppUrl() {
		String currentUrl =  driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "https://bstackdemo.com/", "Application URL is incorrect!");
		System.out.println("Application URL is correct!" + currentUrl);
	}
	
	// Method to perform SignIn action
	public void clickSignIn() {
		SignIn.click();
		//String expectedUrl = driver.getCurrentUrl();
		Assert.assertTrue(SelectUsername.isDisplayed(), "SignIn Button is not working");
		System.out.println("Successfully landed on the SignIn page");
		
	} 
	
	//Method to select username
	public void selectusername() {
		//SignIn.click();
		SelectUsername.click();
		userName.click();
		
	}

	// Method to select password
	public void selectpassword() {
		SelectPassword.click();
		password.click();
	}
	
	// Method to perform login action
	public void performlogin() {
		LoginBtn.click();		
		
	}
	
}
