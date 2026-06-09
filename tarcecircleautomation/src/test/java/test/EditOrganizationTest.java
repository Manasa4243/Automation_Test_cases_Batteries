package test;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EditOrganizationPage;
import pages.LoginPage;
import pages.OrganizationListPage;


public class EditOrganizationTest extends BaseTest {

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

    public EditOrganizationPage openEditOrganizationPage() {

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.isOrganizationListPageOpened(),
                "Organization List page is not opened"
        );

        organizationListPage.clickEditFromActionMenu();

        EditOrganizationPage editOrganizationPage =
                new EditOrganizationPage(driver, wait);

        Assert.assertTrue(
                editOrganizationPage.isEditOrganizationPageOpened(),
                "Edit Organization page is not opened"
        );

        return editOrganizationPage;
    }
 @Test
public void TC_ORG_EDIT_001_verifyEditOrganizationPageLoads() {

    loginToApplication();

    EditOrganizationPage editOrganizationPage =
            openEditOrganizationPage();

    Assert.assertTrue(
            editOrganizationPage.isEditOrganizationPageOpened(),
            "Edit Organization page should load successfully"
    );
}
    @Test
    public void TC_ORG_EDIT_002_verifyPreFilledData() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        Assert.assertTrue(
                editOrganizationPage.isPreFilledDataDisplayed(),
                "Saved organization details are not pre-filled correctly"
        );
    }

    @Test
    public void TC_ORG_EDIT_003_updateOrganizationName() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        String updatedName = "Trace Circle Updated " + System.currentTimeMillis();

        editOrganizationPage.updateOrganizationName(updatedName);
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Organization name was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_004_updateSector() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateSector("Battery (EU)");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Sector was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_005_updateCompanyWebsite() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateCompanyWebsite("https://example.com");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Website was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_006_updateAddress() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateAddress("Bangalore Karnataka Updated Address");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Address was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_007_updateCountryStateCity() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateLocation("India", "Karnataka", "Bangalore");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Location was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_008_updatePostalCode() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updatePostalCode("560001");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Postal code was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_009_updateContactPersonName() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateContactPersonName("Manasa Gowda");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Contact person name was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_010_updateContactEmail() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        String email = "manasa.updated" + System.currentTimeMillis() + "@gmail.com";

        editOrganizationPage.updateContactPersonEmail(email);
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Contact email was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_011_updatePhoneNumber() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updatePhoneNumber("9876543210");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Phone number was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_012_updateDescription() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateDescription("Updated description using Selenium automation.");
        editOrganizationPage.clickUpdate();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "Description was not updated successfully"
        );
    }

    @Test
    public void TC_ORG_EDIT_013_cancelEdit() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.clickCancel();

        Assert.assertTrue(
                editOrganizationPage.isReturnedToOrganizationList(),
                "User did not return to organization list after cancel"
        );
    }

    @Test
    public void TC_ORG_EDIT_014_verifyUpdatedDataInList() {

        loginToApplication();

        String updatedName = "Trace Circle Updated " + System.currentTimeMillis();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateOrganizationName(updatedName);
        editOrganizationPage.clickUpdate();

        OrganizationListPage organizationListPage =
                new OrganizationListPage(driver, wait);

        Assert.assertTrue(
                organizationListPage.isCreatedOrganizationVisible(updatedName),
                "Updated organization data is not reflected in organization list"
        );
    }

    @Test
    public void TC_ORG_EDIT_015_verifyDependentDropdowns() {

        loginToApplication();

        EditOrganizationPage editOrganizationPage = openEditOrganizationPage();

        editOrganizationPage.updateLocation("India", "Karnataka", "Bangalore");

        Assert.assertTrue(
                editOrganizationPage.isStateDropdownEnabled(),
                "State dropdown is not enabled based on selected country"
        );

        Assert.assertTrue(
                editOrganizationPage.isCityDropdownEnabled(),
                "City dropdown is not enabled based on selected state"
        );
    }
}