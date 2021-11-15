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

import java.util.concurrent.TimeUnit;

public class TestLogin {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start (){
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }
    @Test
    public void login() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id=\"sidebar\"]")));

    }

    @After
    public void stop () {
        driver.quit();
        driver=null;
    }
}
