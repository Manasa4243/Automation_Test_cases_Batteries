package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateEmployeePage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateEmployeePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle = By.xpath("//h1[contains(normalize-space(),'Create Employee') or contains(normalize-space(),'New Employee')]");

    private By organizationDropdown = By.xpath("//label[normalize-space()='Organization']/following::button[1]");
    private By plantDropdown = By.xpath("//label[normalize-space()='Plant']/following::button[1]");

    private By employeeName = By.xpath("//input[contains(@placeholder,'name') or contains(@placeholder,'Name')]");
    private By employeeEmail = By.xpath("//input[@type='email' or contains(@placeholder,'email')]");
    private By phoneNumber = By.xpath("//input[contains(@placeholder,'phone') or contains(@placeholder,'Phone')]");
    private By designation = By.xpath("//input[contains(@placeholder,'designation') or contains(@placeholder,'Designation')]");

    private By createEmployeeBtn = By.xpath("//button[contains(@class,'bg-emerald-700') and contains(.,'Add Employee')]");
    private By cancelBtn = By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private void enterText(By locator, String value) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
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

    public boolean isCreateEmployeePageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllFieldsVisible() {
        try {
            return driver.findElement(organizationDropdown).isDisplayed()
                    && driver.findElement(plantDropdown).isDisplayed()
                    && driver.findElement(employeeName).isDisplayed()
                    && driver.findElement(employeeEmail).isDisplayed()
                    && driver.findElement(phoneNumber).isDisplayed()
                    && driver.findElement(createEmployeeBtn).isDisplayed()
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

    public void selectOrganization(String organization) {
        selectDropdownValue(organizationDropdown, organization);
    }

    public void selectPlant(String plant) {
        selectDropdownValue(plantDropdown, plant);
    }

    public void fillValidEmployeeData() {
        long time = System.currentTimeMillis();

        selectOrganization("org1");

        wait.until(ExpectedConditions.elementToBeClickable(plantDropdown));
        selectPlant("Automation Plant");

        enterText(employeeName, "Employee " + time);
        enterText(employeeEmail, "employee" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");

        try {
            enterText(designation, "QA Tester");
        } catch (Exception ignored) {
        }
    }

    public void clickCreateEmployee() {
        clickJS(createEmployeeBtn);
    }

    public void clickCancel() {
        clickJS(cancelBtn);
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
}