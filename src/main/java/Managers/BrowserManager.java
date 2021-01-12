package Managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import Enums.BrowserTypes;
import Enums.EnvironmentTypes;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserManager {
	private WebDriver driver;
	private static EnvironmentTypes envType;
	private static BrowserTypes browserType;
	
	public BrowserManager() {
		envType = FileReaderManager.getInstance().getConfigReader().getEnvironmentType();
		browserType = FileReaderManager.getInstance().getConfigReader().getBrowserType();
	}
	
	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}
	
	private WebDriver createDriver() {
		
		switch(envType){
		case LOCAL: driver = createLocalDriver();
		break;
		case REMOTE: driver = createRemoteDriver();
		break;
		}
		return driver;
	}
	
	private WebDriver createLocalDriver() {
		switch(browserType) {
		case CHROME: WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		break;
		case EDGE: WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		break;
		case FIREFOX: WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		break;
		case INTERNETEXPLORER: WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		break;
		}
		return driver;
	}
	
	private WebDriver createRemoteDriver() {
		throw new RuntimeException("Implementation pending");
	}

}
