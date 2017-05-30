package tests;

import library.CommonLib;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class YahooPageObjectsTest {

    WebDriver driver;
    public static String baseURL = "https://yahoo.com/";
    /**
     * BeforeTest will launch the browser and opens the baseurl.
     *
     */

    @BeforeTest
    public void OpenBrowser() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     *
     * Data provider to read the queries from the queries.property file
     *
     *
     * @return
     * @throws Exception
     */

    @DataProvider(name = "getAllQueries")
    public Object[][] getAllQueries() throws Exception {
        ArrayList<String> allQueries = CommonLib.getQueries();
        Assert.assertTrue(allQueries.size() != 0, "No Queries found::" + allQueries);
        Object[][] object = new Object[allQueries.size()][];
        Iterator<String> iter = allQueries.iterator();
        int i = 0;
        while (iter.hasNext()) {
            object[i] = new String[] { iter.next() };
            i++;
        }
        return object;
    }

    /**
     * AfterTest method will close the browser to reduce the system memory by closing the launched browser.
     *
     *
     */

    @AfterTest
    public void CloseBrowser() {
        driver.quit();
    }

    /**
     *
     * verifyYahooPageTitle - validate yahoo home page have the page title yahoo.
     *
     */

    @Test
    public void verifyYahooPageTitle() {
        System.out.println("Inside verifyYahooPageTitle");
        String title = driver.getTitle();
        System.out.println("Title is : " + title);
        Assert.assertTrue(title.contains("Yahoo"), "Yahoo page title doesn't contain yahoo");
    }

    /**
     * verifyYahooLogo - validates yahoo logo is present and home page.
     *
     * @throws InterruptedException
     */

    @Test
    public void verifyYahooLogo() throws InterruptedException {
        System.out.println("Inside verifyYahooLogo");
        WebElement yahooLogo = driver.findElement(By.xpath(CommonLibrary.getPath("YahooHomePage", "HomePage", "logo"));
        Assert.assertTrue(yahooLogo.isDisplayed(), "Yahoo Logo is not present");
    }

    /**
     *
     * verifySearchBar - Verify search bar test validates the on yahoo home page search bar is present and entering the
     * query in the search box and clicking on the search button and validates the landing page url.
     *
     * @param query
     * @throws InterruptedException
     */

    @Test( dataProvider = "getAllQueries")
    public void verifySearchBar(String query ) throws InterruptedException {
        System.out.println("Inside verifySearchBar");
        WebElement searchBox = driver.findElement(By.xpath(CommonLibrary.getPath("YahooHomePage", "HomePageSearchBar", "searchbar"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search bar is not display");
        searchBox.sendKeys(query);
        searchBox.submit();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String searchResultsLandingPage = driver.getCurrentUrl();
        String searchLandingPageURL = "https://search.yahoo.com/search?p="+query;
        Assert.assertTrue(searchResultsLandingPage.contains(searchLandingPageURL), "Search landing doesn't contain" + searchLandingPageURL);
        drive.back();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
