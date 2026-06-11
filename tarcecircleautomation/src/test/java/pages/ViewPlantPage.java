package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewPlantPage {

    WebDriver driver;
    WebDriverWait wait;

    public ViewPlantPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'View Plant') or contains(normalize-space(),'Plant Details')]");

    private By backButton =
            By.xpath("//button[.//*[contains(@class,'lucide-arrow-left')]]");

    private By plantName =
            By.xpath("//*[contains(normalize-space(),'Plant Name')]/following::*[1]");

    private By plantCode =
            By.xpath("//*[contains(normalize-space(),'Plant Code')]/following::*[1]");

    private By organization =
            By.xpath("//*[contains(normalize-space(),'Organization')]/following::*[1]");

    private By organizationId =
            By.xpath("//*[contains(normalize-space(),'Organization ID') or contains(normalize-space(),'Organization Id')]/following::*[1]");

    private By country =
            By.xpath("//*[contains(normalize-space(),'Country')]/following::*[1]");

    private By state =
            By.xpath("//*[contains(normalize-space(),'State') or contains(normalize-space(),'Region')]/following::*[1]");

    private By city =
            By.xpath("//*[contains(normalize-space(),'City')]/following::*[1]");

    private By postalCode =
            By.xpath("//*[contains(normalize-space(),'Postal') or contains(normalize-space(),'ZIP')]/following::*[1]");

    private By addressLine1 =
            By.xpath("//*[contains(normalize-space(),'Address Line 1')]/following::*[1]");

    private By addressLine2 =
            By.xpath("//*[contains(normalize-space(),'Address Line 2')]/following::*[1]");

    private By contactName =
            By.xpath("//*[contains(normalize-space(),'Contact') and contains(normalize-space(),'Name')]/following::*[1]");

    private By contactEmail =
            By.xpath("//*[contains(normalize-space(),'Email')]/following::*[1]");

    private By phoneNumber =
            By.xpath("//*[contains(normalize-space(),'Phone')]/following::*[1]");

    private By createdBy =
            By.xpath("//*[contains(normalize-space(),'Created By')]/following::*[1]");

    private By updatedBy =
            By.xpath("//*[contains(normalize-space(),'Updated By')]/following::*[1]");

    private By createdAt =
            By.xpath("//*[contains(normalize-space(),'Created At')]/following::*[1]");

    private By updatedAt =
            By.xpath("//*[contains(normalize-space(),'Updated At')]/following::*[1]");

  public boolean isViewPlantPageOpened() {
    try {
        Thread.sleep(2000);

        String url = driver.getCurrentUrl().toLowerCase();
        String body = driver.findElement(By.tagName("body")).getText();

        return url.contains("view")
                || body.contains("View Plant")
                || body.contains("Plant Details")
                || body.contains("Plant Name");

    } catch (Exception e) {
        return false;
    }
}

    public boolean areBasicDetailsDisplayed() {
        return isValueDisplayed(plantName)
                && isValueDisplayed(plantCode)
                && isValueDisplayed(organization);
    }

    public boolean isOrganizationIdDisplayed() {
        return isValueDisplayed(organizationId);
    }

    public boolean isStatusDisplayed() {
        try {
            By statusValue =
                    By.xpath("//span[normalize-space()='Active' or normalize-space()='Inactive']");

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(statusValue)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAddressDetailsDisplayed() {
        return isValueDisplayed(country)
                && isValueDisplayed(state)
                && isValueDisplayed(city)
                && isValueDisplayed(postalCode);
    }

    public boolean isAddressLine1Displayed() {
        return isValueDisplayed(addressLine1);
    }

    public boolean isAddressLine2Displayed() {
        return isValueDisplayed(addressLine2);
    }

    public boolean areContactDetailsDisplayed() {
        return isValueDisplayed(contactName)
                && isValueDisplayed(contactEmail)
                && isValueDisplayed(phoneNumber);
    }

    public boolean areAuditDetailsDisplayed() {
        return isValueDisplayed(createdBy)
                && isValueDisplayed(updatedBy)
                && isValueDisplayed(createdAt)
                && isValueDisplayed(updatedAt);
    }

    public void clickBackButton() {
        WebElement back = wait.until(
                ExpectedConditions.elementToBeClickable(backButton)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                back
        );
    }

    public boolean isReturnedToPlantList() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//h1[contains(normalize-space(),'Plants')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValueDisplayed(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

            return element.isDisplayed()
                    && element.getText() != null
                    && !element.getText().trim().isEmpty();

        } catch (Exception e) {
            return false;
        }
    }
}
