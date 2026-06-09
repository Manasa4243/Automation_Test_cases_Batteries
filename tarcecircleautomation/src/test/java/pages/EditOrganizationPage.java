package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditOrganizationPage {

    WebDriver driver;
    WebDriverWait wait;

    public EditOrganizationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//*[contains(normalize-space(),'Edit Organization') or contains(normalize-space(),'Update Organization')]");

    private By organizationName =
            By.xpath("//label[normalize-space()='Organization Name']/following::input[1]");

    private By sectorDropdown =
            By.xpath("//label[normalize-space()='Sector']/following::button[1]");

    private By companyWebsite =
            By.xpath("//label[normalize-space()='Company Website']/following::input[1]");

    private By address =
            By.xpath("//label[normalize-space()='Address']/following::input[1]");

    private By countryDropdown =
            By.xpath("//label[normalize-space()='Country']/following::button[1]");

    private By stateDropdown =
            By.xpath("//label[contains(normalize-space(),'State')]/following::button[1]");

    private By cityDropdown =
            By.xpath("//label[contains(normalize-space(),'City')]/following::button[1]");

    private By postalCode =
            By.xpath("//label[contains(normalize-space(),'Postal')]/following::input[1]");

    private By contactPersonName =
            By.xpath("//label[normalize-space()='Contact Person Name']/following::input[1]");

    private By contactPersonEmail =
            By.xpath("//label[normalize-space()='Contact Person Email']/following::input[1]");

    private By phoneNumber =
            By.xpath("//label[normalize-space()='Phone Number']/following::input[1]");

    private By description =
            By.xpath("//label[normalize-space()='Description']/following::textarea[1] | //label[normalize-space()='Description']/following::input[1]");

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

    private void jsClick(By locator) {
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

            Thread.sleep(1000);

            By option = By.xpath(
                    "//*[self::div or self::span or self::li or self::button]" +
                    "[normalize-space()='" + value + "' or contains(normalize-space(),'" + value + "')]"
            );

            WebElement optionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(option));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    optionElement
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    optionElement
            );

        } catch (Exception e) {
            throw new RuntimeException("Dropdown value not selected: " + value, e);
        }
    }

  public boolean isEditOrganizationPageOpened() {

    try {

        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Edit Organization')]")
                )
        );

        return title.isDisplayed();

    } catch (Exception e) {

        return false;
    }
}

    public boolean isPreFilledDataDisplayed() {
        try {
            return !getValue(organizationName).isEmpty()
                    && !getValue(companyWebsite).isEmpty()
                    && !getValue(address).isEmpty()
                    && !getValue(postalCode).isEmpty()
                    && !getValue(contactPersonName).isEmpty()
                    && !getValue(contactPersonEmail).isEmpty()
                    && !getValue(phoneNumber).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void updateOrganizationName(String value) {
        enterText(organizationName, value);
    }

    public void updateSector(String value) {
        selectDropdownValue(sectorDropdown, value);
    }

    public void updateCompanyWebsite(String value) {
        enterText(companyWebsite, value);
    }

    public void updateAddress(String value) {
        enterText(address, value);
    }

    public void updateLocation(String country, String state, String city) {
        selectDropdownValue(countryDropdown, country);

        wait.until(ExpectedConditions.elementToBeClickable(stateDropdown));
        selectDropdownValue(stateDropdown, state);

        wait.until(ExpectedConditions.elementToBeClickable(cityDropdown));
        selectDropdownValue(cityDropdown, city);
    }

    public void updatePostalCode(String value) {
        enterText(postalCode, value);
    }

    public void updateContactPersonName(String value) {
        enterText(contactPersonName, value);
    }

    public void updateContactPersonEmail(String value) {
        enterText(contactPersonEmail, value);
    }

    public void updatePhoneNumber(String value) {
        enterText(phoneNumber, value);
    }

    public void updateDescription(String value) {
        enterText(description, value);
    }

    public void clickUpdate() {
        jsClick(updateBtn);
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public boolean isReturnedToOrganizationList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("organizations"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[normalize-space()='Organizations']")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStateDropdownEnabled() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(stateDropdown)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCityDropdownEnabled() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(cityDropdown)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}