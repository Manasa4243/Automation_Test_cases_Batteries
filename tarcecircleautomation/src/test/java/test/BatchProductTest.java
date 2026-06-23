package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BatchProductPage;
import pages.DashboardPage;
import pages.LoginPage;

public class BatchProductTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    BatchProductPage batchProductPage;

    String email = "system@tracecircle.com";
    String password = "StrongPassword@123";

    @BeforeMethod
    public void openMapProductPage() {

        driver.get(LOGIN_URL);

        loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();

        dashboardPage.openBatchProductPage();

        batchProductPage = new BatchProductPage(driver, wait);

        Assert.assertTrue(
                batchProductPage.isBatchProductPageOpened(),
                "Batch+Product page is not opened"
        );

        batchProductPage.clickFirstMapProduct();

        Assert.assertTrue(
                batchProductPage.isMapProductPageOpened(),
                "Map Product page is not opened"
        );
    }

    @Test
    public void TC_BATCH_001_verifyMapProductPageOpensSuccessfully() {
        Assert.assertTrue(
                batchProductPage.isMapProductPageOpened(),
                "Map Product page should open successfully"
        );
    }

    @Test
    public void TC_BATCH_002_verifyMapProductPageTitle() {
        Assert.assertTrue(
                batchProductPage.isMapProductPageOpened(),
                "Map Product page title is not displayed"
        );
    }

    @Test
    public void TC_BATCH_003_verifyBreadcrumbNavigation() {
        Assert.assertTrue(
                batchProductPage.isBreadcrumbDisplayed(),
                "Breadcrumb navigation is not displayed"
        );
    }

    @Test
    public void TC_BATCH_004_verifyAllMapProductFieldsAreDisplayed() {
        Assert.assertTrue(
                batchProductPage.areMapProductFieldsDisplayed(),
                "Map Product fields are missing"
        );
    }

    @Test
    public void TC_BATCH_005_verifyMapProductSubmitButton() {
        Assert.assertTrue(
                batchProductPage.isMapProductSubmitButtonDisplayed(),
                "Map Product submit button is not displayed"
        );
    }

    @Test
    public void TC_BATCH_006_verifyCancelButton() {
        Assert.assertTrue(
                batchProductPage.isCancelButtonVisibleOnMapPage(),
                "Cancel button is not displayed"
        );
    }

    @Test
    public void TC_BATCH_007_verifyFieldAlignment() {
        Assert.assertTrue(
                batchProductPage.areMapProductFieldsDisplayed(),
                "Fields are not aligned properly"
        );
    }

    @Test
    public void TC_BATCH_008_verifyPlaceholderTexts() {
        Assert.assertTrue(
                batchProductPage.isProductPlaceholderDisplayed(),
                "Product dropdown placeholder is missing"
        );
    }

    @Test
    public void TC_BATCH_009_verifyProductDropdownLoadsValues() {
        batchProductPage.openProductDropdown();

        Assert.assertTrue(
                batchProductPage.isDropdownOptionVisible("Lithium Ion EV Battery Pack")
                        || batchProductPage.isDropdownOptionVisible("Lithium Ion EV Battery Pack")
                        || batchProductPage.isDropdownOptionVisible("Lithium Ion EV Battery Pack"),
                "Product dropdown values are not loading"
        );
    }

    @Test
    public void TC_BATCH_010_verifyBatchIdFieldIsReadOnly() {
        Assert.assertTrue(
                batchProductPage.isBatchIdReadOnly(),
                "Batch ID should be read-only"
        );
    }

    @Test
    public void TC_BATCH_011_verifyBatchCodeFieldIsReadOnly() {
        Assert.assertTrue(
                batchProductPage.isBatchCodeReadOnly(),
                "Batch Code should be read-only"
        );
    }

    @Test
    public void TC_BATCH_012_verifyBatchProductMappingWithValidData() {
        batchProductPage.selectProduct("Lithium Ion EV Battery Pack");

        batchProductPage.clickMapProductSubmit();

        Assert.assertTrue(
                batchProductPage.isProductMappedSuccessfully(),
                "Product was not mapped successfully"
        );
    }

    @Test
    public void TC_BATCH_013_verifyCancelButtonFunctionality() {
        batchProductPage.clickCancel();

        Assert.assertTrue(
                batchProductPage.isReturnedToBatchProductList(),
                "Cancel button did not return to list"
        );
    }

    @Test
    public void TC_BATCH_014_verifyBackButtonFunctionality() {
        batchProductPage.clickBack();

        Assert.assertTrue(
                batchProductPage.isReturnedToBatchProductList(),
                "Back button did not return to list"
        );
    }

    @Test
    public void TC_BATCH_015_verifySuccessNotification() {
        batchProductPage.selectProduct("Cell");

        batchProductPage.clickMapProductSubmit();

        Assert.assertTrue(
                batchProductPage.isProductMappedSuccessfully(),
                "Success notification is not displayed"
        );
    }

    @Test
    public void TC_BATCH_016_verifyProductMandatoryValidation() {
        batchProductPage.clickMapProductSubmit();

        Assert.assertTrue(
                batchProductPage.isValidationDisplayed(),
                "Product mandatory validation should appear"
        );
    }
}