package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CartPage {

	WebDriver driver;
	
	@FindBy(xpath = "//span[@class=\"bag bag--float-cart-closed\"]")
	private WebElement cartIcon;
	
	@FindBy(xpath = "//div[@class=\"buy-btn\"]")
	private WebElement continueShoppingBtn;
	
	@FindBy(xpath = "//div[text()=\"Checkout\"]")
	private WebElement checkoutBtn;
	
	// Locators for the products 
	@FindBy(xpath = "//p[@class=\"shelf-item__title\"]")
	private List<WebElement> allProductNames;
	
	// Locator for the specific product to be added to the cart
	@FindBy(xpath = "(//div[@class=\"shelf-item__buy-btn\"])[3]")
	private WebElement iPhone12ProProduct;
	
	@FindBy(xpath = "(//div[@class=\"shelf-item__buy-btn\"])[12]")
	private WebElement GalaxyS20UltraProduct;
	
	@FindBy(xpath = "(//div[@class=\"shelf-item__buy-btn\"])[10]")
	private WebElement GalaxyS20Product;
	
	@FindBy(xpath = "(//div[@class=\"shelf-item__buy-btn\"])[17]")
	private WebElement Pixel4Product;
	
	// Locator for the list of all products 
	@FindBy(xpath = "//div[@class=\"shelf-container\"]")
	private List<WebElement> allProductList;
	
	//Locator for number of products in the cart
	@FindBy(xpath= "//span[@class=\"bag__quantity\"]")
	private WebElement numberOfProductsInCart;
	
	// Locator for remove button in the cart
	@FindBy(xpath = "(//div[@class=\"shelf-item__del\"])[2]")
	private WebElement removeBtn;
	
	// Constructor to initialize the WebElements
	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	// Method to click on the cart icon
	public void clickCartIcon() {
		cartIcon.click();
	}
	// Method to click on the Continue Shopping button
	public void clickContinueShopping() {
		continueShoppingBtn.click();
		System.out.println("Number of products in the list : " +numberOfProductsInCart.getText());
		String cartMsg = driver.findElement(By.xpath("//p[@class=\"shelf-empty\"]")).getText();
		System.out.println("Cart Message :" +cartMsg);
	}
	
	// Method to click on a specific product based on its name
		
	public void checkSingleProduct(String productName) {
			for (WebElement i : allProductList) {
				if(i.getText().contains(productName)) {
					break;
					
				}
			}
			System.out.println("Product name is : "+productName);
			switch(productName) {
			case "iPhone 12 Pro":
				iPhone12ProProduct.click();
				break;
			case "Galaxy S20 Ultra":
				GalaxyS20UltraProduct.click();
				break;
			case "Galaxy S20":
				GalaxyS20Product.click();
				break;
			case "Pixel 4":
				Pixel4Product.click();
				break;	
			}
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
		// Method to check the number of products in the cart
		public void checkNumberOfProductsInCart() {
			System.out.println("Number of products in the list : " +numberOfProductsInCart.getText());
			
			if(numberOfProductsInCart.getText().equals("0")) {
				System.out.println("There is no products in the cart! Continue shopping to add products to the cart!");
			}
			else if(numberOfProductsInCart.getText().equals("1"))
			{
			System.out.println("There is only one product in the cart!");
			}
			else {
			System.out.println("There are multiple products in the cart!");
			}
			}
	
	
	// Method to remove the product from the cart and verify that the cart is empty	
		public void removeProductFromCart() {
			if(numberOfProductsInCart.getText()!="0") {
				String NumberOfProductsBeforeRemoving = numberOfProductsInCart.getText();
				System.out.println("Number of products in the cart before removing : " +NumberOfProductsBeforeRemoving);
				removeBtn.click();
				String NumberOfProductsAfterRemoving = numberOfProductsInCart.getText();
				System.out.println("Number of products in the cart after removing : " +NumberOfProductsAfterRemoving);
			}else {
				System.out.println("There is no products in the cart! Continue shopping to add products to the cart!");
			}	
			
		}
	public void clickCheckout() {
		checkoutBtn.click();
		String checkoutMsg = driver.findElement(By.xpath("//legend[text()='Shipping Address']")).getText();
		Assert.assertEquals(checkoutMsg, "Shipping Address", "Checkout page was not successfully landed!");
		System.out.println("Successfully landed on the checkout page! Expected text is : " +checkoutMsg);
	}
}
