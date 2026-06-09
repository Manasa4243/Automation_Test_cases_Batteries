package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeRoleListPage {

    WebDriver driver;
    WebDriverWait wait;

    public EmployeeRoleListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'Employee Role Management')]");

    private By addEmployeeRoleBtn =
            By.xpath("//button[contains(normalize-space(),'Add Employee Role')]");

    private By searchInput =
            By.xpath("//input[contains(@placeholder,'Search')]");

    private By tableRows =
            By.xpath("//table//tbody/tr");

    private By firstRowThreeDotMenu =
            By.xpath("(//table//tbody//tr[1]//button)[last()]");

    private By actionMenuOptions =
            By.xpath("//*[normalize-space()='View' or normalize-space()='Edit' or contains(normalize-space(),'Activate') or contains(normalize-space(),'Deactivate')]");

    private By viewOption =
            By.xpath("//*[normalize-space()='View']");

    private By editOption =
            By.xpath("//*[normalize-space()='Edit']");

    private By activateOption =
            By.xpath("//*[contains(normalize-space(),'Activate')]");

    private By deactivateOption =
            By.xpath("//*[contains(normalize-space(),'Deactivate')]");

    private By nextBtn =
            By.xpath("//button[contains(normalize-space(),'Next')]");

    private By previousBtn =
            By.xpath("//button[contains(normalize-space(),'Previous')]");

    private void jsClick(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
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

    public boolean isAddEmployeeRoleButtonVisible() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(addEmployeeRoleBtn)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickAddEmployeeRole() {
        jsClick(addEmployeeRoleBtn);
    }

    public boolean isAddEmployeeRolePageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employee-role"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Add Employee Role Assignment')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isHeaderDisplayed(String headerName) {
        try {
            By header = By.xpath("//th[contains(normalize-space(),'" + headerName + "')]");
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(header)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areRecordsDisplayed() {
        try {
            wait.until(
                    ExpectedConditions.numberOfElementsToBeMoreThan(tableRows, 0)
            );
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSearchFieldVisible() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(searchInput)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void search(String value) {
        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        search.clear();
        search.sendKeys(value);
    }

    public boolean isSearchResultDisplayed(String value) {
        try {
            By result = By.xpath(
                    "//table//tbody/tr[contains(translate(normalize-space(.)," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'" +
                            value.toLowerCase() + "')]"
            );

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(result)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clearSearch() {
        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );

        search.clear();
    }

    public void openActionsMenu() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            WebElement menu = wait.until(
                    ExpectedConditions.elementToBeClickable(firstRowThreeDotMenu)
            );

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

    public boolean isActionsMenuOpened() {
        try {
            WebElement option = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionMenuOptions)
            );

            return option.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickViewFromActionMenu() {
        try {
            openActionsMenu();

            WebElement view = wait.until(
                    ExpectedConditions.elementToBeClickable(viewOption)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", view);

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("View option not clicked", e);
        }
    }

    public void clickEditFromActionMenu() {
        try {
            openActionsMenu();

            WebElement edit = wait.until(
                    ExpectedConditions.elementToBeClickable(editOption)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", edit);

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Edit option not clicked", e);
        }
    }

    public void clickActivateFromActionMenu() {
        try {
            openActionsMenu();

            WebElement activate = wait.until(
                    ExpectedConditions.elementToBeClickable(activateOption)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", activate);

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Activate option not clicked", e);
        }
    }

    public void clickDeactivateFromActionMenu() {
        try {
            openActionsMenu();

            WebElement deactivate = wait.until(
                    ExpectedConditions.elementToBeClickable(deactivateOption)
            );

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", deactivate);

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Deactivate option not clicked", e);
        }
    }

    public void clickNext() {
        jsClick(nextBtn);
    }

    public void clickPrevious() {
        jsClick(previousBtn);
    }

    public void clickPageNumber(String number) {
        jsClick(By.xpath("//button[normalize-space()='" + number + "']"));
    }

    public boolean isViewPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("view"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'Employee Role Details')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEditPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("edit"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'Edit Employee Role')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }
}