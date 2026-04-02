package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Utils.ScreenshotUtils;

public class CheckoutPageTest extends BaseTest {
  @Test(priority=2)
  public void testforValidInputs() {
	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
	  cart.checkSingleProduct("iPhone 12 Pro");
	  cart.checkSingleProduct("Pixel 4");
	  cart.clickCheckout();
	  
	  checkout.fillShippingDetails("John","Smith","Main street","NY","40004");  
	 checkout.verifyOrderConfirmation();
	 ScreenshotUtils.getScreenshot(driver,"OrderPlaced");
  }
  
  @Test(priority=1)
  public void testMissingDetails() throws InterruptedException {
	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
	  cart.checkSingleProduct("iPhone 12 Pro");
	  cart.checkSingleProduct("Pixel 4");
	  cart.clickCheckout();
	  
	  checkout.fillShippingDetails("John","Smith","","NY","40004");
	  Thread.sleep(2000);
	  ScreenshotUtils.getScreenshot(driver,"EmptyField");
	  String expectedUrl = driver.getCurrentUrl();
	  Assert.assertTrue(expectedUrl.contains("checkout"), "Address error message is not displayed!");
	  System.out.println("Please fill out Address field");
  }
  
  
}
