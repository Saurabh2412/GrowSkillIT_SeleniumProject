package Base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Base {
	
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	private static Properties prop;
	
	static {
		try {
		
			String path = System.getProperty("user.dir") + "\\src\\main\\java\\data.properties";
			FileInputStream fis = new 
					FileInputStream(path);
			prop = new Properties();
			prop.load(fis);
		
		}catch(Exception e) {
			throw new RuntimeException("Failed to load properties file: " + e.getMessage());
		}
	}
	
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	public void setDriver(WebDriver driver) {
		Base.driver.set(driver);
	}

}
