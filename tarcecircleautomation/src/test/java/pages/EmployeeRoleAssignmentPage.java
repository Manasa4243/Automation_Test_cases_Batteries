package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeRoleAssignmentPage {

    WebDriver driver;
    WebDriverWait wait;

    public EmployeeRoleAssignmentPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'Add Employee Role Assignment')]");

    private By organizationDropdown =
            By.xpath("//label[normalize-space()='Organization']/following::button[1]");

    private By employeeDropdown =
            By.xpath("//label[normalize-space()='Employee']/following::button[1]");

    private By roleDropdown =
            By.xpath("//label[normalize-space()='Role']/following::button[1]");

    private By effectiveFrom =
            By.xpath("//label[contains(normalize-space(),'Effective From')]/following::input[1]");

    private By effectiveTo =
            By.xpath("//label[contains(normalize-space(),'Effective To')]/following::input[1]");

    private By addRoleBtn =
            By.xpath("//button[contains(normalize-space(),'Add Role')]");

    private By primaryRoleBtn =
            By.xpath("//button[contains(normalize-space(),'Primary Role')]");

    private By createAssignmentBtn =
            By.xpath("//button[contains(normalize-space(),'Create Assignment')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private By assignmentSection =
            By.xpath("//*[contains(normalize-space(),'Assignment #')]");

    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void enterText(By locator, String value) {

    try {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].removeAttribute('readonly');",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value='';",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value=arguments[1];",
                element,
                value
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));" +
                "arguments[0].dispatchEvent(new Event('blur', { bubbles: true }));",
                element
        );

    } catch (Exception e) {
        throw new RuntimeException("Unable to enter text/date: " + value, e);
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

        By searchInput = By.xpath(
                "//input[@role='combobox'] | //input[contains(@placeholder,'Search')] | //input"
        );

        try {
            WebElement input = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchInput)
            );

            input.clear();
            input.sendKeys(value);

            Thread.sleep(1000);

        } catch (Exception ignored) {
            // If dropdown has no search input, directly select option
        }

       By optionLocator = By.xpath(
        "//div[@role='option' and normalize-space()='Battery_Org']" +
        " | //div[normalize-space()='Battery_Org']" +
        " | //span[normalize-space()='Battery_Org']"
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

        wait.until(ExpectedConditions.textToBePresentInElementLocated(dropdown, value));

    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}

    public boolean isPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddEmployeeRolePageOpened() {
        return isPageOpened();
    }

    public boolean areAllFieldsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationDropdown)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(employeeDropdown)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(roleDropdown)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(effectiveFrom)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(effectiveTo)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(primaryRoleBtn)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(createAssignmentBtn)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openOrganizationDropdown() {
        jsClick(organizationDropdown);
    }

    public void openRoleDropdown() {
        jsClick(roleDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[normalize-space()='" + value + "' or contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganization(String organizationName) {
        selectDropdownValue(organizationDropdown, organizationName);
    }

   public void selectEmployee(String employeeName) {

    try {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(employeeDropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dropdown
        );

        Thread.sleep(1000);

        By employeeOption = By.xpath(
                "//*[normalize-space()='" + employeeName + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(employeeOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                option
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                option
        );

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException("Employee not selected: " + employeeName, e);
    }
}
   public void selectRole(String roleName) {

    try {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(roleDropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdown
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dropdown
        );

        Thread.sleep(1000);

        By roleOption = By.xpath(
                "//*[normalize-space()='" + roleName + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(roleOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                option
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                option
        );

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException("Role not selected: " + roleName, e);
    }
}
public boolean isEmployeeSelected(String employeeName) {
    try {
        WebElement employee = wait.until(
                ExpectedConditions.presenceOfElementLocated(employeeDropdown)
        );

        return employee.getText().contains(employeeName);

    } catch (Exception e) {
        return false;
    }
}
   public boolean isEmployeeDropdownEnabled() {

    try {

        Thread.sleep(3000);

        WebElement employee = wait.until(
                ExpectedConditions.presenceOfElementLocated(employeeDropdown)
        );

        String disabled = employee.getAttribute("disabled");
        String ariaDisabled = employee.getAttribute("aria-disabled");
        String className = employee.getAttribute("class");

        return (disabled == null || disabled.equals("false"))
                && (ariaDisabled == null || ariaDisabled.equals("false"))
                && !className.contains("disabled")
                && !className.contains("opacity-50");

    } catch (Exception e) {

        return false;
    }
}

    public void clickAddRole() {
        jsClick(addRoleBtn);
    }

    public boolean isNewRoleAssignmentAdded() {
        try {
            return driver.findElements(assignmentSection).size() >= 2;
        } catch (Exception e) {
            return false;
        }
    }
    

    public void clickPrimaryRole() {
        jsClick(primaryRoleBtn);
    }

    public boolean isPrimaryRoleSelected() {
        try {
            WebElement primary = wait.until(ExpectedConditions.visibilityOfElementLocated(primaryRoleBtn));
            return primary.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterEffectiveDates(String fromDate, String toDate) {
        enterText(effectiveFrom, fromDate);
        enterText(effectiveTo, toDate);
    }

    public void fillValidAssignmentData() {
        selectOrganization("Battery_Org");

        wait.until(ExpectedConditions.elementToBeClickable(employeeDropdown));
        selectEmployee("Manufaturer_admin");

        selectRole("Organization Admin");

        enterEffectiveDates("01-06-2026", "30-06-2026");
    }

    public void clickCreateAssignment() {
        jsClick(createAssignmentBtn);
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public boolean isReturnedToEmployeeRoleList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employee-role"),
                    ExpectedConditions.urlContains("roles"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Employee Role')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAssignmentCreatedOrSaved() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employee-role"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'success') or contains(normalize-space(),'created') or contains(normalize-space(),'Assigned')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }
}