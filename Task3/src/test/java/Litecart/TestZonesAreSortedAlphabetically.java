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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestZonesAreSortedAlphabetically {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 2);
    }

    @Test
    public void sortTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id=\"sidebar\"]")));
        driver.findElement(By.xpath("//span[contains(text(),'Geo')]")).click();
        List<WebElement> countries = driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=3]"));
        int zonesQuantity = countries.size();
        for (int i=0;i<zonesQuantity;i++) {
            driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=3]//a")).get(i).click();
            ArrayList<String> countriesZones = new ArrayList<>();
            List <WebElement> zones = driver.findElements(By.xpath("//select[contains(@name,'zone_code')]//option[@selected=\"selected\"]"));
            for (WebElement zone : zones) {
                countriesZones.add(zone.getText());
            }
            ArrayList<String> sortedCountriesZones = new ArrayList<>();
            for (String z : countriesZones) {
                sortedCountriesZones.add(z);
            }
            Collections.sort(sortedCountriesZones);
            System.out.println(countriesZones);
            System.out.println(sortedCountriesZones);
            Assert.assertTrue(countriesZones.equals(sortedCountriesZones));
            driver.navigate().back();
        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}