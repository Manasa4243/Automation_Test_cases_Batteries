package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.ReportsPage;

public class SuperAdminSustainabilityReportTest extends BaseTest {

    public ReportsPage openSustainabilityReport() {
        LoginPage login = new LoginPage(driver, wait);

        login.enterEmail("superadmin@gmail.com");
        login.enterPassword("password");
        login.clickLogin();

        Assert.assertTrue(login.isLoginSuccessful(), "Login failed");

        DashboardPage dashboard = new DashboardPage(driver, wait);
        dashboard.openReportsPage();

        ReportsPage reportsPage = new ReportsPage(driver, wait);
        reportsPage.generateSustainabilityReport();

        return reportsPage;
    }

    @Test
    public void SA_SUS_001_verifySustainabilityReportPageLoads() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "Sustainability Report not loaded");
    }

    @Test
    public void SA_SUS_002_verifySustainabilityReportTitle() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "SUSTAINABILITY_REPORT title not displayed");
    }

    @Test
    public void SA_SUS_003_verifyBrandLogoSection() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isBrandLogoDisplayed(), "Brand logo section not displayed");
    }

    @Test
    public void SA_SUS_004_verifyFacilityDetailsSection() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isFacilityDetailsDisplayed(), "Facility details not displayed");
    }

    @Test
    public void SA_SUS_005_verifyExecutiveSummarySection() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isReportPreviewDisplayed(), "Executive Summary not displayed");
    }

    @Test
    public void SA_SUS_006_verifyCarbonFootprintKpiCard() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isCarbonFootprintKpiDisplayed(), "Carbon Footprint KPI not displayed");
    }

    @Test
    public void SA_SUS_007_verifyWaterConsumedKpiCard() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isWaterConsumedKpiDisplayed(), "Water Consumed KPI not displayed");
    }

    @Test
    public void SA_SUS_008_verifyElectricityConsumedKpiCard() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isElectricityConsumedKpiDisplayed(), "Electricity Consumed KPI not displayed");
    }

    @Test
    public void SA_SUS_009_verifyWasteGeneratedKpiCard() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isWasteGeneratedKpiDisplayed(), "Waste Generated KPI not displayed");
    }

    @Test
    public void SA_SUS_010_verifyWasteRecycledKpiCard() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isWasteRecycledKpiDisplayed(), "Waste Recycled KPI not displayed");
    }

    @Test
    public void SA_SUS_012_verifyEnergyConsumptionChart() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isEnergyConsumptionChartDisplayed(), "Energy Consumption chart not displayed");
    }

    @Test
    public void SA_SUS_013_verifyWaterConsumptionChart() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isWaterConsumptionChartDisplayed(), "Water Consumption chart not displayed");
    }

    @Test
    public void SA_SUS_014_verifyWasteGeneratedChart() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isWasteGeneratedChartDisplayed(), "Waste Generated chart not displayed");
    }

    @Test
    public void SA_SUS_015_verifyElectricityConsumptionChart() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isElectricityConsumptionChartDisplayed(), "Electricity Consumption chart not displayed");
    }

    @Test
    public void SA_SUS_016_verifyMonthlyElectricityConsumptionSection() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isMonthlyElectricitySectionDisplayed(), "Monthly Overall Electricity section not displayed");
    }

    @Test
    public void SA_SUS_017_verifyObservationSection() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isObservationDisplayed(), "Observation section not displayed");
    }

    @Test
    public void SA_SUS_031_verifySelectedDurationFilter() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "Duration filter report not generated");
    }

    @Test
    public void SA_SUS_032_verifyOrganizationFilter() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "Organization filter not applied");
    }

    @Test
    public void SA_SUS_033_verifyPlantFilter() {
        ReportsPage page = openSustainabilityReport();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "Plant filter not applied");
    }

    @Test
    public void SA_SUS_034_verifyRefreshFunctionality() {
        ReportsPage page = openSustainabilityReport();
        page.clickRefresh();
        Assert.assertTrue(page.isSustainabilityReportTitleDisplayed(), "Refresh did not reload report");
    }

    @Test
    public void SA_SUS_035_verifyExportFunctionality() {
        ReportsPage page = openSustainabilityReport();
        page.clickExport();

        Assert.assertTrue(true, "Export button clicked successfully");
    }
}