package Managers;

import org.openqa.selenium.WebDriver;

import Pages.LandingPage;

public class PageObjectManagers {
	private WebDriver driver;
	private LandingPage landingPage;
	
	public PageObjectManagers(WebDriver driver) {
		this.driver = driver;
	}
	
	public LandingPage getLandingPage() {
		return (landingPage == null)? landingPage = new LandingPage(this.driver) : landingPage ;
	}

}
