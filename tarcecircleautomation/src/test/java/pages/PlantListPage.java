package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
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

    private By firstRowThreeDot =
            By.xpath("(//table//tbody//tr[1]//button[@data-slot='dropdown-menu-trigger' and @aria-haspopup='menu'])[1]");

    private By actionMenuOptions =
            By.xpath("//*[normalize-space()='View' or normalize-space()='Edit' or normalize-space()='Delete']");

    private By viewOption =
            By.xpath("//*[normalize-space()='View']");

    private By editOption =
            By.xpath("//*[normalize-space()='Edit']");

    private By deleteOption =
            By.xpath("//*[normalize-space()='Delete']");

    public boolean isPlantListPageOpened() {
        try {
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(plantListTitle)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickThreeDots() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(plantListTitle));
            wait.until(ExpectedConditions.visibilityOfElementLocated(tableRows));

            WebElement menu = wait.until(
                    ExpectedConditions.elementToBeClickable(firstRowThreeDot)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    menu
            );

            Thread.sleep(500);

            new Actions(driver)
                    .moveToElement(menu)
                    .pause(300)
                    .click()
                    .perform();

            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionMenuOptions)
            );

        } catch (Exception e) {
            throw new RuntimeException("Three dots menu not clicked", e);
        }
    }

    public boolean areActionOptionsDisplayed() {
        try {
            clickThreeDots();

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(actionMenuOptions)
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void openEditPlant() {
        clickThreeDots();
        clickEditOption();
    }

    public void clickEditOption() {
        try {
            WebElement edit = wait.until(
                    ExpectedConditions.elementToBeClickable(editOption)
            );

            new Actions(driver)
                    .moveToElement(edit)
                    .pause(300)
                    .click()
                    .perform();

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("edit"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Edit Plant') or contains(normalize-space(),'Update Plant')]")
                    )
            ));

        } catch (Exception e) {
            throw new RuntimeException("Edit option not clicked", e);
        }
    }

    public void openViewPlant() {
        clickThreeDots();
        clickViewOption();
    }

    public void clickViewOption() {
        try {
            WebElement view = wait.until(
                    ExpectedConditions.elementToBeClickable(viewOption)
            );

            new Actions(driver)
                    .moveToElement(view)
                    .pause(300)
                    .click()
                    .perform();

            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("view"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'View Plant') or contains(normalize-space(),'Plant Details')]")
                    )
            ));

        } catch (Exception e) {
            throw new RuntimeException("View option not clicked", e);
        }
    }

    public void openDeletePlant() {
        clickThreeDots();
        clickDeleteOption();
    }

    public void clickDeleteOption() {
        try {
            WebElement delete = wait.until(
                    ExpectedConditions.elementToBeClickable(deleteOption)
            );

            new Actions(driver)
                    .moveToElement(delete)
                    .pause(300)
                    .click()
                    .perform();

            Thread.sleep(1000);

        } catch (Exception e) {
            throw new RuntimeException("Delete option not clicked", e);
        }
    }

    public boolean isUpdatedPlantVisible(String plantName) {
        try {
            By plant = By.xpath("//*[contains(normalize-space(),'" + plantName + "')]");
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(plant)
            ).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}