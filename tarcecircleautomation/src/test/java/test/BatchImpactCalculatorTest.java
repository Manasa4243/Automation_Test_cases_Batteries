package test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.BatchImpactCalculatorPage;
import pages.DashboardPage;
import pages.LoginPage;

public class BatchImpactCalculatorTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    BatchImpactCalculatorPage calculatorPage;

    String email = "system@tracecircle.com";
    String password = "StrongPassword@123";

    @BeforeMethod
    public void openCalculatorPage() {

        driver.get(LOGIN_URL);

        loginPage = new LoginPage(driver, wait);
        dashboardPage = new DashboardPage(driver, wait);

        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        dashboardPage.waitForSidebarAfterLogin();

        dashboardPage.openBatchImpactCalculatorPage();

        calculatorPage = new BatchImpactCalculatorPage(driver, wait);

        Assert.assertTrue(
                calculatorPage.isCalculatorPageOpened(),
                "Batch Impact Calculator page is not opened"
        );
    }

    @Test
    public void TC_CALC_001_verifyCalculatorPageOpens() {
        Assert.assertTrue(
                calculatorPage.isCalculatorPageOpened(),
                "Calculator page did not open"
        );
    }

    @Test
    public void TC_CALC_002_verifyBatchNameAndBatchCodeDisplayed() {
        Assert.assertTrue(
                calculatorPage.areBatchDetailsDisplayed(),
                "Batch Name or Batch Code is not displayed"
        );
    }

    @Test
    public void TC_CALC_003_verifyBatchFieldsAreReadOnly() {
        Assert.assertTrue(
                calculatorPage.areBatchFieldsReadOnly(),
                "Batch Name and Batch Code should be read-only"
        );
    }

    @Test
    public void TC_CALC_004_verifyWaterTypeDropdownLoadsValues() {
        Assert.assertTrue(
                calculatorPage.isWaterTypeDropdownOpened(),
                "Water Type dropdown values are not loading"
        );
    }

    @Test
    public void TC_CALC_005_verifyWasteTypeDropdownLoadsValues() {
        Assert.assertTrue(
                calculatorPage.isWasteTypeDropdownOpened(),
                "Waste Type dropdown values are not loading"
        );
    }

 @Test
public void TC_CALC_006_verifyHazardousMaterialDropdownLoadsValues() {
    Assert.assertTrue(
            calculatorPage.isHazardousDropdownOpened(),
            "Hazardous Material dropdown values are not loading"
    );
}

    @Test
    public void TC_CALC_007_verifyValidCalculationSubmission() throws InterruptedException {
        calculatorPage.fillValidCalculatorData();

        Assert.assertTrue(
                calculatorPage.isCalculateButtonEnabled(),
                "Calculate Batch button is disabled"
        );

        calculatorPage.clickCalculateBatch();
Thread.sleep(2000);
        Assert.assertTrue(
                driver.getPageSource().contains("Battery CO2e calculated successfully"),

                "Success message is not displayed after valid calculation submission"
        );
    }

    @Test
    public void TC_CALC_008_verifyValidationWithoutWaterType() {
        calculatorPage.selectWasteType("Anode Scrap");
        calculatorPage.enterTotalWaterUsed("54");
        calculatorPage.enterTotalElectricityUsed("65");
        calculatorPage.enterTotalWasteGenerated("56");
        calculatorPage.enterAirEmission("5656");
        calculatorPage.enterNoiseEmission("567");
        calculatorPage.enterSoilEmission("67");
        calculatorPage.enterPlasticWasteGenerated("56");
        calculatorPage.selectHazardousMaterial("No");

        calculatorPage.clickCalculateBatch();

        Assert.assertTrue(
                calculatorPage.isValidationDisplayed(),
                "Validation message is not displayed for missing Water Type"
        );
    }

    @Test
    public void TC_CALC_009_verifyValidationWithoutWasteType() {
        calculatorPage.selectWaterType("Blue Water");
        calculatorPage.enterTotalWaterUsed("54");
        calculatorPage.enterTotalElectricityUsed("65");
        calculatorPage.enterTotalWasteGenerated("56");
        calculatorPage.enterAirEmission("5656");
        calculatorPage.enterNoiseEmission("567");
        calculatorPage.enterSoilEmission("67");
        calculatorPage.enterPlasticWasteGenerated("56");
        calculatorPage.selectHazardousMaterial("No");

        calculatorPage.clickCalculateBatch();

        Assert.assertTrue(
                calculatorPage.isValidationDisplayed(),
                "Validation message is not displayed for missing Waste Type"
        );
    }

  @Test
