package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
            By.xpath("//*[normalize-space()='Select Organization']");

    private By employeeDropdown =
            By.xpath("//*[normalize-space()='Select Organization First' or normalize-space()='Select Employee']");

    private By plantDropdown =
            By.xpath("(//*[contains(@class,'select') or @role='combobox'])[3] | //label[normalize-space()='Plant Name']/following::*[@role='combobox'][1]");

  private By roleDropdown =
        By.xpath("//button[@role='combobox' and .//span[@data-slot='select-value' and (normalize-space()='Select role' or normalize-space()='Organization Admin' or normalize-space()='Super Admin')]]");
    private By effectiveFrom =
            By.xpath("//input[@placeholder='dd-mm-yyyy'][1]");

    private By effectiveTo =
            By.xpath("(//input[@placeholder='dd-mm-yyyy'])[2]");

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

            element.click();
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
            element.sendKeys(value);

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

        Thread.sleep(700);

        By optionLocator = By.xpath(
                "//*[@role='option' and normalize-space()='" + value + "']" +
                " | //*[@role='menuitem' and normalize-space()='" + value + "']" +
                " | //*[@data-slot='select-item' and normalize-space()='" + value + "']" +
                " | //*[normalize-space()='" + value + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                option
        );

        Thread.sleep(700);

    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}

    public boolean isPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle)
            ).isDisplayed();
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

    public void openEmployeeDropdown() {
        jsClick(employeeDropdown);
    }

    public void openRoleDropdown() {
        jsClick(roleDropdown);
    }

    public void openPlantDropdown() {
        jsClick(plantDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[normalize-space()='" + value + "' or contains(normalize-space(),'" + value + "')]");
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(option)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganization(String organizationName) {
        selectDropdownValue(organizationDropdown, organizationName);
    }

    public void selectEmployee(String employeeName) {
        selectDropdownValue(employeeDropdown, employeeName);
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

        Thread.sleep(700);

        By roleOption = By.xpath(
                "//div[normalize-space()='" + roleName + "']" +
                " | //span[normalize-space()='" + roleName + "']" +
                " | //*[@role='option' and normalize-space()='" + roleName + "']" +
                " | //*[normalize-space()='" + roleName + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(roleOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                option
        );

        Thread.sleep(700);

    } catch (Exception e) {
        throw new RuntimeException("Role not selected: " + roleName, e);
    }
}
    public void selectPlant(String plantName) {
        selectDropdownValue(plantDropdown, plantName);
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
            Thread.sleep(1500);

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
            WebElement primary = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(primaryRoleBtn)
            );
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
        selectOrganization("Exide Industries");

        wait.until(ExpectedConditions.elementToBeClickable(employeeDropdown));
        selectEmployee("Prabha");

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
    public void printRoleDropdownValues() {
    jsClick(roleDropdown);

    java.util.List<WebElement> options = driver.findElements(
            By.xpath("//*[@role='option' or @role='menuitem' or @data-slot='select-item' or contains(@class,'SelectItem')]")
    );

    for (WebElement option : options) {
        System.out.println("Role option: " + option.getText());
    }
}
}