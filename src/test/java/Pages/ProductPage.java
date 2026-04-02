package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	WebDriver driver;
	
	@FindBy(xpath = "//div[@class=\"shelf-container\"]")
	private List<WebElement> allProductList;
	
	@FindBy(xpath = "//p[@class=\"shelf-item__title\"]")
	private List<WebElement> allProductNames;
	
	//@FindBy(xpath = "(//div[@class=\"shelf-item__buy-btn\"])")
//	private WebElement specificProduct;
	
	// Constructor to initialize the WebElements
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		
	}
	
	// Method to get the total number of products displayed on the product page
	public void getTotalProducts() {
		int totalProducts = allProductList.size();
		System.out.println("Total number of products displayed on the product page: " +totalProducts);
	}

	// Method to get the details of each product displayed on the product page
	public void getAppProductDetail() {
		
		for(WebElement i : allProductList) {
		
			System.out.println(i.getText());
		}
		System.out.println("-----------------------------------------------------------");
	}
}
	
/*	// Method to click on a specific product based on its name
	public void clickOnProduct(String productName) {
		for (WebElement i : allProductList) {
			if(i.getText().contains(productName)) {
				break;
				
			}
		}
		System.out.println("Product name is : "+productName);
		specificProduct.click();
		}
		*/
	


