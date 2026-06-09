package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.LoginPage;
import pages.ViewEmployeePage;

public class ViewEmployeeTest extends BaseTest {

    DashboardPage dashboardPage;
    EmployeeListPage employeeListPage;
    ViewEmployeePage viewEmployeePage;

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
    public void openViewEmployeePage() {
        loginToApplication();

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickEmployees();

        employeeListPage = new EmployeeListPage(driver, wait);
        employeeListPage.openEditEmployeePage();

        viewEmployeePage = new ViewEmployeePage(driver, wait);
    }

    @Test
    public void TC_EMP_VIEW_001_verifyViewEmployeePageLoads() {
        Assert.assertTrue(
                viewEmployeePage.isViewEmployeePageOpened(),
                "View Employee page did not load"
        );
    }

    @Test
    public void TC_EMP_VIEW_002_verifyEmployeeBasicDetailsDisplay() {
        Assert.assertTrue(
                viewEmployeePage.areBasicDetailsDisplayed(),
                "Employee Name, Email, or Phone Number is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_003_verifyDesignationDisplay() {
        Assert.assertTrue(
                viewEmployeePage.isDesignationDisplayed(),
                "Designation is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_004_verifyDepartmentDisplay() {
        Assert.assertTrue(
                viewEmployeePage.isDepartmentDisplayed(),
                "Department is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_005_verifyOrganizationDetailsDisplay() {
        Assert.assertTrue(
                viewEmployeePage.areOrganizationDetailsDisplayed(),
                "Organization Name or Organization ID is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_006_verifyPlantDetailsDisplay() {
        Assert.assertTrue(
                viewEmployeePage.isPlantDetailsDisplayed(),
                "Plant details are not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_007_verifyEmployeeStatusDisplay() {
        Assert.assertTrue(
                viewEmployeePage.isEmployeeStatusDisplayed(),
                "Employee status is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_008_verifyLoginAccountStatusDisplay() {
        Assert.assertTrue(
                viewEmployeePage.isLoginAccountStatusDisplayed(),
                "Login account status is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_009_verifyAuditDetailsDisplay() {
        Assert.assertTrue(
                viewEmployeePage.areAuditDetailsDisplayed(),
                "Created At or Updated At is not displayed"
        );
    }

    @Test
    public void TC_EMP_VIEW_010_verifyBackNavigation() {
        viewEmployeePage.clickBackButton();

        Assert.assertTrue(
                viewEmployeePage.isReturnedToEmployeeList(),
                "Back button did not navigate to Employee List page"
        );
    }

    @Test
    public void TC_EMP_VIEW_011_verifyResponsiveUI() {
        Assert.assertTrue(
                viewEmployeePage.isResponsiveUIWorking(),
                "View Employee page UI is not responsive"
        );
    }

    @Test
    public void TC_EMP_VIEW_012_verifyEmptyOptionalFieldHandling() {
        Assert.assertTrue(
                viewEmployeePage.isEmptyOptionalFieldHandled(),
                "Empty optional fields are showing null, undefined, or NaN"
        );
    }
}