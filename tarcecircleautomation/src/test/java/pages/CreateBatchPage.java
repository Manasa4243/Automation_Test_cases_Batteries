package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateBatchPage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateBatchPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//*[contains(normalize-space(),'New Batch') or contains(normalize-space(),'Create Batch')]");

    private By organizationDropdown =
            By.xpath("//*[normalize-space()='Select organization']");

    private By plantDropdown =
            By.xpath("//*[normalize-space()='Select organization first' or normalize-space()='Select plant']");

    private By productGtinInput =
            By.xpath("//input[@placeholder='Enter GTIN Number']");

    private By batchNameInput =
            By.xpath("//input[@placeholder='Enter batch name']");

    private By productQuantityInput =
            By.xpath("//input[@placeholder='Enter quantity']");

    private By createBatchBtn =
            By.xpath("//button[contains(normalize-space(),'Create Batch')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private By backBtn =
            By.xpath("//button[.//*[name()='svg']] | //button[contains(@aria-label,'Back')]");

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

    private void selectDropdownValue(By dropdown, String value) {

    try {

        WebElement dropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(dropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dropdownElement
        );

        By searchBox = By.xpath(
                "//input[contains(@placeholder,'Search')]"
        );

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBox)
        );

        search.sendKeys(value);

        Thread.sleep(1000);

        search.sendKeys(Keys.ENTER);

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException(
                "Dropdown value not selected: " + value,
                e
        );
    }
}

    public boolean isCreateBatchPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle),
                    ExpectedConditions.visibilityOfElementLocated(organizationDropdown),
                    ExpectedConditions.visibilityOfElementLocated(batchNameInput),
                    ExpectedConditions.visibilityOfElementLocated(createBatchBtn)
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAllFieldsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationDropdown)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(plantDropdown)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(productGtinInput)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(batchNameInput)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(productQuantityInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openOrganizationDropdown() {
        jsClick(organizationDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganization(String orgName) {
        selectDropdownValue(organizationDropdown, orgName);
    }

    public void selectPlant(String plantName) {
        selectDropdownValue(plantDropdown, plantName);
    }

    public boolean isOrganizationSelected(String orgName) {
        try {
            WebElement org = wait.until(
                    ExpectedConditions.presenceOfElementLocated(organizationDropdown)
            );

            String text = org.getAttribute("textContent");

            return text != null && text.toLowerCase().contains(orgName.toLowerCase());

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantDropdownEnabled() {
        try {
            WebElement plant = wait.until(
                    ExpectedConditions.presenceOfElementLocated(plantDropdown)
            );

            String disabled = plant.getAttribute("disabled");
            String ariaDisabled = plant.getAttribute("aria-disabled");
            String className = plant.getAttribute("class");

            return plant.isDisplayed()
                    && plant.isEnabled()
                    && (disabled == null || disabled.equals("false"))
                    && (ariaDisabled == null || ariaDisabled.equals("false"))
                    && !className.contains("disabled")
                    && !className.contains("opacity-50");

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantSelected(String plantName) {
        try {
            WebElement plant = wait.until(
                    ExpectedConditions.presenceOfElementLocated(plantDropdown)
            );

            String text = plant.getAttribute("textContent");

            return text != null && text.toLowerCase().contains(plantName.toLowerCase());

        } catch (Exception e) {
            return false;
        }
    }

    public void enterProductGtin(String gtin) {
        enterText(productGtinInput, gtin);
    }

    public void enterBatchName(String batchName) {
        enterText(batchNameInput, batchName);
    }

    public void enterProductQuantity(String quantity) {
        enterText(productQuantityInput, quantity);
    }

    public boolean isProductGtinAccepted(String gtin) {
        try {
            return wait.until(ExpectedConditions.attributeToBe(productGtinInput, "value", gtin));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBatchNameAccepted(String batchName) {
        try {
            return wait.until(ExpectedConditions.attributeToBe(batchNameInput, "value", batchName));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isQuantityAccepted(String quantity) {
        try {
            return wait.until(ExpectedConditions.attributeToBe(productQuantityInput, "value", quantity));
        } catch (Exception e) {
            return false;
        }
    }

    public void fillValidBatchData() {
        selectOrganization("Battery_Org");
        selectPlant("Battery_Plant");

        enterProductGtin("BPAN-2026-001");
        enterBatchName("Batch_Auto_" + System.currentTimeMillis());
        enterProductQuantity("100");
    }

    public void clickCreateBatch() {
        jsClick(createBatchBtn);
    }

    public boolean isBatchCreatedOrSaved() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("batches"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'success') or contains(normalize-space(),'created') or contains(normalize-space(),'Batch created')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public boolean isReturnedToBatchList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("batches"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'Batch')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickBack() {
        jsClick(backBtn);
    }

    public boolean isCreatedBatchVisible(String batchName) {
        try {
            By batch = By.xpath("//*[contains(normalize-space(),'" + batchName + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(batch)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public void fillAllValidBatchData() {
    selectOrganization("Exide");
    selectPlant("Exide Plant");
    enterProductGtin("BPAN-2026-001");
    enterBatchName("Batch_Auto_" + System.currentTimeMillis());
    enterProductQuantity("2");
}

public boolean isValidationDisplayed() {
    try {
        String text = driver.getPageSource().toLowerCase();

        return text.contains("required")
                || text.contains("invalid")
                || text.contains("duplicate")
                || text.contains("already")
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
    clickCreateBatch();
    scrollToFirstValidationError();
}
}