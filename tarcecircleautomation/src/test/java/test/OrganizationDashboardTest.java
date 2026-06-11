package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrganizationDashboardPage;


public class OrganizationDashboardTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    public OrganizationDashboardPage openDashboardPage() {
        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();

        return new OrganizationDashboardPage(driver, wait);
    }

    @Test
    public void ORG_DASH_001_verifyDashboardPageLoads() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isDashboardPageOpened(),
                "Dashboard Overview page is not loaded properly"
        );
    }

    @Test
    public void ORG_DASH_002_verifyPageTitle() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isDashboardTitleDisplayed(),
                "Dashboard Overview heading is not visible"
        );
    }

    @Test
    public void ORG_DASH_004_verifySidebarMenus() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.areSidebarMenusDisplayed(),
                "Dashboard, Org Management, DPP Management, Reports, or Analytics menu is missing"
        );
    }

    @Test
    public void ORG_DASH_005_verifyActiveMenuHighlight() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isDashboardMenuHighlighted(),
                "Dashboard menu is not highlighted"
        );
    }

    @Test
    public void ORG_DASH_008_verifySeverityBadges() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.areRiskFactorCardsDisplayed(),
                "Risk factor cards or severity badges are not displayed"
        );
    }

    @Test
    public void ORG_DASH_010_verifyChartLabels() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.areChartLabelsDisplayed(),
                "One or more dashboard chart labels are missing"
        );
    }

    @Test
    public void ORG_DASH_014_verifyUserRoleDisplay() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isUserProfileDisplayed(),
                "Logged-in user name or role is not displayed"
        );
    }

    @Test
    public void ORG_DASH_015_verifyTotalPlantsCard() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isTotalPlantsCardDisplayed(),
                "Total Plants card is not displayed"
        );
    }

    @Test
    public void ORG_DASH_016_verifyTotalProductsCard() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isTotalProductsCardDisplayed(),
                "Total Products card is not displayed"
        );
    }

    @Test
    public void ORG_DASH_017_verifyTotalBatchesCard() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isTotalBatchesCardDisplayed(),
                "Total Batches card is not displayed"
        );
    }

    @Test
    public void ORG_DASH_018_verifyTotalBatteriesCard() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isTotalBatteriesCardDisplayed(),
                "Total Batteries card is not displayed"
        );
    }

    @Test
    public void ORG_DASH_019_verifyBatteryPassportCard() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        Assert.assertTrue(
                dashboardPage.isBatteryPassportCardDisplayed(),
                "Battery Passports card is not displayed"
        );
    }

    @Test
    public void ORG_DASH_031_verifyOrgManagementNavigation() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        dashboardPage.clickOrgManagement();

        Assert.assertTrue(
                dashboardPage.isPageNavigated("Org Management")
                        || dashboardPage.isPageNavigated("Organizations")
                        || dashboardPage.isPageNavigated("Plants"),
                "Org Management navigation is not working"
        );
    }

    @Test
    public void ORG_DASH_032_verifyDppManagementNavigation() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        dashboardPage.clickDppManagement();

        Assert.assertTrue(
                dashboardPage.isPageNavigated("DPP")
                        || dashboardPage.isPageNavigated("Products")
                        || dashboardPage.isPageNavigated("Batch"),
                "DPP Management navigation is not working"
        );
    }

    @Test
    public void ORG_DASH_033_verifyAnalyticsNavigation() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        dashboardPage.clickAnalytics();

        Assert.assertTrue(
                dashboardPage.isPageNavigated("Analytics"),
                "Analytics navigation is not working"
        );
    }

    @Test
    public void ORG_DASH_034_verifyReportsNavigation() {
        OrganizationDashboardPage dashboardPage = openDashboardPage();

        dashboardPage.clickReports();

        Assert.assertTrue(
                dashboardPage.isPageNavigated("Reports")
                        || dashboardPage.isPageNavigated("Audits"),
                "Reports navigation is not working"
        );
    }
    @Test
public void ORG_DASH_009_verifyChartSeverityLabels() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.areSeverityLabelsDisplayed(),
            "HIGH or CRITICAL severity labels are not displayed"
    );
}

@Test
public void ORG_DASH_011_verifyResponsiveChartLabelsReadable() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.areChartLabelsDisplayed(),
            "Chart labels are not readable or not displayed"
    );
}

@Test
public void ORG_DASH_012_verifyHeaderIconsDisplayed() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.areHeaderIconsDisplayed(),
            "Header icons are not displayed properly"
    );
}

@Test
public void ORG_DASH_013_verifyUserRoleDisplay() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isUserNameAndRoleDisplayed(),
            "User name or role is not displayed"
    );
}

@Test
public void ORG_DASH_020_verifyPercentageChangeDisplayed() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isPercentageChangeDisplayed(),
            "Percentage change is not displayed"
    );
}

@Test
public void ORG_DASH_021_verifyBatteryProductionTrend() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isProductionTrendDisplayed(),
            "Battery Production Trend chart is not displayed"
    );
}

@Test
public void ORG_DASH_022_verifyCO2eTrendChart() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isCO2eTrendDisplayed(),
            "CO2e Trend chart is not displayed"
    );
}

@Test
public void ORG_DASH_023_verifyWaterUsageChart() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isWaterUsageDisplayed(),
            "Water Usage chart is not displayed"
    );
}

@Test
public void ORG_DASH_024_verifyElectricityConsumptionChart() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isElectricityChartDisplayed(),
            "Electricity Consumption chart is not displayed"
    );
}

@Test
public void ORG_DASH_025_verifyWasteGenerationChart() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isWasteGenerationDisplayed(),
            "Waste Generation chart is not displayed"
    );
}

@Test
public void ORG_DASH_026_verifyWasteRecoveryAnalytics() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isWasteRecoveryDisplayed(),
            "Waste Recovery Analytics is not displayed"
    );
}

@Test
public void ORG_DASH_027_verifyMissingQRCodeRisk() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isMissingQRCodeRiskDisplayed(),
            "Missing QR Codes risk is not displayed"
    );
}

@Test
public void ORG_DASH_028_verifyIncompletePassportDataRisk() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isIncompletePassportRiskDisplayed(),
            "Incomplete Battery Passport Data risk is not displayed"
    );
}

@Test
public void ORG_DASH_029_verifyPoorDataQualityRisk() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    Assert.assertTrue(
            dashboardPage.isPoorDataQualityRiskDisplayed(),
            "Poor Data Quality risk is not displayed"
    );
}

@Test
public void ORG_DASH_030_verifyDashboardRefresh() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    dashboardPage.refreshDashboard();

    Assert.assertTrue(
            dashboardPage.isDashboardPageOpened(),
            "Dashboard did not load correctly after refresh"
    );
}

@Test
public void ORG_DASH_031_verifyDashboardMenuNavigation() {
    OrganizationDashboardPage dashboardPage = openDashboardPage();

    dashboardPage.clickDashboardMenu();

    Assert.assertTrue(
            dashboardPage.isDashboardPageOpened(),
            "Dashboard menu navigation is not working"
    );
}
}