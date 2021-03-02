package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utility.Log;

public class LandingPage extends BasePageObject {
	
	private WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Best Sellers']")
	private WebElement bestSellerBtn;
	
	@FindAll(@FindBy(xpath = "//ul[@id='blockbestsellers']//div[@class='product-container']"))
	private List<WebElement> bestSellerProductList;
	
	@FindBy(xpath = "//span[@title='Continue shopping']")
	private WebElement confiramtionWindowContinueShoppinfBtn;
	
	@FindBy(xpath = "//div[@class='shopping_cart']/.//span[starts-with(@class, 'ajax_cart_quantity')]")
	private WebElement numberOfProductsInCart;
	
	public void openLandingPage(String landingPage) {
		visit(landingPage);
	}
	
	public String getPageTitle() {
		Log.info("Getting title of the page");
		return getTitleOfPage();
	}
	
	public void clickOnBestSellerTab() {
		Log.info("Clicking on Best Seller button");
		clickOn(bestSellerBtn);
	}
	
	public int getTotalNumberOfBestSellerProducts() {
		int size = bestSellerProductList.size();
		Log.info("Getting best seller product list size as :" + size);
		return size;
	}
	
	public void pointToProductNumber(int num) {
		if(num > this.getTotalNumberOfBestSellerProducts()) throw new RuntimeException("Element requested to point is not available");
		WebElement element = bestSellerProductList.get(num);
		Log.info("moving to element: " + element.toString());
		pointToElement(element);
	}
	
	public void clickOnAddToCartOfBestSellerProductNo(int num) {
		if(num > this.getTotalNumberOfBestSellerProducts()) throw new RuntimeException("Element requested to point is not available");
		Log.info("Adding element to cart");
		WebElement product = bestSellerProductList.get(num);
		WebElement addToCart = product.findElement(By.className("right-block"));		
		clickOn(addToCart);
	}
	
	public void clickOnContinueShopping() {
		Log.info("Clicking on continue shopping");
		clickOn(confiramtionWindowContinueShoppinfBtn);
	}
	
	public String getNumberOfProductsInCart() {
		//"/.//" means "look under the selected element".
		Log.info("Getting number of products in cart");
		scrollToElement(numberOfProductsInCart);
		return numberOfProductsInCart.getText();
	}

}
