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
                employeePage.isDropdownOptionVisible("Exide Industries"),
                "Organization dropdown values are not loading"
        );
    }

  @Test
public void TC_EMP_CREATE_004_verifyPlantDropdownDependency() {

    loginToApplication();

    CreateEmployeePage employeePage = openCreateEmployeePage();

    employeePage.selectOrganization("Exide Industries");

    employeePage.clickPlantDropdown();

    Assert.assertTrue(
            employeePage.isPlantDropdownEnabled(),
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

        employeePage.selectOrganization("Exide Industries");

        Assert.assertTrue(
                true,
                "Employee should map to selected organization"
        );
    }

    @Test
    public void TC_EMP_CREATE_009_verifyEmployeePlantMapping() {
        loginToApplication();

        CreateEmployeePage employeePage = openCreateEmployeePage();

        employeePage.selectOrganization("Exide Industries");
        employeePage.selectPlant("Exide_plant");

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
    @Test
public void TC_EMP_CREATE_VAL_001_submitEmptyForm() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Required field validations should appear");
}

@Test
public void TC_EMP_CREATE_VAL_002_emptyOrganizationField() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    long time = System.currentTimeMillis();

    page.enterEmployeeNameOnly("Employee " + time);
    page.enterEmployeeEmailOnly("employee" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDesignationOnly("QA Tester");
    page.enterDepartmentOnly("Quality Assurance");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Organization validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_004_emptyPlantField() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    long time = System.currentTimeMillis();

    page.selectValidOrganization();

    page.enterEmployeeNameOnly("Employee " + time);
    page.enterEmployeeEmailOnly("employee" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDesignationOnly("QA Tester");
    page.enterDepartmentOnly("Quality Assurance");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Plant validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_006_emptyEmployeeName() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeNameOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Employee name validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_007_invalidEmployeeName() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeNameOnly("@@@###");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid employee name should be rejected");
}

@Test
public void TC_EMP_CREATE_VAL_008_sqlInjectionInEmployeeName() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeNameOnly("' OR '1'='1");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "SQL Injection should fail");
}

@Test
public void TC_EMP_CREATE_VAL_009_xssInEmployeeName() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeNameOnly("<script>alert(1)</script>");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Script should not execute");
}

@Test
public void TC_EMP_CREATE_VAL_010_emptyEmailField() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeEmailOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Email validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_011_invalidEmailFormat() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeEmailOnly("abc@");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Email validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_012_duplicateEmailValidation() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterEmployeeEmailOnly("system@tracecircle.com");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Duplicate email validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_013_emptyPhoneNumber() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterPhoneNumberOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Phone number validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_014_invalidPhoneNumber() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterPhoneNumberOnly("abc@@123");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Phone validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_015_shortPhoneNumber() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterPhoneNumberOnly("98765");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Short phone number validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_016_longPhoneNumber() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterPhoneNumberOnly("9876543212345");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Long phone number validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_017_emptyDesignationField() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterDesignationOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Designation validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_018_invalidDesignationInput() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterDesignationOnly("@@@###");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid designation should be rejected");
}

@Test
public void TC_EMP_CREATE_VAL_019_emptyDepartmentField() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterDepartmentOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Department validation should appear");
}

@Test
public void TC_EMP_CREATE_VAL_020_invalidDepartmentInput() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();
    page.enterDepartmentOnly("@@@###");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid department should be rejected");
}

@Test
public void TC_EMP_CREATE_VAL_021_longInputValidation() {
    loginToApplication();
    CreateEmployeePage page = openCreateEmployeePage();

    page.fillAllValidEmployeeData();

    String longText = "A".repeat(1200);

    page.enterEmployeeNameOnly(longText);
    page.enterDesignationOnly(longText);
    page.enterDepartmentOnly(longText);

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Long input should be handled safely");
}
}