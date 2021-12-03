package Litecart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestAddProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest () {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElement(By.xpath("//a[@href=\"http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product\"]")).click();

        driver.findElement(By.xpath("//label[contains(text (), 'Enabled')]")).click();
        String productName = "Elza";
        driver.findElement(By.xpath("//input[@name=\"name[en]\"]")).sendKeys(productName);

        Random randomNumber = new Random();
        int randomId = randomNumber.nextInt(10000);
        String productId = "TP" + randomId;
        driver.findElement(By.xpath("//input[@name=\"code\"]")).sendKeys(productId);
        driver.findElement(By.xpath("//input[@name=\"product_groups[]\"and @value=\"1-3\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"quantity\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"quantity\"]")).sendKeys("3");

        String relativePath = "./src/test/resources/air-walker-balloon-frozen.jpg";
        Path imagePath = Paths.get(relativePath);
        String absolutePath = imagePath.normalize().toAbsolutePath().toString();

        WebElement UploadImg = driver.findElement(By.xpath("//input[@name=\"new_images[]\"]"));
        UploadImg.sendKeys(absolutePath);


        driver.findElement(By.name("date_valid_from")).sendKeys("02.12.2021");



        driver.findElement(By.xpath("//a[contains(text (), 'Information')]")).click();
        WebElement vendor = driver.findElement(By.xpath("//select[@name=\"manufacturer_id\"]"));
        Select vendorName = new Select(vendor);
        vendorName.selectByValue("1");

        driver.findElement(By.xpath("//input[@name=\"keywords\"]")).sendKeys("keywords for seo");
        driver.findElement(By.xpath("//input[@name=\"short_description[en]\"]")).sendKeys("this is very very good product");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("This product is still the best");
        driver.findElement(By.xpath("//input[@name=\"head_title[en]\"]")).sendKeys("Title for seo");
        driver.findElement(By.xpath("//input[@name=\"meta_description[en]\"]")).sendKeys("Description for seo");

        driver.findElement(By.xpath("//a[@href=\"#tab-prices\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"purchase_price\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"purchase_price\"]")).sendKeys("10");

        WebElement price = driver.findElement(By.xpath("//select[@name=\"purchase_price_currency_code\"]"));
        Select priceP = new Select(price);
        priceP.selectByValue("EUR");

        driver.findElement(By.xpath("//input[@name=\"gross_prices[USD]\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"gross_prices[USD]\"]")).sendKeys("13");
        driver.findElement(By.xpath("//input[@name=\"gross_prices[EUR]\"]")).clear();
        driver.findElement(By.xpath("//input[@name=\"gross_prices[EUR]\"]")).sendKeys("12");

        driver.findElement(By.xpath("//button[@name=\"save\"]")).click();

        Assert.assertTrue(driver.getPageSource().contains(productName));


    }

    @After
    public void stop () {
        driver.quit();
        driver = null;
    }
}