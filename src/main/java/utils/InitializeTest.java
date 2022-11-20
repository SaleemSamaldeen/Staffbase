package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class InitializeTest extends ConfigReader{

    protected WebDriver driver;

    @BeforeMethod
    public void launchPage(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(baseURL());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
