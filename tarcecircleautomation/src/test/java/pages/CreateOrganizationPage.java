package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOrganizationPage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateOrganizationPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'New Organization')]");

    private By organizationName =
            By.xpath("//input[@placeholder='Enter organization name']");

    private By sectorDropdown =
            By.xpath("//*[normalize-space()='Select sector']");

private By companyWebsite =
        By.xpath("//label[normalize-space()='Company Website']/following::input[1]");
    private By address =
            By.xpath("//input[@placeholder='Enter address']");

    private By countryDropdown =
            By.xpath("//*[normalize-space()='Select country']");

    private By stateDropdown =
            By.xpath("//*[normalize-space()='Select state']");

    private By cityDropdown =
            By.xpath("//*[normalize-space()='Select city']");

    private By postalCode =
            By.xpath("//input[@placeholder='Enter postal code']");

    private By contactPersonName =
            By.xpath("//input[@placeholder='Enter contact person']");

    private By contactPersonEmail =
            By.xpath("//input[@placeholder='Enter email']");

    private By phoneNumber =
            By.xpath("//input[@placeholder='Enter phone number']");

    private By description =
            By.xpath("//textarea[@placeholder='Enter organization description']");

    private By createOrganizationBtn =
            By.xpath("//button[contains(normalize-space(),'Create Organization')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private void enterText(By locator, String value) {
    try {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        Thread.sleep(300);

        element.click();

        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);

        Thread.sleep(300);

    } catch (Exception e) {
        throw new RuntimeException("Unable to enter text: " + value, e);
    }
}
    private void jsClick(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.elementToBeClickable(locator)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    element
            );

        } catch (Exception e) {
            throw new RuntimeException("Unable to click element: " + locator, e);
        }
    }

    private void selectDropdownValue(By dropdown, String value) {
        try {
            WebElement dropdownElement = wait.until(
                    ExpectedConditions.elementToBeClickable(dropdown)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    dropdownElement
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    dropdownElement
            );

            Thread.sleep(500);

            By optionLocator = By.xpath(
                    "//*[(@role='option' or @role='menuitem' or @cmdk-item='' or contains(@class,'SelectItem'))" +
                    " and normalize-space(.)='" + value + "']"
            );

            WebElement option = wait.until(
                    ExpectedConditions.elementToBeClickable(optionLocator)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    option
            );

            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException("Dropdown value not selected: " + value, e);
        }
    }

    public boolean isCreateOrganizationPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillValidOrganizationData() {
        long time = System.currentTimeMillis();

        enterText(organizationName, "Test Organization " + time);

        selectDropdownValue(sectorDropdown, "Battery");

        enterText(companyWebsite, "https://example.com");
        enterText(address, "Bangalore, Karnataka");

        selectDropdownValue(countryDropdown, "India");
        selectDropdownValue(stateDropdown, "Karnataka");
        selectDropdownValue(cityDropdown, "Bengaluru");

        enterText(postalCode, "560001");
        enterText(contactPersonName, "Manasa");
        enterText(contactPersonEmail, "manasa" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");
        enterText(description, "Automation test organization created using Selenium Java.");
    }

    public void selectCountry(String country) {
        selectDropdownValue(countryDropdown, country);
    }

    public void selectState(String state) {
        selectDropdownValue(stateDropdown, state);
    }

    public void openSectorDropdown() {
        jsClick(sectorDropdown);
    }

    public void openCountryDropdown() {
        jsClick(countryDropdown);
    }

    public void openStateDropdown() {
        jsClick(stateDropdown);
    }

    public void openCityDropdown() {
        jsClick(cityDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[normalize-space()='" + value + "']");
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(option)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCreateOrganization() {
        jsClick(createOrganizationBtn);
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }
    // Add inside CreateOrganizationPage.java

public void clickCreateWithoutData() {
    clickCreateOrganization();
}

public boolean isValidationDisplayed() {
    try {
        String pageText = driver.getPageSource().toLowerCase();

        return pageText.contains("required")
                || pageText.contains("invalid")
                || pageText.contains("please")
                || pageText.contains("must")
                || pageText.contains("error");
    } catch (Exception e) {
        return false;
    }
}

public void enterOrganizationNameOnly(String value) {
    enterText(organizationName, value);
}

public void enterWebsiteOnly(String value) {
    enterText(companyWebsite, value);
}

public void enterAddressOnly(String value) {
    enterText(address, value);
}

public void enterPostalCodeOnly(String value) {
    enterText(postalCode, value);
}

public void enterContactPersonNameOnly(String value) {
    enterText(contactPersonName, value);
}

public void enterContactEmailOnly(String value) {
    enterText(contactPersonEmail, value);
}

public void enterPhoneNumberOnly(String value) {
    enterText(phoneNumber, value);
}

public void enterDescriptionOnly(String value) {
    enterText(description, value);
}

public void selectValidSector() {
    selectDropdownValue(sectorDropdown, "Battery");
}

public void selectValidCountry() {
    selectDropdownValue(countryDropdown, "India");
}

public void selectValidState() {
    selectDropdownValue(stateDropdown, "Karnataka");
}

public void selectValidCity() {
    selectDropdownValue(cityDropdown, "Bengaluru");
}

public void fillAllValidDataExceptOrganizationName() {
    selectValidSector();
    enterText(companyWebsite, "https://example.com");
    enterText(address, "Bangalore");
    selectValidCountry();
    selectValidState();
    selectValidCity();
    enterText(postalCode, "560001");
    enterText(contactPersonName, "Manasa");
    enterText(contactPersonEmail, "manasa" + System.currentTimeMillis() + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(description, "Automation validation test.");
}

public void fillAllValidDataExceptSector() {
    long time = System.currentTimeMillis();
    enterText(organizationName, "Test Org " + time);
    enterText(companyWebsite, "https://example.com");
    enterText(address, "Bangalore");
    selectValidCountry();
    selectValidState();
    selectValidCity();
    enterText(postalCode, "560001");
    enterText(contactPersonName, "Manasa");
    enterText(contactPersonEmail, "manasa" + time + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(description, "Automation validation test.");
}

public void fillAllValidDataExceptAddress() {
    long time = System.currentTimeMillis();
    enterText(organizationName, "Test Org " + time);
    selectValidSector();
    enterText(companyWebsite, "https://example.com");
    selectValidCountry();
    selectValidState();
    selectValidCity();
    enterText(postalCode, "560001");
    enterText(contactPersonName, "Manasa");
    enterText(contactPersonEmail, "manasa" + time + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(description, "Automation validation test.");
}
public void fillAllValidData() {
    long time = System.currentTimeMillis();

    enterText(organizationName, "Test Org " + time);
    selectValidSector();
    enterText(companyWebsite, "https://example.com");
    enterText(address, "Bangalore");
    selectValidCountry();
    selectValidState();
    selectValidCity();
    enterText(postalCode, "560001");
    enterText(contactPersonName, "Manasa");
    enterText(contactPersonEmail, "manasa" + time + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(description, "Automation validation test.");
}
public void scrollToFirstValidationError() {

    try {

        By errorLocator = By.xpath(
            "//*[contains(@class,'error') " +
            "or contains(@class,'invalid') " +
            "or contains(@class,'destructive') " +
            "or contains(text(),'Required') " +
            "or contains(text(),'required') " +
            "or contains(text(),'Invalid') " +
            "or contains(text(),'invalid')]"
        );

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior:'smooth', block:'center'});",
                errorElement
        );

    } catch (Exception e) {

        // Fallback - scroll to top of page
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0,0);"
        );
    }
}
}