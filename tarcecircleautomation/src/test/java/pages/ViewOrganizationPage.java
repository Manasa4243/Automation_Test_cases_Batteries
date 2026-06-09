package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewOrganizationPage {

    WebDriver driver;
    WebDriverWait wait;

    public ViewOrganizationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[normalize-space()='View Organization']");

    private By backButton =
            By.xpath("//button[.//*[contains(@class,'lucide-arrow-left')]]");

    private By organizationName =
            By.xpath("//*[contains(normalize-space(),'Organization Name')]/following::*[1]");

    private By organizationEmail =
            By.xpath("//*[contains(normalize-space(),'Organization Email')]/following::*[1]");

    private By sector =
            By.xpath("//*[contains(normalize-space(),'Sector')]/following::*[1]");

    private By country =
            By.xpath("//*[contains(normalize-space(),'Country')]/following::*[1]");

    private By state =
            By.xpath("//*[contains(normalize-space(),'State') or contains(normalize-space(),'Region')]/following::*[1]");

    private By city =
            By.xpath("//*[contains(normalize-space(),'City')]/following::*[1]");

    private By postalCode =
            By.xpath("//*[contains(normalize-space(),'Postal') or contains(normalize-space(),'ZIP')]/following::*[1]");

    private By address =
            By.xpath("//*[contains(normalize-space(),'Address')]/following::*[1]");

    private By companyWebsite =
            By.xpath("//*[contains(normalize-space(),'Company Website')]/following::*[1]");

    private By contactPersonNameValue =
            By.xpath("//*[contains(normalize-space(),'Contact Person Name')]/following::*[1]");

    private By phoneNumberValue =
            By.xpath("//*[contains(normalize-space(),'Phone Number')]/following::*[1]");

    private By description =
            By.xpath("//*[contains(normalize-space(),'Description')]/following::*[1]");

    public boolean isViewOrganizationPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isOrganizationNameDisplayed() {
        return isDisplayed(organizationName);
    }

    public boolean isOrganizationEmailDisplayed() {
        return isDisplayed(organizationEmail);
    }

    public boolean isSectorDisplayed() {
        return isDisplayed(sector);
    }

    public boolean isCountryDisplayed() {
        return isDisplayed(country);
    }

    public boolean isStateDisplayed() {
        return isDisplayed(state);
    }

    public boolean isCityDisplayed() {
        return isDisplayed(city);
    }

    public boolean isPostalCodeDisplayed() {
        return isDisplayed(postalCode);
    }

    public boolean isAddressDisplayed() {
        return isDisplayed(address);
    }

    public boolean isCompanyWebsiteDisplayed() {
        return isDisplayed(companyWebsite);
    }

    public String getContactPersonName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(contactPersonNameValue)
        ).getText().trim();
    }

    public String getPhoneNumber() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(phoneNumberValue)
        ).getText().trim();
    }

    public boolean isContactDetailsDisplayed() {
        try {
            String name = getContactPersonName();
            String phone = getPhoneNumber();

            return !name.isEmpty()
                    && !phone.isEmpty();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDescriptionDisplayed() {
        return isDisplayed(description);
    }

    public boolean areOrganizationDetailsDisplayed() {
        return isOrganizationNameDisplayed()
                && isOrganizationEmailDisplayed()
                && isSectorDisplayed()
                && isCountryDisplayed()
                && isStateDisplayed()
                && isCityDisplayed()
                && isPostalCodeDisplayed()
                && isAddressDisplayed()
                && isCompanyWebsiteDisplayed()
                && isContactDetailsDisplayed()
                && isDescriptionDisplayed();
    }

    public void clickBackButton() {
        WebElement back = wait.until(
                ExpectedConditions.elementToBeClickable(backButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", back);
    }

    public boolean isReturnedToOrganizationList() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[normalize-space()='Organizations']")
                    )
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isDisplayed(By locator) {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}