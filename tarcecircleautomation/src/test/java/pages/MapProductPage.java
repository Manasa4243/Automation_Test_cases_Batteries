package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MapProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public MapProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'Map Product')]");

    private By batchIdField =
            By.xpath("//label[normalize-space()='Batch ID']/following::input[1]");

    private By batchCodeField =
            By.xpath("//label[normalize-space()='Batch Code']/following::input[1]");

    private By organizationIdField =
            By.xpath("//label[normalize-space()='Organization ID']/following::input[1]");

    private By plantIdField =
            By.xpath("//label[normalize-space()='Plant ID']/following::input[1]");

    private By productDropdown =
            By.xpath("//label[normalize-space()='Product']/following::*[@role='combobox'][1] | //button[contains(.,'Select product')]");

    private By mapProductButton =
            By.xpath("//button[normalize-space()='Map Product']");

    private By cancelButton =
            By.xpath("//button[normalize-space()='Cancel']");

    private By backButton =
            By.xpath("//button[.//*[name()='svg']] | //button[contains(@class,'rounded')]");

    private By successToast =
            By.xpath("//*[contains(text(),'success') or contains(text(),'mapped') or contains(text(),'Map Product successfully')]");

    public boolean isMapProductPageOpened() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
    }

    public boolean areBatchDetailsDisplayed() {
        return driver.findElement(batchIdField).isDisplayed()
                && driver.findElement(batchCodeField).isDisplayed()
                && driver.findElement(organizationIdField).isDisplayed()
                && driver.findElement(plantIdField).isDisplayed();
    }

    public boolean areBatchFieldsReadOnly() {
        return isReadOnly(batchIdField)
                && isReadOnly(batchCodeField)
                && isReadOnly(organizationIdField)
                && isReadOnly(plantIdField);
    }

    private boolean isReadOnly(By locator) {
        WebElement element = driver.findElement(locator);

        String readonly = element.getAttribute("readonly");
        String disabled = element.getAttribute("disabled");

        return readonly != null || disabled != null || !element.isEnabled();
    }

    public void openProductDropdown() {
        jsClick(productDropdown);
    }

    public boolean isProductDropdownOpened() {
        openProductDropdown();

        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//*[@role='option'] | //div[contains(@cmdk-item,'')] | //span[contains(text(),'Battery')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectProduct(String productName) {
        openProductDropdown();

        By productOption = By.xpath(
                "//*[normalize-space()='" + productName + "']"
        );

        jsClick(productOption);
    }

    public void clickMapProduct() {
        jsClick(mapProductButton);
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

    public boolean isMapProductButtonEnabled() {
        return driver.findElement(mapProductButton).isEnabled();
    }

    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element
        );

        wait.until(ExpectedConditions.visibilityOf(element));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();", element
        );
    }
}