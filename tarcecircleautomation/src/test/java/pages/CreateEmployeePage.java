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

    private By pageTitle =
        By.xpath("//h1[contains(normalize-space(),'New Employee')]");

private By organizationDropdown =
        By.xpath("//label[normalize-space()='Organization']/following::button[1]");

private By plantDropdown =
        By.xpath("//label[normalize-space()='Plant']/following::button[1]");

private By employeeName =
        By.xpath("//label[normalize-space()='Employee Name']/following::input[1]");

private By employeeEmail =
        By.xpath("//label[normalize-space()='Email Address']/following::input[1]");

private By phoneNumber =
        By.xpath("//label[normalize-space()='Phone Number']/following::input[1]");

private By designation =
        By.xpath("//label[normalize-space()='Designation']/following::input[1]");

private By department =
        By.xpath("//label[normalize-space()='Department']/following::input[1]");

private By createEmployeeBtn =
        By.xpath("//button[normalize-space()='Create Employee']");

private By cancelBtn =
        By.xpath("//button[normalize-space()='Cancel']");
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
    return isElementVisible(organizationDropdown)
            && isElementVisible(plantDropdown)
            && isElementVisible(employeeName)
            && isElementVisible(employeeEmail)
            && isElementVisible(phoneNumber)
            && isElementVisible(designation)
            && isElementVisible(department)
            && isElementVisible(createEmployeeBtn)
            && isElementVisible(cancelBtn);
}
public void clickPlantDropdown() {

    WebElement plant = wait.until(
            ExpectedConditions.elementToBeClickable(plantDropdown)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            plant
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            plant
    );
}
public boolean isPlantDropdownEnabled() {
    try {
        WebElement plant = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantDropdown)
        );

        return plant.isDisplayed() && plant.isEnabled();

    } catch (Exception e) {
        return false;
    }
}
private boolean isElementVisible(By locator) {
    try {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        return wait.until(
                ExpectedConditions.visibilityOf(element)
        ).isDisplayed();

    } catch (Exception e) {
        System.out.println("Field not visible: " + locator);
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

        selectOrganization("Exide Industries");

        wait.until(ExpectedConditions.elementToBeClickable(plantDropdown));
        selectPlant("Exide_plant");

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