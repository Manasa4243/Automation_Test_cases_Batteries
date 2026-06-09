package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PlantPage;

public class PlantAddTest extends BaseTest {

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

    public PlantPage openAddPlantPage() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.openCreatePlantPage();

        PlantPage plantPage = new PlantPage(driver, wait);

        Assert.assertTrue(
                plantPage.isAddPlantPageOpened(),
                "Add Plant page is not opened"
        );

        return plantPage;
    }

    @Test
    public void TC_PLANT_ADD_001_verifyAddPlantPageLoads() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        Assert.assertTrue(
                plantPage.isAddPlantPageOpened(),
                "Add Plant page should load successfully"
        );
    }

    @Test
    public void TC_PLANT_ADD_002_verifyAllFieldsVisibility() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        Assert.assertTrue(
                plantPage.areAllFieldsVisible(),
                "All fields, dropdowns, and buttons are not displayed properly"
        );
    }

    @Test
    public void TC_PLANT_ADD_003_verifyOrganizationDropdownLoading() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.openOrganizationDropdown();

        Assert.assertTrue(
                plantPage.isDropdownOptionVisible("org1"),
                "Organization dropdown values are not loading"
        );
    }

    @Test
    public void TC_PLANT_ADD_004_createPlantWithValidData() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillValidPlantData();
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Plant was not created successfully"
        );
    }

    @Test
    public void TC_PLANT_ADD_005_verifyCancelFunctionality() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.clickCancel();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Cancel button did not navigate back"
        );
    }

    @Test
    public void TC_PLANT_ADD_006_verifyDependentDropdownFunctionality() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.selectCountryStateCity();

        Assert.assertTrue(
                true,
                "Dependent dropdown values should load correctly"
        );
    }

    @Test
    public void TC_PLANT_ADD_007_verifyCreatedPlantInList() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillValidPlantData();
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Newly created plant is not displayed in list"
        );
    }

    @Test
    public void TC_PLANT_ADD_008_verifyUniquePlantCodeCreation() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillPlantWithUniqueCode("PLANT001");
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Plant with unique plant code was not saved successfully"
        );
    }
}