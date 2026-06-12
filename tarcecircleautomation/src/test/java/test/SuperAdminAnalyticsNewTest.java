package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SuperAdminAnalyticsNewPage;

public class SuperAdminAnalyticsNewTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    public SuperAdminAnalyticsNewPage openAnalyticsPage() {

        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
        dashboardPage.clickAnalytics();

        SuperAdminAnalyticsNewPage page =
                new SuperAdminAnalyticsNewPage(driver, wait);

        Assert.assertTrue(
                page.isAnalyticsPageOpened(),
                "Analytics page is not opened through Dashboard sidebar"
        );

        return page;
    }

    @Test
    public void SA_ANA_001_verifyAnalyticsPageLoads() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isAnalyticsPageOpened(),
                "Analytics page did not load successfully"
        );
    }

    @Test
    public void SA_ANA_002_verifyAnalyticsTitle() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isAnalyticsTitleDisplayed(),
                "Analytics title is not displayed"
        );
    }

    @Test
    public void SA_ANA_003_verifyTotalBatteriesCardVisibility() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isTotalBatteriesDisplayed(),
                "Total Batteries card is not displayed"
        );
    }

    @Test
    public void SA_ANA_004_verifyRegisteredManufacturersCardVisibility() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isRegisteredManufacturersDisplayed(),
                "Registered Manufacturers card is not displayed"
        );
    }

    @Test
    public void SA_ANA_005_verifyBatteriesInServiceCardVisibility() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isBatteriesInServiceDisplayed(),
                "Batteries in Service card is not displayed"
        );
    }

    @Test
    public void SA_ANA_006_verifyBatteriesRecycledCardVisibility() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isBatteriesRecycledDisplayed(),
                "Batteries Recycled card is not displayed"
        );
    }

    @Test
    public void SA_ANA_007_verifyComplianceScoreCardVisibility() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isComplianceScoreDisplayed(),
                "Compliance Score card is not displayed"
        );
    }

    @Test
    public void SA_ANA_008_verifyBatteryRegistrationTrendChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isBatteryRegistrationTrendDisplayed(),
                "Battery Registration Trend chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_009_verifyManufacturerLeaderboardSection() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isManufacturerLeaderboardDisplayed(),
                "Manufacturer Leaderboard section is not displayed"
        );
    }

    @Test
    public void SA_ANA_010_verifyBatteryChemistryDistributionChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isBatteryChemistryDistributionDisplayed(),
                "Battery Chemistry Distribution chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_011_verifyStateOfHealthDistributionChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isStateOfHealthDisplayed(),
                "State of Health Distribution chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_012_verifyBatteryDegradationTrendChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isBatteryDegradationTrendDisplayed(),
                "Battery Degradation Trend chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_013_verifyRemainingUsefulLifeSection() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isRemainingUsefulLifeDisplayed(),
                "Remaining Useful Life section is not displayed"
        );
    }

    @Test
    public void SA_ANA_014_verifyThermalIncidentChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isThermalIncidentDisplayed(),
                "Thermal Incident chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_015_verifyHighRiskBatteryModelsTable() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isHighRiskModelsDisplayed(),
                "High Risk Battery Models table is not displayed"
        );
    }

    @Test
    public void SA_ANA_016_verifyBatteriesReachingEolChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isEolDisplayed(),
                "Batteries Reaching EOL chart is not displayed"
        );
    }

    @Test
    public void SA_ANA_017_verifyRecyclerPerformanceTable() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isRecyclerPerformanceDisplayed(),
                "Recycler Performance table is not displayed"
        );
    }

    @Test
    public void SA_ANA_018_verifyMaterialRecoveryAnalyticsChart() {
        SuperAdminAnalyticsNewPage page = openAnalyticsPage();

        Assert.assertTrue(
                page.isMaterialRecoveryDisplayed(),
                "Material Recovery Analytics chart is not displayed"
        );
    }

   @Test
public void SA_ANA_019_verifyBpanComplianceChart() {

    SuperAdminAnalyticsNewPage page = openAnalyticsPage();

    page.scrollDownTwice();

    Assert.assertTrue(
            page.isBpanComplianceDisplayed(),
            "BPAN Compliance chart is not displayed"
    );
}

@Test
public void SA_ANA_020_verifyDataCompletenessScoreSection() {

    SuperAdminAnalyticsNewPage page = openAnalyticsPage();

    page.scrollDownTwice();

    Assert.assertTrue(
            page.isDataCompletenessDisplayed(),
            "Data Completeness Score section is not displayed"
    );
}

@Test
public void SA_ANA_021_verifyManufacturerComplianceRankingTable() {

    SuperAdminAnalyticsNewPage page = openAnalyticsPage();

    page.scrollDownTwice();

    Assert.assertTrue(
            page.isManufacturerRankingDisplayed(),
            "Manufacturer Compliance Ranking table is not displayed"
    );
}

@Test
public void SA_ANA_022_verifyWarrantyClaimForecastSection() {

    SuperAdminAnalyticsNewPage page = openAnalyticsPage();

    page.scrollDownTwice();

    Assert.assertTrue(
            page.isWarrantyForecastDisplayed(),
            "Warranty Claim Forecast section is not displayed"
    );
}

@Test
public void SA_ANA_023_verifyFailureProductsChart() {

    SuperAdminAnalyticsNewPage page = openAnalyticsPage();

    page.scrollDownTwice();

    Assert.assertTrue(
            page.isFailureProductsDisplayed(),
            "Failure Products chart is not displayed"
    );
}
}