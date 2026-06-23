package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class OrganizationListPage {

    WebDriver driver;
    WebDriverWait wait;

    public OrganizationListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By createOrganizationBtn =
            By.xpath("//button[contains(normalize-space(),'Create Organization')]");

    private By organizationListPage =
            By.xpath("//h1[normalize-space()='Organizations']");

    private By tableRows =
            By.xpath("//table//tbody//tr");

    private By searchBox =
            By.xpath("//input[contains(@placeholder,'Search') or contains(@placeholder,'search')]");

    private By nextBtn =
            By.xpath("//*[normalize-space()='Next']");

    private By previousBtn =
            By.xpath("//*[normalize-space()='Previous']");

    private By currentPage =
            By.xpath("//a[@aria-current='page']");

    private By firstRowThreeDotMenu =
            By.xpath("(//table//tbody//tr[1]//button[@data-slot='dropdown-menu-trigger' and @aria-haspopup='menu'])[1]");

    private By actionMenuOptions =
            By.xpath("//*[@role='menuitem' or @data-slot='dropdown-menu-item' or normalize-space()='View' or normalize-space()='Edit' or normalize-space()='Delete']");

    private By editOption =
        By.xpath("//*[contains(@class,'dropdown') and normalize-space()='Edit'] | //*[@role='menuitem' and normalize-space()='Edit'] | //*[@data-slot='dropdown-menu-item' and normalize-space()='Edit'] | //*[normalize-space()='Edit']");
    private By clearFilterBtn =
            By.xpath("//button[contains(normalize-space(),'Clear')]");

    private By activeFilter =
            By.xpath("//button[contains(normalize-space(),'Active')]");
private By inactiveFilter =
        By.xpath("//button[contains(normalize-space(),'Inactive')]");
        private By deleteOption =
        By.xpath("//*[normalize-space()='Deactivate' or normalize-space()='Deactivate']");

public void deactivateOrganization(String orgName) {

    try {
        By orgRow = By.xpath(
                "//table//tbody//tr[.//*[contains(normalize-space(),'" + orgName + "')]]"
        );

        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(orgRow)
        );

        WebElement threeDots = row.findElement(
                By.xpath(".//button[@data-slot='dropdown-menu-trigger' and @aria-haspopup='menu']")
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                threeDots
        );

        Thread.sleep(500);

        new Actions(driver)
                .moveToElement(threeDots)
                .pause(500)
                .click()
                .perform();

        Thread.sleep(1000);

        WebElement deactivateOption = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[@role='menuitem' and normalize-space()='Deactivate'] | //*[@data-slot='dropdown-menu-item' and normalize-space()='Deactivate'] | //*[normalize-space()='Deactivate']")
                )
        );

        new Actions(driver)
                .moveToElement(deactivateOption)
                .pause(300)
                .click()
                .perform();

        WebElement confirmBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(normalize-space(),'Confirm') or contains(normalize-space(),'Deactivate') or contains(normalize-space(),'Delete')]")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                confirmBtn
        );

        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(2000);

    } catch (Exception e) {
        throw new RuntimeException("Failed to deactivate organization: " + orgName, e);
    }
}
public boolean isOrganizationDeactivated(String orgName) {
    try {
        driver.navigate().refresh();
        Thread.sleep(2000);

        By inactiveStatus = By.xpath(
                "//table//tbody//tr[td[contains(normalize-space(),'" + orgName + "')]]//*[contains(normalize-space(),'Inactive')]"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(inactiveStatus)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}
    public boolean isOrganizationListPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationListPage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCreatedOrganizationVisible(String orgName) {
        try {
            By orgValue = By.xpath("//*[contains(normalize-space(),'" + orgName + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(orgValue)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCreateOrganization() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(createOrganizationBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public boolean isCreateOrganizationButtonVisible() {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(createOrganizationBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areOrganizationRecordsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

   public boolean isColumnDisplayed(String columnName) {
    try {
        By column = By.xpath(
                "//th[contains(translate(normalize-space(.),'abcdefghijklmnopqrstuvwxyz','ABCDEFGHIJKLMNOPQRSTUVWXYZ'),'"
                        + columnName.toUpperCase()
                        + "')]"
        );

        return wait.until(ExpectedConditions.visibilityOfElementLocated(column)).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}
public void selectInactiveFilter() {
    try {
        WebElement filter = wait.until(
                ExpectedConditions.elementToBeClickable(activeFilter)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                filter
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                filter
        );

        Thread.sleep(500);

        By inactiveOption = By.xpath(
                "//*[(@role='option' or @role='menuitem' or @data-slot='dropdown-menu-item')"
                        + " and normalize-space()='Inactive']"
                        + " | //*[normalize-space()='Inactive']"
        );

        WebElement inactive = wait.until(
                ExpectedConditions.elementToBeClickable(inactiveOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                inactive
        );

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException("Inactive option was not selected from filter dropdown", e);
    }
}
private void selectStatusFilter(String string) {
    throw new UnsupportedOperationException("Unimplemented method 'selectStatusFilter'");
}



public boolean areOnlyInactiveOrganizationsDisplayed() {
    try {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

        String bodyText = driver.findElement(By.tagName("body")).getText();

        return bodyText.contains("Inactive");

    } catch (Exception e) {
        return false;
    }
}
    public int getOrganizationCount() {
        try {
            List<WebElement> rows = driver.findElements(tableRows);
            return rows.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void searchOrganization(String orgName) {
        WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox));
        search.clear();
        search.sendKeys(orgName);
    }

    public boolean isSearchResultDisplayed(String orgName) {
        return isCreatedOrganizationVisible(orgName);
    }

    public boolean isCreateOrganizationPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("create"),
                    ExpectedConditions.urlContains("new"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Organization')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

   public boolean isPaginationWorking() {
    try {

        WebElement page2 = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[normalize-space()='2']")
                )
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                page2
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                page2
        );

        Thread.sleep(2000);

        return driver.getPageSource().contains("2");

    } catch (Exception e) {
        return false;
    }
}
   public void clickThreeDotMenu() {
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

        Actions actions = new Actions(driver);
        actions.moveToElement(menu).pause(500).click().perform();

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

        WebElement edit = wait.until(
                ExpectedConditions.elementToBeClickable(editOption)
        );

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

    public void selectActiveFilter() {
        try {
            WebElement active = wait.until(
                    ExpectedConditions.elementToBeClickable(activeFilter)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    active
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    active
            );

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Active filter was not selected", e);
        }
    }

    public boolean areOnlyActiveOrganizationsDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            List<WebElement> rows = driver.findElements(tableRows);

            if (rows.isEmpty()) {
                return false;
            }

            for (WebElement row : rows) {
                String rowText = row.getText().toLowerCase();

                if (!rowText.contains("active")) {
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void clickClearFilter() {
        try {
            WebElement clear = wait.until(
                    ExpectedConditions.elementToBeClickable(clearFilterBtn)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    clear
            );

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Clear filter button was not clicked", e);
        }
    }

    public boolean isFilterCleared() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            String pageText = driver.getPageSource().toLowerCase();

            return !pageText.contains("active filter applied")
                    && !pageText.contains("filter applied");

        } catch (Exception e) {
            return false;
        }
    }
    public void clickViewFromActionMenu() {
    try {
        clickThreeDotMenu();

        WebElement view = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//*[normalize-space()='View']")
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", view);

        Thread.sleep(2000);

    } catch (Exception e) {
        throw new RuntimeException("View option not clicked", e);
    }
}
}