package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[text()='Best Sellers']")
	private WebElement bestSellerBtn;
	
	@FindAll(@FindBy(xpath = "//ul[@id='blockbestsellers']//div[@class='product-container']"))
	private List<WebElement> bestSellerProductList;
	
	@FindBy(xpath = "//span[@title='Continue shopping']")
	private WebElement confiramtionWindowContinueShoppinfBtn;
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void clickOnBestSellerTab() {
		this.waitTillElementAvailable(bestSellerBtn);
		bestSellerBtn.click();
	}
	
	public int getTotalNumberOfBestSellerProducts() {
		return bestSellerProductList.size();
	}
	
	public void pointToProductNumber(int num) {
		if(num > this.getTotalNumberOfBestSellerProducts()) throw new RuntimeException("Element requested to point is not available");
		WebElement element = bestSellerProductList.get(num);
		this.scrollToElement(element);
		this.pointToElement(element);
	}
	
	public void clickOnAddToCartOfBestSellerProductNo(int num) {
		if(num > this.getTotalNumberOfBestSellerProducts()) throw new RuntimeException("Element requested to point is not available");
		WebElement product = bestSellerProductList.get(num);
		WebElement addToCart = product.findElement(By.className("right-block"));
		
		this.waitTillElementAvailable(addToCart);
		addToCart.click();
	}
	
	public void clickOnContinueShopping() {
		this.waitTillElementAvailable(confiramtionWindowContinueShoppinfBtn);
		confiramtionWindowContinueShoppinfBtn.click();
	}
	
	private void waitTillElementAvailable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(this.driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	private void scrollToElement(WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void pointToElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

}
