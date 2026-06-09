package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewEmployeePage {

    WebDriver driver;
    WebDriverWait wait;

    public ViewEmployeePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
            By.xpath("//h1[contains(normalize-space(),'View Employee') or contains(normalize-space(),'Employee Details')]");

    private By employeeName =
            By.xpath("//*[contains(normalize-space(),'Employee Name')]/following::*[1]");

    private By employeeEmail =
            By.xpath("//*[contains(normalize-space(),'Email')]/following::*[1]");

    private By phoneNumber =
            By.xpath("//*[contains(normalize-space(),'Phone')]/following::*[1]");

    private By designation =
            By.xpath("//*[contains(normalize-space(),'Designation')]/following::*[1]");

    private By department =
            By.xpath("//*[contains(normalize-space(),'Department')]/following::*[1]");

    private By organizationName =
            By.xpath("//*[contains(normalize-space(),'Organization Name')]/following::*[1]");

    private By organizationId =
            By.xpath("//*[contains(normalize-space(),'Organization ID') or contains(normalize-space(),'Organization Id')]/following::*[1]");

    private By plantName =
            By.xpath("//*[contains(normalize-space(),'Plant')]/following::*[1]");

    private By employeeStatus =
            By.xpath("//*[contains(normalize-space(),'Status')]/following::*[1]");

    private By loginAccountStatus =
            By.xpath("//*[contains(normalize-space(),'Login Account')]/following::*[1]");

    private By createdAt =
            By.xpath("//*[contains(normalize-space(),'Created At')]/following::*[1]");

    private By updatedAt =
            By.xpath("//*[contains(normalize-space(),'Updated At')]/following::*[1]");

      private By backButton =
            By.xpath("//button[.//*[contains(@class,'lucide-arrow-left')]]");


    public boolean isViewEmployeePageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areBasicDetailsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeName)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(employeeEmail)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDesignationDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(designation)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDepartmentDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(department)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areOrganizationDetailsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationName)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(organizationId)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantDetailsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(plantName)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmployeeStatusDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(employeeStatus)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginAccountStatusDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(loginAccountStatus)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areAuditDetailsDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(createdAt)).isDisplayed()
                    && wait.until(ExpectedConditions.visibilityOfElementLocated(updatedAt)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

     public void clickBackButton() {
        WebElement back = wait.until(
                ExpectedConditions.elementToBeClickable(backButton)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", back);
    }


    public boolean isReturnedToEmployeeList() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("employees"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//h1[contains(normalize-space(),'Employees')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isResponsiveUIWorking() {
        try {
            driver.manage().window().setSize(new Dimension(1366, 768));
            Thread.sleep(1000);
            boolean desktop = isViewEmployeePageOpened();

            driver.manage().window().setSize(new Dimension(768, 1024));
            Thread.sleep(1000);
            boolean tablet = isViewEmployeePageOpened();

            driver.manage().window().setSize(new Dimension(390, 844));
            Thread.sleep(1000);
            boolean mobile = isViewEmployeePageOpened();

            driver.manage().window().maximize();

            return desktop && tablet && mobile;

        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmptyOptionalFieldHandled() {
        try {
            String pageText = driver.getPageSource();

            return pageText != null
                    && !pageText.toLowerCase().contains("undefined")
                    && !pageText.toLowerCase().contains("null")
                    && !pageText.toLowerCase().contains("nan");
        } catch (Exception e) {
            return false;
        }
    }
}