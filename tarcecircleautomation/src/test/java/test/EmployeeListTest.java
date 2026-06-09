package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;

public class EmployeeListTest extends BaseTest {

    DashboardPage dashboardPage;
    EmployeeListPage employeeListPage;

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
    public void openEmployeeListPage() {
        loginToApplication();

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickEmployees();

        employeeListPage = new EmployeeListPage(driver, wait);
    }

    @Test
    public void TC_EMP_LIST_001_verifyEmployeeListPageLoads() {
        Assert.assertTrue(
                employeeListPage.isEmployeeListPageOpened(),
                "Employee List page did not load"
        );
    }

    @Test
    public void TC_EMP_LIST_002_verifyEmployeeRecordsDisplay() {
        Assert.assertTrue(
                employeeListPage.areEmployeeRecordsDisplayed(),
                "Employee records are not displayed"
        );
    }

    @Test
    public void TC_EMP_LIST_003_verifyEmployeeTableColumns() {
        Assert.assertTrue(
                employeeListPage.areAllEmployeeColumnsDisplayed(),
                "Employee table columns are missing"
        );
    }

    @Test
    public void TC_EMP_LIST_004_verifyEmployeeDataMapping() {
        String firstRowText = employeeListPage.getFirstRowText();

        Assert.assertFalse(
                firstRowText.isEmpty(),
                "Employee UI data is empty, cannot compare with DB/API"
        );
    }

    @Test
    public void TC_EMP_LIST_005_verifyAddEmployeeButton() {
        employeeListPage.clickAddEmployee();

        Assert.assertTrue(
                employeeListPage.isCreateEmployeePageOpened(),
                "Add Employee button did not navigate to Create Employee page"
        );
    }

   @Test
public void TC_EMP_LIST_006_verifySearchFunctionality() {

    employeeListPage.searchEmployee("Manufa");

    Assert.assertTrue(
            employeeListPage.isSearchResultDisplayed("Manufa"),
            "Matching employee is not displayed"
    );
}

    @Test
    public void TC_EMP_LIST_007_verifyPartialSearch() {
        employeeListPage.searchEmployee("Emp");

        Assert.assertTrue(
                employeeListPage.areEmployeeRecordsDisplayed(),
                "Relevant employee results are not displayed for partial search"
        );
    }

    @Test
    public void TC_EMP_LIST_008_verifySearchByEmail() {
        employeeListPage.searchEmployee("@gmail.com");

        Assert.assertTrue(
                employeeListPage.areEmployeeRecordsDisplayed(),
                "Matching employee email result is not displayed"
        );
    }

    @Test
    public void TC_EMP_LIST_009_verifyOrganizationFilter() {
        employeeListPage.selectOrganizationFilter("1245");

        Assert.assertTrue(
                employeeListPage.areOnlySelectedOrganizationEmployeesDisplayed("1245"),
                "Employees related to selected organization are not displayed"
        );
    }

    @Test
    public void TC_EMP_LIST_010_verifyStatusFilter() {
        employeeListPage.selectStatusFilter("Active");

        Assert.assertTrue(
                employeeListPage.areOnlySelectedStatusEmployeesDisplayed("Active"),
                "Employees based on selected status are not displayed"
        );
    }

    @Test
    public void TC_EMP_LIST_011_verifyLoginStatusButton() {
        Assert.assertTrue(
                employeeListPage.isLoginStatusDisplayed(),
                "Correct login status is not displayed"
        );  
    }

    @Test
    public void TC_EMP_LIST_012_verifyActionMenuOpens() {
        Assert.assertTrue(
                employeeListPage.areActionOptionsDisplayed(),
                "Action options are not displayed after clicking three dots"
        );
    }

    @Test
    public void TC_EMP_LIST_013_verifyPagination() {
        Assert.assertTrue(
                employeeListPage.isPaginationWorking(),
                "Pagination Next/Previous is not working"
        );
    }

    @Test
    public void TC_EMP_LIST_014_verifyEmployeeCount() {
        Assert.assertTrue(
                employeeListPage.isTotalEmployeeCountDisplayed(),
                "Total Employees count is not displayed or records count is zero"
        );
    }

    @Test
    public void TC_EMP_LIST_015_verifyNoRecordsFoundMessage() {
        employeeListPage.searchEmployee("InvalidEmployeeName123456");

        Assert.assertTrue(
                employeeListPage.isNoRecordsFoundDisplayed(),
                "No records found message is not displayed for invalid employee search"
        );
    }
}