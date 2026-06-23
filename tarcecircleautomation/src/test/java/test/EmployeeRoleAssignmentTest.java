package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EmployeeListPage;
import pages.EmployeeRoleAssignmentPage;
import pages.LoginPage;

public class EmployeeRoleAssignmentTest extends BaseTest {

    DashboardPage dashboardPage;
    EmployeeListPage employeeListPage;
    EmployeeRoleAssignmentPage employeeRolePage;

    public void loginToApplication() {

        LoginPage loginPage = new LoginPage(driver, wait);

        driver.get(LOGIN_URL);

        loginPage.enterEmail("system@tracecircle.com");
        loginPage.enterPassword("StrongPassword@123");
        loginPage.clickLogin();

        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();
    }
public void openEmployeeRoleAssignmentForm() {
    employeeListPage.clickAddEmployeeRole();
    employeeRolePage = new EmployeeRoleAssignmentPage(driver, wait);
}

public void fillRequiredDropdownFlow() {
    employeeRolePage.selectOrganization("Battery_Org");
    employeeRolePage.selectEmployee("Manufaturer_admin");
    employeeRolePage.selectRole("Organization Admin");
}
    @BeforeMethod
    public void openEmployeeRoleListPage() {

        loginToApplication();

        dashboardPage.clickOrganizationManagement();
        dashboardPage.clickEmployeeRole();

        employeeListPage = new EmployeeListPage(driver, wait);
    }

    @Test
    public void TC_EMP_ROLE_001_verifyAddEmployeeRolePageLoads() {

        employeeListPage.clickAddEmployeeRole();

        employeeRolePage = new EmployeeRoleAssignmentPage(driver, wait);

        Assert.assertTrue(
                employeeRolePage.isAddEmployeeRolePageOpened(),
                "Add Employee Role Assignment page did not open"
        );
    }

    @Test
    public void TC_EMP_ROLE_002_verifyAllFieldsDisplay() {
        employeeListPage.clickAddEmployeeRole();

        employeeRolePage = new EmployeeRoleAssignmentPage(driver, wait);
        Assert.assertTrue(
            
                employeeRolePage.areAllFieldsDisplayed(),
                "Organization, Employee, Role, Effective From, Effective To, or Primary Role field is missing"
        );
    }

    @Test
    public void TC_EMP_ROLE_003_verifyOrganizationDropdownLoading() {
        employeeListPage.clickAddEmployeeRole();

        employeeRolePage = new EmployeeRoleAssignmentPage(driver, wait);
        employeeRolePage.openOrganizationDropdown();

        Assert.assertTrue(
                employeeRolePage.isDropdownOptionVisible("1245"),
                "Organization dropdown list is not displayed"
        );
    }

  @Test
public void TC_EMP_ROLE_004_verifyEmployeeDropdownDependency() {

    openEmployeeRoleAssignmentForm();

    employeeRolePage.selectOrganization("Battery_Org");
    employeeRolePage.selectEmployee("Manufacturer_admin");
    Assert.assertTrue(
            employeeRolePage.isEmployeeSelected("Manufacturer_admin"),
            "Employee dropdown is not enabled after selecting organization"
    );
}

@Test
public void TC_EMP_ROLE_005_verifyRoleDropdownLoading() {

    openEmployeeRoleAssignmentForm();

    employeeRolePage.selectOrganization("Battery_Org");
    employeeRolePage.selectEmployee("Manufacturer_admin");

    employeeRolePage.openRoleDropdown();

    Assert.assertTrue(
            employeeRolePage.isDropdownOptionVisible("Organization Admin"),
            "Role dropdown list is not displayed"
    );
     Assert.assertTrue(
            employeeRolePage.isDropdownOptionVisible("Super Admin"),
            "Super Admin role is missing"
    );
}


@Test
public void TC_EMP_ROLE_006_verifyAddRoleFunctionality() {

    openEmployeeRoleAssignmentForm();
employeeRolePage.selectOrganization("Exide Industries");
    employeeRolePage.selectEmployee("Prabha");

    employeeRolePage.openRoleDropdown();
    // fillRequiredDropdownFlow();

    employeeRolePage.clickAddRole();

    Assert.assertTrue(
            employeeRolePage.isNewRoleAssignmentAdded(),
            "New role assignment section was not added"
    );
}

@Test
public void TC_EMP_ROLE_007_verifyPrimaryRoleSelection() {

    openEmployeeRoleAssignmentForm();

    fillRequiredDropdownFlow();

    employeeRolePage.clickPrimaryRole();

    Assert.assertTrue(
            employeeRolePage.isPrimaryRoleSelected(),
            "Primary Role was not selected"
    );
}

@Test
public void TC_EMP_ROLE_008_verifyAssignmentCreationWithValidData() {

    openEmployeeRoleAssignmentForm();

    fillRequiredDropdownFlow();

    employeeRolePage.enterEffectiveDates("01-06-2026", "30-06-2026");

    employeeRolePage.clickCreateAssignment();

    Assert.assertTrue(
            employeeRolePage.isAssignmentCreatedOrSaved(),
            "Employee role assignment was not created successfully"
    );
}

@Test
public void TC_EMP_ROLE_009_verifyMultipleRoleAssignment() {

    openEmployeeRoleAssignmentForm();

    employeeRolePage.selectOrganization("Battery_Org");
    employeeRolePage.selectEmployee("Manufacturer_admin");

    employeeRolePage.selectRole("Organization Admin");
    employeeRolePage.enterEffectiveDates("01-06-2026", "30-06-2026");

    employeeRolePage.clickAddRole();

    employeeRolePage.selectRole("Super Admin");
    employeeRolePage.enterEffectiveDates("01-06-2026", "30-06-2026");

    employeeRolePage.clickCreateAssignment();

    Assert.assertTrue(
            employeeRolePage.isAssignmentCreatedOrSaved(),
            "Multiple roles were not assigned successfully"
    );
}

@Test
public void TC_EMP_ROLE_010_verifyEffectiveDateRange() {

    openEmployeeRoleAssignmentForm();

    fillRequiredDropdownFlow();

    employeeRolePage.enterEffectiveDates("01-06-2026", "30-06-2026");

    employeeRolePage.clickCreateAssignment();

    Assert.assertTrue(
            employeeRolePage.isAssignmentCreatedOrSaved(),
            "Effective date range was not saved successfully"
    );
}

@Test
public void TC_EMP_ROLE_011_verifyCancelFunctionality() {

    openEmployeeRoleAssignmentForm();

    employeeRolePage.clickCancel();

    Assert.assertTrue(
            employeeRolePage.isReturnedToEmployeeRoleList(),
            "Cancel button did not navigate back"
    );
}

   @Test
public void TC_EMP_ROLE_012_verifyCreatedAssignmentInList() {

    openEmployeeRoleAssignmentForm();

    // fillRequiredDropdownFlow();

    employeeRolePage.selectOrganization("Exide Industries");
    employeeRolePage.selectEmployee("Prabha");

    employeeRolePage.selectRole("Organization Admin");
    employeeRolePage.enterEffectiveDates("01-06-2026", "30-06-2026");
    employeeRolePage.clickCreateAssignment();

    Assert.assertTrue(
            employeeRolePage.isAssignmentCreatedOrSaved(),
            "Assigned role is not displayed correctly in list"
    );
}
}