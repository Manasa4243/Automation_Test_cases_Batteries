package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.OrganizationListPage;
import pages.ViewOrganizationPage;

public class ViewOrganizationTest extends BaseTest {

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

        OrganizationListPage organizationListPage =
                new OrganizationListPage(driver, wait);

        Assert.assertTrue(
                organizationListPage.isOrganizationListPageOpened(),
                "Organization List page is not opened"
        );

        return organizationListPage;
    }

    public ViewOrganizationPage openViewOrganizationPage() {

        OrganizationListPage organizationListPage =
                openOrganizationListPage();

        organizationListPage.clickViewFromActionMenu();

        ViewOrganizationPage viewOrganizationPage =
                new ViewOrganizationPage(driver, wait);

        Assert.assertTrue(
                viewOrganizationPage.isViewOrganizationPageOpened(),
                "View Organization page is not opened"
        );

        return viewOrganizationPage;
    }

    @Test
    public void TC_ORG_VIEW_001_verifyViewOrganizationPageLoads() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isViewOrganizationPageOpened(),
                "View Organization page should load successfully"
        );
    }

    @Test
    public void TC_ORG_VIEW_002_verifyOrganizationDetailsDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.areOrganizationDetailsDisplayed(),
                "All organization details are not displayed correctly"
        );
    }

    @Test
    public void TC_ORG_VIEW_003_verifyOrganizationNameDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isOrganizationNameDisplayed(),
                "Organization name is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_004_verifyOrganizationEmailDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isOrganizationEmailDisplayed(),
                "Organization email is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_005_verifySectorDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isSectorDisplayed(),
                "Sector is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_006_verifyCountryDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isCountryDisplayed(),
                "Country is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_007_verifyStateDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isStateDisplayed(),
                "State is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_008_verifyCityDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isCityDisplayed(),
                "City is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_009_verifyPostalCodeDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isPostalCodeDisplayed(),
                "Postal code is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_010_verifyAddressDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isAddressDisplayed(),
                "Address is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_011_verifyCompanyWebsiteDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isCompanyWebsiteDisplayed(),
                "Company website is not displayed"
        );
    }
 @Test
public void TC_ORG_VIEW_012_verifyContactPersonDetailsDisplay() {

    loginToApplication();

    ViewOrganizationPage viewOrganizationPage =
            openViewOrganizationPage();

    String contactPersonName =
            viewOrganizationPage.getContactPersonName();

    String phoneNumber =
            viewOrganizationPage.getPhoneNumber();

    Assert.assertFalse(
            contactPersonName.isEmpty(),
            "Contact person name is not displayed"
    );

    Assert.assertFalse(
            phoneNumber.isEmpty(),
            "Phone number is not displayed"
    );

    System.out.println("Contact Person Name : " + contactPersonName);
    System.out.println("Phone Number : " + phoneNumber);
}
    @Test
    public void TC_ORG_VIEW_013_verifyDescriptionDisplay() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        Assert.assertTrue(
                viewOrganizationPage.isDescriptionDisplayed(),
                "Description is not displayed"
        );
    }

    @Test
    public void TC_ORG_VIEW_015_verifyBackNavigation() {

        loginToApplication();

        ViewOrganizationPage viewOrganizationPage =
                openViewOrganizationPage();

        viewOrganizationPage.clickBackButton();

        Assert.assertTrue(
                viewOrganizationPage.isReturnedToOrganizationList(),
                "User did not return to organization list page"
        );
    }
}