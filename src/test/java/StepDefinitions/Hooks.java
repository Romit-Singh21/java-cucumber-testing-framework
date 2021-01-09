package StepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import Managers.FileReaderManager;
import cucumberHelpers.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	TestContext testContext;
	
	public Hooks(TestContext context) {
		testContext = context;
	}
	
	@Before
	public void setup() {
		WebDriver driver = testContext.getBrowserManager().getDriver();
		driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitWaitTime(), TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(FileReaderManager.getInstance().getConfigReader().getPageLoadTimeout(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@After
	public void teardown() {
		testContext.getBrowserManager().getDriver().quit();
	}

}
