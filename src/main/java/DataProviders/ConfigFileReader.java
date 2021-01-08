package DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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
	
	public String getBrowserType() {
		String browser = property.getProperty("browser");
		return (browser == null)? "chrome" : browser;
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

}
