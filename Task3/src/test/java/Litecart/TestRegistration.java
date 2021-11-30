package Litecart;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestRegistration {
    private WebDriver driver;
    private WebDriverWait wait;

    public void logout(){
        driver.findElement(By.linkText("Logout")).click();
    }

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest () {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//a[contains(@href, 'create_account')]")).click();
        Assert.assertTrue(driver.findElement(By.tagName("form")).isDisplayed());
        driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys("Anthony");
        driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys("Lockwood");
        driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys("221B Baker Street");
        driver.findElement(By.xpath("//input[@name=\"postcode\"]")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys("London");

        WebElement country = driver.findElement(By.xpath("//select[@name=\"country_code\"]"));
        Select sel = new Select(country);
        sel.selectByValue("US");
        Assert.assertTrue(driver.findElement(By.xpath("//select[@name=\"zone_code\"]")).isDisplayed());
        WebElement zone = driver.findElement(By.xpath("//select[@name=\"zone_code\"]"));
        Select zoneSel = new Select(zone);
        zoneSel.selectByValue("AK");
        Random randomEmail = new Random();
        int randomNumber = randomEmail.nextInt(1000);
        String personalEmail = "user" + randomNumber + "@gmail.com";
        driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(personalEmail);

        driver.findElement(By.xpath("//input[@name=\"phone\"]")).sendKeys("+79999999999");
        String password = "parol123";
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name=\"confirmed_password\"]")).sendKeys(password);

        driver.findElement(By.xpath("//button[@name=\"create_account\"]")).click();

        WebElement logoutLink = driver.findElement(By.xpath("//a[@href=\"http://localhost/litecart/en/logout\"]"));
        Assert.assertTrue(logoutLink.isDisplayed());
        logoutLink.click();

        Assert.assertTrue(driver.findElement(By.id("box-account-login")).isDisplayed());

        driver.findElement(By.xpath("//input[@name=\"email\"]")).sendKeys(personalEmail);
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();

        logout();

        Assert.assertTrue(driver.findElement(By.id("box-account-login")).isDisplayed());

    }

}
