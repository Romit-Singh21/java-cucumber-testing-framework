package StepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepsAddToCart {
	public WebDriver driver;
	public static final Logger logger = LogManager.getLogger();
	
	@Given("The user has opened the home page {string}")
	public void the_user_has_opened_the_home_page(String string) {
		logger.info("Opening the home page " + string);
		//WebDriverManager.chromedriver().setup();
		WebDriverManager.edgedriver().setup();
	    driver = new EdgeDriver();
	    driver.get(string);
	    driver.manage().window().maximize();
	    driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	    driver.manage().timeouts().implicitlyWait(30,  TimeUnit.SECONDS); 
	}
	
	@Given("The user is on application home page")
	public void the_user_is_on_application_home_page() {
		logger.info("Getting page title");
	    String pageTitle = driver.getTitle();
	    logger.info("Page title is: "+ pageTitle);
	    Assert.assertEquals("The page title " + pageTitle + "is incorrect", "My Store", pageTitle);
	}

	@And("The user clicks on {string} tab")
	public void the_user_clicks_on_tab(String string) {
		logger.info("Clicking on Best Seller tab");
		WebElement bestSellers = driver.findElement(By.xpath("//a[text()='Best Sellers']"));
		bestSellers.click();
	}

	@When("The user clicks on {string} for {string} items")
	public void the_user_clicks_on_for_items(String whichBtnToClick, String noOfItems) {
		logger.info("Adding firt two to cart");
		List<WebElement> productList = driver.findElements(By.xpath("//ul[@id='blockbestsellers']//div[@class='product-container']"));
		int counter = 0;
		logger.info("Total number of products:::" + productList.size());
		for(WebElement ele : productList) {
			Actions action = new Actions(driver);
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", ele);
			
//			WebElement productName= ele.findElement(By.className("product-name"));
			
//			logger.info("Scrolling now to :" + productName.getText());
//			jse.executeScript("arguments[0].scrollIntoView(true);", productName);
			
			logger.info("Moving to element " + ele.toString());
			action.moveToElement(ele).build().perform();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			WebElement addToCart = ele.findElement(By.className("right-block"));
			logger.info("clicking on element ::::::"+addToCart);
			addToCart.click();
			
			driver.findElement(By.xpath("//span[@title='Continue shopping']")).click();
			
			counter++;
			logger.info("Element number: " +counter);
			if(counter == 2) break;
		}		
	}

	@Then("The cart should have {string} items in it")
	public void the_cart_should_have_items_in_it(String string) {
		logger.info("Checking the cart");
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0,0)");
		WebElement shoppingCart = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
		Actions action = new Actions(driver);
		action.moveToElement(shoppingCart).build().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//List<WebElement> productList = driver.findElements(By.xpath("//span[@class='ajax_cart_quantity']"));
		List<WebElement> productList = driver.findElements(By.className("shopping_cart"));
		logger.info("Total number of elements " + productList.size());
		
		//"/.//" means "look under the selected element".
		String productsInCart = driver.findElement(By.xpath("//div[@class='shopping_cart']/.//span[starts-with(@class, 'ajax_cart_quantity')]")).getText();
		//String productsInCart = productList.get(0).getAttribute("innerHTML");
		logger.info("Products in cart:::" + productsInCart);
		Assert.assertEquals("Number of products not equal, Cart has :" + productsInCart , "2", productsInCart);
	}

}
