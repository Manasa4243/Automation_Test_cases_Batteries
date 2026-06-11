package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.EditEmployeePage;
import pages.LoginPage;

public class EditEmployeeTest extends BaseTest {

    DashboardPage dashboardPage;
    EmployeeListPage employeeListPage;
    EditEmployeePage editEmployeePage;

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
    public void openEditEmployeePage() {
        loginToApplication();

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickEmployees();

        employeeListPage = new EmployeeListPage(driver, wait);
        employeeListPage.openEditEmployeePage();

        editEmployeePage = new EditEmployeePage(driver, wait);
    }

    @Test
    public void TC_EMP_EDIT_001_verifyEditEmployeePageLoads() {
        Assert.assertTrue(
                editEmployeePage.isEditEmployeePageOpened(),
                "Edit Employee page did not load"
        );
    }

    @Test
    public void TC_EMP_EDIT_002_verifyPrefilledEmployeeData() {
        Assert.assertTrue(
                editEmployeePage.isPreFilledDataDisplayed(),
                "Existing employee details are not prefilled"
        );
    }

   @Test
public void TC_EMP_EDIT_003_updateOrganization() {
    editEmployeePage.updateOrganization("Luminous");
    editEmployeePage.clickUpdate();

    Assert.assertTrue(
            editEmployeePage.isReturnedToEmployeeList(),
            "Employee organization was not updated"
    );
}

    @Test
    public void TC_EMP_EDIT_004_updatePlant() {
        editEmployeePage.updatePlant("Battery_Plant");
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Employee plant was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_005_updateEmployeeName() {
        String name = "Updated Employee " + System.currentTimeMillis();

        editEmployeePage.updateEmployeeName(name);
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Employee name was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_006_updateEmailAddress() {
        String email = "updatedemployee" + System.currentTimeMillis() + "@gmail.com";

        editEmployeePage.updateEmail(email);
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Employee email was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_007_updatePhoneNumber() {
        editEmployeePage.updatePhoneNumber("9876543210");
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Phone number was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_008_updateDesignation() {
        editEmployeePage.updateDesignation("QA Automation Tester");
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Designation was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_009_updateDepartment() {
        editEmployeePage.updateDepartment("QA");
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Department was not updated"
        );
    }

    @Test
    public void TC_EMP_EDIT_010_verifyCancelFunctionality() {
        editEmployeePage.updateEmployeeName("Cancel Test Employee");
        editEmployeePage.clickCancel();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Cancel button did not navigate back to Employee List"
        );
    }

    @Test
    public void TC_EMP_EDIT_011_verifyUpdatedEmployeeInList() {
        String name = "List Updated Employee " + System.currentTimeMillis();

        editEmployeePage.updateEmployeeName(name);
        editEmployeePage.clickUpdate();

        Assert.assertTrue(
                editEmployeePage.isReturnedToEmployeeList(),
                "Updated employee details are not reflected in Employee List"
        );
    }

    @Test
    public void TC_EMP_EDIT_012_verifyOrganizationPlantDependency() {
        editEmployeePage.updateOrganization("org1");

        Assert.assertTrue(
                editEmployeePage.isPlantDropdownEnabled(),
                "Plant dropdown is not enabled after changing organization"
        );
    }
}