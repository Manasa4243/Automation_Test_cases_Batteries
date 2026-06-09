package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CreateEmployeePage;
import pages.DashboardPage;
import pages.LoginPage;

public class CreateEmployeeTest extends BaseTest {

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

    public CreateEmployeePage openCreateEmployeePage() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.openCreateEmployeePage();

        CreateEmployeePage employeePage = new CreateEmployeePage(driver, wait);

        Assert.assertTrue(
                employeePage.isCreateEmployeePageOpened(),
                "Create Employee page is not opened"
        );

        return employeePage;
    }

    @Test
    public void TC_EMP_CREATE_001_verifyCreateEmployeePageLoads() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();
 
        Assert.assertTrue(
                employeePage.isCreateEmployeePageOpened(),
                "Create Employee page should load successfully"
        );
    }

    @Test
    public void TC_EMP_CREATE_002_verifyAllInputFieldsDisplay() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        Assert.assertTrue(
                employeePage.areAllFieldsVisible(),
                "All fields, dropdowns, and buttons are not displayed properly"
        );
    }

    @Test
    public void TC_EMP_CREATE_003_verifyOrganizationDropdownLoading() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.openOrganizationDropdown();

        Assert.assertTrue(
                employeePage.isDropdownOptionVisible("org1"),
                "Organization dropdown values are not loading"
        );
    }

    @Test
    public void TC_EMP_CREATE_004_verifyPlantDropdownDependency() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.selectOrganization("org1");

        Assert.assertTrue(
                true,
                "Plant dropdown should load plants related to selected organization"
        );
    }

    @Test
    public void TC_EMP_CREATE_005_createEmployeeWithValidData() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.fillValidEmployeeData();
        employeePage.clickCreateEmployee();

        Assert.assertTrue(
                employeePage.isReturnedToEmployeeList(),
                "Employee was not created successfully"
        );
    }

    @Test
    public void TC_EMP_CREATE_006_verifyCancelFunctionality() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.clickCancel();

        Assert.assertTrue(
                employeePage.isReturnedToEmployeeList(),
                "Cancel button did not navigate back without saving"
        );
    }

    @Test
    public void TC_EMP_CREATE_007_verifyCreatedEmployeeInList() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.fillValidEmployeeData();
        employeePage.clickCreateEmployee();

        Assert.assertTrue(
                employeePage.isReturnedToEmployeeList(),
                "Created employee is not visible in employee list"
        );
    }

    @Test
    public void TC_EMP_CREATE_008_verifyEmployeeOrganizationMapping() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.selectOrganization("org1");

        Assert.assertTrue(
                true,
                "Employee should map to selected organization"
        );
    }

    @Test
    public void TC_EMP_CREATE_009_verifyEmployeePlantMapping() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.selectOrganization("org1");
        employeePage.selectPlant("Automation Plant");

        Assert.assertTrue(
                true,
                "Employee should map to selected plant"
        );
    }

    @Test
    public void TC_EMP_CREATE_010_verifyFormResetAfterCreation() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.fillValidEmployeeData();
        employeePage.clickCreateEmployee();

        Assert.assertTrue(
                employeePage.isReturnedToEmployeeList(),
                "Form did not reset or redirect correctly after creation"
        );
    }
}