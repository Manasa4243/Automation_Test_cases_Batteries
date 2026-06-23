package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            By.xpath("//*[normalize-space()='Select organization']");

    private By plantCode =
            By.xpath("//input[@placeholder='Enter plant code']");

    private By plantName =
            By.xpath("//input[@placeholder='Enter plant name']");

    private By addressLine1 =
            By.xpath("//input[@placeholder='Enter address line 1']");

    private By addressLine2 =
            By.xpath("//input[@placeholder='Enter address line 2']");

    private By countryDropdown =
            By.xpath("//*[normalize-space()='Select country']");

    private By stateDropdown =
            By.xpath("//*[normalize-space()='Select state']");

    private By cityDropdown =
            By.xpath("//*[normalize-space()='Select city']");

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

    private By createPlantBtn =
            By.xpath("//button[contains(normalize-space(),'Create Plant')]");

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

    private void clickJS(By locator) {
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

    public boolean isAddPlantPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle)
            ).isDisplayed();
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

    public void openCountryDropdown() {
        clickJS(countryDropdown);
    }

    public void openStateDropdown() {
        clickJS(stateDropdown);
    }

    public void openCityDropdown() {
        clickJS(cityDropdown);
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

    public void fillValidPlantData() {
        long time = System.currentTimeMillis();

        selectDropdownValue(organizationDropdown, "Exide Industries");

        enterText(plantCode, "PLANT" + time);
        enterText(plantName, "Automation Plant " + time);
        enterText(addressLine1, "Bangalore Address Line 1");
        enterText(addressLine2, "Bangalore Address Line 2");

        selectDropdownValue(countryDropdown, "India");
        selectDropdownValue(stateDropdown, "Karnataka");
        selectDropdownValue(cityDropdown, "Bengaluru");

        enterText(postalCode, "560001");
        enterText(contactPersonName, "Manasa Gowda");
        enterText(contactEmail, "plant" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");
        enterText(description, "Plant created using Selenium automation.");
    }

    public void selectCountryStateCity() {
        selectDropdownValue(countryDropdown, "India");
        selectDropdownValue(stateDropdown, "Karnataka");
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
    public void fillAllValidPlantData() {
    long time = System.currentTimeMillis();

    selectDropdownValue(organizationDropdown, "Exide Industries");
    enterText(plantCode, "PLANT" + time);
    enterText(plantName, "Automation Plant " + time);
    enterText(addressLine1, "Bangalore Address Line 1");
    enterText(addressLine2, "Bangalore Address Line 2");

    selectDropdownValue(countryDropdown, "India");
    selectDropdownValue(stateDropdown, "Karnataka");
    selectDropdownValue(cityDropdown, "Bengaluru");

    enterText(postalCode, "560001");
    enterText(contactPersonName, "Manasa Gowda");
    enterText(contactEmail, "plant" + time + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(description, "Plant validation automation test.");
}

public boolean isValidationDisplayed() {
    try {
        String text = driver.getPageSource().toLowerCase();

        return text.contains("required")
                || text.contains("invalid")
                || text.contains("already")
                || text.contains("duplicate")
                || text.contains("must")
                || text.contains("error")
                || text.contains("please");
    } catch (Exception e) {
        return false;
    }
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

        WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                error
        );

    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }
}

public void submitAndScrollToError() {
    clickCreatePlant();
    scrollToFirstValidationError();
}

public void enterPlantCodeOnly(String value) {
    enterText(plantCode, value);
}

public void enterPlantNameOnly(String value) {
    enterText(plantName, value);
}

public void enterAddressLine1Only(String value) {
    enterText(addressLine1, value);
}

public void enterPostalCodeOnly(String value) {
    enterText(postalCode, value);
}

public void enterContactPersonNameOnly(String value) {
    enterText(contactPersonName, value);
}

public void enterContactEmailOnly(String value) {
    enterText(contactEmail, value);
}

public void enterPhoneNumberOnly(String value) {
    enterText(phoneNumber, value);
}

public void enterDescriptionOnly(String value) {
    enterText(description, value);
}

public void selectValidOrganization() {
    selectDropdownValue(organizationDropdown, "Exide Industries");
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
}