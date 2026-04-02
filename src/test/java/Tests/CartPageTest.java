package Tests;


import org.testng.annotations.Test;

import Utils.ScreenshotUtils;

public class CartPageTest extends BaseTest {
  @Test
  public void testforEmptyCart() throws InterruptedException {
	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
	  cart.clickCartIcon();
	  cart.clickContinueShopping();
	  Thread.sleep(2000);
	  ScreenshotUtils.getScreenshot(driver,"EmptyCart");
  }
  
  @Test
  public void testforSingleProduct() {
  	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
	  cart.checkSingleProduct("iPhone 12 Pro");
	  cart.checkNumberOfProductsInCart();
	  cart.clickCheckout();
  }
  
  @Test
  public void testforMultipleProducts() throws InterruptedException{
	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
		cart.checkSingleProduct("iPhone 12 Pro");
		cart.checkSingleProduct("Galaxy S20");
		cart.checkSingleProduct("Pixel 4");
		cart.checkNumberOfProductsInCart();
		cart.clickCheckout();
  }
  
  @Test
  public void testforRemoveProduct() throws InterruptedException {
	  
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
		cart.checkSingleProduct("iPhone 12 Pro");
		cart.checkSingleProduct("Galaxy S20");
		cart.checkSingleProduct("Pixel 4");
		cart.removeProductFromCart();
		Thread.sleep(2000);
		ScreenshotUtils.getScreenshot(driver,"RemoveProduct");
		System.out.println("Product Successfully removed from cart");
		cart.clickCheckout();
  }
}
