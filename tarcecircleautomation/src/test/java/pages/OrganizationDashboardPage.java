package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrganizationDashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public OrganizationDashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By dashboardTitle =
            By.xpath("//*[contains(normalize-space(),'Dashboard Overview')]");

    private By subtitle =
            By.xpath("//*[contains(normalize-space(),'Organization') or contains(normalize-space(),'overview')]");

    private By dashboardMenu =
            By.xpath("//span[normalize-space()='Dashboard'] | //a[contains(@href,'dashboard')]");

    private By orgManagementMenu =
            By.xpath("//span[normalize-space()='Org Management']");

    private By dppManagementMenu =
            By.xpath("//span[contains(normalize-space(),'DPP Management') or contains(normalize-space(),'Dpp Management')]");

    private By reportsMenu =
            By.xpath("//span[contains(normalize-space(),'Reports')]");

    private By analyticsMenu =
            By.xpath("//span[contains(normalize-space(),'Analytics')]");

    private By totalPlantsCard =
            By.xpath("//*[contains(normalize-space(),'Total Plants')]");

    private By totalProductsCard =
            By.xpath("//*[contains(normalize-space(),'Total Products')]");

    private By totalBatchesCard =
            By.xpath("//*[contains(normalize-space(),'Total Batches')]");

    private By totalBatteriesCard =
            By.xpath("//*[contains(normalize-space(),'Total Batteries')]");

    private By batteryPassportCard =
            By.xpath("//*[contains(normalize-space(),'Battery Passports')]");

    private By riskFactorSection =
            By.xpath("//*[contains(normalize-space(),'Risk Factors')]");

    private By severityBadge =
            By.xpath("//*[normalize-space()='HIGH' or normalize-space()='CRITICAL' or normalize-space()='MEDIUM' or normalize-space()='LOW']");

    private By productionTrend =
            By.xpath("//*[contains(normalize-space(),'Battery Production Trend')]");

    private By co2eTrend =
            By.xpath("//*[contains(normalize-space(),'CO2e Trend') or contains(normalize-space(),'CO₂e Trend')]");

    private By waterUsage =
            By.xpath("//*[contains(normalize-space(),'Water Usage')]");

    private By electricityConsumption =
            By.xpath("//*[contains(normalize-space(),'Electricity Consumption')]");

    private By wasteGeneration =
            By.xpath("//*[contains(normalize-space(),'Waste Generation')]");

    private By wasteRecovery =
            By.xpath("//*[contains(normalize-space(),'Waste Recovery Analytics')]");

    private By userProfile =
            By.xpath("//*[contains(normalize-space(),'Admin') or contains(normalize-space(),'system@tracecircle.com')]");

    public boolean isDashboardPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
public boolean areSeverityLabelsDisplayed() {
    return isDisplayed(By.xpath("//*[normalize-space()='HIGH' or normalize-space()='CRITICAL']"));
}

public boolean areHeaderIconsDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'EN')]"))
            && isDisplayed(By.xpath("//*[contains(@class,'bell') or contains(@class,'notification') or name()='svg']"));
}

public boolean isUserNameAndRoleDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'Admin') or contains(normalize-space(),'System')]"));
}

public boolean isPercentageChangeDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'from last month') or contains(normalize-space(),'%')]"));
}

public boolean isProductionTrendDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'Battery Production Trend')]"));
}

public boolean isCO2eTrendDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'CO2e') or contains(normalize-space(),'CO₂e')]"));
}

