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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCountriesAreSortedAlphabetically {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void sortTest () {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id=\"sidebar\"]")));
        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        ArrayList<String> originalList = new ArrayList<>();
        List<WebElement> countriesList = driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=5]"));
        for (WebElement country : countriesList) {
            originalList.add(country.getText());
        }
        ArrayList<String> sortedCountriesList = new ArrayList<>();
        for (String element : originalList){
            sortedCountriesList.add(element);
        }
        Collections.sort(sortedCountriesList);
        System.out.println(originalList);
        System.out.println(sortedCountriesList);
        Assert.assertTrue(sortedCountriesList.equals(originalList));

      List<WebElement> zonesList = driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=6]"));
         int zonesListCount = zonesList.size();
         int i;
         for (i=0;i<zonesListCount;i++) {
             if(!driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=6]")).get(i).getText().equals("0")) {
                 driver.findElements(By.xpath("//tr[@class=\"row\"]//td[position()=5]//a")).get(i).click();
                 ArrayList<String> allZoneslList = new ArrayList<>();
                 List<WebElement> listOfAllZones = driver.findElements(By.xpath("//input[contains(@name,'zones') and contains(@name,'name')]"));
                 for (WebElement singleZone : listOfAllZones) {
                     allZoneslList.add(singleZone.getAttribute("value"));
                 }
                 ArrayList<String> sortedAllZoneslList = new ArrayList<>();
                 for (String el : allZoneslList) {
                     sortedAllZoneslList.add(el);
                 }
                 Collections.sort(sortedAllZoneslList);
                 System.out.println(allZoneslList);
                 System.out.println(sortedAllZoneslList);
                 Assert.assertTrue(sortedAllZoneslList.equals(allZoneslList));
                 driver.navigate().back();
             }

         }


            }



    @After
    public void stop () {
        driver.quit();
        driver = null;
    }

}

