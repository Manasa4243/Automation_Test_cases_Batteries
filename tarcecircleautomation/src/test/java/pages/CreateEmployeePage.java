package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            By.xpath("//*[normalize-space()='Select organization']");

    private By plantDropdown =
            By.xpath("//*[normalize-space()='Select organization first' or normalize-space()='Select plant']");

    private By employeeName =
            By.xpath("//input[@placeholder='Enter employee name']");

    private By employeeEmail =
            By.xpath("//input[@placeholder='Enter email address']");

    private By phoneNumber =
            By.xpath("//input[@placeholder='Enter phone number']");

    private By designation =
            By.xpath("//input[@placeholder='Enter designation']");

    private By department =
            By.xpath("//input[@placeholder='Enter department']");

    private By createEmployeeBtn =
            By.xpath("//button[normalize-space()='Create Employee']");

    private By cancelBtn =
            By.xpath("//button[normalize-space()='Cancel']");

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

    public boolean isCreateEmployeePageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle)
            ).isDisplayed();
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

    public void clickPlantDropdown() {
        clickJS(plantDropdown);
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

        selectPlant("Exide Plant");

        enterText(employeeName, "Employee " + time);
        enterText(employeeEmail, "employee" + time + "@gmail.com");
        enterText(phoneNumber, "9876543210");
        enterText(designation, "QA Tester");
        enterText(department, "Quality Assurance");
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
    public void fillAllValidEmployeeData() {
    long time = System.currentTimeMillis();

    selectOrganization("Exide Industries");
    wait.until(ExpectedConditions.elementToBeClickable(plantDropdown));
    selectPlant("Exide Plant");

    enterText(employeeName, "Employee " + time);
    enterText(employeeEmail, "employee" + time + "@gmail.com");
    enterText(phoneNumber, "9876543210");
    enterText(designation, "QA Tester");
    enterText(department, "Quality Assurance");
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
    clickCreateEmployee();
    scrollToFirstValidationError();
}

public void enterEmployeeNameOnly(String value) {
    enterText(employeeName, value);
}

public void enterEmployeeEmailOnly(String value) {
    enterText(employeeEmail, value);
}

public void enterPhoneNumberOnly(String value) {
    enterText(phoneNumber, value);
}

public void enterDesignationOnly(String value) {
    enterText(designation, value);
}

public void enterDepartmentOnly(String value) {
    enterText(department, value);
}

public void selectValidOrganization() {
    selectOrganization("Exide Industries");
}

public void selectValidPlant() {
    selectPlant("Exide Plant");
}
}