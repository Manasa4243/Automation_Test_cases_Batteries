package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PlantListPage;
import pages.ViewPlantPage;

public class ViewPlantTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    public void loginToApplication() {
        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
    }

    public ViewPlantPage openViewPlantPage() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickPlants();

        PlantListPage plantListPage = new PlantListPage(driver, wait);

        Assert.assertTrue(
                plantListPage.isPlantListPageOpened(),
                "Plant list page is not opened"
        );

        plantListPage.openViewPlant();

        ViewPlantPage viewPlantPage = new ViewPlantPage(driver, wait);

        Assert.assertTrue(
                viewPlantPage.isViewPlantPageOpened(),
                "View Plant page is not opened"
        );

        return viewPlantPage;
    }

    @Test
    public void TC_PLANT_VIEW_001_verifyViewPlantPageLoads() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isViewPlantPageOpened(),
                "View Plant page should load successfully"
        );
    }

    @Test
    public void TC_PLANT_VIEW_002_verifyPlantBasicDetailsDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.areBasicDetailsDisplayed(),
                "Plant Name, Plant Code, or Organization is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_003_verifyOrganizationIdDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isOrganizationIdDisplayed(),
                "Organization ID is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_004_verifyPlantStatusDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isStatusDisplayed(),
                "Plant status is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_005_verifyAddressDetailsDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.areAddressDetailsDisplayed(),
                "Country, State, City, or Postal Code is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_006_verifyAddressLine1Display() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isAddressLine1Displayed(),
                "Address Line 1 is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_007_verifyAddressLine2Display() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isAddressLine2Displayed(),
                "Address Line 2 is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_008_verifyContactDetailsDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.areContactDetailsDisplayed(),
                "Contact Name, Email, or Phone is not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_009_verifyAuditDetailsDisplay() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.areAuditDetailsDisplayed(),
                "Audit details are not displayed"
        );
    }

    @Test
    public void TC_PLANT_VIEW_010_verifyBackNavigation() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        viewPlantPage.clickBackButton();

        Assert.assertTrue(
                viewPlantPage.isReturnedToPlantList(),
                "User did not navigate back to Plant List page"
        );
    }

    @Test
    public void TC_PLANT_VIEW_012_verifyEmptyOptionalFieldHandling() {
        loginToApplication();

        ViewPlantPage viewPlantPage = openViewPlantPage();

        Assert.assertTrue(
                viewPlantPage.isViewPlantPageOpened(),
                "View Plant page is broken when optional fields are empty"
        );
    }
}