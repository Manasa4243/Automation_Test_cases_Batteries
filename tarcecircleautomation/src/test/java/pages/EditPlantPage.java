package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
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
            By.xpath("//label[contains(normalize-space(),'Postal Code')]/following::input[1]");

    private By contactPersonName =
            By.xpath("//label[contains(normalize-space(),'Contact Person Name')]/following::input[1]");

    private By contactEmail =
            By.xpath("//label[contains(normalize-space(),'Contact Email')]/following::input[1]");

    private By phoneNumber =
            By.xpath("//label[contains(normalize-space(),'Phone Number')]/following::input[1]");

    private By description =
            By.xpath("//textarea[@placeholder='Enter plant description']");

    private By updateBtn =
            By.xpath("//button[contains(normalize-space(),'Update') or contains(normalize-space(),'Save')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    public boolean isEditPlantPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle),
                    ExpectedConditions.urlContains("edit")
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPrefilledDataDisplayed() {
    try {
        Thread.sleep(2000);

        String orgValue = getText(organizationDropdown);
        String plantCodeValue = getInputValue(plantCode);
        String plantNameValue = getInputValue(plantName);
        String address1Value = getInputValue(addressLine1);
        String address2Value = getInputValue(addressLine2);
        String countryValue = getText(countryDropdown);
        String stateValue = getText(stateDropdown);
        String cityValue = getText(cityDropdown);
        String contactNameValue = getInputValue(contactPersonName);
        String contactEmailValue = getInputValue(contactEmail);
        String phoneValue = getInputValue(phoneNumber);

        System.out.println("Organization = " + orgValue);
        System.out.println("Plant Code = " + plantCodeValue);
        System.out.println("Plant Name = " + plantNameValue);
        System.out.println("Address Line 1 = " + address1Value);
        System.out.println("Address Line 2 = " + address2Value);
        System.out.println("Country = " + countryValue);
        System.out.println("State = " + stateValue);
        System.out.println("City = " + cityValue);
        System.out.println("Contact Name = " + contactNameValue);
        System.out.println("Contact Email = " + contactEmailValue);
        System.out.println("Phone = " + phoneValue);

        return isNotEmpty(orgValue)
                && isNotEmpty(plantCodeValue)
                && isNotEmpty(plantNameValue)
                && isNotEmpty(address1Value)
                && isNotEmpty(address2Value)
                && isNotEmpty(countryValue)
                && isNotEmpty(stateValue)
                && isNotEmpty(cityValue)
                && isNotEmpty(contactNameValue)
                && isNotEmpty(contactEmailValue)
                && isNotEmpty(phoneValue);

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

private String getInputValue(By locator) {
    try {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        String value = element.getAttribute("value");

        if (value == null || value.trim().isEmpty()) {
            value = (String) ((JavascriptExecutor) driver)
                    .executeScript("return arguments[0].value;", element);
        }

        return value == null ? "" : value.trim();

    } catch (Exception e) {
        return "";
    }
}

private String getText(By locator) {
    try {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        String text = element.getText();

        if (text == null || text.trim().isEmpty()) {
            text = element.getAttribute("textContent");
        }

        return text == null ? "" : text.trim();

    } catch (Exception e) {
        return "";
    }
}

private boolean isNotEmpty(String value) {
    return value != null
            && !value.trim().isEmpty()
            && !value.trim().equalsIgnoreCase("Select")
            && !value.trim().contains("Select");
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

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        element.clear();
        element.sendKeys(value);
    }

   private String getValue(By locator) {

    WebElement element =
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    String value = element.getAttribute("value");

    System.out.println("Locator: " + locator);
    System.out.println("Value: " + value);

    return value;
}

    private void clickJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
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
}