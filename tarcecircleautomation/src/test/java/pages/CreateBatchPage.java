package pages;

import org.openqa.selenium.*;
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
        By.xpath("//label[normalize-space()='Organization']/following::button[1]");

private By plantDropdown =
        By.xpath("//label[normalize-space()='Plant']/following::button[1]");

    private By productGtinInput =
            By.xpath("//label[contains(normalize-space(),'Product GTIN') or contains(normalize-space(),'GTIN')]/following::input[1]");

    private By batchNameInput =
            By.xpath("//label[contains(normalize-space(),'Batch Name')]/following::input[1]");

    private By productQuantityInput =
            By.xpath("//label[contains(normalize-space(),'Product Quantity') or contains(normalize-space(),'Quantity')]/following::input[1]");

    private By createBatchBtn =
            By.xpath("//button[contains(normalize-space(),'Create Batch')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private By backBtn =
            By.xpath("//button[.//*[name()='svg']] | //button[contains(@aria-label,'Back')]");

    private void jsClick(By locator) {

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
    }

    private void enterText(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        element.clear();
        element.sendKeys(value);
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

        By searchInput = By.xpath(
                "//input[contains(@placeholder,'Search organization')]" +
                " | //input[contains(@placeholder,'Search')]" +
                " | //input[@role='combobox']"
        );

        WebElement search = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchInput)
        );
search.sendKeys(value);
Thread.sleep(1000);
search.sendKeys(Keys.ENTER);
Thread.sleep(1000);
    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}
private void selectPlantDropdownValue(By dropdown, String value) {

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

        Thread.sleep(1000);

        By option = By.xpath(
            "//div[@role='option' and contains(.,'" + value + "')]"
            + " | //li[contains(.,'" + value + "')]"
            + " | //span[contains(.,'" + value + "')]"
        );

        WebElement plantOption = wait.until(
                ExpectedConditions.elementToBeClickable(option)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                plantOption
        );

        Thread.sleep(1000);

    } catch (Exception e) {
        throw new RuntimeException(
                "Plant dropdown value not selected: " + value,
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
    public boolean isOrganizationSelected(String orgName) {

    try {

        WebElement org = wait.until(
                ExpectedConditions.presenceOfElementLocated(organizationDropdown)
        );

        String text = org.getAttribute("textContent");

        System.out.println("Selected Org = " + text);

        return text != null &&
               text.toLowerCase().contains(orgName.toLowerCase());

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
        selectPlantDropdownValue(plantDropdown, plantName);
       
}
    

    public boolean isPlantDropdownEnabled() {
        try {
            WebElement plant = wait.until(
                    ExpectedConditions.presenceOfElementLocated(plantDropdown)
            );

            String disabled = plant.getAttribute("disabled");
            String ariaDisabled = plant.getAttribute("aria-disabled");

            return plant.isEnabled()
                    && (disabled == null || disabled.equals("false"))
                    && (ariaDisabled == null || ariaDisabled.equals("false"));

        } catch (Exception e) {
            return false;
        }
    }
public boolean isPlantSelected(String plantName) {

    try {
        WebElement plant = wait.until(
                ExpectedConditions.presenceOfElementLocated(plantDropdown)
        );

        String selectedText = plant.getText();
        String textContent = plant.getAttribute("textContent");
        String innerText = plant.getAttribute("innerText");

        System.out.println("Plant getText = " + selectedText);
        System.out.println("Plant textContent = " + textContent);
        System.out.println("Plant innerText = " + innerText);

        return (selectedText != null && selectedText.contains(plantName))
                || (textContent != null && textContent.contains(plantName))
                || (innerText != null && innerText.contains(plantName));

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
            return wait.until(ExpectedConditions
                    .attributeToBe(productGtinInput, "value", gtin));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBatchNameAccepted(String batchName) {
        try {
            return wait.until(ExpectedConditions
                    .attributeToBe(batchNameInput, "value", batchName));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isQuantityAccepted(String quantity) {
        try {
            return wait.until(ExpectedConditions
                    .attributeToBe(productQuantityInput, "value", quantity));
        } catch (Exception e) {
            return false;
        }
    }

    public void fillValidBatchData() {
        selectOrganization("Battery_Org");
        selectPlant("Battery_Plant");
        enterProductGtin("BPAN-2026-001");
        enterBatchName("Batch_Auto_001");
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
}