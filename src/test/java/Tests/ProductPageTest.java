package Tests;

import org.testng.annotations.Test;

public class ProductPageTest extends BaseTest {
  @Test
  public void testProductPage()  {
	 
	  login.clickSignIn();
	  login.selectusername();
	  login.selectpassword();
	  login.performlogin();
	  
	  product.getTotalProducts();
	  product.getAppProductDetail();
	 // product.clickOnProduct();
	  
  }
}
