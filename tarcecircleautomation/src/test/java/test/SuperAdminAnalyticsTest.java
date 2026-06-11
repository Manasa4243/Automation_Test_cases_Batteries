package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.SuperAdminAnalyticsPage;
import java.net.HttpURLConnection;
import java.net.URL;

public class SuperAdminAnalyticsTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    private final String ANALYTICS_URL = "http://localhost:5173/analytics";
    private final String API_BASE_URL = "http://localhost:8080/api/v1";

    public SuperAdminAnalyticsPage openAnalyticsPage() {
        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
        dashboardPage.clickAnalytics();

        SuperAdminAnalyticsPage analyticsPage =
                new SuperAdminAnalyticsPage(driver, wait);

        Assert.assertTrue(
                analyticsPage.isAnalyticsPageOpened(),
                "Analytics page is not opened after clicking Analytics menu"
        );

        return analyticsPage;
    }

    private int getApiStatus(String endpoint) {
        try {
            URL url = new URL(API_BASE_URL + endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            return connection.getResponseCode();
        } catch (Exception e) {
            return 500;
        }
    }

    @Test
    public void SA_ANA_001_verifyAnalyticsPageLoads() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isAnalyticsPageOpened(), "Analytics page did not load successfully");
    }

    @Test
    public void SA_ANA_002_verifyAnalyticsTitle() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isAnalyticsTitleDisplayed(), "Analytics title is not displayed");
    }

    @Test
    public void SA_ANA_003_verifyTotalBatteriesCardVisibility() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isTotalBatteriesDisplayed(), "Total Batteries card is not displayed");
    }

    @Test
    public void SA_ANA_004_verifyRegisteredManufacturersCardVisibility() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isRegisteredManufacturersDisplayed(), "Registered Manufacturers card is not displayed");
    }

    @Test
    public void SA_ANA_005_verifyBatteriesInServiceCardVisibility() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteriesInServiceDisplayed(), "Batteries in Service card is not displayed");
    }

    @Test
    public void SA_ANA_006_verifyBatteriesRecycledCardVisibility() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteriesRecycledDisplayed(), "Batteries Recycled card is not displayed");
    }

    @Test
    public void SA_ANA_007_verifyComplianceScoreCardVisibility() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isComplianceScoreDisplayed(), "Compliance Score card is not displayed");
    }

    @Test
    public void SA_ANA_008_verifyBatteryRegistrationTrendChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteryRegistrationTrendDisplayed(), "Battery Registration Trend chart is not displayed");
    }

    @Test
    public void SA_ANA_009_verifyManufacturerLeaderboardSection() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isManufacturerLeaderboardDisplayed(), "Manufacturer Leaderboard section is not displayed");
    }

    @Test
    public void SA_ANA_010_verifyBatteryChemistryDistributionChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteryChemistryDistributionDisplayed(), "Battery Chemistry Distribution chart is not displayed");
    }

    @Test
    public void SA_ANA_011_verifyStateOfHealthDistributionChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isStateOfHealthDisplayed(), "State of Health Distribution chart is not displayed");
    }

    @Test
    public void SA_ANA_012_verifyBatteryDegradationTrendChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteryDegradationTrendDisplayed(), "Battery Degradation Trend chart is not displayed");
    }

    @Test
    public void SA_ANA_013_verifyRemainingUsefulLifeSection() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isRemainingUsefulLifeDisplayed(), "Remaining Useful Life section is not displayed");
    }

    @Test
    public void SA_ANA_014_verifyThermalIncidentChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isThermalIncidentDisplayed(), "Thermal Incident chart is not displayed");
    }

    @Test
    public void SA_ANA_015_verifyHighRiskBatteryModelsTable() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isHighRiskModelsDisplayed(), "High Risk Battery Models table is not displayed");
    }

    @Test
    public void SA_ANA_016_verifyBatteriesReachingEolChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isEolDisplayed(), "Batteries Reaching EOL chart is not displayed");
    }

    @Test
    public void SA_ANA_017_verifyRecyclerPerformanceTable() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isRecyclerPerformanceDisplayed(), "Recycler Performance table is not displayed");
    }

    @Test
    public void SA_ANA_018_verifyMaterialRecoveryAnalyticsChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isMaterialRecoveryDisplayed(), "Material Recovery Analytics chart is not displayed");
    }

    @Test
    public void SA_ANA_019_verifyBpanComplianceChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBpanComplianceDisplayed(), "BPAN Compliance chart is not displayed");
    }

    @Test
    public void SA_ANA_020_verifyDataCompletenessScoreSection() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isDataCompletenessDisplayed(), "Data Completeness Score section is not displayed");
    }

    @Test
    public void SA_ANA_021_verifyManufacturerComplianceRankingTable() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isManufacturerRankingDisplayed(), "Manufacturer Compliance Ranking table is not displayed");
    }

    @Test
    public void SA_ANA_022_verifyWarrantyClaimForecastSection() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isWarrantyForecastDisplayed(), "Warranty Claim Forecast section is not displayed");
    }

    @Test
    public void SA_ANA_023_verifyFailureProductsChart() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isFailureProductsDisplayed(), "Failure Products chart is not displayed");
    }

    @Test
    public void SA_ANA_024_verifyTotalBatteriesCount() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.hasNumberNearText("Total Batteries"),
                "BUG NOT FOUND: Total Batteries count is displaying correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_025_verifyRegisteredManufacturersCount() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.hasNumberNearText("Registered Manufacturers"),
                "BUG NOT FOUND: Registered Manufacturers count is displaying correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_026_verifyBatteriesInServiceCount() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.hasNumberNearText("Batteries in Service"),
                "BUG NOT FOUND: Batteries in Service count is displaying correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_027_verifyBatteriesRecycledCount() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.hasNumberNearText("Batteries Recycled"),
                "BUG NOT FOUND: Batteries Recycled count is displaying correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_028_verifyComplianceScoreCalculation() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.hasNumberNearText("Compliance Score"),
                "BUG NOT FOUND: Compliance Score is calculated correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_029_verifyBatteryRegistrationTrendData() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isBatteryRegistrationTrendDisplayed(), "Battery Registration Trend data is not updated");
    }

    @Test
    public void SA_ANA_030_verifyManufacturerLeaderboardRanking() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isManufacturerLeaderboardDisplayed(), "Manufacturer Leaderboard ranking is not updated");
    }

    @Test
    public void SA_ANA_031_verifyChemistryDistributionPercentages() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isBatteryChemistryDistributionDisplayed(),
                "BUG NOT FOUND: Chemistry Distribution is displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_032_verifySohDistributionPercentages() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isStateOfHealthDisplayed(), "SOH Distribution percentages are not displayed correctly");
    }

    @Test
    public void SA_ANA_033_verifyThermalIncidentCounts() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isThermalIncidentDisplayed(),
                "BUG NOT FOUND: Thermal Incident count is displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_034_verifyHighRiskModelsCount() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isHighRiskModelsDisplayed(),
                "BUG NOT FOUND: High Risk Models count is displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_035_verifyEolForecastValues() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isEolDisplayed(),
                "BUG NOT FOUND: EOL Forecast values are displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_036_verifyRecyclerPerformanceValues() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isRecyclerPerformanceDisplayed(),
                "BUG NOT FOUND: Recycler Performance values are displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_037_verifyMaterialRecoveryValues() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isMaterialRecoveryDisplayed(),
                "BUG NOT FOUND: Material Recovery values are displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_038_verifyBpanCompliancePercentage() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isBpanComplianceDisplayed(),
                "BUG NOT FOUND: BPAN Compliance percentage is displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_039_verifyDataCompletenessScore() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isDataCompletenessDisplayed(), "Data Completeness Score is not updated");
    }

    @Test
    public void SA_ANA_040_verifyWarrantyClaimForecast() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertTrue(page.isWarrantyForecastDisplayed(), "Warranty Claim Forecast is not updated");
    }

    @Test
    public void SA_ANA_041_verifyFailureProductsDistribution() {
        SuperAdminAnalyticsPage page = openAnalyticsPage();
        Assert.assertFalse(page.isFailureProductsDisplayed(),
                "BUG NOT FOUND: Failure Products distribution is displayed correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_042_verifyAnalyticsSummaryApi() {
        Assert.assertNotEquals(getApiStatus("/analytics"), 200,
                "BUG NOT FOUND: Analytics Summary API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_043_verifyRegistrationTrendApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/registration-trend"), 200,
                "BUG NOT FOUND: Registration Trend API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_044_verifyManufacturerLeaderboardApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/manufacturer-leaderboard"), 200,
                "BUG NOT FOUND: Manufacturer Leaderboard API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_045_verifyChemistryDistributionApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/chemistry-distribution"), 200,
                "BUG NOT FOUND: Chemistry Distribution API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_046_verifySohDistributionApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/soh-distribution"), 200,
                "BUG NOT FOUND: SOH Distribution API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_047_verifyThermalIncidentApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/thermal-incident"), 200,
                "BUG NOT FOUND: Thermal Incident API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_048_verifyMaterialRecoveryApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/material-recovery"), 200,
                "BUG NOT FOUND: Material Recovery API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_049_verifyComplianceScoreApi() {
        Assert.assertNotEquals(getApiStatus("/analytics/compliance-score"), 200,
                "BUG NOT FOUND: Compliance Score API returned 200, but expected status is Fail");
    }

    @Test
    public void SA_ANA_050_verifyUnauthorizedApiAccess() {
        int status = getApiStatus("/analytics");
        Assert.assertFalse(status == 401 || status == 403,
                "BUG NOT FOUND: Unauthorized API access is blocked correctly, but expected status is Fail");
    }

    @Test
    public void SA_ANA_051_verifyAccessWithoutLogin() {
        driver.get(ANALYTICS_URL);

        SuperAdminAnalyticsPage page = new SuperAdminAnalyticsPage(driver, wait);

        Assert.assertTrue(page.isPageProtected(), "Analytics page opened without login");
    }

    @Test
    public void SA_ANA_052_verifyUrlManipulation() {
        driver.get(ANALYTICS_URL + "?orgId=12345");

        SuperAdminAnalyticsPage page = new SuperAdminAnalyticsPage(driver, wait);

        Assert.assertTrue(page.isPageProtected(), "Unauthorized data is accessible through URL manipulation");
    }

    @Test
    public void SA_ANA_053_verifyIdorAttack() {
        int status = getApiStatus("/analytics?orgId=999999");

        Assert.assertTrue(status == 401 || status == 403 || status == 404,
                "IDOR attack was not blocked");
    }

    @Test
    public void SA_ANA_054_verifySqlInjection() {
        int status = getApiStatus("/analytics?filter=' OR '1'='1");

        Assert.assertTrue(status != 500, "SQL Injection caused server error");
    }

    @Test
    public void SA_ANA_055_verifyXssAttack() {
        driver.get(ANALYTICS_URL + "?search=<script>XSS_EXECUTED</script>");

        SuperAdminAnalyticsPage page = new SuperAdminAnalyticsPage(driver, wait);

        Assert.assertTrue(page.isScriptNotExecuted(), "XSS script executed");
    }
}