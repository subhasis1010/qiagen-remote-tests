package hotwire.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ResultsValidation {
    WebDriver driver;

    @BeforeMethod
    public void pageLoad() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.hotwire.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void search() throws InterruptedException {

        driver.findElement(By.xpath("/html/body/div[1]/div/div/div[1]/div/div/div/farefinder/div/farefinder-options/div/div[4]")).click();
        driver.findElement(By.xpath("//button[text()='Car']")).click();
        driver.findElement(By.xpath("//input[@id='farefinder-package-origin-location-input']")).sendKeys("SFO");
        driver.findElement(By.xpath("//a[contains(text(), 'San Francisco, CA')]")).click();
        driver.findElement(By.xpath("//input[@id='farefinder-package-destination-location-input']")).sendKeys("LAX");
        driver.findElement(By.xpath("//a[contains(text(), 'Los Angeles, CA')]")).click();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        String departingDate = sdf.format(c.getTime());
        c.add(Calendar.DATE, 20);
        String returningDate = sdf.format(c.getTime());

        driver.findElement(By.xpath("//input[@id='farefinder-package-startdate-input']")).clear();
        driver.findElement(By.xpath("//input[@id='farefinder-package-startdate-input']")).sendKeys(departingDate);
        driver.findElement(By.xpath("//input[@id='farefinder-package-enddate-input']")).clear();
        driver.findElement(By.xpath("//input[@id='farefinder-package-enddate-input']")).sendKeys(returningDate);

        driver.findElement(By.xpath("//select[@id='farefinder-package-pickuptime-input']/option[@label='Evening']")).click();
        driver.findElement(By.xpath("//select[@id='farefinder-package-dropofftime-input']/option[@label='Morning']")).click();
        driver.findElement(By.xpath("//button[@id='farefinder-package-search-button']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 50);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//header[@id='hotelResultTitle']//h1")));
        String resultsText = driver.findElement(By.xpath("//header[@id='hotelResultTitle']//h1")).getText();
        Assert.assertEquals(resultsText, "Start by choosing your hotel");
        System.out.println("Atleast one result is displayed, since the text to search for hotel is enabled");

    }
}