public void TC_CALC_010_verifyValidationWithEmptyNumericFields() throws Exception {

    String beforeUrl = driver.getCurrentUrl();

    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Thread.sleep(5000);

    String afterUrl = driver.getCurrentUrl();
    String pageText = driver.findElement(By.tagName("body")).getText();

    boolean calculationHappened =
            !beforeUrl.equals(afterUrl)
            || pageText.contains("Battery COe2 calculated successfully")
            || pageText.contains("calculated successfully")
            || pageText.contains("COe2")
            || pageText.contains("View Calculation");

    Assert.assertFalse(
            calculationHappened,
            "BUG: Calculation is happening even when numeric fields are empty"
    );
}

   @Test
public void TC_CALC_011_verifyAlphabetInputInNumericFields() {

    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");

    calculatorPage.enterTotalWaterUsed("abc");
    calculatorPage.enterTotalElectricityUsed("abc");
    calculatorPage.enterTotalWasteGenerated("abc");
    calculatorPage.enterAirEmission("abc");
    calculatorPage.enterNoiseEmission("abc");
    calculatorPage.enterSoilEmission("abc");
    calculatorPage.enterPlasticWasteGenerated("abc");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "", "Alphabet accepted in Total Water Used");
    Assert.assertEquals(calculatorPage.getTotalElectricityUsedValue(), "", "Alphabet accepted in Electricity Used");
    Assert.assertEquals(calculatorPage.getTotalWasteGeneratedValue(), "", "Alphabet accepted in Waste Generated");
    Assert.assertEquals(calculatorPage.getAirEmissionValue(), "", "Alphabet accepted in Air Emission");
    Assert.assertEquals(calculatorPage.getNoiseEmissionValue(), "", "Alphabet accepted in Noise Emission");
    Assert.assertEquals(calculatorPage.getSoilEmissionValue(), "", "Alphabet accepted in Soil Emission");
    Assert.assertEquals(calculatorPage.getPlasticWasteGeneratedValue(), "", "Alphabet accepted in Plastic Waste");
}

 @Test
    public void TC_CALC_012_verifyNegativeValuesAreNotAccepted() {
        calculatorPage.selectWaterType("Blue Water");
        calculatorPage.selectWasteType("Anode Scrap");

        calculatorPage.enterTotalWaterUsed("-10");
        calculatorPage.enterTotalElectricityUsed("-10");
        calculatorPage.enterTotalWasteGenerated("-10");
        calculatorPage.enterAirEmission("-10");
        calculatorPage.enterNoiseEmission("-10");
        calculatorPage.enterSoilEmission("-10");
        calculatorPage.enterPlasticWasteGenerated("-10");

        calculatorPage.selectHazardousMaterial("No");
        calculatorPage.clickCalculateBatch();

        Assert.assertTrue(
                calculatorPage.isValidationDisplayed(),
                "Validation message is not displayed for negative values"
        );
    }

    @Test
    public void TC_CALC_013_verifyDecimalValuesAccepted() throws InterruptedException {
        calculatorPage.selectWaterType("Blue Water");
        calculatorPage.selectWasteType("Anode Scrap");

        calculatorPage.enterTotalWaterUsed("10.5");
        calculatorPage.enterTotalElectricityUsed("20.5");
        calculatorPage.enterTotalWasteGenerated("30.5");
        calculatorPage.enterAirEmission("40.5");
        calculatorPage.enterNoiseEmission("50.5");
        calculatorPage.enterSoilEmission("60.5");
        calculatorPage.enterPlasticWasteGenerated("70.5");

        calculatorPage.selectHazardousMaterial("No");
        calculatorPage.clickCalculateBatch();

      Thread.sleep(2000);
        Assert.assertTrue(
                driver.getPageSource().contains("Battery CO2e calculated successfully"),

                "Success message is not displayed after valid calculation submission"
        );
    }

   @Test
public void TC_CALC_014_verifyCancelButtonFunctionality() throws Exception {

    calculatorPage.clickCancel();

    Thread.sleep(2000);

    String pageText = driver.findElement(By.tagName("body")).getText();
    String currentUrl = driver.getCurrentUrl();

    Assert.assertTrue(
            currentUrl.toLowerCase().contains("calculator")
                    || pageText.contains("Batch Calculation"),
            "Cancel button did not redirect to calculator list page. Current URL: "
                    + currentUrl
                    + " Page Text: "
                    + pageText
    );
}

    @Test
    public void TC_CALC_015_verifyBackButtonFunctionality() {
        calculatorPage.clickBack();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("calculator")
                        || driver.getPageSource().contains("Sustainability Calculator"),
                "Back button did not navigate to previous page"
        );
    }
}