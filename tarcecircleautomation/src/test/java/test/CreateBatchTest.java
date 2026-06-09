package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.CreateBatchPage;

public class CreateBatchTest extends BaseTest {

    DashboardPage dashboardPage;
    CreateBatchPage createBatchPage;

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
    public void openCreateBatchPage() {

        loginToApplication();

        dashboardPage.clickDppManagement();
        dashboardPage.clickBatches();
        dashboardPage.clickCreateBatch();

        createBatchPage = new CreateBatchPage(driver, wait);
    }

    @Test
    public void TC_BATCH_001_verifyNewBatchPageLoads() {

        Assert.assertTrue(
                createBatchPage.isCreateBatchPageOpened(),
                "New Batch page is not opened"
        );
    }

    @Test
    public void TC_BATCH_002_verifyAllFieldsDisplay() {

        Assert.assertTrue(
                createBatchPage.areAllFieldsDisplayed(),
                "Organization, Plant, Product GTIN Number, Batch Name, or Product Quantity field is missing"
        );
    }

    @Test
    public void TC_BATCH_003_verifyOrganizationDropdownLoading() {

        createBatchPage.openOrganizationDropdown();

        Assert.assertTrue(
                createBatchPage.isDropdownOptionVisible("Battery_Org"),
                "Organization dropdown list is not displayed"
        );
    }

    @Test
    public void TC_BATCH_004_verifyPlantDropdownDependency() {

        createBatchPage.selectOrganization("Battery_Org");

        Assert.assertTrue(
                createBatchPage.isPlantDropdownEnabled(),
                "Plant dropdown is not enabled after selecting organization"
        );
    }

    @Test
    public void TC_BATCH_005_verifyProductGtinFieldAcceptsValidInput() {

        createBatchPage.enterProductGtin("BPAN-2026-001");

        Assert.assertTrue(
                createBatchPage.isProductGtinAccepted("BPAN-2026-001"),
                "Product GTIN Number is not accepted"
        );
    }

    @Test
    public void TC_BATCH_006_verifyBatchNameField() {

        createBatchPage.enterBatchName("Batch_Auto_001");

        Assert.assertTrue(
                createBatchPage.isBatchNameAccepted("Batch_Auto_001"),
                "Batch Name is not accepted"
        );
    }

    @Test
    public void TC_BATCH_007_verifyProductQuantityField() {

        createBatchPage.enterProductQuantity("100");

        Assert.assertTrue(
                createBatchPage.isQuantityAccepted("100"),
                "Product Quantity is not accepted"
        );
    }

  @Test
public void TC_BATCH_008_verifyCreateBatchFunctionality() {

    createBatchPage.selectOrganization("Battery_Org");

    Assert.assertTrue(
            createBatchPage.isOrganizationSelected("Battery_Org"),
            "Organization value is not selected"
    );

    Assert.assertTrue(
            createBatchPage.isPlantDropdownEnabled(),
            "Plant dropdown is not enabled after selecting organization"
    );

    createBatchPage.selectPlant("Battery_plant");
Assert.assertTrue(
            createBatchPage.isPlantSelected("Battery_plant"),
            "Plant value is not selected"
    );
        createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_001");
    createBatchPage.enterProductQuantity("100");

    createBatchPage.clickCreateBatch();

    Assert.assertTrue(
            createBatchPage.isBatchCreatedOrSaved(),
            "Batch was not created successfully"
    );
}

    @Test
    public void TC_BATCH_009_verifyCancelButton() {

        createBatchPage.clickCancel();

        Assert.assertTrue(
                createBatchPage.isReturnedToBatchList(),
                "Cancel button did not navigate back without saving"
        );
    }

    @Test
    public void TC_BATCH_010_verifyBackButton() {

        createBatchPage.clickBack();

        Assert.assertTrue(
                createBatchPage.isReturnedToBatchList(),
                "Back button did not navigate to Batch List page"
        );
    }

    @Test
    public void TC_BATCH_011_verifyBatchCreationSuccessMessage() {

       createBatchPage.selectOrganization("Battery_Org");

    Assert.assertTrue(
            createBatchPage.isOrganizationSelected("Battery_Org"),
            "Organization value is not selected"
    );

    Assert.assertTrue(
            createBatchPage.isPlantDropdownEnabled(),
            "Plant dropdown is not enabled after selecting organization"
    );

    createBatchPage.selectPlant("Battery_plant");
Assert.assertTrue(
            createBatchPage.isPlantSelected("Battery_plant"),
            "Plant value is not selected"
    );
        createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_001");
    createBatchPage.enterProductQuantity("100");

    createBatchPage.clickCreateBatch();

    Assert.assertTrue(
            createBatchPage.isBatchCreatedOrSaved(),
            "Batch was not created successfully"
    );
}

    @Test
    public void TC_BATCH_012_verifyCreatedBatchInList() { 
createBatchPage.selectOrganization("Battery_Org");

    Assert.assertTrue(
            createBatchPage.isOrganizationSelected("Battery_Org"),
            "Organization value is not selected"
    );

    Assert.assertTrue(
            createBatchPage.isPlantDropdownEnabled(),
            "Plant dropdown is not enabled after selecting organization"
    );

    createBatchPage.selectPlant("Battery_plant");
Assert.assertTrue(
            createBatchPage.isPlantSelected("Battery_plant"),
            "Plant value is not selected"
    );
        createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_001");
    createBatchPage.enterProductQuantity("100");

    createBatchPage.clickCreateBatch();

    Assert.assertTrue(
            createBatchPage.isBatchCreatedOrSaved(),
            "Batch was not created successfully"
    );
}

}