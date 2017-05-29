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
	}


	@AfterTest 
	public void CloseBrowser() {
		driver.quit();
	} 

	@Test 
	public void verifyYahooPageTitle() {
		System.out.println("Inside verifyYahooPageTitle");
		driver.get("https://yahoo.com/");
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		String title = driver.getTitle();
		System.out.println("Title is : "+title);
		Assert.assertTrue(title.contains("Yahoo"));
	 }

	@Test
	public void verifyYahooLogo() throws InterruptedException{
		System.out.println("Inside verifyYahooLogo");
		WebElement yahooLogo = driver.findElement(By.id(CommonLibrary.getPath("YahooHomePage","HomePage","logo"));
		Assert.assertTrue(yahooLogo.isDisplayed(), "Yahoo Logo is not present");
	}

	 @Test
	 public void verifySearchBar() throws InterruptedException{
		 System.out.println("Inside verifySearchBar");
		 String  Query = "iPhone";
		 System.out.println("Inside verifyYahooLogo");
		 WebElement searchBox = driver.findElement(By.id(CommonLibrary.getPath("YahooHomePage", "HomePageSearchBar","searchbar"));
		 Assert.assertTrue(searchBox.isDisplayed(), "Search bar is not display");
		 searchBox.sendKeys(Query);
		 searchBox.submit();
		 String searchResultsLandingPage = driver.getCurrentUrl();
		 String searchLandingPageURL = "https://in.search.yahoo.com/search?p=dvd";
		 Assert.assertTrue(searchResultsLandingPage.contains(searchLandingPageURL), "Search landing doesn't contain"+searchLandingPageURL);
	 }

}
