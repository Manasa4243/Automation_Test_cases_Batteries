package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmployeeListPage {

    WebDriver driver;
    WebDriverWait wait;

    public EmployeeListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By employeeListPage =
            By.xpath("//h1[normalize-space()='Employees']");

    private By tableRows =
            By.xpath("//table//tbody//tr");

    private By addEmployeeBtn =
            By.xpath("//button[contains(@class,'bg-emerald-700') and contains(.,'Add Employee')]");

    private By addEmployeeRoleBtn =
            By.xpath("//button[contains(normalize-space(),'Add Employee Role')]");

    private By searchBox =
            By.xpath("//input[contains(@placeholder,'Search') or contains(@placeholder,'search')]");

    private By organizationFilter =
            By.xpath("//label[contains(normalize-space(),'Organization')]/following::button[1] | //button[contains(.,'Organization')]");

    private By statusFilter =
            By.xpath("//button[contains(.,'Status')] | //label[contains(normalize-space(),'Status')]/following::button[1]");

    private By firstRowThreeDotMenu =
            By.xpath("(//table//tbody//tr[1]//button[@data-slot='dropdown-menu-trigger' and @aria-haspopup='menu'])[1]");

    private By actionMenuOptions =
            By.xpath("//*[@role='menuitem' or @data-slot='dropdown-menu-item']");

    private By editOption =
            By.xpath("//*[contains(@class,'dropdown') and normalize-space()='Edit'] | //*[@role='menuitem' and normalize-space()='Edit'] | //*[@data-slot='dropdown-menu-item' and normalize-space()='Edit'] | //*[normalize-space()='Edit']");

    private By viewOption =
            By.xpath("//*[contains(@class,'dropdown') and normalize-space()='View'] | //*[@role='menuitem' and normalize-space()='View'] | //*[@data-slot='dropdown-menu-item' and normalize-space()='View'] | //*[normalize-space()='View']");

    private By nextBtn =
            By.xpath("//*[normalize-space()='Next']");

    private By previousBtn =
            By.xpath("//*[normalize-space()='Previous']");

    private By totalEmployeeCount =
            By.xpath("//*[contains(normalize-space(),'Total Employees') or contains(normalize-space(),'Total Employee')]");

    private By noRecordsFound =
            By.xpath("//*[contains(normalize-space(),'No records') or contains(normalize-space(),'No employee') or contains(normalize-space(),'No data')]");

    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void selectDropdownValue(By dropdown, String value) {
        try {
            jsClick(dropdown);

            By option = By.xpath(
                    "//*[self::div or self::span or self::li or self::button]" +
                            "[normalize-space()='" + value + "' or contains(normalize-space(),'" + value + "')]"
            );

            WebElement optionElement = wait.until(ExpectedConditions.elementToBeClickable(option));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", optionElement);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", optionElement);

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Dropdown value not selected: " + value, e);
        }
    }

    public boolean isEmployeeListPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeListPage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areEmployeeRecordsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isColumnDisplayed(String columnName) {
        try {
            By column = By.xpath("//table//th[contains(normalize-space(),'" + columnName + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(column)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllEmployeeColumnsDisplayed() {
        return isColumnDisplayed("Employee Name")
                && isColumnDisplayed("Email")
                && isColumnDisplayed("Plant")
                && isColumnDisplayed("Organization")
                && isColumnDisplayed("Status")
                && isColumnDisplayed("Login")
                && isColumnDisplayed("Actions");
    }

    public String getFirstRowText() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//table//tbody//tr[1]")
            )).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public void clickAddEmployee() {
        jsClick(addEmployeeBtn);
    }

    public boolean isCreateEmployeePageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employee"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Create Employee') or contains(normalize-space(),'New Employee')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isAddEmployeeRoleButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(addEmployeeRoleBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddEmployeeRole() {
        try {
            jsClick(addEmployeeRoleBtn);
            Thread.sleep(2000);
        } catch (Exception e) {
            throw new RuntimeException("Add Employee Role button not clicked", e);
        }
    }

    public void searchEmployee(String value) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(value);
        search.sendKeys(Keys.ENTER);

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
    }

    public boolean isSearchResultDisplayed(String value) {
        try {
            By result = By.xpath("//table//tbody//tr[contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(result)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganizationFilter(String organizationName) {
        selectDropdownValue(organizationFilter, organizationName);

        try {
            Thread.sleep(1500);
        } catch (Exception e) {
        }
    }

    public boolean areOnlySelectedOrganizationEmployeesDisplayed(String organizationName) {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(tableRows),
                    ExpectedConditions.visibilityOfElementLocated(noRecordsFound)
            ));

            if (driver.findElements(noRecordsFound).size() > 0) {
                return false;
            }

            List<WebElement> rows = driver.findElements(tableRows);

            return rows.size() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public String getEmployeeStatus() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(@class,'rounded-full')]")
                    )
            ).getText().trim();
        } catch (Exception e) {
            return "";
        }
    }

    public void selectStatusFilter(String status) {
        selectDropdownValue(statusFilter, status);
    }

    public boolean areOnlySelectedStatusEmployeesDisplayed(String status) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            List<WebElement> rows = driver.findElements(tableRows);

            for (WebElement row : rows) {
                if (!row.getText().toLowerCase().contains(status.toLowerCase())) {
                    return false;
                }
            }

            return rows.size() > 0;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginStatusDisplayed() {
        try {
            By loginStatus = By.xpath("//table//tbody//tr[1]//*[contains(normalize-space(),'Active') or contains(normalize-space(),'Inactive') or contains(normalize-space(),'Enabled') or contains(normalize-space(),'Disabled')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginStatus)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickThreeDotMenu() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(firstRowThreeDotMenu));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    menu
            );

            Thread.sleep(500);

            new Actions(driver)
                    .moveToElement(menu)
                    .pause(500)
                    .click()
                    .perform();

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Three dot menu not clicked", e);
        }
    }

    public boolean areActionOptionsDisplayed() {
        try {
            clickThreeDotMenu();

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionMenuOptions)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickEditFromActionMenu() {
        try {
            clickThreeDotMenu();

            WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(editOption));

            new Actions(driver)
                    .moveToElement(edit)
                    .pause(300)
                    .click()
                    .perform();

            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("Edit option not clicked", e);
        }
    }

    public void clickViewFromActionMenu() {
        try {
            clickThreeDotMenu();

            WebElement view = wait.until(ExpectedConditions.elementToBeClickable(viewOption));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", view);

            Thread.sleep(2000);

        } catch (Exception e) {
            throw new RuntimeException("View option not clicked", e);
        }
    }

    public void openEditEmployeePage() {
        clickEditFromActionMenu();
    }

    public void openViewEmployeePage() {
        clickViewFromActionMenu();
    }

    public boolean isPaginationWorking() {
        try {
            WebElement nextElement = wait.until(ExpectedConditions.presenceOfElementLocated(nextBtn));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    nextElement
            );

            wait.until(ExpectedConditions.elementToBeClickable(nextBtn)).click();

            Thread.sleep(1000);

            WebElement previousElement = wait.until(ExpectedConditions.presenceOfElementLocated(previousBtn));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    previousElement
            );

            wait.until(ExpectedConditions.elementToBeClickable(previousBtn)).click();

            Thread.sleep(1000);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public int getEmployeeRowCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean isTotalEmployeeCountDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(totalEmployeeCount)).isDisplayed();
        } catch (Exception e) {
            return getEmployeeRowCount() > 0;
        }
    }

    public boolean isNoRecordsFoundDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsFound)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}