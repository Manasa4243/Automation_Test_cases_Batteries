package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import pages.MapProductPage;

public class MapProductTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    MapProductPage mapProductPage;

    String email = "system@tracecircle.com";
    String password = "StrongPassword@123";

    @BeforeMethod
    public void openMapProductPage() {

        driver.get(LOGIN_URL);

        loginPage = new LoginPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        dashboardPage.waitForSidebarAfterLogin();

        dashboardPage.openMapProductPage();

        mapProductPage = new MapProductPage(driver, wait);

        Assert.assertTrue(
                mapProductPage.isMapProductPageOpened(),
                "Map Product page is not opened"
        );
    }

    @Test
    public void TC_BATCH_009_verifyBatchDetailsAreDisplayed() {

        Assert.assertTrue(
                mapProductPage.areBatchDetailsDisplayed(),
                "Batch ID, Batch Code, Organization ID, or Plant ID is not displayed"
        );
    }

    @Test
    public void TC_BATCH_010_verifyBatchDetailsAreReadOnly() {

        Assert.assertTrue(
                mapProductPage.areBatchFieldsReadOnly(),
                "Batch information fields should be read-only"
        );
    }

    @Test
    public void TC_BATCH_011_verifyProductDropdownLoadsValues() {

        Assert.assertTrue(
                mapProductPage.isProductDropdownOpened(),
                "Product dropdown values are not loading"
        );
    }

    @Test
    public void TC_BATCH_012_verifyMapProductWithValidData() {

        mapProductPage.selectProduct("Lithium Ion EV Battery Pack");

        Assert.assertTrue(
                mapProductPage.isMapProductButtonEnabled(),
                "Map Product button is disabled"
        );

        mapProductPage.clickMapProduct();

        Assert.assertTrue(
                mapProductPage.isSuccessMessageDisplayed(),
                "Success message is not displayed after mapping product"
        );
    }

    @Test
    public void TC_BATCH_013_verifyCancelButtonFunctionality() {

        mapProductPage.clickCancel();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("batch") ||
                driver.getPageSource().contains("Batch Product Mapping"),
                "Cancel button did not redirect to Batch Product list page"
        );
    }

    @Test
    public void TC_BATCH_014_verifyBackButtonFunctionality() {

        mapProductPage.clickBack();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("batch") ||
                driver.getPageSource().contains("Batch Product Mapping"),
                "Back button did not navigate to previous page"
        );
    }

    @Test
    public void TC_BATCH_015_verifyValidationWithoutSelectingProduct() {

        mapProductPage.clickMapProduct();

        Assert.assertTrue(
                driver.getPageSource().contains("Please select a product") ||
                driver.getPageSource().contains("required") ||
                driver.getPageSource().contains("Select product"),
                "Validation message is not displayed when product is not selected"
        );
    }
}