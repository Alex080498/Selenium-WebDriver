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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCart {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }


    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector((".listing-wrapper"))));
        List<WebElement> productList = driver.findElements(By.cssSelector(".product"));
        int i;
        for (i = 0; i < 3; i++) {

            driver.findElement(By.xpath("//li[@class=\"product column shadow hover-light\"]")).click();
            // WebElement size = driver.findElement(By.xpath("//select[@name=\"options[Size]\"]"));
            //  if (Assert.assertTrue(size.isDisplayed()));
            if (driver.findElement(By.xpath("//select[@name=\"options[Size]\"]")).isDisplayed() == true) {
                WebElement size = driver.findElement(By.xpath("//select[@name=\"options[Size]\"]"));
                Select sizeProduct = new Select(size);
                sizeProduct.selectByValue("Small");
            } else {

                driver.findElement(By.xpath(("//button[@name=\"add_cart_product\"]"))).click();
                wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.className("content")), "" + (i + 1)));
                driver.navigate().back();
            }
            driver.findElement(By.xpath("//a[@href=\"http://localhost/litecart/en/checkout\"]")).click();
            List<WebElement> products = driver.findElements(By.xpath("//li[@class=\"shortcut\"]"));
            int productsCount = products.size();
            int j;
            for (j = 0; j < productsCount; j--) {
                driver.findElement(By.xpath("//li[@class=\"shortcut\"]")).click();
                driver.findElement(By.xpath("//button[@name=\"remove_cart_item\"]")).click();
            }
            Assert.assertTrue(driver.findElement(By.id("checkout-cart-wrapper")).getText().contains("There are no items in your cart."));
        }
    }
        @After
        public void stop () {
            driver.quit();
            driver = null;
        }

}