package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BatchProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public BatchProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle = By.xpath("//h1[contains(normalize-space(),'Batches + Product')]");
    private By searchBox = By.xpath("//input[contains(@placeholder,'Search batch')]");
    private By orgFilter = By.xpath("//*[normalize-space()='All Organizations']");
    private By plantFilter = By.xpath("//*[normalize-space()='All Plants']");
    private By statusFilter = By.xpath("//*[normalize-space()='Active']");
    private By clearFilterBtn = By.xpath("//button[contains(normalize-space(),'Clear Filter')]");
    private By batchTable = By.xpath("//table | //*[contains(normalize-space(),'BATCH NAME')]");
    private By mapProductBtn = By.xpath("//button[contains(normalize-space(),'Map Product')]");
    private By viewBtn = By.xpath("//button[contains(normalize-space(),'View')]");
    private By actionMenu = By.xpath("//button[contains(normalize-space(),'...') or contains(@aria-label,'menu')]");

    private By mapPageTitle = By.xpath("//h1[contains(normalize-space(),'Map Product')]");
    private By batchId = By.xpath("//label[contains(normalize-space(),'Batch ID')]/following::input[1]");
    private By batchCode = By.xpath("//label[contains(normalize-space(),'Batch Code')]/following::input[1]");
    private By organizationId = By.xpath("//label[contains(normalize-space(),'Organization ID')]/following::input[1]");
    private By plantId = By.xpath("//label[contains(normalize-space(),'Plant ID')]/following::input[1]");
    private By productDropdown = By.xpath("//label[normalize-space()='Product']/following::button[1]");
    private By mapProductSubmitBtn = By.xpath("//button[contains(normalize-space(),'Map Product')]");
    private By cancelBtn = By.xpath("//button[contains(normalize-space(),'Cancel')]");
    private By backBtn = By.xpath("//button[.//*[name()='svg']]");

    private WebElement scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        return element;
    }

    private void jsClick(By locator) {
        WebElement element = scrollToElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void enterText(By locator, String value) {
        WebElement element = scrollToElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    private void selectDropdownValue(By dropdown, String value) {
        try {
            WebElement dropdownElement = wait.until(ExpectedConditions.elementToBeClickable(dropdown));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    dropdownElement
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownElement);

            Thread.sleep(800);

            By optionLocator = By.xpath(
                    "//*[(@role='option' or @role='menuitem' or @cmdk-item='' or contains(@class,'SelectItem'))" +
                    " and contains(normalize-space(.),'" + value + "')]"
                    + " | //*[contains(normalize-space(),'" + value + "')]"
            );

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException("Dropdown value not selected: " + value, e);
        }
    }

    public boolean isBatchProductPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageTitleDisplayed() {
        return isBatchProductPageOpened();
    }

    public boolean isBreadcrumbDisplayed() {
        return driver.getPageSource().contains("Batches + Product")
                || driver.getPageSource().contains("Dpp Management");
    }

    public boolean areAllListFieldsDisplayed() {
        try {
            return driver.findElement(searchBox).isDisplayed()
                    && driver.findElement(orgFilter).isDisplayed()
                    && driver.findElement(plantFilter).isDisplayed()
                    && driver.findElement(statusFilter).isDisplayed()
                    && driver.findElement(clearFilterBtn).isDisplayed()
                    && driver.findElement(batchTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isMapProductButtonDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mapProductBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCancelButtonDisplayed() {
        clickFirstMapProduct();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).isDisplayed();
    }

    public boolean isFieldAlignmentDisplayed() {
        return areAllListFieldsDisplayed();
    }

    public boolean arePlaceholderTextsDisplayed() {
        try {
            return driver.findElement(searchBox).getAttribute("placeholder").contains("Search batch");
        } catch (Exception e) {
            return false;
        }
    }

    public void openOrganizationFilter() {
        jsClick(orgFilter);
    }

    public void openPlantFilter() {
        jsClick(plantFilter);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantFilterDisabledBeforeOrgSelection() {
        try {
            WebElement plant = wait.until(ExpectedConditions.presenceOfElementLocated(plantFilter));
            String disabled = plant.getAttribute("disabled");
            String ariaDisabled = plant.getAttribute("aria-disabled");
            return disabled != null || "true".equals(ariaDisabled);
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganizationFilter(String orgName) {
        selectDropdownValue(orgFilter, orgName);
    }

    public boolean isPlantFilterEnabled() {
        try {
            WebElement plant = wait.until(ExpectedConditions.presenceOfElementLocated(plantFilter));
            String disabled = plant.getAttribute("disabled");
            String ariaDisabled = plant.getAttribute("aria-disabled");
            return plant.isDisplayed()
                    && plant.isEnabled()
                    && (disabled == null || disabled.equals("false"))
                    && (ariaDisabled == null || ariaDisabled.equals("false"));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickFirstMapProduct() {
        jsClick(mapProductBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mapPageTitle));
    }

    public boolean isMapProductPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(mapPageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areMapProductFieldsDisplayed() {
        try {
            return driver.findElement(batchId).isDisplayed()
                    && driver.findElement(batchCode).isDisplayed()
                    && driver.findElement(organizationId).isDisplayed()
                    && driver.findElement(plantId).isDisplayed()
                    && driver.findElement(productDropdown).isDisplayed()
                    && driver.findElement(mapProductSubmitBtn).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectProduct(String productName) {
        selectDropdownValue(productDropdown, productName);
    }

    public boolean isProductSelected(String productName) {
        try {
            WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(productDropdown));
            return product.getText().contains(productName);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickMapProductSubmit() {
        jsClick(mapProductSubmitBtn);
    }

    public boolean isProductMappedSuccessfully() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("batch-product"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'success') or contains(normalize-space(),'mapped')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public void clickBack() {
        jsClick(backBtn);
    }

    public boolean isReturnedToBatchProductList() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed()
                    || driver.getCurrentUrl().contains("batch-product");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidationDisplayed() {
        String text = driver.getPageSource().toLowerCase();
        return text.contains("required")
                || text.contains("invalid")
                || text.contains("must")
                || text.contains("error")
                || text.contains("please");
    }

    public void submitWithoutProduct() {
        clickFirstMapProduct();
        clickMapProductSubmit();
    }
    public boolean isMapProductSubmitButtonDisplayed() {
    try {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(mapProductSubmitBtn)
        ).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isCancelButtonVisibleOnMapPage() {
    try {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cancelBtn)
        ).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isProductPlaceholderDisplayed() {
    try {
        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productDropdown)
        );

        return product.getText().contains("Select product")
                || product.getAttribute("textContent").contains("Select product");

    } catch (Exception e) {
        return false;
    }
}

public void openProductDropdown() {
    jsClick(productDropdown);
}

public boolean isBatchIdReadOnly() {
    try {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(batchId)
        );

        return element.getAttribute("readonly") != null
                || element.getAttribute("disabled") != null
                || !element.isEnabled();

    } catch (Exception e) {
        return false;
    }
}

public boolean isBatchCodeReadOnly() {
    try {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(batchCode)
        );

        return element.getAttribute("readonly") != null
                || element.getAttribute("disabled") != null
                || !element.isEnabled();

    } catch (Exception e) {
        return false;
    }
}
}