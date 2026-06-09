package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateOrganizationPage;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrganizationListPage;

public class CreateOrganizationFunctionalTest extends BaseTest {

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

public CreateOrganizationPage openCreateOrganizationUsingDashboardFlow() {

    DashboardPage dashboardPage = new DashboardPage(driver, wait);
    dashboardPage.openCreateOrganizationPage();

    CreateOrganizationPage createOrgPage = new CreateOrganizationPage(driver, wait);

    Assert.assertTrue(
            createOrgPage.isCreateOrganizationPageOpened(),
            "Create Organization page is not opened"
    );

    return createOrgPage;
}
    @Test
    public void TC_ORG_FUNC_001_openAddOrganizationPage() {

        loginToApplication();


        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        Assert.assertTrue(
                createOrgPage.isCreateOrganizationPageOpened(),
                "Add Organization page did not open"
        );
    }

    @Test
    public void TC_ORG_FUNC_002_createOrganizationWithValidData() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.fillValidOrganizationData();
        createOrgPage.clickCreateOrganization();

        waitTime(3000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("organizations"),
                "Organization was not created or page did not redirect to organization list"
        );
    }

    @Test
    public void TC_ORG_FUNC_003_cancelOrganizationCreation() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.clickCancel();

        waitTime(2000);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("organizations"),
                "Cancel button did not return user to organization list page"
        );
    }

    @Test
    public void TC_ORG_FUNC_004_verifyDropdownLoading() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.openSectorDropdown();
        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("Battery (EU)"),
                "Sector dropdown values are not loading"
        );
    }
@Test
    public void TC_ORG_FUNC_00_verifyDropdownLoading() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

      
        createOrgPage.openCountryDropdown();
        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("India"),
                "Country dropdown values are not loading"
        );
    }

    @Test
    public void TC_ORG_FUNC_005_verifyStateDependsOnCountry() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.selectCountry("India");

        createOrgPage.openStateDropdown();

        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("Karnataka"),
                "State list is not loading based on selected country"
        );
    }

    @Test
    public void TC_ORG_FUNC_006_verifyCityDependsOnState() {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.selectCountry("India");
        createOrgPage.selectState("Karnataka");

        createOrgPage.openCityDropdown();

        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("Bengaluru"),
                "City list is not loading based on selected state"
        );
    }

    @Test
    public void TC_ORG_FUNC_007_verifyOrganizationAppearsInList() {

        loginToApplication();

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.openCreateOrganizationPage();

        CreateOrganizationPage createOrgPage = new CreateOrganizationPage(driver, wait);

        String orgName = "Automation Org " + System.currentTimeMillis();

        createOrgPage.fillValidOrganizationData();

        createOrgPage.clickCreateOrganization();

        waitTime(3000);

        OrganizationListPage organizationListPage = new OrganizationListPage(driver, wait);

        Assert.assertTrue(
                organizationListPage.isOrganizationListPageOpened(),
                "Organization list page is not opened after creating organization"
        );
    }
}