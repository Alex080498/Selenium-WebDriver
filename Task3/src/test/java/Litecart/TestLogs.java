package Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestLogs {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
        public void start() {
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, 10);
    }
    @Test
    public void maintest (){
        driver.get("http://localhost/litecart/admin/");
        //driver.manage().logs().get("browser");
        driver.findElement(By.xpath("//input[@name=\"username\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//input[@name=\"password\"]")).sendKeys("admin");
        driver.findElement(By.xpath("//button[@name=\"login\"]")).click();
        driver.findElement(By.xpath("//span[contains(text(), \"Catalog\")]")).click();
        driver.findElement(By.xpath("//li[@id=\"doc-catalog\"]//span[contains(text(), \"Catalog\")]")).click();
        driver.findElement(By.xpath("//a[@href=\"http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1\"]")).click();

        List<WebElement> products = driver.findElements(By.xpath("//a[contains(@href, \"product_id\")][not(@title=\"Edit\")]"));
        int quantity = products.size();
        int i;
        for (i=0;i<quantity;i++) {
            products = driver.findElements(By.xpath("//a[contains(@href, \"product_id\")][not(@title=\"Edit\")]"));
            products.get(i).click();
            //driver.findElement(By.xpath("//a[contains(@href, \"product_id\")][not(@title=\"Edit\")]")).click();
            driver.navigate().back();
        }

        List<LogEntry> logsList = driver.manage().logs().get("browser").getAll();
        if (logsList.size()!=0) {
            for (LogEntry l : logsList)
                System.out.println(l);
        } else {
            System.out.println("No logs for you");
        }
    }
 @After
public void stop () {
    driver.quit();
    driver=null;
}
}
