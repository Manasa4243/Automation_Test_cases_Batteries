package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditPlantPage {

    WebDriver driver;
    WebDriverWait wait;

    public EditPlantPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//*[contains(normalize-space(),'Edit Plant') or contains(normalize-space(),'Update Plant')]");

    private By organizationDropdown =
            By.xpath("//label[normalize-space()='Organization']/following::button[1]");

    private By plantCode =
            By.xpath("//input[@placeholder='Enter plant code']");

    private By plantName =
            By.xpath("//input[@placeholder='Enter plant name']");

    private By addressLine1 =
            By.xpath("//input[@placeholder='Enter address line 1']");

    private By addressLine2 =
            By.xpath("//input[@placeholder='Enter address line 2']");

    private By countryDropdown =
            By.xpath("//label[normalize-space()='Country']/following::button[1]");

    private By stateDropdown =
            By.xpath("//label[contains(normalize-space(),'State')]/following::button[1]");

    private By cityDropdown =
            By.xpath("//label[normalize-space()='City']/following::button[1]");

    private By postalCode =
            By.xpath("//input[@placeholder='Enter postal code']");

    private By contactPersonName =
            By.xpath("//input[@placeholder='Enter contact person name']");

    private By contactEmail =
            By.xpath("//input[@placeholder='Enter email']");

    private By phoneNumber =
            By.xpath("//input[@placeholder='Enter phone number']");

    private By description =
            By.xpath("//textarea[@placeholder='Enter plant description']");

    private By updateBtn =
            By.xpath("//button[contains(normalize-space(),'Update') or contains(normalize-space(),'Save')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    private String getValue(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("value");
    }

    private void clickJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void selectDropdownValue(By dropdown, String value) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(dropdown));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    dropdownElement
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    dropdownElement
            );

            By optionLocator = By.xpath(
                    "//*[(@role='option' or @role='menuitem' or @cmdk-item='' or contains(@class,'SelectItem'))" +
                    " and normalize-space(.)='" + value + "']"
            );

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    option
            );

        } catch (Exception e) {
            throw new RuntimeException("Dropdown value not selected: " + value, e);
        }
    }

    public boolean isEditPlantPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPrefilledDataDisplayed() {
        try {
            return !getValue(plantCode).isEmpty()
                    && !getValue(plantName).isEmpty()
                    && !getValue(addressLine1).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void updateOrganization(String orgName) {
        selectDropdownValue(organizationDropdown, orgName);
    }

    public void updatePlantCode(String value) {
        enterText(plantCode, value);
    }

    public void updatePlantName(String value) {
        enterText(plantName, value);
    }

    public void updateAddressLine1(String value) {
        enterText(addressLine1, value);
    }

    public void updateAddressLine2(String value) {
        enterText(addressLine2, value);
    }

    public void updateLocation(String country, String state, String city) {
        selectDropdownValue(countryDropdown, country);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Karnataka']")
        ));

        selectDropdownValue(stateDropdown, state);

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Bengaluru']")
        ));

        selectDropdownValue(cityDropdown, city);
    }

    public void updatePostalCode(String value) {
        enterText(postalCode, value);
    }

    public void updateContactPersonName(String value) {
        enterText(contactPersonName, value);
    }

    public void updateContactEmail(String value) {
        enterText(contactEmail, value);
    }

    public void updatePhoneNumber(String value) {
        enterText(phoneNumber, value);
    }

    public void updateDescription(String value) {
        enterText(description, value);
    }

    public void clickUpdate() {
        clickJS(updateBtn);
    }

    public void clickCancel() {
        clickJS(cancelBtn);
    }

    public boolean isReturnedToPlantList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("plants"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Plants')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }
}