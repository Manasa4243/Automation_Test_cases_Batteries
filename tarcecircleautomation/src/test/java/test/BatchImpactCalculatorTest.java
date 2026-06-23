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
        // Assert.assertTrue(
        //         driver.getPageSource().contains("Battery CO2e calculated successfully"),

        //         "Success message is not displayed after valid calculation submission"
        // );
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
    @Test
public void TC_CALC_018_verifyWaterTypeMandatoryValidation() {
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

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Water Type validation message should appear");
}

@Test
public void TC_CALC_019_verifyWasteTypeMandatoryValidation() {
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

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Waste Type validation message should appear");
}

@Test
public void TC_CALC_020_verifyTotalWaterUsedMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Total Water Used validation message should appear");
}

@Test
public void TC_CALC_021_verifyElectricityUsedMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Electricity Used validation message should appear");
}

@Test
public void TC_CALC_022_verifyWasteGeneratedMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Waste Generated validation message should appear");
}

@Test
public void TC_CALC_023_verifyAirEmissionMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Air Emission validation message should appear");
}

@Test
public void TC_CALC_024_verifyNoiseEmissionMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Noise Emission validation message should appear");
}

@Test
public void TC_CALC_025_verifySoilEmissionMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterPlasticWasteGenerated("56");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Soil Emission validation message should appear");
}

@Test
public void TC_CALC_026_verifyPlasticWasteMandatoryValidation() {
    calculatorPage.selectWaterType("Blue Water");
    calculatorPage.selectWasteType("Anode Scrap");
    calculatorPage.enterTotalWaterUsed("54");
    calculatorPage.enterTotalElectricityUsed("65");
    calculatorPage.enterTotalWasteGenerated("56");
    calculatorPage.enterAirEmission("5656");
    calculatorPage.enterNoiseEmission("567");
    calculatorPage.enterSoilEmission("67");
    calculatorPage.selectHazardousMaterial("No");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Plastic Waste validation message should appear");
}

@Test
public void TC_CALC_027_verifyWaterUsedAcceptsNumericValues() {
    calculatorPage.enterTotalWaterUsed("100");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "100", "Water Used numeric value should be accepted");
}

@Test
public void TC_CALC_028_verifyWaterUsedRejectsAlphabets() {
    calculatorPage.enterTotalWaterUsed("ABC");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "", "Water Used should reject alphabets");
}

@Test
public void TC_CALC_029_verifyWaterUsedRejectsSpecialCharacters() {
    calculatorPage.enterTotalWaterUsed("@#$");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "", "Water Used should reject special characters");
}

@Test
public void TC_CALC_030_verifyNegativeWaterUsedValue() {
    calculatorPage.fillValidCalculatorData();
    calculatorPage.enterTotalWaterUsed("-100");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Negative Water Used validation should appear");
}

@Test
public void TC_CALC_031_verifyDecimalWaterUsedValue() {
    calculatorPage.enterTotalWaterUsed("100.50");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "100.50", "Decimal Water Used should be accepted if supported");
}

@Test
public void TC_CALC_032_verifyElectricityUsedAcceptsNumericValues() {
    calculatorPage.enterTotalElectricityUsed("200");

    Assert.assertEquals(calculatorPage.getTotalElectricityUsedValue(), "200", "Electricity Used numeric value should be accepted");
}

@Test
public void TC_CALC_033_verifyElectricityUsedRejectsAlphabets() {
    calculatorPage.enterTotalElectricityUsed("ABC");

    Assert.assertEquals(calculatorPage.getTotalElectricityUsedValue(), "", "Electricity Used should reject alphabets");
}

@Test
public void TC_CALC_034_verifyWasteGeneratedAcceptsNumericValues() {
    calculatorPage.enterTotalWasteGenerated("300");

    Assert.assertEquals(calculatorPage.getTotalWasteGeneratedValue(), "300", "Waste Generated numeric value should be accepted");
}

@Test
public void TC_CALC_035_verifyAirEmissionAcceptsDecimalValues() {
    calculatorPage.enterAirEmission("40.50");

    Assert.assertEquals(calculatorPage.getAirEmissionValue(), "40.50", "Air Emission decimal value should be accepted");
}

@Test
public void TC_CALC_036_verifyNoiseEmissionRange() {
    calculatorPage.enterNoiseEmission("75");

    Assert.assertEquals(calculatorPage.getNoiseEmissionValue(), "75", "Valid Noise Emission value should be accepted");
}

@Test
public void TC_CALC_037_verifyInvalidNoiseEmissionValue() {
    calculatorPage.fillValidCalculatorData();
    calculatorPage.enterNoiseEmission("-50");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Invalid Noise Emission validation should appear");
}

@Test
public void TC_CALC_038_verifySoilEmissionAcceptsOnlyNumericValues() {
    calculatorPage.enterSoilEmission("67");

    Assert.assertEquals(calculatorPage.getSoilEmissionValue(), "67", "Soil Emission numeric value should be accepted");
}

@Test
public void TC_CALC_039_verifyPlasticWasteAcceptsOnlyNumericValues() {
    calculatorPage.enterPlasticWasteGenerated("56");

    Assert.assertEquals(calculatorPage.getPlasticWasteGeneratedValue(), "56", "Plastic Waste numeric value should be accepted");
}

@Test
public void TC_CALC_040_verifyMaximumFieldLength() {
    calculatorPage.enterTotalWaterUsed("999999");

    Assert.assertEquals(calculatorPage.getTotalWaterUsedValue(), "999999", "Maximum allowed value should be accepted");
}

@Test
public void TC_CALC_041_verifyValuesExceedingMaximumLimit() {
    calculatorPage.fillValidCalculatorData();
    calculatorPage.enterTotalWaterUsed("999999999999999999999999");

    calculatorPage.clickCalculateBatch();

    Assert.assertTrue(calculatorPage.isValidationDisplayed(), "Extremely large value validation should appear");
}
}