package Pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Log;

public class BasePageObject {
	private WebDriver driver;
	
	public BasePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void visit(String url) {
		Log.info("Opening site: " +url);
		driver.get(url);
	}
	
	public String getPageTitle() {
		Log.info("Getting page title");
		return driver.getTitle();
	}
	
	public void clickOn(WebElement element) {
		Log.info("Clicking on element " + element.toString());
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		scrollToElement(element);
		element.click();	
	}
	
	public void scrollToElement(WebElement element) {
		Log.info("Scrolling to element " + element);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void pointToElement(WebElement element) {
		Log.info("Moving cursor to element : " + element);
		scrollToElement(element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

}
