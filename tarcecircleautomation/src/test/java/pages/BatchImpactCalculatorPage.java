package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BatchImpactCalculatorPage {

    WebDriver driver;
    WebDriverWait wait;

    public BatchImpactCalculatorPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle = By.xpath("//h1[contains(normalize-space(),'Batch Impact Calculator')]");

    private By batchNameField = By.xpath("//label[normalize-space()='Batch Name']/following::input[1]");
    private By batchCodeField = By.xpath("//label[normalize-space()='Batch Code']/following::input[1]");

 private By waterTypeDropdown =
        By.xpath("//label[contains(normalize-space(),'Water Type')]/following::button[1]");

private By wasteTypeDropdown =
        By.xpath("//label[contains(normalize-space(),'Waste Type')]/following::button[1]");

private By hazardousDropdown =
        By.xpath("//label[contains(normalize-space(),'Hazardous')]/following::button[1]");

private By totalWaterUsedField =
        By.xpath("//label[contains(normalize-space(),'Total Water Used')]/following::input[not(@type='hidden')][1]");
private By totalElectricityUsedField =
        By.xpath("//label[contains(normalize-space(),'Total Electricity Used')]/following::input[not(@type='hidden')][1]");

private By totalWasteGeneratedField =
        By.xpath("//label[contains(normalize-space(),'Total Waste Generated')]/following::input[not(@type='hidden')][1]");

private By airEmissionField =
        By.xpath("//label[contains(normalize-space(),'Air Emission')]/following::input[not(@type='hidden')][1]");

private By noiseEmissionField =
        By.xpath("//label[contains(normalize-space(),'Noise Emission')]/following::input[not(@type='hidden')][1]");

private By soilEmissionField =
        By.xpath("//label[contains(normalize-space(),'Soil Emission')]/following::input[not(@type='hidden')][1]");

private By plasticWasteField =
        By.xpath("//label[contains(normalize-space(),'Plastic Waste Generated')]/following::input[not(@type='hidden')][1]");
    private By calculateBatchButton = By.xpath("//button[normalize-space()='Calculate Batch']");
    private By cancelButton = By.xpath("//button[normalize-space()='Cancel']");
    private By backButton = By.xpath("//button[.//*[name()='svg']]");

    private By successToast = By.xpath("//*[contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'success') or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'saved') or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'calculated')]");

    public boolean isCalculatorPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public boolean areBatchDetailsDisplayed() {
        return driver.findElement(batchNameField).isDisplayed()
                && driver.findElement(batchCodeField).isDisplayed();
    }

    public boolean areBatchFieldsReadOnly() {
        return isReadOnly(batchNameField) && isReadOnly(batchCodeField);
    }

    private boolean isReadOnly(By locator) {
        WebElement element = driver.findElement(locator);
        String readonly = element.getAttribute("readonly");
        String disabled = element.getAttribute("disabled");
        return readonly != null || disabled != null || !element.isEnabled();
    }

    public boolean isWaterTypeDropdownOpened() {
        openDropdown(waterTypeDropdown);
        return isDropdownOptionVisible();
    }

    public boolean isWasteTypeDropdownOpened() {
        openDropdown(wasteTypeDropdown);
        return isDropdownOptionVisible();
    }

    public boolean isHazardousDropdownOpened() {
    scrollAndClick(hazardousDropdown);

    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[normalize-space()='Yes'] | //*[normalize-space()='No']")
        )).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

    public void selectWaterType(String value) {
    selectDropdownValue(waterTypeDropdown, value);
}

public void selectWasteType(String value) {
    selectDropdownValue(wasteTypeDropdown, value);
}

public void selectHazardousMaterial(String value) {
    selectDropdownValue(hazardousDropdown, value);
}

//     By option = By.xpath("//*[normalize-space()='" + value + "']");

//     scrollAndClick(option);
// }

    public void enterTotalWaterUsed(String value) {
        type(totalWaterUsedField, value);
    }

    public void enterTotalElectricityUsed(String value) {
        type(totalElectricityUsedField, value);
    }

    public void enterTotalWasteGenerated(String value) {
        type(totalWasteGeneratedField, value);
    }

    public void enterAirEmission(String value) {
        type(airEmissionField, value);
    }

    public void enterNoiseEmission(String value) {
        type(noiseEmissionField, value);
    }

    public void enterSoilEmission(String value) {
        type(soilEmissionField, value);
    }

    public void enterPlasticWasteGenerated(String value) {
        type(plasticWasteField, value);
    }
private void scrollAndClick(By locator) {
    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
            element
    );

    try {
        Thread.sleep(800);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    wait.until(ExpectedConditions.visibilityOf(element));

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].focus();",
            element
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            element
    );
}
public void fillValidCalculatorData() {

    selectWaterType("Blue Water");
    selectWasteType("Anode Scrap");

    enterTotalWaterUsed("54");
    enterTotalElectricityUsed("65");
    enterTotalWasteGenerated("56");
    enterAirEmission("5656");
    enterNoiseEmission("567");
    enterSoilEmission("67");
    enterPlasticWasteGenerated("56");

    selectHazardousMaterial("No");
}

    public void clickCalculateBatch() {
        jsClick(calculateBatchButton);
    }

    public void clickCancel() {
        jsClick(cancelButton);
    }

    public void clickBack() {
        jsClick(backButton);
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(successToast)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidationDisplayed() {
        return driver.getPageSource().toLowerCase().contains("required")
                || driver.getPageSource().toLowerCase().contains("select")
                || driver.getPageSource().toLowerCase().contains("invalid");
    }

    public boolean isCalculateButtonEnabled() {
        return driver.findElement(calculateBatchButton).isEnabled();
    }
public String getTotalWaterUsedValue() {
    return driver.findElement(totalWaterUsedField).getAttribute("value");
}

public String getTotalElectricityUsedValue() {
    return driver.findElement(totalElectricityUsedField).getAttribute("value");
}

public String getTotalWasteGeneratedValue() {
    return driver.findElement(totalWasteGeneratedField).getAttribute("value");
}

public String getAirEmissionValue() {
    return driver.findElement(airEmissionField).getAttribute("value");
}

public String getNoiseEmissionValue() {
    return driver.findElement(noiseEmissionField).getAttribute("value");
}

public String getSoilEmissionValue() {
    return driver.findElement(soilEmissionField).getAttribute("value");
}

public String getPlasticWasteGeneratedValue() {
    return driver.findElement(plasticWasteField).getAttribute("value");
}
  private void type(By locator, String value) {

    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            element
    );

    wait.until(ExpectedConditions.elementToBeClickable(element));

    ((JavascriptExecutor) driver).executeScript("arguments[0].focus();", element);

    try {
        element.clear();
        element.sendKeys(value);
    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value=''; arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element,
                value
        );
    }
}

    private void openDropdown(By locator) {
        jsClick(locator);
    }

    private boolean isDropdownOptionVisible() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@role='option'] | //*[@cmdk-item] | //div[contains(@class,'select')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
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

        By optionLocator = By.xpath(
                "//*[(@role='option' or @role='menuitem' or @cmdk-item='' or contains(@class,'SelectItem'))" +
                " and normalize-space(.)='" + value + "']"
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

    } catch (Exception e) {
        throw new RuntimeException("Dropdown value not selected: " + value, e);
    }
}
    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        wait.until(ExpectedConditions.visibilityOf(element));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }
}