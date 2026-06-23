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

       createBatchPage.selectOrganization("Exide ");

    // Assert.assertTrue(
    //         createBatchPage.isOrganizationSelected("Exide "),
    //         "Organization value is not selected"
    // );

    // Assert.assertTrue(
    //         createBatchPage.isPlantDropdownEnabled(),
    //         "Plant dropdown is not enabled after selecting organization"
    // );

    createBatchPage.selectPlant("Exide Plant");
// Assert.assertTrue(
//             createBatchPage.isPlantSelected("Exide Plant"),
//             "Plant value is not selected"
//     );
        createBatchPage.enterProductGtin("1234567890");
    createBatchPage.enterBatchName("Batch_Auto_001");
    createBatchPage.enterProductQuantity("1");

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
@Test
public void TC_BATCH_VAL_001_submitEmptyForm() {
    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Validation messages should appear"
    );
}

@Test
public void TC_BATCH_VAL_002_emptyOrganizationField() {
    createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_" + System.currentTimeMillis());
    createBatchPage.enterProductQuantity("100");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Organization validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_003_emptyPlantField() {
    createBatchPage.selectOrganization("Exide");
    createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_" + System.currentTimeMillis());
    createBatchPage.enterProductQuantity("100");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Plant validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_004_emptyGTINNumber() {
    createBatchPage.selectOrganization("Exide");
    createBatchPage.selectPlant("Exide Plant");
    createBatchPage.enterBatchName("Batch_Auto_" + System.currentTimeMillis());
    createBatchPage.enterProductQuantity("100");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "GTIN validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_005_emptyBatchName() {
    createBatchPage.selectOrganization("Exide");
    createBatchPage.selectPlant("Exide Plant");
    createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterProductQuantity("100");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Batch name validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_006_emptyQuantity() {
    createBatchPage.selectOrganization("Exide");
    createBatchPage.selectPlant("Exide Plant");
    createBatchPage.enterProductGtin("BPAN-2026-001");
    createBatchPage.enterBatchName("Batch_Auto_" + System.currentTimeMillis());

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Quantity validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_009_invalidGTINFormat() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterProductGtin("@@@INVALID###");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Invalid GTIN validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_010_duplicateBatchName() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterBatchName("Batch_Auto_001");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Duplicate batch name validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_011_quantityAsAlphabet() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterProductQuantity("ABC");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Quantity alphabet validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_012_quantityAsSpecialCharacters() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterProductQuantity("@#$%");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Quantity special character validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_013_quantityAsNegativeValue() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterProductQuantity("-100");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Negative quantity validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_014_quantityAsZero() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterProductQuantity("0");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Zero quantity validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_015_longBatchName() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterBatchName("A".repeat(600));

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Long batch name validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_016_sqlInjectionInBatchName() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterBatchName("' OR '1'='1");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "SQL injection validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_017_xssInBatchName() {
    createBatchPage.fillAllValidBatchData();
    createBatchPage.enterBatchName("<script>alert(1)</script>");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "XSS validation should appear"
    );
}

@Test
public void TC_BATCH_VAL_018_invalidGTINFromAnotherOrganization() {
    createBatchPage.selectOrganization("Exide");
    createBatchPage.selectPlant("Exide Plant");
    createBatchPage.enterProductGtin("GTIN-OTHER-ORG-001");
    createBatchPage.enterBatchName("Batch_Auto_" + System.currentTimeMillis());
    createBatchPage.enterProductQuantity("2");

    createBatchPage.submitAndScrollToError();

    Assert.assertTrue(
            createBatchPage.isValidationDisplayed(),
            "Invalid GTIN from another organization validation should appear"
    );
}
}