package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuperAdminDashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public SuperAdminDashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By dashboardTitle =
            By.xpath("//*[contains(normalize-space(),'Dashboard Overview')]");

    private By superAdminTitle =
            By.xpath("//*[contains(normalize-space(),'Dashboard Overview') and contains(normalize-space(),'Super Admin')]");

    private By totalOrganizations =
            By.xpath("//*[contains(normalize-space(),'Total Organizations')]");

    private By totalPlants =
            By.xpath("//*[contains(normalize-space(),'Total Plants')]");

    private By totalProducts =
            By.xpath("//*[contains(normalize-space(),'Total Products')]");

    private By totalBatches =
            By.xpath("//*[contains(normalize-space(),'Total Batches')]");

    private By totalBatteries =
            By.xpath("//*[contains(normalize-space(),'Total Batteries')]");

    private By qrCodes =
            By.xpath("//*[contains(normalize-space(),'QR Codes')]");

    private By dailyLogins =
            By.xpath("//*[contains(normalize-space(),'Daily Logins')]");

    private By apiRequests =
            By.xpath("//*[contains(normalize-space(),'API Requests')]");

    private By requestsVsErrors =
            By.xpath("//*[contains(normalize-space(),'Requests vs Errors')]");

    private By riskTrend =
            By.xpath("//*[contains(normalize-space(),'Risk Trend')]");

    private By dataQuality =
            By.xpath("//*[contains(normalize-space(),'Data Quality')]");

    private By eolStatus =
            By.xpath("//*[contains(normalize-space(),'End-of-Life') or contains(normalize-space(),'EOL')]");

    private By wasteRecovery =
            By.xpath("//*[contains(normalize-space(),'Waste Recovery')]");

    private By circularScore =
            By.xpath("//*[contains(normalize-space(),'Circular Score')]");

    private By recentActivity =
            By.xpath("//*[contains(normalize-space(),'Recent Activity')]");

    private By incidentLog =
            By.xpath("//*[contains(normalize-space(),'Incident Log') or contains(normalize-space(),'Recent Incident')]");

    private By viewAllActivity =
            By.xpath("//*[contains(normalize-space(),'View All')]");

    private By superAdminRole =
            By.xpath("//*[contains(normalize-space(),'Super Admin')]");

    public boolean isDashboardLoaded() {
        return isDisplayed(dashboardTitle);
    }

    public boolean isSuperAdminTitleDisplayed() {
        return isDisplayed(superAdminTitle) || isDisplayed(dashboardTitle);
    }

    public boolean isTotalOrganizationsDisplayed() {
        return isDisplayed(totalOrganizations);
    }

    public boolean isTotalPlantsDisplayed() {
        return isDisplayed(totalPlants);
    }

    public boolean isTotalProductsDisplayed() {
        return isDisplayed(totalProducts);
    }

    public boolean isTotalBatchesDisplayed() {
        return isDisplayed(totalBatches);
    }

    public boolean isTotalBatteriesDisplayed() {
        return isDisplayed(totalBatteries);
    }

    public boolean isQRCodesDisplayed() {
        return isDisplayed(qrCodes);
    }

    public boolean isDailyLoginsDisplayed() {
        return isDisplayed(dailyLogins);
    }

    public boolean isApiRequestsDisplayed() {
        return isDisplayed(apiRequests);
    }

    public boolean areSidebarMenusDisplayed() {
        return isDisplayed(By.xpath("//span[normalize-space()='Dashboard']"))
                && isDisplayed(By.xpath("//span[contains(normalize-space(),'Org Management')]"))
                && isDisplayed(By.xpath("//span[contains(normalize-space(),'Dpp Management') or contains(normalize-space(),'DPP Management')]"))
                && isDisplayed(By.xpath("//span[contains(normalize-space(),'Reports')]"))
                && isDisplayed(By.xpath("//span[contains(normalize-space(),'Analytics')]"));
    }

    public boolean isSuperAdminRoleDisplayed() {
        return isDisplayed(superAdminRole);
    }

    public boolean isRequestsVsErrorsDisplayed() {
        solidScrollToElement(requestsVsErrors);
        return isDisplayed(requestsVsErrors);
    }

    public boolean isRiskTrendDisplayed() {
        solidScrollToElement(riskTrend);
        return isDisplayed(riskTrend);
    }

    public boolean isDataQualityDisplayed() {
        solidScrollToElement(dataQuality);
        return isDisplayed(dataQuality);
    }

    public boolean isEolStatusDisplayed() {
        solidScrollToElement(eolStatus);
        return isDisplayed(eolStatus);
    }

    public boolean isWasteRecoveryDisplayed() {
        solidScrollToElement(wasteRecovery);
        return isDisplayed(wasteRecovery);
    }

    public boolean isCircularScoreDisplayed() {
        solidScrollToElement(circularScore);
        return isDisplayed(circularScore);
    }

    public boolean isRecentActivityDisplayed() {
        solidScrollToElement(recentActivity);
        return isDisplayed(recentActivity);
    }

    public boolean isIncidentLogDisplayed() {
        solidScrollToElement(incidentLog);
        return isDisplayed(incidentLog);
    }

    public boolean isPercentageGrowthDisplayed() {
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'%') or contains(normalize-space(),'from last')]"));
    }

    public boolean isErrorChartDataDisplayed() {
        solidScrollToElement(requestsVsErrors);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Error') or contains(normalize-space(),'Errors')]"));
    }

    public boolean isHighRiskDisplayed() {
        solidScrollToElement(riskTrend);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'High') or contains(normalize-space(),'HIGH')]"));
    }

    public boolean isMediumRiskDisplayed() {
        solidScrollToElement(riskTrend);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Medium') or contains(normalize-space(),'MEDIUM')]"));
    }

    public boolean isLowRiskDisplayed() {
        solidScrollToElement(riskTrend);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Low') or contains(normalize-space(),'LOW')]"));
    }

    public boolean isRecycledDisplayed() {
        solidScrollToElement(eolStatus);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Recycled')]"));
    }

    public boolean isRefurbishedDisplayed() {
        solidScrollToElement(eolStatus);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Refurbished')]"));
    }

    public boolean isReusedDisplayed() {
        solidScrollToElement(eolStatus);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Reused')]"));
    }

    public boolean isDisposedDisplayed() {
        solidScrollToElement(eolStatus);
        return isDisplayed(By.xpath("//*[contains(normalize-space(),'Disposed')]"));
    }

    public boolean clickViewAllActivity() {
        try {
            solidScrollToElement(viewAllActivity);
            clickJS(viewAllActivity);

            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("activity"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'Activity')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public void refreshDashboard() {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
    }

    private boolean isDisplayed(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void clickJS(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    private void solidScrollToElement(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.presenceOfElementLocated(locator)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                    element
            );

            Thread.sleep(1000);

            ((JavascriptExecutor) driver).executeScript(
                    "window.scrollBy(0, -120);"
            );

            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException("Unable to scroll to element: " + locator, e);
        }
    }
}