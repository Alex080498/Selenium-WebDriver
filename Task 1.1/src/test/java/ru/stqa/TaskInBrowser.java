package ru.stqa;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TaskInBrowser {
    private WebDriver driver;
    //private WebDriverWait wait;


    @Before
    public void start() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
       // wait = new WebDriverWait(driver,10);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

    @Test
    public void taskInBrowser() {
        driver.get("https://demoqa.com/");
        driver.findElement(By.xpath("//a[@href=\"https://www.toolsqa.com/selenium-training/\"]")).click();

    }
    @After
    public void stop () {
        driver.quit();
        driver=null;
    }

}
