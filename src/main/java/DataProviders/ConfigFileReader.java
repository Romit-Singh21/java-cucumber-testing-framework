package DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import Enums.BrowserTypes;
import Enums.EnvironmentTypes;

public class ConfigFileReader {
	private Properties property;
	private static final String porpertyLocation = "Configs/Configuration.properties";
	public ConfigFileReader() {
		try {
			FileInputStream fs = new FileInputStream(new File(porpertyLocation));
			property = new Properties();
			property.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BrowserTypes getBrowserType() {
		String browser = property.getProperty("browser");
		if(browser == null || browser.equalsIgnoreCase("chrome")) return BrowserTypes.CHROME;
		else if(browser.equalsIgnoreCase("FireFox")) return BrowserTypes.FIREFOX;
		else if(browser.equalsIgnoreCase("Edge")) return BrowserTypes.EDGE;
		else if(browser.equalsIgnoreCase("internetexplorer")) return BrowserTypes.INTERNETEXPLORER;
		else throw new RuntimeException("Unsupported Browser Type " + browser);
	}
	
	public Long getImplicitWaitTime() {
		String implicitlyWait = property.getProperty("implicitlyWait");
		if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
		else throw new RuntimeException("Implicit wait value not defined");
	}
	
	public Long getPageLoadTimeout() {
		String pageLoadTimeout = property.getProperty("pageLoadTime");
		if(pageLoadTimeout != null) return Long.parseLong(pageLoadTimeout);
		else throw new RuntimeException("PageLoad timeout value not defined");
	}
	
	public EnvironmentTypes getEnvironmentType() {
		String environmentType = property.getProperty("environment");
		
		if(environmentType.equalsIgnoreCase("local")) return EnvironmentTypes.LOCAL;
		else if(environmentType.equalsIgnoreCase("remote")) return EnvironmentTypes.REMOTE;
		else throw new RuntimeException("Environment not specified");
	}
	

}
