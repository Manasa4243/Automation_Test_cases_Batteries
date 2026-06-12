package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SuperAdminAnalyticsNewPage {

    WebDriver driver;
    WebDriverWait wait;

    public SuperAdminAnalyticsNewPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private boolean isDisplayedByText(String text) {
        try {
            By locator = By.xpath("//*[contains(normalize-space(),'" + text + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    private void scrollToText(String text) {
        try {
            By locator = By.xpath("//*[contains(normalize-space(),'" + text + "')]");
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center', inline:'nearest'});",
                    element
            );

            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100);");
            Thread.sleep(500);

        } catch (Exception e) {
            throw new RuntimeException("Unable to scroll to: " + text, e);
        }
    }

    public boolean isAnalyticsPageOpened() {
        return isDisplayedByText("Analytics");
    }

    public boolean isAnalyticsTitleDisplayed() {
        return isDisplayedByText("Analytics");
    }

    public boolean isTotalBatteriesDisplayed() {
        return isDisplayedByText("Total Batteries");
    }

    public boolean isRegisteredManufacturersDisplayed() {
        return isDisplayedByText("Registered Manufacturers");
    }

    public boolean isBatteriesInServiceDisplayed() {
        return isDisplayedByText("Batteries in Service");
    }

    public boolean isBatteriesRecycledDisplayed() {
        return isDisplayedByText("Batteries Recycled");
    }

    public boolean isComplianceScoreDisplayed() {
        return isDisplayedByText("Compliance Score");
    }

    public boolean isBatteryRegistrationTrendDisplayed() {
        return isDisplayedByText("Battery Registration Trend");
    }

    public boolean isManufacturerLeaderboardDisplayed() {
        return isDisplayedByText("Manufacturer Leaderboard");
    }

    public boolean isBatteryChemistryDistributionDisplayed() {
        return isDisplayedByText("Battery Chemistry Distribution");
    }

    public boolean isStateOfHealthDisplayed() {
        scrollToText("State of Health");
        return isDisplayedByText("State of Health");
    }

    public boolean isBatteryDegradationTrendDisplayed() {
        scrollToText("Battery Degradation Trend");
        return isDisplayedByText("Battery Degradation Trend");
    }

    public boolean isRemainingUsefulLifeDisplayed() {
        scrollToText("Remaining Useful Life");
        return isDisplayedByText("Remaining Useful Life") || isDisplayedByText("RUL");
    }

    public boolean isThermalIncidentDisplayed() {
        scrollToText("Thermal Incident");
        return isDisplayedByText("Thermal Incident");
    }

    public boolean isHighRiskModelsDisplayed() {
        scrollToText("High-Risk Battery Models");
        return isDisplayedByText("High-Risk Battery Models");
    }

    public boolean isEolDisplayed() {
        scrollToText("Batteries Reaching EOL");
        return isDisplayedByText("Batteries Reaching EOL");
    }

    public boolean isRecyclerPerformanceDisplayed() {
        scrollToText("Recycler Performance");
        return isDisplayedByText("Recycler Performance");
    }

    public boolean isMaterialRecoveryDisplayed() {
        scrollToText("Material Recovery Analytics");
        return isDisplayedByText("Material Recovery Analytics");
    }

    public boolean isBpanComplianceDisplayed() {
        scrollToText("BPAN Compliance");
        return isDisplayedByText("BPAN Compliance");
    }

    public boolean isDataCompletenessDisplayed() {
        scrollToText("Data Completeness Score");
        return isDisplayedByText("Data Completeness Score");
    }

    public boolean isManufacturerRankingDisplayed() {
        scrollToText("Manufacturer Compliance Ranking");
        return isDisplayedByText("Manufacturer Compliance Ranking");
    }

    public boolean isWarrantyForecastDisplayed() {
        scrollToText("Warranty Claim Forecast");
        return isDisplayedByText("Warranty Claim Forecast");
    }

    public boolean isFailureProductsDisplayed() {
        scrollToText("Failure Products");
        return isDisplayedByText("Failure Products");
    }

    public boolean hasNumberNearText(String label) {
        try {
            String bodyText = driver.findElement(By.tagName("body")).getText();
            return bodyText.contains(label) && bodyText.matches("(?s).*" + label + ".*\\d+.*");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageProtected() {
        try {
            String url = driver.getCurrentUrl().toLowerCase();
            String body = driver.findElement(By.tagName("body")).getText().toLowerCase();

            return url.contains("login")
                    || body.contains("login")
                    || body.contains("unauthorized")
                    || body.contains("access denied");
        } catch (Exception e) {
            return false;
        }
    }
public void scrollDownTwice() {
    try {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(2000);

        js.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(2000);

    } catch (Exception e) {
        throw new RuntimeException("Unable to scroll down", e);
    }
}
    public boolean isScriptNotExecuted() {
        try {
            String body = driver.findElement(By.tagName("body")).getText();
            return !body.contains("XSS_EXECUTED");
        } catch (Exception e) {
            return true;
        }
    }
}