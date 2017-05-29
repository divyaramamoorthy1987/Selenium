package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YahooPageValidation {
	
	WebDriver driver; 

	@BeforeTest 
	public void OpenBrowser() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://yahoo.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	}


	@AfterTest 
	public void CloseBrowser() {
		driver.quit();
	} 

	@Test 
	public void verifyYahooPageTitle() {
		System.out.println("Inside verifyYahooPageTitle");
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		Assert.assertTrue(title.contains("Yahoo"), "Yahoo page title doesn't contain yahoo");
	 }

	@Test
	public void verifyYahooLogo() throws InterruptedException{
		System.out.println("Inside verifyYahooLogo");
		WebElement yahooLogo = driver.findElement(By.xpath(CommonLibrary.getPath("YahooHomePage","HomePage","logo"));
		Assert.assertTrue(yahooLogo.isDisplayed(), "Yahoo Logo is not present");
	}

	 @Test
	 public void verifySearchBar() throws InterruptedException{
		 System.out.println("Inside verifySearchBar");
		 String  Query = "iPhone";
		 WebElement searchBox = driver.findElement(By.xpath(CommonLibrary.getPath("YahooHomePage", "HomePageSearchBar","searchbar"));
		 Assert.assertTrue(searchBox.isDisplayed(), "Search bar is not display");
		 searchBox.sendKeys(Query);
		 searchBox.submit();
		 driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		 String searchResultsLandingPage = driver.getCurrentUrl();
		 String searchLandingPageURL = "https://search.yahoo.com/search?p=iphone";
		 Assert.assertTrue(searchResultsLandingPage.contains(searchLandingPageURL), "Search landing doesn't contain"+searchLandingPageURL);
	 }

}
