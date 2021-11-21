package Litecart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestProductsStickers {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/");
        List<WebElement> productsList = driver.findElements(By.cssSelector("li.product"));
        int numberOfProducts = productsList.size();
        for (int i = 0; i < numberOfProducts; i++) {
            WebElement sticker = driver.findElement(By.cssSelector("div.sticker"));
            Assert.assertEquals(true, sticker.isDisplayed());
        }
        System.out.println(numberOfProducts);
        List<WebElement> stickerCount = driver.findElements(By.cssSelector("div.sticker"));
        int numberOfStickers = stickerCount.size();
        System.out.println(numberOfStickers);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}