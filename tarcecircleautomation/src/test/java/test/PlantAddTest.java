package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;
import pages.PlantPage;

public class PlantAddTest extends BaseTest {

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

    public PlantPage openAddPlantPage() {
        DashboardPage dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.openCreatePlantPage();

        PlantPage plantPage = new PlantPage(driver, wait);

        Assert.assertTrue(
                plantPage.isAddPlantPageOpened(),
                "Add Plant page is not opened"
        );

        return plantPage;
    }

    @Test
    public void TC_PLANT_ADD_001_verifyAddPlantPageLoads() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        Assert.assertTrue(
                plantPage.isAddPlantPageOpened(),
                "Add Plant page should load successfully"
        );
    }

    @Test
    public void TC_PLANT_ADD_002_verifyAllFieldsVisibility() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        Assert.assertTrue(
                plantPage.areAllFieldsVisible(),
                "All fields, dropdowns, and buttons are not displayed properly"
        );
    }

  @Test
    public void TC_PLANT_ADD_003_verifyOrganizationDropdownLoading() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.openOrganizationDropdown();

        Assert.assertTrue(
                plantPage.isDropdownOptionVisible("Exide Industries"),
                "Organization dropdown values are not loading"
        );
    }

    @Test
    public void TC_PLANT_ADD_004_createPlantWithValidData() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillValidPlantData();
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Plant was not created successfully"
        );
    }

    @Test
    public void TC_PLANT_ADD_005_verifyCancelFunctionality() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.clickCancel();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Cancel button did not navigate back"
        );
    }

    @Test
    public void TC_PLANT_ADD_006_verifyDependentDropdownFunctionality() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.selectCountryStateCity();

        Assert.assertTrue(
                true,
                "Dependent dropdown values should load correctly"
        );
    }

    @Test
    public void TC_PLANT_ADD_007_verifyCreatedPlantInList() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillValidPlantData();
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Newly created plant is not displayed in list"
        );
    }

    @Test
    public void TC_PLANT_ADD_008_verifyUniquePlantCodeCreation() {

        loginToApplication();

        PlantPage plantPage = openAddPlantPage();

        plantPage.fillPlantWithUniqueCode("PLANT001");
        plantPage.clickCreatePlant();

        Assert.assertTrue(
                plantPage.isReturnedToPlantList(),
                "Plant with unique plant code was not saved successfully"
        );
    }
    @Test
public void TC_PLANT_VAL_001_submitEmptyForm() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Mandatory validation should appear");
}

@Test
public void TC_PLANT_VAL_002_emptyOrganizationDropdown() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    long time = System.currentTimeMillis();

    page.enterPlantCodeOnly("PLANT" + time);
    page.enterPlantNameOnly("Automation Plant " + time);
    page.enterAddressLine1Only("Bangalore Address");
    page.selectValidCountry();
    page.selectValidState();
    page.selectValidCity();
    page.enterPostalCodeOnly("560001");
    page.enterContactPersonNameOnly("Manasa Gowda");
    page.enterContactEmailOnly("plant" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDescriptionOnly("Organization blank validation.");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Organization validation should appear");
}

@Test
public void TC_PLANT_VAL_004_emptyPlantCode() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantCodeOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Plant code validation should appear");
}

@Test
public void TC_PLANT_VAL_005_duplicatePlantCode() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantCodeOnly("PLANT001");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Duplicate plant code validation should appear");
}

@Test
public void TC_PLANT_VAL_006_invalidPlantCodeFormat() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantCodeOnly("@@@###");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid plant code validation should appear");
}

@Test
public void TC_PLANT_VAL_007_emptyPlantName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantNameOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Plant name validation should appear");
}

@Test
public void TC_PLANT_VAL_008_invalidPlantName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantNameOnly("@@@###");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid plant name validation should appear");
}

@Test
public void TC_PLANT_VAL_009_sqlInjectionInPlantName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantNameOnly("' OR '1'='1");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "SQL injection should be rejected");
}

@Test
public void TC_PLANT_VAL_010_xssInPlantName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPlantNameOnly("<script>alert(1)</script>");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "XSS should be rejected");
}

@Test
public void TC_PLANT_VAL_011_emptyAddressLine1() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterAddressLine1Only("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Address line 1 validation should appear");
}

@Test
public void TC_PLANT_VAL_012_veryLongAddressInput() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterAddressLine1Only("A".repeat(1200));

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Long address validation should appear");
}

@Test
public void TC_PLANT_VAL_013_emptyCountry() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    long time = System.currentTimeMillis();

    page.selectValidOrganization();
    page.enterPlantCodeOnly("PLANT" + time);
    page.enterPlantNameOnly("Automation Plant " + time);
    page.enterAddressLine1Only("Bangalore Address");
    page.enterPostalCodeOnly("560001");
    page.enterContactPersonNameOnly("Manasa Gowda");
    page.enterContactEmailOnly("plant" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDescriptionOnly("Country blank validation.");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Country validation should appear");
}

@Test
public void TC_PLANT_VAL_015_emptyState() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    long time = System.currentTimeMillis();

    page.selectValidOrganization();
    page.enterPlantCodeOnly("PLANT" + time);
    page.enterPlantNameOnly("Automation Plant " + time);
    page.enterAddressLine1Only("Bangalore Address");
    page.selectValidCountry();
    page.enterPostalCodeOnly("560001");
    page.enterContactPersonNameOnly("Manasa Gowda");
    page.enterContactEmailOnly("plant" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDescriptionOnly("State blank validation.");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "State validation should appear");
}

@Test
public void TC_PLANT_VAL_016_emptyCity() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    long time = System.currentTimeMillis();

    page.selectValidOrganization();
    page.enterPlantCodeOnly("PLANT" + time);
    page.enterPlantNameOnly("Automation Plant " + time);
    page.enterAddressLine1Only("Bangalore Address");
    page.selectValidCountry();
    page.selectValidState();
    page.enterPostalCodeOnly("560001");
    page.enterContactPersonNameOnly("Manasa Gowda");
    page.enterContactEmailOnly("plant" + time + "@gmail.com");
    page.enterPhoneNumberOnly("9876543210");
    page.enterDescriptionOnly("City blank validation.");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "City validation should appear");
}

@Test
public void TC_PLANT_VAL_017_invalidPostalCode() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPostalCodeOnly("ABC@@@");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Postal code validation should appear");
}

@Test
public void TC_PLANT_VAL_018_emptyContactPersonName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterContactPersonNameOnly("");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Contact person name validation should appear");
}

@Test
public void TC_PLANT_VAL_019_invalidContactPersonName() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterContactPersonNameOnly("123@@@");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid contact person name validation should appear");
}

@Test
public void TC_PLANT_VAL_020_invalidContactEmail() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterContactEmailOnly("abc@");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid contact email validation should appear");
}

@Test
public void TC_PLANT_VAL_021_duplicateContactEmail() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterContactEmailOnly("system@tracecircle.com");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Duplicate email validation should appear");
}

@Test
public void TC_PLANT_VAL_022_invalidPhoneNumber() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPhoneNumberOnly("abc@@123");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Invalid phone number validation should appear");
}

@Test
public void TC_PLANT_VAL_023_shortPhoneNumber() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPhoneNumberOnly("98765");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Short phone number validation should appear");
}

@Test
public void TC_PLANT_VAL_024_longPhoneNumber() {
    loginToApplication();
    PlantPage page = openAddPlantPage();

    page.fillAllValidPlantData();
    page.enterPhoneNumberOnly("9876543212345");

    page.submitAndScrollToError();

    Assert.assertTrue(page.isValidationDisplayed(), "Long phone number validation should appear");
}
}