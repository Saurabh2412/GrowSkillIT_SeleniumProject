package pageObjects;

import java.util.List;

import org.openqa.selenium.*;

public class FilterPage {
	
	WebDriver driver;
	
	public FilterPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By fromLabel = By.xpath("//label[text()='From']//preceding-sibling::input");
	By toLabel = By.xpath("//label[text()='To']//preceding-sibling::input");
	By tomorrowButton =By.xpath("//span[text()='Tomorrow']/parent::button");
	By ascending = By.xpath("(//*[contains(@class,'sortType')])[3]");
	By descending = By.xpath("(//*[contains(@class,'sortType')])[3]");
	By busCards=By.xpath("//li[contains(@class,'ListItem')]/li[@role='button']");
	By busTypes= By.xpath("//li[@role='button']//div[contains(@class,'bottomRow')]/p");
	By boardingTimes=By.xpath("//li[@role='button']//p[contains(@class,'boardingTime')]");
	By priceElements= By.xpath("//li[@role='button']//p[contains(@class,'finalFare')]");
	
	
	public WebElement getFromLabel() {
		return driver.findElement(this.fromLabel);
	}
	
	public WebElement getToLabel() {
		return driver.findElement(this.toLabel);
	}
	
	public WebElement getTomorrowButton() {
		return driver.findElement(this.tomorrowButton);
	}
	
	public WebElement getAscendingLabel() {
		return driver.findElement(this.ascending);
	}
	
	public WebElement getDescendingLabel() {
		return driver.findElement(this.descending);
	}
	
	public List<WebElement> getBusCards() {
		return driver.findElements(this.busCards);
	}
	
	public List<WebElement> getBusTypes() {
		return driver.findElements(this.busTypes);
	}
	
	public List<WebElement> getBoardingTimes() {
		return driver.findElements(this.boardingTimes);
	}
	
	public List<WebElement> getPriceElements() {
		return driver.findElements(this.priceElements);
	}
	
	

}
