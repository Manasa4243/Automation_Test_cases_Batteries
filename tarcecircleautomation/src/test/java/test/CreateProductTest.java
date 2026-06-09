package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.CreateProductPage;

public class CreateProductTest extends BaseTest {

    DashboardPage dashboardPage;
    CreateProductPage createProductPage;

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
public void openCreateProductPage() {

    loginToApplication();

    dashboardPage.clickDppManagement();
    dashboardPage.clickProducts();

    createProductPage = new CreateProductPage(driver, wait);
}
    @Test
    public void TC_PRODUCT_001_verifyCreateProductPageLoads() {
        Assert.assertTrue(
                createProductPage.isCreateProductPageOpened(),
                "Create New Product page is not opened"
        );
    }

@Test
public void TC_PRODUCT_002_verifyAllStep1FieldsDisplay() {

    createProductPage.openCreateBatteryPage();

    Assert.assertTrue(
            createProductPage.isCreateProductPageOpened(),
            "Create Battery page is not opened"
    );

    Assert.assertTrue(
            createProductPage.areStep1FieldsDisplayed(),
            "All Step 1 fields are not displayed"
    );
}

@Test
public void TC_PRODUCT_003_verifyAllStep2FieldsDisplay() {

    createProductPage = new CreateProductPage(driver, wait);

    createProductPage.openCreateBatteryPage();

    Assert.assertTrue(
            createProductPage.isCreateProductPageOpened(),
            "Create Battery form is not opened"
    );

    createProductPage.clickNext();

    Assert.assertTrue(
            createProductPage.isStep2Opened(),
            "Step 2 page is not opened after clicking Next"
    );

    Assert.assertTrue(
            createProductPage.areStep2FieldsDisplayed(),
            "All Step 2 fields are not displayed"
    );
}
@Test
public void TC_PRODUCT_004_verifyOrganizationDropdownLoading() {

    createProductPage.openCreateBatteryPage();

    String orgName = "Battery_Org";

    createProductPage.selectOrganization(orgName);

    Assert.assertTrue(
            createProductPage.isOrganizationSelected(orgName),
            "Organization dropdown value is not selected"
    );
}
@Test
public void TC_PRODUCT_005_verifyPlantDropdownDependency() {

    createProductPage.openCreateBatteryPage();

    createProductPage.selectOrganization("Battery_Org");

    Assert.assertTrue(
            createProductPage.isOrganizationSelected("Battery_Org"),
            "Organization dropdown value is not selected"
    );

    createProductPage.selectPlant("Battery_plant");

    Assert.assertTrue(
            createProductPage.isPlantSelected("Battery_plant"),
            "Plant dropdown value is not selected"
    );
}
    @Test
    public void TC_PRODUCT_006_verifyBatteryTypeDropdown() {
            createProductPage.openCreateBatteryPage();

        createProductPage.selectBatteryType("EV");

        Assert.assertTrue(
                createProductPage.isDropdownOptionVisible("EV"),
                "Battery Type dropdown list is not displayed"
        );
    }

    @Test
    public void TC_PRODUCT_007_verifyBatteryCategoryDependency() {
                    createProductPage.openCreateBatteryPage();

        createProductPage.selectBatteryType("EV");
        createProductPage.selectBatteryCategory("EV 2 Wheeler");

        Assert.assertTrue(
                createProductPage.isDropdownOptionVisible("EV 2 Wheeler"),
                "Battery Category is not loaded based on Battery Type"
        );
    }

    @Test
    public void TC_PRODUCT_008_verifyBatteryChemistryDependency() {
                            createProductPage.openCreateBatteryPage();

        // createProductPage.selectBatteryType("EV");
        createProductPage.selectBatteryChemistry("Lithium Ion");

        Assert.assertTrue(
                createProductPage.isDropdownOptionVisible("Lithium Ion"),
                "Battery Chemistry is not loaded based on Battery Type"
        );
    }

    @Test
    public void TC_PRODUCT_009_verifyExtinguisherClassDependency() {
        createProductPage.openCreateBatteryPage();
        createProductPage.selectBatteryType("EV");
        createProductPage.selectExtinguisherClass("Class D");
        Assert.assertTrue(
                createProductPage.isDropdownOptionVisible("Class D"),
                "Extinguisher Class is not loaded based on Battery Type"
        );
    }

  @Test
    public void TC_PRODUCT_010_verifyValidStep1Submission() {
                createProductPage.openCreateBatteryPage();

        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();

        Assert.assertTrue(
                createProductPage.isStep2Opened(),
                "User did not move to Step 2 after valid Step 1 submission"
        );
    }

    @Test
    public void TC_PRODUCT_011_verifyPreviousButton() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();
        createProductPage.clickPrevious();

        Assert.assertTrue(
                createProductPage.areStep1FieldsDisplayed(),
                "Previous button did not navigate back to Step 1"
        );
    }

    @Test
    public void TC_PRODUCT_012_verifyStep1DataRetention() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();
        createProductPage.clickPrevious();

        Assert.assertTrue(
                createProductPage.areStep1FieldsDisplayed(),
                "Step 1 data is not retained after navigating back"
        );
    }

    @Test
    public void TC_PRODUCT_013_verifyCreateProductWithValidData() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();
        createProductPage.fillStep1ValidData();
        createProductPage.clickCreateProduct();

        Assert.assertTrue(
                createProductPage.isProductCreatedOrSaved(),
                "Product was not created successfully"
        );
    }

    @Test
    public void TC_PRODUCT_014_verifyCancelButton() {
        createProductPage.clickCancel();

        Assert.assertTrue(
                createProductPage.isReturnedToProductList(),
                "Cancel button did not navigate back to product list"
        );
    }

    @Test
    public void TC_PRODUCT_015_verifyAddProcessButton() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();

        createProductPage.clickAddProcess();

        Assert.assertTrue(
                createProductPage.isProcessSectionAdded(),
                "New manufacturing process section is not added"
        );
    }

    @Test
    public void TC_PRODUCT_016_verifyDownloadTemplateButton() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();

        Assert.assertTrue(
                createProductPage.isDownloadTemplateButtonVisible(),
                "Download Template button is not visible"
        );
    }

    @Test
    public void TC_PRODUCT_017_verifyFileUpload() {
        createProductPage.fillStep1ValidData();
        createProductPage.clickNext();

        createProductPage.uploadFile("D:\\test-files\\sample.pdf");

        Assert.assertTrue(
                createProductPage.areStep2FieldsDisplayed(),
                "File was not uploaded successfully"
        );
    }
}