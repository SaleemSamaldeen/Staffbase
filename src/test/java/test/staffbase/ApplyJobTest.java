package test.staffbase;

import io.qameta.allure.*;
import io.qameta.allure.testng.Tag;
import org.testng.annotations.Test;
import page.ApplicationPage;
import page.DescriptionPage;
import page.SuccessPage;
import utils.DataInputProvider;
import utils.InitializeTest;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("Apply for Quality Assurance Engineer")
@TmsLink("TC01")
@Severity(SeverityLevel.CRITICAL)
@Tag("E2ETest")
class ApplyJobTest extends InitializeTest {

    private DescriptionPage descriptionPage;
    private ApplicationPage applicationPage;

    private SuccessPage successPage;

    private final String title = "Quality Assurance Engineer | Careers - Staffbase";

    @Test(dataProvider = "GenericDataProvider", dataProviderClass = DataInputProvider.class, testName = "Application")
    @Description("Submit Application for QA Engineer role")
    void applyForQAEngineerPosition(String firstName, String lastName, String email, String phoneNumber, String visaStatus) {

        descriptionPage = new DescriptionPage(driver);
        step("Launch Job Description page and accept cookies", () -> {
            descriptionPage.acceptCookies();
            assertThat(descriptionPage.getTitle()).isEqualTo(title);
        });
        step("Click Apply button in Job Description page", () -> {
            applicationPage = descriptionPage.clickApplyButton();
        });
        step("Enter Personal Information in Application page", () -> {
            applicationPage.enterFirstName(firstName);
            applicationPage.enterLastName(lastName);
            applicationPage.enterEmail(email);
            applicationPage.enterPhoneNumber(phoneNumber);
        });
        step("Attach Resume", () -> {
            applicationPage.attachResume();
            assertThat(applicationPage.isResumeAttached()).isEqualTo("Resume.pdf");
        });
        step("Attach Cover Letter", () -> {
            applicationPage.attachCoverLetter();
            assertThat(applicationPage.isCoverLetterAttached()).isEqualTo("CoverLetter.pdf");
        });
        step("Enter legal work authorization status", () -> {
            applicationPage.enterVisaInformation(visaStatus);
        });
        step("Acknowledge Staffbase Candidate Privacy", () -> {
            applicationPage.selectCandidatePrivacy("Yes");
        });
        step("Enter Github url and click Submit button", () -> {
            applicationPage.enterRepositoryLink();
            successPage = applicationPage.submitApplication();
        });

        step("Verify if success page is displayed", () -> {
            assertThat(successPage.getTitle()).isEqualTo("Apply: " + title);
        });
    }

}
