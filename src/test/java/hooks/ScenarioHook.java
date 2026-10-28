package hooks;


import org.openqa.selenium.chrome.*;
import org.openqa.selenium.edge.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.*;
import Base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class ScenarioHook extends Base {

	@Before
	public void setUp() throws Exception {

		RemoteWebDriver driver;

		String browser = getProperty("browser");
		System.out.println("java -jar src/main/resources/selenium-server-4.51.0.jar standalone");

		if (browser.equalsIgnoreCase("chrome")) {
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.setCapability("platformName", "Windows");
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),options);
		} 
		else if (browser.equalsIgnoreCase("firefox")) {
			//driver = new FirefoxDriver();
			FirefoxOptions options = new FirefoxOptions();
			options.setCapability("platformName", "Windows");
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),options);
		} 
		else if (browser.equalsIgnoreCase("edge")) {
			//driver = new EdgeDriver();
			EdgeOptions options = new EdgeOptions();
			options.setCapability("platformName", "Windows");
			driver=new RemoteWebDriver(new URL("http://localhost:4444"),options);
		} else {
			throw new RuntimeException("Invalid browser name: " + browser);
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		setDriver(driver);

	}

	@After
	public void tearDown(Scenario scenario) {
		
		if(!scenario.isFailed())
		{
			byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
		}	

		getDriver().quit();

	}

}