public boolean isWaterUsageDisplayed() {
    try {

        By waterChart =
                By.xpath("//*[contains(normalize-space(),'Water Usage')]");

        scrollToElement(waterChart);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(waterChart)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}

public boolean isElectricityChartDisplayed() {
    try {

        By electricityChart =
                By.xpath("//*[contains(normalize-space(),'Electricity Consumption')]");

        scrollToElement(electricityChart);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(electricityChart)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}

public boolean isWasteGenerationDisplayed() {
    try {

        By wasteGeneration =
                By.xpath("//*[contains(normalize-space(),'Waste Generation')]");

        scrollToElement(wasteGeneration);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(wasteGeneration)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}

public boolean isWasteRecoveryDisplayed() {
    try {

        By wasteRecovery =
                By.xpath("//*[contains(normalize-space(),'Waste Recovery Analytics')]");

        scrollToElement(wasteRecovery);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(wasteRecovery)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}

public boolean isMissingQRCodeRiskDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'Missing QR')]"));
}

public boolean isIncompletePassportRiskDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'Incomplete') and contains(normalize-space(),'Passport')]"));
}

public boolean isPoorDataQualityRiskDisplayed() {
    return isDisplayed(By.xpath("//*[contains(normalize-space(),'Poor Data Quality')]"));
}

public void refreshDashboard() {
    driver.navigate().refresh();
    wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
}

public void clickDashboardMenu() {
    clickJS(dashboardMenu);
}
    public boolean isDashboardTitleDisplayed() {
        return isDisplayed(dashboardTitle);
    }

    public boolean isSubtitleDisplayed() {
        return isDisplayed(subtitle);
    }

    public boolean areSidebarMenusDisplayed() {
        return isDisplayed(dashboardMenu)
                && isDisplayed(orgManagementMenu)
                && isDisplayed(dppManagementMenu)
                && isDisplayed(reportsMenu)
                && isDisplayed(analyticsMenu);
    }

    public boolean isDashboardMenuHighlighted() {
        try {
            WebElement menu = wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardMenu));
            String classValue = menu.getAttribute("class");
            return classValue != null &&
                    (classValue.toLowerCase().contains("active")
                            || classValue.toLowerCase().contains("bg")
                            || classValue.toLowerCase().contains("emerald"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areSummaryCardsDisplayed() {
        return isDisplayed(totalPlantsCard)
                && isDisplayed(totalProductsCard)
                && isDisplayed(totalBatchesCard)
                && isDisplayed(totalBatteriesCard)
                && isDisplayed(batteryPassportCard);
    }

    public boolean areRiskFactorCardsDisplayed() {
        return isDisplayed(riskFactorSection) && isDisplayed(severityBadge);
    }

    public boolean areChartLabelsDisplayed() {
        scrollToBottom();

        return isDisplayed(productionTrend)
                && isDisplayed(co2eTrend)
                && isDisplayed(waterUsage)
                && isDisplayed(electricityConsumption)
                && isDisplayed(wasteGeneration)
                && isDisplayed(wasteRecovery);
    }

    public boolean isUserProfileDisplayed() {
        return isDisplayed(userProfile);
    }

    public boolean isTotalPlantsCardDisplayed() {
        return isDisplayed(totalPlantsCard);
    }

    public boolean isTotalProductsCardDisplayed() {
        return isDisplayed(totalProductsCard);
    }

    public boolean isTotalBatchesCardDisplayed() {
        return isDisplayed(totalBatchesCard);
    }

    public boolean isTotalBatteriesCardDisplayed() {
        return isDisplayed(totalBatteriesCard);
    }

    public boolean isBatteryPassportCardDisplayed() {
        return isDisplayed(batteryPassportCard);
    }

    public void clickOrgManagement() {
        clickJS(orgManagementMenu);
    }

    public void clickDppManagement() {
        clickJS(dppManagementMenu);
    }

    public void clickAnalytics() {
        clickJS(analyticsMenu);
    }

    public void clickReports() {
        clickJS(reportsMenu);
    }

    public boolean isPageNavigated(String pageText) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(normalize-space(),'" + pageText + "')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
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
private void scrollToElement(By locator) {
    WebElement element = wait.until(
            ExpectedConditions.visibilityOfElementLocated(locator)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            element
    );

    try {
        Thread.sleep(1000);
    } catch (Exception e) {
    }
}
    private void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript(
                "window.scrollTo(0, document.body.scrollHeight);"
        );
    }
}