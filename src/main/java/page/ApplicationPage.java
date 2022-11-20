package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ApplicationPage extends CommonPage {

    private WebDriver driver;

    public ApplicationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='first_name']")
    WebElement firstNameField;

    @FindBy(xpath = "//input[@id='last_name']")
    WebElement lastNameField;

    @FindBy(xpath = "//input[@id='email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='phone']")
    WebElement inputPhone;

    @FindBy(xpath = "//button[@data-source='attach' and contains(@aria-describedby,'resume-allowable')]")
    WebElement resumeButton;

    @FindBy(xpath = "//button[@data-source='attach' and contains(@aria-describedby,'cover_letter')]")
    WebElement coverLetterButton;

    @FindBy(xpath = "//input[@id='job_application_answers_attributes_0_text_value']")
    WebElement visaSponsorship;

    @FindBy(xpath = "//textarea[@id='job_application_answers_attributes_2_text_value']")
    WebElement repositoryURL;

    @FindBy(xpath = "//div[contains(@id,'job_application_answers')]")
    WebElement candidatePrivacy;

    By privacyOption(String value){
      return By.xpath(String.format("//ul[@id='select2List0']//li//div[contains(text(),'%s')]", value)) ;
    }

    @FindBy(xpath = "//input[@value='Submit Application']")
    WebElement submitApplication;

    @FindBy(css = "#resume_filename")
    WebElement resumeFileName;

    @FindBy(css = "#cover_letter_filename")
    WebElement coverLetterFileName;

    String frameName = "grnhse_iframe";

    public void enterFirstName(String firstName) {
        driver.switchTo().frame(frameName);
        firstNameField.sendKeys(firstName);
    }

    public String getTitle() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver.getTitle();
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    public void enterPhoneNumber(String phoneNumber) {
        inputPhone.sendKeys(phoneNumber);
    }

    public void attachResume() {
        resumeButton.click();
        uploadFile(resumePath());
    }

    public void attachCoverLetter() {
        coverLetterButton.click();
        uploadFile(coverLetterPath());
    }

    public void enterVisaInformation(String visaInfo) {
        visaSponsorship.sendKeys(visaInfo);
    }

    public void selectCandidatePrivacy(String privacyInfo) {
        candidatePrivacy.click();
        driver.findElement(privacyOption(privacyInfo)).click();
    }

    public void enterRepositoryLink() {
        repositoryURL.sendKeys(gitURL());
    }

    public SuccessPage submitApplication() {
        submitApplication.click();
        return new SuccessPage(driver);
    }

    public String isResumeAttached() {
        return resumeFileName.getText().trim();
    }

    public String isCoverLetterAttached() {
        return coverLetterFileName.getText().trim();
    }


}
