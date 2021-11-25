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

public class TestClickMenuItems {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id=\"sidebar\"]")));

        List<WebElement> elementList = driver.findElements(By.cssSelector("#app-"));
        int numberOfMenuElements = elementList.size();
        for (int i = 0; i < numberOfMenuElements; i++) {
            elementList = driver.findElements(By.cssSelector("#app-"));
            elementList.get(i).click();
            Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());

            List<WebElement> subMenuElementsList = driver.findElements(By.cssSelector(".docs>li"));
            int numberOfsubMenuElements = subMenuElementsList.size();
            for (int j = 0; j < numberOfsubMenuElements; j++) {
                subMenuElementsList = driver.findElements(By.cssSelector(".docs>li"));
                subMenuElementsList.get(j).click();
                Assert.assertTrue(driver.findElement(By.tagName("h1")).isDisplayed());
            }

        }
    }
        @After
        public void stop () {
            driver.quit();
            driver = null;
        }

}
