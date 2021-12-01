package Litecart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class TestProductOpensCorrectly {
    private WebDriver driver;
    private WebDriverWait wait;

//in Chrome
    @Before
    public void start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/en/");

        String nameInCatalog = driver.findElement(By.xpath("//*[contains(@id, 'box-campaigns')]//li//div[@class=\"name\"]")).getText(); //название товара
        String priceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("regular-price")).getText(); //исходная цена
        String salePriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("campaign-price")).getText();// цена со скидкой
        String crossedPriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("regular-price")).getCssValue("text-decoration-line");// цена перечеркнута

        String greyPriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("regular-price")).getCssValue("color");//цена серая
        String[] greys = greyPriceInCatalog.replace("rgba(", "").replace(")", "").split(",");
        int r = Integer.parseInt(greys[0].trim());
        int g = Integer.parseInt(greys[1].trim());
        int b = Integer.parseInt(greys[2].trim());
        Assert.assertEquals(r,g);
        Assert.assertEquals(g,b);

        String boldSalePriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("campaign-price")).getAttribute("tagName");//цена полужирная

        String redSalePriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("campaign-price")).getCssValue("color");//цена красная
        String[] reds = redSalePriceInCatalog.replace("rgba(", "").replace(")", "").split(",");
        int r1 = Integer.parseInt(reds[0].trim());
        int g1 = Integer.parseInt(reds[1].trim());
        int b1 = Integer.parseInt(reds[2].trim());
        Assert.assertEquals(g1,0);
        Assert.assertEquals(b1,0);

        String fontPriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("regular-price")).getCssValue("font-size").substring(0,2);//размер шрифта обычной цены
        String fontSalePriceInCatalog = driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).findElement(By.className("campaign-price")).getCssValue("font-size").substring(0,2);//размер шрифта цены со скидкой
        int sizeOfRegularPrice = Integer.parseInt(fontPriceInCatalog);
        int sizeOfSalePrice = Integer.parseInt(fontSalePriceInCatalog);

        Assert.assertTrue(crossedPriceInCatalog.equals("line-through"));
        //Assert.assertTrue(greyPriceInCatalog.equals("rgba(119, 119, 119, 1)"));
        //Assert.assertTrue(redSalePriceInCatalog.equals("rgba(204, 0, 0, 1)"));
        Assert.assertTrue(boldSalePriceInCatalog.equals("STRONG"));
        Assert.assertTrue(sizeOfSalePrice > sizeOfRegularPrice);

        driver.findElements(By.xpath("//*[contains(@id, 'box-campaigns')]//li")).get(0).click();
        Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().equals(nameInCatalog)); // сравнение названий
        Assert.assertTrue(driver.findElement(By.className("regular-price")).getText().equals(priceInCatalog)); // сравнение обычных цен
        Assert.assertTrue(driver.findElement(By.className("campaign-price")).getText().equals(salePriceInCatalog));// сравнение акционных цен

        String greyPriceInDetailPage = driver.findElement(By.className("regular-price")).getCssValue("color"); // обычная цена на детальной странице серая
        String[] greysDetailPage = greyPriceInDetailPage.replace("rgba(", "").replace(")", "").split(",");
        int r3 = Integer.parseInt(greysDetailPage[0].trim());
        int g3 = Integer.parseInt(greysDetailPage[1].trim());
        int b3 = Integer.parseInt(greysDetailPage[2].trim());
        Assert.assertEquals(r3,g3);
        Assert.assertEquals(g3,b3);

        Assert.assertTrue(driver.findElement(By.className("regular-price")).getCssValue("text-decoration-line").equals("line-through")); // обычная цена перечеркнута

        String redPriceOnDetailPage = driver.findElement(By.className("campaign-price")).getCssValue("color"); // акционная цена красная
        String[] redsDetailPage = redPriceOnDetailPage.replace("rgba(", "").replace(")", "").split(",");
        int r4 = Integer.parseInt(redsDetailPage[0].trim());
        int g4 = Integer.parseInt(redsDetailPage[1].trim());
        int b4 = Integer.parseInt(redsDetailPage[2].trim());
        Assert.assertEquals(g4,0);
        Assert.assertEquals(b4,0);

        Assert.assertTrue(driver.findElement(By.className("campaign-price")).getAttribute("tagName").equals("STRONG")); // акционная цена полужирная
         String  fontOfRegPrice = driver.findElement(By.className("regular-price")).getCssValue("font-size").substring(0,2);
         String fontOfSaPrice = driver.findElement(By.className("campaign-price")).getCssValue("font-size").substring(0,2);
        int sizeOfRegPrice = Integer.parseInt(fontOfRegPrice);
        int sizeOfSaPrice = Integer.parseInt(fontOfSaPrice);

        Assert.assertTrue(sizeOfSaPrice > sizeOfRegPrice);
    }

        @After
        public void stop () {
            driver.quit();
            driver = null;
        }
    }