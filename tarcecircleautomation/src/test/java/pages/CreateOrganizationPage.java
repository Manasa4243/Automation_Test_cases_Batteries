package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    private By pageTitle = By.xpath("//h1[contains(normalize-space(),'New Organization')]");

    private By organizationName = By.xpath("//label[normalize-space()='Organization Name']/following::input[1]");
    private By sectorDropdown = By.xpath("//label[normalize-space()='Sector']/following::button[1]");
    private By companyWebsite = By.xpath("//label[normalize-space()='Company Website']/following::input[1]");
private By dropdownOption(String value) {
    return By.xpath(
        "//*[(@role='option' or @role='menuitem' or @cmdk-item='')" +
        " and normalize-space(.)='" + value + "']"
    );
}
    private By address = By.xpath("//label[normalize-space()='Address']/following::input[1]");
    private By countryDropdown = By.xpath("//label[normalize-space()='Country']/following::button[1]");
   
    private By stateDropdown = By.xpath("//label[contains(normalize-space(),'State')]/following::button[1]");
    private By cityDropdown =
    By.xpath("//label[normalize-space()='City']/following::button[1]");
    private By postalCode = By.xpath("//label[contains(normalize-space(),'Postal')]/following::input[1]");

    private By contactPersonName = By.xpath("//label[normalize-space()='Contact Person Name']/following::input[1]");
    private By contactPersonEmail = By.xpath("//label[normalize-space()='Contact Person Email']/following::input[1]");
    private By phoneNumber = By.xpath("//label[normalize-space()='Phone Number']/following::input[1]");

    private By description = By.xpath("//label[normalize-space()='Description']/following::textarea[1] | //label[normalize-space()='Description']/following::input[1]");

    private By createOrganizationBtn = By.xpath("//button[contains(normalize-space(),'Create Organization')]");
    private By cancelBtn = By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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

    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}

    public boolean isCreateOrganizationPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public void fillValidOrganizationData() {
        enterText(organizationName, "Test Organization " + System.currentTimeMillis());
        selectDropdownValue(sectorDropdown, "Battery (EU)");
        enterText(companyWebsite, "https://example.com");

        enterText(address, "Bangalore, Karnataka");
        selectDropdownValue(countryDropdown, "India");
        selectDropdownValue(stateDropdown, "Karnataka");
        selectDropdownValue(cityDropdown, "Bengaluru");
        enterText(postalCode, "560001");

        enterText(contactPersonName, "Manasa");
        enterText(contactPersonEmail, "manasa" + System.currentTimeMillis() + "@gmail.com");
        enterText(phoneNumber, "9876543210");

        enterText(description, "Automation test organization created using Selenium Java.");
    }
public void selectCountry(String country) {

    selectDropdownValue(countryDropdown, country);

    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[normalize-space()='Karnataka']")
    ));
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
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
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
}