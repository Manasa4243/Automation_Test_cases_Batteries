package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SuperAdminDashboardPage;

public class SuperAdminDashboardTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    public SuperAdminDashboardPage openSuperAdminDashboard() {
        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();

        return new SuperAdminDashboardPage(driver, wait);
    }

    @Test
    public void SA_DASH_001_verifySuperAdminDashboardLoads() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDashboardLoaded(), "Super Admin Dashboard is not loaded");
    }

    @Test
    public void SA_DASH_002_verifyDashboardTitle() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isSuperAdminTitleDisplayed(), "Dashboard Overview Super Admin title is not displayed");
    }

    @Test
    public void SA_DASH_003_verifyTotalOrganizationsCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalOrganizationsDisplayed(), "Total Organizations card is not displayed");
    }

    @Test
    public void SA_DASH_004_verifyTotalPlantsCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalPlantsDisplayed(), "Total Plants card is not displayed");
    }

    @Test
    public void SA_DASH_005_verifyTotalProductsCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalProductsDisplayed(), "Total Products card is not displayed");
    }

    @Test
    public void SA_DASH_006_verifyTotalBatchesCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalBatchesDisplayed(), "Total Batches card is not displayed");
    }

    @Test
    public void SA_DASH_007_verifyTotalBatteriesCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalBatteriesDisplayed(), "Total Batteries card is not displayed");
    }

    @Test
    public void SA_DASH_008_verifyQRCodesCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isQRCodesDisplayed(), "QR Codes card is not displayed");
    }

    @Test
    public void SA_DASH_009_verifyDailyLoginsCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDailyLoginsDisplayed(), "Daily Logins card is not displayed");
    }

    @Test
    public void SA_DASH_010_verifyApiRequestsCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isApiRequestsDisplayed(), "API Requests card is not displayed");
    }

    @Test
    public void SA_DASH_013_verifySidebarMenus() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.areSidebarMenusDisplayed(), "Sidebar menus are not displayed");
    }

    @Test
    public void SA_DASH_015_verifyUserRoleDisplay() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isSuperAdminRoleDisplayed(), "Super Admin role is not displayed");
    }

    @Test
    public void SA_DASH_016_verifyRequestsVsErrorsChart() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRequestsVsErrorsDisplayed(), "Requests vs Errors chart is not displayed");
    }

    @Test
    public void SA_DASH_017_verifyRiskTrendChart() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRiskTrendDisplayed(), "Risk Trend chart is not displayed");
    }

    @Test
    public void SA_DASH_018_verifyDataQualityChart() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDataQualityDisplayed(), "Data Quality donut chart is not displayed");
    }

    @Test
    public void SA_DASH_019_verifyEolStatusChart() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isEolStatusDisplayed(), "End-of-Life Status chart is not displayed");
    }

    @Test
    public void SA_DASH_020_verifyWasteRecoverySection() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isWasteRecoveryDisplayed(), "Waste Recovery section is not displayed");
    }

    @Test
    public void SA_DASH_021_verifyCircularScoreCard() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isCircularScoreDisplayed(), "Circular Score card is not displayed");
    }

    @Test
    public void SA_DASH_022_verifyRecentActivityPanel() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRecentActivityDisplayed(), "Recent Activity panel is not displayed");
    }

    @Test
    public void SA_DASH_023_verifyRecentIncidentLog() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isIncidentLogDisplayed(), "Recent Incident Log is not displayed");
    }

    @Test
    public void SA_DASH_024_verifyTotalOrganizationsCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalOrganizationsDisplayed(), "Total Organizations count/card is not displayed");
    }

    @Test
    public void SA_DASH_025_verifyTotalPlantsCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalPlantsDisplayed(), "Total Plants count/card is not displayed");
    }

    @Test
    public void SA_DASH_026_verifyTotalProductsCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalProductsDisplayed(), "Total Products count/card is not displayed");
    }

    @Test
    public void SA_DASH_027_verifyTotalBatchesCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalBatchesDisplayed(), "Total Batches count/card is not displayed");
    }

    @Test
    public void SA_DASH_028_verifyTotalBatteriesCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isTotalBatteriesDisplayed(), "Total Batteries count/card is not displayed");
    }

    @Test
    public void SA_DASH_029_verifyQrCodesCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isQRCodesDisplayed(), "QR Codes count/card is not displayed");
    }

    @Test
    public void SA_DASH_030_verifyDailyLoginCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDailyLoginsDisplayed(), "Daily Login count/card is not displayed");
    }

    @Test
    public void SA_DASH_031_verifyApiRequestCount() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isApiRequestsDisplayed(), "API Request count/card is not displayed");
    }

    @Test
    public void SA_DASH_032_verifyPercentageGrowthValues() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isPercentageGrowthDisplayed(), "Percentage growth values are not displayed");
    }

    @Test
    public void SA_DASH_033_verifyRequestsChartData() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRequestsVsErrorsDisplayed(), "Requests chart data is not displayed");
    }

    @Test
    public void SA_DASH_034_verifyErrorsChartData() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isErrorChartDataDisplayed(), "Errors chart data is not displayed");
    }

    @Test
    public void SA_DASH_035_verifyRiskTrendHighSeverity() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isHighRiskDisplayed(), "High severity risk count is not displayed");
    }

    @Test
    public void SA_DASH_036_verifyRiskTrendMediumSeverity() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isMediumRiskDisplayed(), "Medium severity risk count is not displayed");
    }

    @Test
    public void SA_DASH_037_verifyRiskTrendLowSeverity() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isLowRiskDisplayed(), "Low severity risk count is not displayed");
    }

    @Test
    public void SA_DASH_038_verifyDataQualityPercentage() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDataQualityDisplayed(), "Data Quality percentage is not displayed");
    }

    @Test
    public void SA_DASH_039_verifyRecentActivityUpdates() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRecentActivityDisplayed(), "Recent Activity is not updated/displayed");
    }

    @Test
    public void SA_DASH_040_verifyViewAllActivityLink() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.clickViewAllActivity(), "View All activity link is not working");
    }

    @Test
    public void SA_DASH_045_verifyEolRecycledPercentage() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRecycledDisplayed(), "Recycled percentage is not displayed");
    }

    @Test
    public void SA_DASH_046_verifyEolRefurbishedPercentage() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isRefurbishedDisplayed(), "Refurbished percentage is not displayed");
    }

    @Test
    public void SA_DASH_047_verifyEolReusedPercentage() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isReusedDisplayed(), "Reused percentage is not displayed");
    }

    @Test
    public void SA_DASH_048_verifyEolDisposedPercentage() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isDisposedDisplayed(), "Disposed percentage is not displayed");
    }

    @Test
    public void SA_DASH_049_verifyWasteRecoveryValue() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isWasteRecoveryDisplayed(), "Waste Recovery value is not displayed");
    }

    @Test
    public void SA_DASH_050_verifyCircularScoreCalculation() {
        SuperAdminDashboardPage page = openSuperAdminDashboard();
        Assert.assertTrue(page.isCircularScoreDisplayed(), "Circular Score is not displayed");
    }
}