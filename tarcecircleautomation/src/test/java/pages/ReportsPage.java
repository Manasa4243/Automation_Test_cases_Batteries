package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReportsPage {

    WebDriver driver;
    WebDriverWait wait;

    public ReportsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By reportsTitle = By.xpath("//h1[contains(normalize-space(),'Reports')]");
    private By subtitle = By.xpath("//*[contains(normalize-space(),'View and download all reports')]");

    private By organizationDropdown = By.xpath("(//button[contains(@role,'combobox')] | //div[contains(@role,'combobox')])[1]");
    private By plantDropdown = By.xpath("(//button[contains(@role,'combobox')] | //div[contains(@role,'combobox')])[2]");
    private By reportTypeDropdown = By.xpath("(//button[contains(@role,'combobox')] | //div[contains(@role,'combobox')])[3]");
    private By durationDropdown = By.xpath("(//button[contains(@role,'combobox')] | //div[contains(@role,'combobox')])[4]");

    private By applyBtn = By.xpath("//button[contains(normalize-space(),'Apply')]");
    private By refreshBtn = By.xpath("//button[contains(normalize-space(),'Refresh')]");
    private By exportBtn = By.xpath("//button[contains(normalize-space(),'Export')]");

    public boolean isReportsTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(reportsTitle)).isDisplayed();
    }

    public boolean isSubtitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(subtitle)).isDisplayed();
    }

    public boolean isOrganizationDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationDropdown)).isDisplayed();
    }

    public boolean isPlantDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(plantDropdown)).isDisplayed();
    }

    public boolean isReportTypeDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(reportTypeDropdown)).isDisplayed();
    }

    public boolean isDurationDropdownDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(durationDropdown)).isDisplayed();
    }

    public boolean isApplyButtonClickable() {
        return wait.until(ExpectedConditions.elementToBeClickable(applyBtn)).isEnabled();
    }

    public boolean isRefreshButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(refreshBtn)).isDisplayed();
    }

    public boolean isExportButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(exportBtn)).isDisplayed();
    }

    public void selectFirstClickableOption(By dropdown) {
        WebElement drop = wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        scrollTo(drop);
        jsClick(drop);

        By firstOption = By.xpath(
                "(//*[@role='option' and not(@aria-disabled='true')]"
              + " | //div[contains(@class,'cursor-pointer')]"
              + " | //li[not(contains(@class,'disabled'))])[1]"
        );

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(firstOption));
        scrollTo(option);
        jsClick(option);
    }

    public void selectOrganization() {
        selectFirstClickableOption(organizationDropdown);
    }

    public void selectPlant() {
        selectFirstClickableOption(plantDropdown);
    }

    public void selectReportType() {
        selectFirstClickableOption(reportTypeDropdown);
    }

    public void selectDuration() {
        selectFirstClickableOption(durationDropdown);
    }

    public void clickApply() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(applyBtn));
        scrollTo(btn);
        jsClick(btn);
    }

    public void generateReportFlow() {
        selectOrganization();
        selectPlant();
        selectReportType();
        selectDuration();
        clickApply();
    }

    public boolean isReportPreviewDisplayed() {
        By preview = By.xpath(
                "//*[contains(normalize-space(),'Executive Summary') " +
                "or contains(normalize-space(),'Production Trend') " +
                "or contains(normalize-space(),'Summary')]"
        );

        return wait.until(ExpectedConditions.visibilityOfElementLocated(preview)).isDisplayed();
    }

    public boolean isNoLogoDisplayed() {
        By noLogo = By.xpath("//*[contains(normalize-space(),'No logo')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(noLogo)).isDisplayed();
    }

    public boolean isKpiCardsDisplayed() {
        return driver.getPageSource().contains("Units")
                && driver.getPageSource().contains("Capacity")
                && driver.getPageSource().contains("Batches")
                && driver.getPageSource().contains("Pass Rate")
                && driver.getPageSource().contains("Rejection Rate");
    }

    public boolean isProductionTrendDisplayed() {
        return driver.getPageSource().contains("Production Trend");
    }

    public boolean isProductionByCategoryDisplayed() {
        return driver.getPageSource().contains("Production By Category");
    }

    public boolean isProductionByProductDisplayed() {
        return driver.getPageSource().contains("Production By Product");
    }

    public boolean isProductionSummaryDisplayed() {
        return driver.getPageSource().contains("Production Summary");
    }

    public boolean isContributionSummaryDisplayed() {
        return driver.getPageSource().contains("Contribution");
    }

    public boolean isSectorTableDisplayed() {
        return driver.getPageSource().contains("Sector");
    }

    private void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", element
        );
    }
    public void selectReportTypeByText(String reportName) {
    WebElement drop = wait.until(ExpectedConditions.elementToBeClickable(reportTypeDropdown));
    scrollTo(drop);
    jsClick(drop);

    By option = By.xpath(
            "//*[normalize-space()='" + reportName + "' or contains(normalize-space(),'" + reportName + "')]"
    );

    WebElement reportOption = wait.until(ExpectedConditions.elementToBeClickable(option));
    scrollTo(reportOption);
    jsClick(reportOption);
}

public void generateSustainabilityReport() {
    selectOrganization();
    selectPlant();
    selectReportTypeByText("SUSTAINABILITY_REPORT");
    selectDuration();
    clickApply();
}

public boolean isSustainabilityReportTitleDisplayed() {
    return driver.getPageSource().contains("SUSTAINABILITY_REPORT");
}

public boolean isBrandLogoDisplayed() {
    return driver.getPageSource().contains("Logo")
            || driver.getPageSource().contains("Brand");
}

public boolean isFacilityDetailsDisplayed() {
    return driver.getPageSource().contains("Facility")
            && driver.getPageSource().contains("Period")
            && driver.getPageSource().contains("Location");
}

public boolean isCarbonFootprintKpiDisplayed() {
    return driver.getPageSource().contains("Carbon Footprint");
}

public boolean isWaterConsumedKpiDisplayed() {
    return driver.getPageSource().contains("Water Consumed");
}

public boolean isElectricityConsumedKpiDisplayed() {
    return driver.getPageSource().contains("Electricity Consumed");
}

public boolean isWasteGeneratedKpiDisplayed() {
    return driver.getPageSource().contains("Waste Generated");
}

public boolean isWasteRecycledKpiDisplayed() {
    return driver.getPageSource().contains("Waste Recycled");
}

public boolean isEnergyConsumptionChartDisplayed() {
    return driver.getPageSource().contains("Energy Consumption");
}

public boolean isWaterConsumptionChartDisplayed() {
    scrollDown();
    return driver.getPageSource().contains("Water Consumption");
}

public boolean isWasteGeneratedChartDisplayed() {
    scrollDown();
    return driver.getPageSource().contains("Waste Generated");
}

public boolean isElectricityConsumptionChartDisplayed() {
    scrollDown();
    return driver.getPageSource().contains("Electricity Consumption");
}

public boolean isMonthlyElectricitySectionDisplayed() {
    scrollDown();
    return driver.getPageSource().contains("Monthly Overall Electricity Consumption");
}

public boolean isObservationDisplayed() {
    return driver.getPageSource().contains("Observation");
}

public void clickRefresh() {
    By refreshBtn = By.xpath("//button[contains(normalize-space(),'Refresh')]");
    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(refreshBtn));
    scrollTo(btn);
    jsClick(btn);
}

public void clickExport() {
    By exportBtn = By.xpath("//button[contains(normalize-space(),'Export')]");
    WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(exportBtn));
    scrollTo(btn);
    jsClick(btn);
}

public void scrollDown() {
    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700)");
}
}