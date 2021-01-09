package cucumberHelpers;

import Managers.BrowserManager;
import Managers.PageObjectManagers;

public class TestContext {
	private BrowserManager browserManager;
	private PageObjectManagers pageOjectManager;
	
	public TestContext() {
		browserManager = new BrowserManager();
		pageOjectManager = new PageObjectManagers(browserManager.getDriver());
	}
	
	public BrowserManager getBrowserManager() {
		return browserManager;
	}
	
	public PageObjectManagers getPageObjectManagers() {
		return pageOjectManager;
	}

}
