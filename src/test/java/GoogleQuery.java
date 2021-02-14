import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class GoogleQuery {
    @Test
    public void testCurrentUrl() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver",
                "D:\\maven\\.idea\\bin\\chromedriver.exe");
        WebDriver browser = new ChromeDriver();

        String searchTerm = "Batman"; //here you can enter any search term

        browser.get("https://google.com");
        browser.manage().window().maximize();
        WebElement searchField = browser.findElement(By.xpath("/html/body//form//div[1]//div[2]/input"));
        searchField.sendKeys(searchTerm);
        WebElement searchButton = browser.findElement(By.name("btnK"));
        searchButton.submit();
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //after getting of search results, checking description of every snippet for containing searching keyword

        List < WebElement > results = browser.findElements(By.cssSelector("#rso > div > div:nth-child(10) > div > div.IsZvec > div > span"));

        for (int i = 0; i < results.size(); i++) {
            Assert.assertTrue(results.get(i).getText().contains(searchTerm), "Search result validation failed at instance [ + i + ].");
        }
    }
}

