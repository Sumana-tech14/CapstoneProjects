package Tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductPage;
import Pages.CheckoutPage;

public class BaseTest {
	
	public static WebDriver driver;
	
	public LoginPage login;
	public ProductPage product;
	public CartPage cart;
	public CheckoutPage checkout;
	//public ScreenshotUtils screenshot;
	
	@BeforeMethod
	public void setup() {
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://bstackdemo.com/");
		
		// Initialize page objects
		login = new LoginPage(driver);
		product = new ProductPage(driver);
		cart = new CartPage(driver);
		checkout = new CheckoutPage(driver);
		//screenshot = new ScreenshotUtils();
	}
	
 @AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		} 
	}
	
}
