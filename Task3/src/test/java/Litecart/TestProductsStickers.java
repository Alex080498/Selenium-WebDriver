package Litecart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestProductsStickers {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".listing-wrapper")));
        List<WebElement> productsList = driver.findElements(By.cssSelector("li.product"));
        for (WebElement product : productsList) {
            Assert.assertEquals(product.findElements(By.cssSelector("div.sticker")).size(), 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}