package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.EmployeeRoleListPage;
import pages.LoginPage;

public class EmployeeRoleListTest extends BaseTest {

    DashboardPage dashboardPage;
    EmployeeListPage employeeListPage;
    EmployeeRoleListPage employeeRoleListPage;

    public void loginToApplication() {

        LoginPage loginPage = new LoginPage(driver, wait);

        driver.get(LOGIN_URL);

        loginPage.enterEmail("system@tracecircle.com");
        loginPage.enterPassword("StrongPassword@123");
        loginPage.clickLogin();

        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
    }

    @BeforeMethod
    public void openEmployeeRoleListPage() {

        loginToApplication();

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickEmployeeRole();

        employeeListPage = new EmployeeListPage(driver, wait);
        employeeRoleListPage = new EmployeeRoleListPage(driver, wait);
    }

    @Test
    public void TC_EMP_ROLE_LIST_001_verifyEmployeeRoleListPageLoads() {

        Assert.assertTrue(
                employeeRoleListPage.isPageOpened(),
                "Employee Role List page is not opened"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_002_verifyPageTitle() {

        Assert.assertTrue(
                employeeRoleListPage.isPageOpened(),
                "Employee Role Management title is not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_003_verifyAddEmployeeRoleButtonVisibility() {

        Assert.assertTrue(
                employeeRoleListPage.isAddEmployeeRoleButtonVisible(),
                "Add Employee Role button is not visible"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_004_verifyAddEmployeeRoleNavigation() {

        employeeListPage.clickAddEmployeeRole();

        Assert.assertTrue(
                employeeRoleListPage.isAddEmployeeRolePageOpened(),
                "Add Employee Role page is not opened"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_005_verifyTableHeadersDisplay() {

        Assert.assertTrue(employeeRoleListPage.isHeaderDisplayed("Employee"), "Employee column missing");
        Assert.assertTrue(employeeRoleListPage.isHeaderDisplayed("Organization"), "Organization column missing");
        Assert.assertTrue(employeeRoleListPage.isHeaderDisplayed("Role"), "Role column missing");
        Assert.assertTrue(employeeRoleListPage.isHeaderDisplayed("Status"), "Status column missing");
        Assert.assertTrue(employeeRoleListPage.isHeaderDisplayed("Actions"), "Actions column missing");
    }

    @Test
    public void TC_EMP_ROLE_LIST_006_verifyEmployeeRoleRecordsDisplay() {

        Assert.assertTrue(
                employeeRoleListPage.areRecordsDisplayed(),
                "Employee role records are not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_008_verifySearchFieldVisibility() {

        Assert.assertTrue(
                employeeRoleListPage.isSearchFieldVisible(),
                "Search field is not visible"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_009_verifySearchByEmployeeName() {

        employeeRoleListPage.search("Manufacturer_admin");

        Assert.assertTrue(
                employeeRoleListPage.isSearchResultDisplayed("Manufacturer_admin"),
                "Employee search result is not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_010_verifyPartialEmployeeSearch() {

        employeeRoleListPage.search("Manu");

        Assert.assertTrue(
                employeeRoleListPage.isSearchResultDisplayed("Manu"),
                "Partial employee search result is not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_011_verifySearchByOrganizationName() {

        employeeRoleListPage.search("Battery_Org");

        Assert.assertTrue(
                employeeRoleListPage.isSearchResultDisplayed("Battery_Org"),
                "Organization search result is not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_012_verifySearchByRoleName() {

        employeeRoleListPage.search("Organization Admin");

        Assert.assertTrue(
                employeeRoleListPage.isSearchResultDisplayed("Organization Admin"),
                "Role search result is not displayed"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_013_verifyCaseInsensitiveSearch() {

        employeeRoleListPage.search("battery_org");

        Assert.assertTrue(
                employeeRoleListPage.isSearchResultDisplayed("battery_org"),
                "Case-insensitive search is not working"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_015_verifyClearSearchFunctionality() {

        employeeRoleListPage.search("Manufacturer_admin");
        employeeRoleListPage.clearSearch();

        Assert.assertTrue(
                employeeRoleListPage.areRecordsDisplayed(),
                "Full employee role list is not displayed after clearing search"
        );
    }

    @Test
    public void TC_EMP_ROLE_LIST_019_verifyActionsMenuOpens() {

        employeeRoleListPage.openActionsMenu();

        Assert.assertTrue(
                employeeRoleListPage.isActionsMenuOpened(),
                "Actions menu is not opened"
        );
    }

   @Test
public void TC_EMP_ROLE_LIST_020_verifyViewEmployeeRoleAction() {

    employeeRoleListPage.clickViewFromActionMenu();

    Assert.assertTrue(
            employeeRoleListPage.isPageOpened(),
            "View Employee Role page is not opened"
    );
}

   @Test
public void TC_EMP_ROLE_LIST_021_verifyEditEmployeeRoleAction() {

    employeeRoleListPage.clickEditFromActionMenu();

    Assert.assertTrue(
            employeeRoleListPage.isPageOpened(),
            "Edit Employee Role page is not opened"
    );
}
}