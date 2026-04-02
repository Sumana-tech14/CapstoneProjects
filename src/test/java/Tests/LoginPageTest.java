package Tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import Utils.ScreenshotUtils;

public class LoginPageTest extends BaseTest {
 
	@Test(priority=1)
	public void testInvalidUsernameAndPassorwd() throws InterruptedException {
		
		login.getAppUrl();
		login.clickSignIn();
		login.performlogin();
		Thread.sleep(2000);
		ScreenshotUtils.getScreenshot(driver,"EmptyLoginField");
		System.out.println(driver.findElement(By.xpath("//h3")).getText());
		}	
	
	@Test(priority=2)
  public void testInvaliduserName() throws InterruptedException {
	  
	  login.getAppUrl();
	  login.clickSignIn();
	  login.selectpassword();
	  login.performlogin();
	  Thread.sleep(2000);
	  ScreenshotUtils.getScreenshot(driver,"InvalidUserName");
	  System.out.println(driver.findElement(By.xpath("//h3")).getText());
	 
  }
  
  @Test(priority=3)
  public void testInvalidPassword() throws InterruptedException {
	  
	  login.getAppUrl();
	  login.clickSignIn();
	  login.selectusername();
	  login.performlogin();
	  Thread.sleep(2000);
	  ScreenshotUtils.getScreenshot(driver,"InvalidPassword");
	  System.out.println(driver.findElement(By.xpath("//h3[text()=\"Invalid Password\"]")).getText());
	  
  }
  
  @Test(priority=4)
  public void testValidLogin() {
	  
	  login.getAppUrl();
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  String expectedtext = driver.findElement(By.xpath("//span[text()=\"Logout\"]")).getText();
	  Assert.assertEquals(expectedtext, "Logout", "Login was not successful!");
	  System.out.println("Login was successful! Expected text is :  " + expectedtext);
	  
  }
  
  
  
  
  
  }

