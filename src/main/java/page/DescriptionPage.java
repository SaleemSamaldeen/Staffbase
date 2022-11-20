package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class DescriptionPage {
    private WebDriver driver;

    public DescriptionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[contains(@class,'order-first')]//a[contains(@class,'font-semibold')]")
    WebElement applyButton;

    @FindBy(xpath = "//button[contains(text(),'ACCEPT')]")
    WebElement acceptCookies;

    public void acceptCookies() {
        acceptCookies.click();
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public ApplicationPage clickApplyButton() {
        applyButton.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return new ApplicationPage(driver);
    }
}
