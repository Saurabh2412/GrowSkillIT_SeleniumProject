package stepDefinations;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.Filer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.Base;
import io.cucumber.java.en.*;
import pageObjects.FilterPage;
import utils.DateTimeUtil;

public class A_validateFilterFunctionalityStep extends Base {

	FilterPage filterPage;
	
	private static final Logger log = LogManager.getLogger(A_validateFilterFunctionalityStep.class);
	
	@Given("user is on RedBus homepage") 
	public void user_is_on_red_bus_homepage() {
		
		try {
		 
		 getDriver().get(getProperty("url"));
		 
		 
		 log.debug("RedBus homepage launched");
		 
		 getDriver().manage().window().maximize();
		 log.debug("Browser got maximized");
		 
		 
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
		
	    
	}
	
	@When("user searches buses from {string} to {string} for tomorrow")
	public void user_searches_buses_from_to_for_tomorrow(String From, String To)  {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
		 
		 
		 filterPage = new FilterPage(getDriver());
		 
		 WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(4));
		 
		 this.filterPage.getFromLabel().sendKeys(From);
		 
		 //getDriver().findElement(By.xpath("//label[text()='From']//preceding-sibling::input")).sendKeys(From);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(normalize-space(text()), '" + From + "')])[1]"))).click();
		 
		 //getDriver().findElement(By.xpath("(//*[contains(normalize-space(text()), '" + From + "')])[1]")).click();
		 log.debug("From location selected: " + From);
		 
		 this.filterPage.getToLabel().sendKeys(To);
		 //getDriver().findElement(By.xpath("//label[text()='To']//preceding-sibling::input")).sendKeys(To);
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[contains(normalize-space(text()), '" + To + "')])[1]"))).click();
		 //getDriver().findElement(By.xpath("(//*[contains(normalize-space(text()), '" + To + "')])[1]")).click();
		 log.debug("To location selected: " + To);
		 
		 
		 this.filterPage.getTomorrowButton().click();
		 //getDriver().findElement(By.xpath("//span[text()='Tomorrow']/parent::button")).click();
		 log.debug("Tomorrow button clicked");
		 
		 
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
	    
	}
	
	@When("user selects {string} and {string} and {string} filters")
	public void user_selects_and_and_filters(String ac, String sleeper, String deptime)  {
	    // Write code here that turns the phrase above into concrete actions
		
		
		try {
			
		
		WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(4));
			
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				(By.xpath("(//div[@data-autoid='desktopTopFilters']//div[starts-with(normalize-space(.),'"+ac+"')])[1]")))).click();
		
		log.debug("A/C filter selected: " + ac);
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				(By.xpath("(//div[@data-autoid='desktopTopFilters']//div[starts-with(normalize-space(.),'"+sleeper+"')])[1]")))).click();
		//getDriver().findElement(By.xpath("(//div[@data-autoid='desktopTopFilters']//div[starts-with(normalize-space(.),'"+sleeper+"')])[1]")).click();
		log.debug("Sleeper filter selected: " + sleeper);
		
		getDriver().findElement(By.xpath("(//div[@data-autoid='departureTime']//div[starts-with(normalize-space(.), 'Departure time from source')])[1]")).click();
		log.debug("Departure time dropdown clicked");
		
		
		
		if(getDriver().findElement(By.xpath("(//div[@data-autoid='departureTime']//div[starts-with(normalize-space(.),'"+deptime+"')])")).isDisplayed()) 
		{
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					(By.xpath("((((//div[@data-autoid='departureTime']//div[starts-with(normalize-space(.),'"+deptime+"')])//parent::div//parent::div)[1]//following-sibling::div)[3])//input//following-sibling::label")))).click();
			//getDriver().findElement(By.xpath("((((//div[@data-autoid='departureTime']//div[starts-with(normalize-space(.),'"+deptime+"')])//parent::div//parent::div)[1]//following-sibling::div)[3])//input//following-sibling::label")).click();
			log.debug("Departure time filter selected: " + deptime);
			
		}
		
		
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
		
	    
		
	}
	
	@When("buses are sorted by price in {string} order")
	public void buses_are_sorted_by_price_in_order(String order) {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
		
			WebDriverWait wait = new WebDriverWait(getDriver(),Duration.ofSeconds(4));
		
		if(order.equalsIgnoreCase("ascending")) {
			
			wait.until(ExpectedConditions.visibilityOf(this.filterPage.getAscendingLabel())).click();
			//this.filterPage.getAscendingLabel().click();
			//getDriver().findElement(By.xpath("//*[@aria-label='Price']/div")).click();
			log.debug("Buses sorted by price in ascending order");
			
		}
		
		if(order.equalsIgnoreCase("descending")) {
			
			Actions actions = new Actions(getDriver());
			wait.until(ExpectedConditions.visibilityOf(this.filterPage.getDescendingLabel())).click();
			actions.doubleClick(this.filterPage.getDescendingLabel()).perform();
			//actions.doubleClick(getDriver().findElement(By.xpath("//*[@aria-label='Price']/div"))).perform();
			log.debug("Buses sorted by price in descending order");
			
		}
		
		Thread.sleep(2000);
		
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
		
	    
	}

	
	
	@Then("at least one bus should be displayed")
	public void at_least_one_bus_should_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
			
		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();

		for (int y = 0; y <= 5000; y += 250) {
		    js.executeScript("window.scrollTo(0, " + y + ");");
		    Thread.sleep(500);
		}
		log.debug("Scrolled down the page to load all bus cards");
		
		List<WebElement> buscards = this.filterPage.getBusCards();
				//getDriver().findElements(By.xpath("//li[contains(@class,'ListItem')]/li[@role='button']"));
		log.debug("Number of bus cards found: " + buscards.size());
				
		if(buscards.size() == 0) {
					
			throw new AssertionError("No buses are displayed after applying filters");
				
		}
		log.debug("At least one bus is displayed after applying filters");
		
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
	    
	}
	
	@Then("all displayed buses should be {string} and {string}")
	public void all_displayed_buses_should_be_and(String ac, String sleeper) throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
			

		List<WebElement> busTypes = 
				this.filterPage.getBusTypes();
				
				//getDriver().findElements(By.xpath("//li[@role='button']//div[contains(@class,'bottomRow')]/p"));
		
		log.debug("Number of bus types found: " + busTypes.size());
		
		
		for(WebElement busType: busTypes) {
			
			String busTypeText = busType.getText().replaceAll("A/C", "AC");
			
			if(!busTypeText.contains(ac)){
				
				throw new AssertionError("Bus type does not contain A/C filter, Actual Text: " + busTypeText);
			
			}
			
			if(!busTypeText.contains(sleeper)) {
				
				throw new AssertionError("Bus type does not contain Sleeper filter, Actual Text: " + busTypeText);
			
			}
			
			log.debug("Bus type have both A/C and Sleeper filter, Actual Text: " + busTypeText);
			
			
		}
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
		
	    
	}
	
	@Then("all buses should have departure time Between {string} and {string}")
	public void all_buses_should_have_departure_time_between_and(String start_range, String end_range)  {
	    // Write code here that turns the phrase above into concrete actions
	    
		try {
			
		LocalTime start=DateTimeUtil.parseTime(start_range);
		LocalTime end=DateTimeUtil.parseTime(end_range);
		
		List<WebElement> boardingTimes = 
				this.filterPage.getBoardingTimes();
				//getDriver().findElements(By.xpath("//li[@role='button']//p[contains(@class,'boardingTime')]"));
		
		log.debug("Number of boarding times found: " + boardingTimes.size());
		
	
		for(WebElement boardingTime: boardingTimes) {
			
			String timeText = boardingTime.getText().trim();
			
			LocalTime actualTime = DateTimeUtil.parseTime(timeText);
			
			if(actualTime.isBefore(start) || actualTime.isAfter(end)) {
				
				throw new AssertionError("Departure time " + timeText + " is outside the range "
	                    + start_range + " to " + end_range);
			
			}
			
			log.debug("Departure time " + timeText + " is inside the range "
	                + start_range + " to " + end_range);
		}
		
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
	
			
	}
	
	@Then("bus prices should be sorted in ascending order")
	public void bus_prices_should_be_sorted_in_ascending_order()  {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
			
		List<WebElement> priceElements = 
				this.filterPage.getPriceElements();
				//getDriver().findElements(By.xpath("//li[@role='button']//p[contains(@class,'finalFare')]"));
		log.debug("Number of price elements found: " + priceElements.size());
		
		List<Integer> prices = new ArrayList<>();
		
		for(WebElement priceElement: priceElements) {
			
			int price = Integer.parseInt(priceElement.getText().replaceAll("[^0-9]", ""));
			
			log.debug("Bus price found: " + price);
			prices.add(price);
		}
		
		for(int i = 0; i < prices.size() - 1; i++) {
			
			if(prices.get(i) > prices.get(i + 1)) {
				
				throw new AssertionError("Bus prices are not sorted in ascending order. Price at index " + i + " is " + prices.get(i) + " and price at index " + (i + 1) + " is " + prices.get(i + 1));
			
			}	
			
		}
		log.debug("Prices are sorted in ascending order");
		
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Exception Occured:"+e.getMessage());
		}
		
	    
	}
	
	

}
