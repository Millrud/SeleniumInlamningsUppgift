package createUserFeature;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriveCreator {

    public WebDriver createBrowser(String browser) {

        WebDriver driver = null;

        if (browser.equals("edge")) {
            System.setProperty("webdriver.edge.driver", "C:/Selenium/edgedriver.exe");
            driver = new EdgeDriver();

        } else {
            System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
            driver = new ChromeDriver();
        }

        return driver;


    }
}
