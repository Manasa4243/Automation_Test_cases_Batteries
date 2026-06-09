package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrganizationListPage;

public class OrganizationListTest extends BaseTest {

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

    public OrganizationListPage openOrganizationListPage() {

        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.openOrganizationsPage();

        OrganizationListPage organizationListPage = new OrganizationListPage(driver, wait);

        Assert.assertTrue(
                organizationListPage.isOrganizationListPageOpened(),
                "Organization List page is not opened"
        );

        return organizationListPage;
    }

    @Test
    public void TC_ORG_LIST_001_verifyOrganizationListPageLoads() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.isOrganizationListPageOpened(),
                "Organization list page did not load successfully"
        );
    }

    @Test
    public void TC_ORG_LIST_002_verifyOrganizationRecordsDisplay() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.areOrganizationRecordsDisplayed(),
                "Created organizations are not displayed"
        );
    }

   @Test
public void TC_ORG_LIST_003_verifyOrganizationColumns() {

    loginToApplication();

    OrganizationListPage organizationListPage = openOrganizationListPage();

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Organization Name"),
            "Organization Name column is missing"
    );

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Email"),
            "Email column is missing"
    );

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Sector"),
            "Sector column is missing"
    );

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Location"),
            "Location column is missing"
    );

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Status"),
            "Status column is missing"
    );

    Assert.assertTrue(
            organizationListPage.isColumnDisplayed("Actions"),
            "Actions column is missing"
    );
}

    @Test
    public void TC_ORG_LIST_004_verifyOrganizationDataMapping() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.areOrganizationRecordsDisplayed(),
                "Organization UI data is not displayed"
        );
    }

    @Test
    public void TC_ORG_LIST_005_verifyCreateOrganizationButton() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.isCreateOrganizationButtonVisible(),
                "Create Organization button is not visible or clickable"
        );
    }

    @Test
    public void TC_ORG_LIST_006_verifyCreateOrganizationNavigation() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        organizationListPage.clickCreateOrganization();

        Assert.assertTrue(
                organizationListPage.isCreateOrganizationPageOpened(),
                "Create Organization page did not open"
        );
    }

    @Test
    public void TC_ORG_LIST_007_verifyPagination() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.isPaginationWorking(),
                "Pagination is not working correctly"
        );
    }

    @Test
    public void TC_ORG_LIST_008_verifyOrganizationCount() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        Assert.assertTrue(
                organizationListPage.getOrganizationCount() > 0,
                "Organization count is zero or records are not displayed"
        );
    }

    @Test
    public void TC_ORG_LIST_009_verifyActionMenuOptions() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        organizationListPage.clickThreeDotMenu();

        Assert.assertTrue(
                organizationListPage.areActionOptionsDisplayed(),
                "Action menu options are not displayed"
        );
    }

    @Test
    public void TC_ORG_LIST_010_verifyActiveFilter() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        organizationListPage.selectActiveFilter();

        Assert.assertTrue(
                organizationListPage.areOnlyActiveOrganizationsDisplayed(),
                "Only active organizations are not displayed"
        );
    }

    @Test
    public void TC_ORG_LIST_011_verifyClearFilterButton() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        organizationListPage.selectActiveFilter();
        organizationListPage.clickClearFilter();

        Assert.assertTrue(
                organizationListPage.isFilterCleared(),
                "Filter was not cleared successfully"
        );
    }

    @Test
    public void TC_ORG_LIST_012_verifyPartialSearch() {

        loginToApplication();

        OrganizationListPage organizationListPage = openOrganizationListPage();

        organizationListPage.searchOrganization("Test");

        Assert.assertTrue(
                organizationListPage.isSearchResultDisplayed("Test"),
                "Partial search result is not displayed"
        );
    }
}