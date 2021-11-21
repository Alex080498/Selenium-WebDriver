import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","C:\\tools\\chromeDriver\\chromedriver.exe");


        driver.get("http://localhost/litecart/admin/");
    }
}
