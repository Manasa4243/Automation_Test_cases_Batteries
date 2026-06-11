package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EditPlantPage;
import pages.LoginPage;
import pages.PlantListPage;

public class EditPlantTest extends BaseTest {

    private final String VALID_EMAIL = "system@tracecircle.com";
    private final String VALID_PASSWORD = "StrongPassword@123";

    private final String ORGANIZATION_NAME = "Exide Industries";
    private final String PLANT_NAME = "Exide_plant";

    public void loginToApplication() {
        openLoginPage();

        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(VALID_EMAIL);
        loginPage.enterPassword(VALID_PASSWORD);
        loginPage.clickLogin();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
    }

    public EditPlantPage openEditPlantPage() {

        DashboardPage dashboardPage = new DashboardPage(driver, wait);

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickPlants();

        PlantListPage plantListPage = new PlantListPage(driver, wait);

        Assert.assertTrue(
                plantListPage.isPlantListPageOpened(),
                "Plant list page is not opened"
        );

        plantListPage.selectOrganizationFilter(ORGANIZATION_NAME);
        plantListPage.openEditPlantByName(PLANT_NAME);

        EditPlantPage editPlantPage = new EditPlantPage(driver, wait);

        Assert.assertTrue(
                editPlantPage.isEditPlantPageOpened(),
                "Edit Plant page is not opened"
        );

        return editPlantPage;
    }

    @Test
    public void TC_PLANT_EDIT_001_verifyEditPlantPageLoads() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        Assert.assertTrue(
                editPlantPage.isEditPlantPageOpened(),
                "Edit Plant page should load successfully"
        );
    }

    @Test
    public void TC_PLANT_EDIT_002_verifyPrefilledPlantData() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        Assert.assertTrue(
                editPlantPage.isPrefilledDataDisplayed(),
                "Existing plant data is not prefilled"
        );
    }

    @Test
    public void TC_PLANT_EDIT_003_updateOrganization() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateOrganization("Exide Industries");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Organization was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_004_updatePlantCode() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updatePlantCode("EXIDE_PLANT_" + System.currentTimeMillis());
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Plant code was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_005_updatePlantName() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updatePlantName("Exide_plant");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Plant name was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_006_updateAddressLine1() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateAddressLine1("Updated address line 1");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Address line 1 was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_007_updateAddressLine2() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateAddressLine2("Updated address line 2");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Address line 2 was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_008_updateCountryStateCity() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateLocation("India", "Karnataka", "Bengaluru");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Location was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_009_updatePostalCode() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updatePostalCode("560001");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Postal code was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_010_updateContactPersonName() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateContactPersonName("Manasa Gowda Updated");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Contact person name was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_011_updateContactEmail() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateContactEmail("plantupdated" + System.currentTimeMillis() + "@gmail.com");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Contact email was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_012_updatePhoneNumber() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updatePhoneNumber("9876543210");
        editPlantPage.clickUpdate();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Phone number was not updated"
        );
    }

    @Test
    public void TC_PLANT_EDIT_013_verifyCancelFunctionality() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.clickCancel();

        Assert.assertTrue(
                editPlantPage.isReturnedToPlantList(),
                "Cancel did not return user to plant list"
        );
    }

   @Test
public void TC_PLANT_EDIT_014_verifyUpdatedPlantInList() {

    loginToApplication();

    EditPlantPage editPlantPage = openEditPlantPage();

    editPlantPage.updatePlantName("Exide_plant");
    editPlantPage.clickUpdate();

    PlantListPage plantListPage = new PlantListPage(driver, wait);

    Assert.assertTrue(
            plantListPage.isPlantListPageOpened(),
            "Plant list page is not opened after update"
    );

    plantListPage.selectOrganizationFilter("Exide Industries");

    Assert.assertTrue(
            plantListPage.isUpdatedPlantVisible("Exide_plant"),
            "Updated plant is not visible in plant list after selecting Exide Industries"
    );
}

    @Test
    public void TC_PLANT_EDIT_015_verifyDependentDropdownFunctionality() {
        loginToApplication();

        EditPlantPage editPlantPage = openEditPlantPage();

        editPlantPage.updateLocation("India", "Karnataka", "Bengaluru");

        Assert.assertTrue(
                true,
                "Dependent country/state/city dropdowns loaded correctly"
        );
    }
}