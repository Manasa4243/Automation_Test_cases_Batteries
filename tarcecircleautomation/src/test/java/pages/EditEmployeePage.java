package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditEmployeePage {

    WebDriver driver;
    WebDriverWait wait;

    public EditEmployeePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'Edit Employee') or contains(normalize-space(),'Update Employee')]");

    private By organizationDropdown =
            By.xpath("//label[normalize-space()='Organization']/following::button[1]");

    private By plantDropdown =
            By.xpath("//label[normalize-space()='Plant']/following::button[1]");

    private By employeeName =
            By.xpath("//input[contains(@placeholder,'name') or contains(@placeholder,'Name')]");

    private By employeeEmail =
            By.xpath("//input[@type='email' or contains(@placeholder,'email') or contains(@placeholder,'Email')]");

    private By phoneNumber =
            By.xpath("//input[contains(@placeholder,'phone') or contains(@placeholder,'Phone')]");

    private By designation =
            By.xpath("//input[contains(@placeholder,'designation') or contains(@placeholder,'Designation')]");

    private By department =
            By.xpath("//label[normalize-space()='Department']/following::button[1] | //input[contains(@placeholder,'Department') or contains(@placeholder,'department')]");

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
        WebElement dropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(dropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        Thread.sleep(500);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dropdownElement
        );

        Thread.sleep(1000);

        // Search inside dropdown if search box is available
        try {
            By searchBox = By.xpath(
                    "//input[contains(@placeholder,'Search') or contains(@placeholder,'search')]"
            );

            WebElement search = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchBox)
            );

            search.clear();
            search.sendKeys(value);

            Thread.sleep(1000);

        } catch (Exception ignored) {
        }

        By option = By.xpath(
                "//*[self::div or self::span or self::li or self::button or @role='option' or @role='menuitem']" +
                "[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" +
                value.toLowerCase() + "')]"
        );

        WebElement optionElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(option)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                optionElement
        );

        Thread.sleep(500);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                optionElement
        );

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}

    public boolean isEditEmployeePageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPreFilledDataDisplayed() {
        try {
            return !getValue(employeeName).isEmpty()
                    && !getValue(employeeEmail).isEmpty()
                    && !getValue(phoneNumber).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public void updateOrganization(String value) {
        selectDropdownValue(organizationDropdown, value);
    }

    public void updatePlant(String value) {
        selectDropdownValue(plantDropdown, value);
    }

    public void updateEmployeeName(String value) {
        enterText(employeeName, value);
    }

    public void updateEmail(String value) {
        enterText(employeeEmail, value);
    }

    public void updatePhoneNumber(String value) {
        enterText(phoneNumber, value);
    }

    public void updateDesignation(String value) {
        enterText(designation, value);
    }

    public void updateDepartment(String value) {
        try {
            selectDropdownValue(department, value);
        } catch (Exception e) {
            enterText(department, value);
        }
    }

    public void clickUpdate() {
        jsClick(updateBtn);
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public boolean isReturnedToEmployeeList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employees"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Employees')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantDropdownEnabled() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(plantDropdown)).isEnabled();
        } catch (Exception e) {
            return false;
        }
    }
}