package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driver {

    public WebDriver driver;

    public WebDriver initlilizeDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/shafiqahmedkhan/Downloads/chromedriver");
        driver = new ChromeDriver();
        return driver;
    }
}
