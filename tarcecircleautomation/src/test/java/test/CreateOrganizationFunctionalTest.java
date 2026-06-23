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

public CreateOrganizationPage openCreateOrganizationUsingDashboardFlow() throws InterruptedException {

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
    public void TC_ORG_FUNC_001_openAddOrganizationPage() throws InterruptedException {

        loginToApplication();


        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        Assert.assertTrue(
                createOrgPage.isCreateOrganizationPageOpened(),
                "Add Organization page did not open"
        );
    }

    @Test
    public void TC_ORG_FUNC_002_createOrganizationWithValidData() throws InterruptedException {

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
    public void TC_ORG_FUNC_003_cancelOrganizationCreation() throws InterruptedException {

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
    public void TC_ORG_FUNC_004_verifyDropdownLoading() throws InterruptedException {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

        createOrgPage.openSectorDropdown();
        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("Battery (EU)"),
                "Sector dropdown values are not loading"
        );
    }
@Test
    public void TC_ORG_FUNC_00_verifyDropdownLoading() throws InterruptedException {

        loginToApplication();

        CreateOrganizationPage createOrgPage = openCreateOrganizationUsingDashboardFlow();

      
        createOrgPage.openCountryDropdown();
        Assert.assertTrue(
                createOrgPage.isDropdownOptionVisible("India"),
                "Country dropdown values are not loading"
        );
    }

    @Test
    public void TC_ORG_FUNC_005_verifyStateDependsOnCountry() throws InterruptedException {

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
    public void TC_ORG_FUNC_006_verifyCityDependsOnState() throws InterruptedException {

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
    public void TC_ORG_FUNC_007_verifyOrganizationAppearsInList() throws InterruptedException {

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
   @Test
public void TC_ORG_VAL_001_submitFormWithAllFieldsEmpty() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Mandatory validation should show");
}

@Test
public void TC_ORG_VAL_002_organizationNameBlank() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterOrganizationNameOnly("");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Only organization name validation should show");
}

@Test
public void TC_ORG_VAL_003_organizationNameWithNumbersOnly() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterOrganizationNameOnly("123456");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Organization name numeric validation should show");
}

@Test
public void TC_ORG_VAL_004_organizationNameWithSQLInjection() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterOrganizationNameOnly("' OR '1'='1");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Organization name SQL validation should show");
}

@Test
public void TC_ORG_VAL_005_organizationNameWithXSS() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterOrganizationNameOnly("<script>alert(1)</script>");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Organization name XSS validation should show");
}

@Test
public void TC_ORG_VAL_008_invalidWebsiteURL() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterWebsiteOnly("abc.commm");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Website validation should show");
}

@Test
public void TC_ORG_VAL_010_addressWithScript() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterAddressOnly("<img src=x onerror=alert(1)>");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Address XSS validation should show");
}

@Test
public void TC_ORG_VAL_016_postalCodeAlphabets() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterPostalCodeOnly("ABCDE");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Postal code validation should show");
}

@Test
public void TC_ORG_VAL_018_contactPersonNameWithNumbers() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterContactPersonNameOnly("12345");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Contact name validation should show");
}

@Test
public void TC_ORG_VAL_019_contactEmailInvalid() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterContactEmailOnly("abc@");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Invalid email validation should show");
}

@Test
public void TC_ORG_VAL_020_contactEmailDuplicate() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterContactEmailOnly("manasagowdamca2002@gmail.com");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Duplicate email validation should show");
}

@Test
public void TC_ORG_VAL_022_phoneNumberWithAlphabets() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterPhoneNumberOnly("abcde12345");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Phone alphabet validation should show");
}

@Test
public void TC_ORG_VAL_023_phoneNumberWithSpecialChars() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterPhoneNumberOnly("98765@@@@@");

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Phone special character validation should show");
}

@Test
public void TC_ORG_VAL_024_descriptionWithLongText() throws InterruptedException {
    loginToApplication();
    CreateOrganizationPage page = openCreateOrganizationUsingDashboardFlow();

    page.fillAllValidData();
    page.enterDescriptionOnly("A".repeat(1200));

    page.clickCreateOrganization();
page.scrollToFirstValidationError();
    Assert.assertTrue(page.isValidationDisplayed(), "Description long text validation should show");
}
}