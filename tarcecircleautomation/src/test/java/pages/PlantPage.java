package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlantPage {

    WebDriver driver;
    WebDriverWait wait;

    public PlantPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[normalize-space()='New Plant']");

    private By organizationDropdown =
            By.xpath("//label[normalize-space()='Organization']/following::button[1]");

  private By plantCode =
        By.xpath("//label[contains(normalize-space(),'Plant Code')]/following::input[1]");
    private By plantName =
            By.xpath("//label[contains(normalize-space(),'Plant Name')]/following::input[1]");

    private By addressLine1 =
            By.xpath("//label[contains(normalize-space(),'Address Line 1')]/following::input[1]");

    private By addressLine2 =
            By.xpath("//label[contains(normalize-space(),'Address Line 2')]/following::input[1]");

    private By countryDropdown =
            By.xpath("//label[normalize-space()='Country']/following::button[1]");

    private By stateDropdown =
            By.xpath("//label[contains(normalize-space(),'State')]/following::button[1]");

    private By cityDropdown =
            By.xpath("//label[normalize-space()='City']/following::button[1]");

    private By postalCode =
            By.xpath("//label[contains(normalize-space(),'Postal') or contains(normalize-space(),'ZIP')]/following::input[1]");

    private By contactPersonName =
            By.xpath("//label[contains(normalize-space(),'Contact Person Name')]/following::input[1]");

    private By contactEmail =
            By.xpath("//label[contains(normalize-space(),'Email')]/following::input[1]");

    private By phoneNumber =
            By.xpath("//label[contains(normalize-space(),'Phone')]/following::input[1]");

    private By description =
            By.xpath("//label[contains(normalize-space(),'Description')]/following::textarea[1]");

    private By createPlantBtn =
            By.xpath("//button[contains(normalize-space(),'Create Plant')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.clear();
        element.sendKeys(value);
    }

    private void clickJS(By locator) {
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

    public boolean isAddPlantPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllFieldsVisible() {
        try {
            return driver.findElement(organizationDropdown).isDisplayed()
                    && driver.findElement(plantCode).isDisplayed()
                    && driver.findElement(plantName).isDisplayed()
                    && driver.findElement(addressLine1).isDisplayed()
                    && driver.findElement(addressLine2).isDisplayed()
                    && driver.findElement(countryDropdown).isDisplayed()
                    && driver.findElement(stateDropdown).isDisplayed()
                    && driver.findElement(cityDropdown).isDisplayed()
                    && driver.findElement(postalCode).isDisplayed()
                    && driver.findElement(contactPersonName).isDisplayed()
                    && driver.findElement(contactEmail).isDisplayed()
                    && driver.findElement(phoneNumber).isDisplayed()
                    && driver.findElement(description).isDisplayed()
                    && driver.findElement(createPlantBtn).isDisplayed()
                    && driver.findElement(cancelBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openOrganizationDropdown() {
        clickJS(organizationDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[normalize-space()='" + value + "']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void fillValidPlantData() {
        long time = System.currentTimeMillis();

        selectDropdownValue(organizationDropdown, "Exide Industries");

        enterText(plantCode, "PLANT" + time);
        enterText(plantName, "Automation Plant " + time);
        enterText(addressLine1, "Bangalore Address Line 1");
        enterText(addressLine2, "Bangalore Address Line 2");

        selectDropdownValue(countryDropdown, "India");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Karnataka']")
        ));

        selectDropdownValue(stateDropdown, "Karnataka");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Bengaluru']")
        ));

        selectDropdownValue(cityDropdown, "Bengaluru");

        enterText(postalCode, "560001");
        enterText(contactPersonName, "Manasa Gowda");
        enterText(contactEmail, "plant" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");
        enterText(description, "Plant created using Selenium automation.");
    }

    public void selectCountryStateCity() {
        selectDropdownValue(countryDropdown, "India");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Karnataka']")
        ));

        selectDropdownValue(stateDropdown, "Karnataka");

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Bengaluru']")
        ));

        selectDropdownValue(cityDropdown, "Bengaluru");
    }

    public void fillPlantWithUniqueCode(String code) {
        long time = System.currentTimeMillis();

        selectDropdownValue(organizationDropdown, "Exide Industries");

        enterText(plantCode, code + time);
        enterText(plantName, "Unique Plant " + time);
        enterText(addressLine1, "Bangalore Address");
        enterText(addressLine2, "Address Line 2");

        selectCountryStateCity();

        enterText(postalCode, "560001");
        enterText(contactPersonName, "Manasa Gowda");
        enterText(contactEmail, "uniqueplant" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");
        enterText(description, "Unique plant code test.");
    }

    public void clickCreatePlant() {
        clickJS(createPlantBtn);
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