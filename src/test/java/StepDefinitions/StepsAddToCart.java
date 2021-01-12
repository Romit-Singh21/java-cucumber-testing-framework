package StepDefinitions;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import Managers.FileReaderManager;
import Pages.LandingPage;
import cucumberHelpers.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepsAddToCart {
	
	public TestContext testContext;
	public LandingPage landingPage;
	public static final Logger logger = LogManager.getLogger();
	
	public StepsAddToCart(TestContext context) {
		testContext = context;
		landingPage = testContext.getPageObjectManagers().getLandingPage();
	}
	
	@Given("The user has opened the home page {string}")
	public void the_user_has_opened_the_home_page(String string) {
		logger.info("Opening the home page " + string);
		logger.info("The browser type from config file is: " + FileReaderManager.getInstance().getConfigReader().getBrowserType());
		landingPage.openLandingPage(string);
	}
	
	@Given("The user is on application home page")
	public void the_user_is_on_application_home_page() {
		logger.info("Getting page title");
		String pageTitle = landingPage.getPageTitle();
	    logger.info("Page title is: "+ pageTitle);
	    Assert.assertEquals("The page title " + pageTitle + "is incorrect", "My Store", pageTitle);
	}

	@And("The user clicks on {string} tab")
	public void the_user_clicks_on_tab(String string) {
		logger.info("Clicking on Best Seller tab");
		landingPage.clickOnBestSellerTab();
	}

	@When("The user clicks on {string} for {string} items")
	public void the_user_clicks_on_for_items(String whichBtnToClick, String noOfItems) {
		logger.info("Adding firt two to cart");
		logger.info("Total number of products:::" + landingPage.getTotalNumberOfBestSellerProducts());
		for(int i=1; i<= Integer.parseInt(noOfItems); i++){
			landingPage.pointToProductNumber(i);
			logger.info("Clicking on product number: " + i);
			landingPage.clickOnAddToCartOfBestSellerProductNo(i);
			landingPage.clickOnContinueShopping();
			
		}	
	}

	@Then("The cart should have {string} items in it")
	public void the_cart_should_have_items_in_it(String string) {
		logger.info("Checking the cart");
		String productsInCart = landingPage.getNumberOfProductsInCart();
		logger.info("Products in cart:::" + productsInCart);
		Assert.assertEquals("Number of products not equal, Cart has :" + productsInCart , "2", productsInCart);
	}

}
