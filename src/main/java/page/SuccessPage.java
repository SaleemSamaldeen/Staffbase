package page;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class SuccessPage {

    private WebDriver driver;

    public SuccessPage(WebDriver driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getTitle() {
       return driver.getTitle();
    }
}
