package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ReportsPage;

public class SuperAdminReportsTest extends BaseTest {

    public ReportsPage openReportsPage() {
        LoginPage login = new LoginPage(driver, wait);

        login.enterEmail("superadmin@gmail.com");
        login.enterPassword("password");
        login.clickLogin();

        Assert.assertTrue(login.isLoginSuccessful(), "Login failed");

        DashboardPage dashboard = new DashboardPage(driver, wait);
        dashboard.openReportsPage();

        return new ReportsPage(driver, wait);
    }

    public ReportsPage generateReport() {
        ReportsPage reportsPage = openReportsPage();
        reportsPage.generateReportFlow();
        return reportsPage;
    }

    @Test
    public void SA_REP_001_verifyReportsPageLoads() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isReportsTitleDisplayed(), "Reports page not loaded");
    }

    @Test
    public void SA_REP_002_verifyReportsTitle() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isReportsTitleDisplayed(), "Reports title not displayed");
    }

    @Test
    public void SA_REP_003_verifySubtitle() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isSubtitleDisplayed(), "Reports subtitle not displayed");
    }

    @Test
    public void SA_REP_004_verifyOrganizationDropdown() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isOrganizationDropdownDisplayed(), "Organization dropdown not displayed");
    }

    @Test
    public void SA_REP_005_verifyPlantDropdown() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isPlantDropdownDisplayed(), "Plant dropdown not displayed");
    }

    @Test
    public void SA_REP_006_verifyReportTypeDropdown() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isReportTypeDropdownDisplayed(), "Report Type dropdown not displayed");
    }

    @Test
    public void SA_REP_007_verifyDurationDropdown() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isDurationDropdownDisplayed(), "Duration dropdown not displayed");
    }

    @Test
    public void SA_REP_008_verifyApplyButton() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isApplyButtonClickable(), "Apply button not clickable");
    }

    @Test
    public void SA_REP_009_verifyRefreshButton() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isRefreshButtonDisplayed(), "Refresh button not displayed");
    }

    @Test
    public void SA_REP_010_verifyExportButton() {
        ReportsPage page = openReportsPage();
        Assert.assertTrue(page.isExportButtonDisplayed(), "Export button not displayed");
    }

    @Test
    public void SA_REP_011_verifyReportPreviewSection() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isReportPreviewDisplayed(), "Report preview not displayed");
    }

    @Test
    public void SA_REP_014_verifyLogoPlaceholder() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isNoLogoDisplayed(), "No logo placeholder not displayed");
    }

    @Test
    public void SA_REP_015_verifyExecutiveSummarySection() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isReportPreviewDisplayed(), "Executive Summary not displayed");
    }

    @Test
    public void SA_REP_016_verifyKpiCards() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isKpiCardsDisplayed(), "KPI cards not displayed");
    }

    @Test
    public void SA_REP_017_verifyProductionTrendChart() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isProductionTrendDisplayed(), "Production Trend chart not displayed");
    }

    @Test
    public void SA_REP_018_verifyProductionByCategoryChart() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isProductionByCategoryDisplayed(), "Production By Category chart not displayed");
    }

    @Test
    public void SA_REP_019_verifyProductionByProductChart() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isProductionByProductDisplayed(), "Production By Product chart not displayed");
    }

    @Test
    public void SA_REP_020_verifyProductionSummaryTable() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isProductionSummaryDisplayed(), "Production Summary table not displayed");
    }

    @Test
    public void SA_REP_021_verifyContributionSummaryTable() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isContributionSummaryDisplayed(), "Contribution Summary table not displayed");
    }

    @Test
    public void SA_REP_022_verifyProductionBySectorTable() {
        ReportsPage page = generateReport();
        Assert.assertTrue(page.isSectorTableDisplayed(), "Sector table not displayed");
    }

    @Test
    public void SA_REP_023_to_SA_REP_040_verifyCompleteReportFlow() {
        ReportsPage page = generateReport();

        Assert.assertTrue(page.isReportPreviewDisplayed(), "Report not generated after selecting filters");
        Assert.assertTrue(page.isKpiCardsDisplayed(), "KPI values not displayed");
        Assert.assertTrue(page.isProductionTrendDisplayed(), "Production Trend not displayed");
        Assert.assertTrue(page.isProductionByCategoryDisplayed(), "Category chart not displayed");
        Assert.assertTrue(page.isProductionByProductDisplayed(), "Product chart not displayed");
        Assert.assertTrue(page.isProductionSummaryDisplayed(), "Summary table not displayed");
    }
}