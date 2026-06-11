package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlantListPage {

    WebDriver driver;
    WebDriverWait wait;

    public PlantListPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By plantListTitle =
            By.xpath("//h1[contains(normalize-space(),'Plants')]");

    private By tableRows =
            By.xpath("//table//tbody//tr");

    private By organizationDropdown =
            By.xpath("//button[contains(normalize-space(),'All Organizations') or contains(normalize-space(),'Organizations')]");

    private By organizationSearchBox =
            By.xpath("//input[contains(@placeholder,'Search organization')]");

    private By viewOption =
            By.xpath("//*[contains(normalize-space(),'View')]");

    public boolean isPlantListPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(plantListTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganizationFilter(String organizationName) {
        try {
            WebElement dropdown = wait.until(
                    ExpectedConditions.elementToBeClickable(organizationDropdown)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    dropdown
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    dropdown
            );

            WebElement search = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(organizationSearchBox)
            );

            search.clear();
            search.sendKeys(organizationName);

            Thread.sleep(1000);

            search.sendKeys(Keys.ARROW_DOWN);
            Thread.sleep(300);
            search.sendKeys(Keys.ENTER);

            Thread.sleep(2500);

        } catch (Exception e) {
            throw new RuntimeException("Organization filter not selected: " + organizationName, e);
        }
    }

public void openViewPlantByName(String plantName) {
    try {
        By plantRow = By.xpath(
                "//table//tbody//tr[.//*[contains(normalize-space(),'" + plantName + "')]]"
        );

        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantRow)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                row
        );

        Thread.sleep(1000);

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

        Thread.sleep(1500);

        By viewOption = By.xpath(
                "(//*[@role='menuitem' or @data-slot='dropdown-menu-item' or self::button]"
                + "[contains(normalize-space(.),'View')])[1]"
        );

        WebElement view = wait.until(
                ExpectedConditions.presenceOfElementLocated(viewOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                view
        );

        Thread.sleep(500);

        try {
            new Actions(driver)
                    .moveToElement(view)
                    .pause(300)
                    .click()
                    .perform();
        } catch (Exception ex) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    view
            );
        }

        Thread.sleep(3000);

    } catch (Exception e) {
        throw new RuntimeException("View option not opened for plant: " + plantName, e);
    }
}
public void openEditPlantByName(String plantName) {
    openPlantActionByName(plantName, "Edit");

    wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("edit"),
            ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(normalize-space(),'Edit Plant') or contains(normalize-space(),'Update Plant')]")
            )
    ));
}
public boolean isUpdatedPlantVisible(String updatedPlantName) {
    try {
        By plantNameInTable = By.xpath(
                "//table//tbody//tr//td[contains(normalize-space(),'" + updatedPlantName + "')]"
        );

        WebElement plant = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantNameInTable)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                plant
        );

        return plant.isDisplayed();

    } catch (Exception e) {
        return false;
    }
}

public void openEditPlant() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'openEditPlant'");
}
private void openPlantActionByName(String plantName, String actionName) {
    try {
        By plantRow = By.xpath(
                "//table//tbody//tr[.//*[contains(normalize-space(),'" + plantName + "')]]"
        );

        WebElement row = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantRow)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                row
        );

        Thread.sleep(1000);

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

        Thread.sleep(1500);

        By actionOption = By.xpath(
                "(//*[@role='menuitem' or @data-slot='dropdown-menu-item' or self::button]" +
                "[contains(normalize-space(.),'" + actionName + "')])[1]"
        );

        WebElement action = wait.until(
                ExpectedConditions.presenceOfElementLocated(actionOption)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                action
        );

        Thread.sleep(500);

        try {
            new Actions(driver)
                    .moveToElement(action)
                    .pause(300)
                    .click()
                    .perform();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    action
            );
        }

        Thread.sleep(2500);

    } catch (Exception e) {
        throw new RuntimeException(
                actionName + " option not opened for plant: " + plantName,
                e
        );
    }
}
}