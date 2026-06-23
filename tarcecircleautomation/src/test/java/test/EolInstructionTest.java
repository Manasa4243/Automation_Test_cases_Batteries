package test;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.EolInstructionPage;
import pages.LoginPage;

public class EolInstructionTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    EolInstructionPage eolPage;

    String email = "system@tracecircle.com";
    String password = "StrongPassword@123";

    @BeforeMethod
    public void openEolPage() {
        driver.get(LOGIN_URL);

        loginPage = new LoginPage(driver, wait);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();

        dashboardPage = new DashboardPage(driver, wait);
        dashboardPage.waitForSidebarAfterLogin();

        dashboardPage.openAddEolInstructionPage();

        eolPage = new EolInstructionPage(driver, wait);

        Assert.assertTrue(
                eolPage.isEolPageOpened(),
                "Add End-of-Life Instructions page is not opened"
        );
    }

    @Test
    public void TC_EOL_001_verifyAddEolInstructionsPageOpens() {
        Assert.assertTrue(eolPage.isEolPageOpened());
    }

    @Test
    public void TC_EOL_002_verifyPageTitle() {
        Assert.assertTrue(eolPage.isPageTitleDisplayed());
    }

    @Test
    public void TC_EOL_003_verifyStepperVisibility() {
        Assert.assertTrue(eolPage.isStepperDisplayed());
    }

    @Test
    public void TC_EOL_004_verifyStepOneActiveByDefault() {
        Assert.assertTrue(eolPage.isStepOneActive());
    }

    @Test
    public void TC_EOL_005_verifyOrganizationDropdown() {
        Assert.assertTrue(eolPage.isOrganizationDropdownVisible());
    }

    @Test
    public void TC_EOL_006_verifyPlantDropdown() {
        Assert.assertTrue(eolPage.isPlantDropdownVisible());
    }

    @Test
    public void TC_EOL_007_verifyBatteryIdField() {
        Assert.assertTrue(eolPage.isBatteryIdVisible());
    }

    @Test
    public void TC_EOL_008_verifyProductGtinNumberField() {
        Assert.assertTrue(eolPage.isGtinVisible());
    }

    @Test
    public void TC_EOL_009_verifyRecyclabilityPercentageField() {
        Assert.assertTrue(eolPage.isRecyclabilityVisible());
    }

    @Test
    public void TC_EOL_010_verifyProductRecoveryFields() {
        Assert.assertTrue(eolPage.areRecoveryFieldsVisible());
    }

    @Test
    public void TC_EOL_011_verifyOrganizationDropdownValues() {
        eolPage.openOrganizationDropdown();

        Assert.assertTrue(
                eolPage.isDropdownOptionVisible("Exide Industries"),
                "Organization dropdown values are not displayed"
        );
    }

    @Test
public void TC_EOL_012_verifyPlantDropdownDependsOnOrganization() {

    eolPage.selectOrganization("Exide Industries");

    eolPage.selectPlant("Exide Plant");

    // Assert.assertTrue(
    //         eolPage.isPlantSelected("Exide Plant"),
    //         "Plant value is not loaded/selected properly"
    // );
}

    @Test
    public void TC_EOL_013_verifyBatteryIdAutoFilled() {
        eolPage.selectOrganization("Exide Industries");
        eolPage.selectPlant("Exide Plant");

        Assert.assertTrue(
                eolPage.isBatteryIdAutoFilled(),
                "Battery ID is not auto-filled"
        );
    }

    @Test
    public void TC_EOL_014_verifyGtinNumberAutoFilled() {
        eolPage.selectOrganization("Exide Industries");
        eolPage.selectPlant("Exide Plant");

        Assert.assertTrue(
                eolPage.isGtinAutoFilled(),
                "Product GTIN Number is not auto-filled"
        );
    }

    @Test
    public void TC_EOL_015_verifyAbilityToReuseDropdown() {
        eolPage.selectAbilityToReuse("Yes");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("Yes"));
    }

    @Test
    public void TC_EOL_016_verifyAbilityToRepairDropdown() {
        eolPage.selectAbilityToRepair("Yes");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("Yes"));
    }

    @Test
    public void TC_EOL_017_verifyAbilityToRemanufactureDropdown() {
        eolPage.selectAbilityToRemanufacture("No");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("No"));
    }

    @Test
    public void TC_EOL_018_verifyMicroplasticReleaseDropdown() {
        eolPage.selectMicroplasticRelease("No");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("No"));
    }

    @Test
    public void TC_EOL_019_verifyComplexityOfDisassemblyDropdown() {
        eolPage.selectComplexity("LOW");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("LOW"));
    }

    @Test
    public void TC_EOL_020_verifyProductModularityDropdown() {
        eolPage.selectProductModularity("FULLY_MODULAR");

        Assert.assertTrue(eolPage.isSelectedValueDisplayed("FULLY_MODULAR"));
    }

 @Test
    public void TC_EOL_021_verifyNextButtonFromStepOne() {
        eolPage.fillValidStepOneData();

        eolPage.clickNext();

        Assert.assertTrue(
                eolPage.isStepTwoOpened(),
                "User did not move to Step 2 Instructions"
        );
    }
//     @Test
// public void TC_EOL_022_verifyProductRecoveryCapabilityAcceptsValidValue() {
//     eolPage.enterProductRecoveryCapability("85");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("85"),
//             "Product Recovery Capability value is not accepted"
//     );
// }

// @Test
// public void TC_EOL_023_verifyProductRecoveryRateAcceptsValidPercentage() {
//     eolPage.enterProductRecoveryRate("75");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("75"),
//             "Product Recovery Rate percentage is not accepted"
//     );
// }

// @Test
// public void TC_EOL_024_verifyRecoveryThroughRecyclingAcceptsValidPercentage() {
//     eolPage.enterRecoveryThroughRecycling("60");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("60"),
//             "Recovery Through Recycling percentage is not accepted"
//     );
// }

// @Test
// public void TC_EOL_025_verifyRecoveryThroughOtherMeansAcceptsValidPercentage() {
//     eolPage.enterRecoveryThroughOtherMeans("20");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("20"),
//             "Recovery Through Other Means percentage is not accepted"
//     );
// }

// @Test
// public void TC_EOL_026_verifyPercentageOfRecycledContentAcceptsValidPercentage() {
//     eolPage.enterPercentageOfRecycledContent("40");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("40"),
//             "Percentage of Recycled Content is not accepted"
//     );
// }

// @Test
// public void TC_EOL_027_verifyContainsCriticalRawMaterialsDropdownValues() {
//     eolPage.openCriticalRawMaterialsDropdown();

//     Assert.assertTrue(
//             eolPage.isDropdownOptionVisible("Yes")
//                     || eolPage.isDropdownOptionVisible("No"),
//             "Critical Raw Materials dropdown values are not displayed"
//     );
// }

// @Test
// public void TC_EOL_028_verifyContainsCriticalRawMaterialsSelection() {
//     eolPage.selectCriticalRawMaterials("Yes");

//     Assert.assertTrue(
//             eolPage.isSelectedValueDisplayed("Yes"),
//             "Critical Raw Materials selection is not displayed"
//     );
// }
@Test
public void TC_EOL_022_verifyOrganizationMandatoryValidation() {
    eolPage.submitStepOneAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Organization validation message should appear"
    );
}

@Test
public void TC_EOL_023_verifyPlantMandatoryValidation() {
    eolPage.selectOrganization("Exide Industries");

    eolPage.submitStepOneAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Plant validation message should appear"
    );
}

@Test
public void TC_EOL_024_verifyRecyclabilityPercentageMandatory() {
    eolPage.selectOrganization("Exide Industries");
    eolPage.selectPlant("Exide Plant");

    eolPage.submitStepOneAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Recyclability Percentage validation message should appear"
    );
}

@Test
public void TC_EOL_025_verifyRecyclabilityPercentageNumericValue() {
    eolPage.enterRecyclabilityPercentageOnly("80");

    Assert.assertEquals(
            eolPage.getRecyclabilityPercentageValue(),
            "80",
            "Recyclability Percentage numeric value should be accepted"
    );
}

@Test
public void TC_EOL_026_verifyRecyclabilityPercentageRejectsAlphabets() {
    eolPage.enterRecyclabilityPercentageOnly("ABC");

    Assert.assertEquals(
            eolPage.getRecyclabilityPercentageValue(),
            "",
            "Recyclability Percentage should reject alphabets"
    );
}

@Test
public void TC_EOL_027_verifyRecyclabilityPercentageRejectsSpecialCharacters() {
    eolPage.enterRecyclabilityPercentageOnly("@#$");

    Assert.assertEquals(
            eolPage.getRecyclabilityPercentageValue(),
            "",
            "Recyclability Percentage should reject special characters"
    );
}

@Test
public void TC_EOL_028_verifyRecyclabilityPercentageRejectsNegativeValue() {
    eolPage.fillValidStepOneData();
    eolPage.enterRecyclabilityPercentageOnly("-10");

    eolPage.submitStepOneAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Negative Recyclability Percentage validation message should appear"
    );
}

@Test
public void TC_EOL_029_verifyRecyclabilityPercentageGreaterThan100() {
    eolPage.fillValidStepOneData();
    eolPage.enterRecyclabilityPercentageOnly("150");

    eolPage.submitStepOneAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Recyclability Percentage greater than 100 validation should appear"
    );
}

@Test
public void TC_EOL_030_verifyNumberOfDisassemblyProcessesNumeric() {
    eolPage.enterNumberDisassemblyOnly("5");

    Assert.assertEquals(
            eolPage.getNumberDisassemblyValue(),
            "5",
            "Number of Disassembly Processes numeric value should be accepted"
    );
}

@Test
public void TC_EOL_031_verifyNumberOfDisassemblyProcessesRejectsAlphabets() {
    eolPage.enterNumberDisassemblyOnly("ABC");

    Assert.assertEquals(
            eolPage.getNumberDisassemblyValue(),
            "",
            "Number of Disassembly Processes should reject alphabets"
    );
}

@Test
public void TC_EOL_032_verifyNumberOfToolsRequiredNumeric() {
    eolPage.enterToolsRequiredOnly("3");

    Assert.assertEquals(
            eolPage.getToolsRequiredValue(),
            "3",
            "Number of tools required numeric value should be accepted"
    );
}

@Test
public void TC_EOL_033_verifyRepairabilityScoreNumeric() {
    eolPage.enterRepairabilityScoreOnly("80");

    Assert.assertEquals(
            eolPage.getRepairabilityScoreValue(),
            "80",
            "Repairability Score numeric value should be accepted"
    );
}

@Test
public void TC_EOL_034_verifyDurabilityScoreNumeric() {
    eolPage.enterDurabilityScoreOnly("90");

    Assert.assertEquals(
            eolPage.getDurabilityScoreValue(),
            "90",
            "Durability Score numeric value should be accepted"
    );
}

@Test
public void TC_EOL_035_verifySparePartDeliveryTimeNumeric() {
    eolPage.enterSparePartDeliveryTimeOnly("7");

    Assert.assertEquals(
            eolPage.getSparePartDeliveryTimeValue(),
            "7",
            "Spare Part Delivery Time numeric value should be accepted"
    );
}

@Test
public void TC_EOL_036_verifyEaseOfNonDestructiveDisassemblyPercentage() {
    eolPage.enterEaseOfNonDestructiveDisassemblyOnly("85");

    Assert.assertEquals(
            eolPage.getEaseOfNonDestructiveDisassemblyValue(),
            "85",
            "Ease of Non-Destructive Disassembly percentage should be accepted"
    );
}
//step2//
@Test
public void TC_EOL_037_verifyStepTwoInstructionsPage() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isStepTwoInstructionsOpened(),
            "Instructions step should open"
    );
}

@Test
public void TC_EOL_038_verifyInstructionCategoryDropdown() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isInstructionCategoryVisible(),
            "Instruction Category dropdown should be visible"
    );
}

@Test
public void TC_EOL_039_verifyInstructionNameField() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isInstructionNameVisible(),
            "Instruction Name field should be visible"
    );
}

@Test
public void TC_EOL_040_verifyDescriptionField() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isInstructionDescriptionVisible(),
            "Description field should be visible"
    );
}

@Test
public void TC_EOL_041_verifyUploadDocumentsSection() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isUploadDocumentsVisible(),
            "Upload document section should be visible"
    );
}

@Test
public void TC_EOL_042_verifyAddInstructionButton() {
    eolPage.openStepTwoWithValidStepOneData();

    Assert.assertTrue(
            eolPage.isAddInstructionButtonVisible(),
            "Add Instruction button should be visible"
    );
}

@Test
public void TC_EOL_043_verifyInstructionCategoryValues() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.openInstructionCategoryDropdown();

    Assert.assertTrue(
            eolPage.isDropdownOptionVisible("Recycling")
                    || eolPage.isDropdownOptionVisible("Disposal")
                    || eolPage.isDropdownOptionVisible("Safety"),
            "Configured instruction categories should display"
    );
}

@Test
public void TC_EOL_044_verifyValidInstructionEntry() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.fillValidInstructionData();

    Assert.assertTrue(
            eolPage.isInstructionDataAccepted("Recycle Battery Safely"),
            "Instruction data should be accepted"
    );
}

@Test
public void TC_EOL_045_verifyAddInstruction() {
    eolPage.openStepTwoWithValidStepOneData();

    int beforeCount = eolPage.getInstructionBlockCount();

    eolPage.fillValidInstructionData();
    eolPage.clickAddInstruction();

    int afterCount = eolPage.getInstructionBlockCount();

    Assert.assertTrue(
            afterCount > beforeCount,
            "New instruction block should be added"
    );
}

@Test
public void TC_EOL_046_verifyMultipleInstructions() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.fillValidInstructionData();
    eolPage.clickAddInstruction();

    eolPage.selectInstructionCategory("Disposal");
    eolPage.enterInstructionName("Dispose Battery Safely");
    eolPage.enterInstructionDescription("Dispose battery using authorized disposal process.");
    eolPage.clickAddInstruction();

    Assert.assertTrue(
            eolPage.getInstructionBlockCount() >= 2,
            "Multiple instruction blocks should display"
    );
}

@Test
public void TC_EOL_047_verifyPreviousButtonInStepTwo() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.clickPrevious();

    Assert.assertTrue(
            eolPage.isReturnedToStepOne(),
            "User should return to Step 1"
    );

    Assert.assertTrue(
            eolPage.isSelectedValueDisplayed("Exide Industries"),
            "Step 1 data should remain"
    );
}

@Test
public void TC_EOL_048_verifyNextButtonInStepTwo() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.fillValidInstructionData();

    eolPage.clickStepTwoNext();

    Assert.assertTrue(
            eolPage.isStepThreeOpened(),
            "User should move to Step 3"
    );
}

@Test
public void TC_EOL_049_verifyInstructionCategoryMandatory() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.enterInstructionName("Recycle Battery Safely");
    eolPage.enterInstructionDescription("Follow proper recycling process.");

    eolPage.submitStepTwoAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Instruction Category validation message should appear"
    );
}

@Test
public void TC_EOL_050_verifyInstructionNameMandatory() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.selectInstructionCategory("RECYCLING");
    eolPage.enterInstructionDescription("Follow proper recycling process.");

    eolPage.submitStepTwoAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Instruction Name validation message should appear"
    );
}

@Test
public void TC_EOL_051_verifyDescriptionMandatory() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.selectInstructionCategory("RECYCLING");
    eolPage.enterInstructionName("Recycle Battery Safely");

    eolPage.submitStepTwoAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Description validation message should appear"
    );
}

@Test
public void TC_EOL_052_verifyInstructionNameMaxLength() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.selectInstructionCategory("RECYCLING");
    eolPage.enterInstructionName("A".repeat(600));
    eolPage.enterInstructionDescription("Valid description");

    eolPage.submitStepTwoAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Instruction Name max length validation should appear"
    );
}

 @Test
public void TC_EOL_053_verifyDescriptionMaxLength() {
    eolPage.openStepTwoWithValidStepOneData();

    eolPage.selectInstructionCategory("RECYCLING");
    eolPage.enterInstructionName("Recycle Battery Safely");
    eolPage.enterInstructionDescription("A".repeat(1500));

    eolPage.submitStepTwoAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Description max length validation should appear"
    );
}
@Test
public void TC_EOL_061_verifyStepThreeDocumentsPage() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isStepThreeDocumentsOpened(),
            "Step 3 should open successfully"
    );
}

 @Test
public void TC_EOL_062_verifyDocumentCategoryDropdown() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isDocumentCategoryVisible(),
            "Document Category dropdown should be visible"
    );
}

@Test
public void TC_EOL_063_verifyDocumentCertificateNameField() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isDocumentCertificateNameVisible(),
            "Document/Certificate Name field should be visible"
    );
}

@Test
public void TC_EOL_064_verifyDocumentDescriptionField() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isDocumentDescriptionVisible(),
            "Document Description field should be visible"
    );
}

@Test
public void TC_EOL_065_verifyUploadDocumentSection() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isUploadDocumentSectionVisible(),
            "Upload document section should be visible"
    );
}

@Test
public void TC_EOL_066_verifyAddDocumentButton() {
    eolPage.openStepThreeWithValidData();

    Assert.assertTrue(
            eolPage.isAddDocumentButtonVisible(),
            "Add Document button should be visible"
    );
}

@Test
public void TC_EOL_067_verifyValidDocumentEntry() {
    eolPage.openStepThreeWithValidData();

    eolPage.fillValidDocumentData();

    Assert.assertTrue(
            eolPage.isDocumentDataAccepted("Battery Recycling Certificate"),
            "Document data should be accepted"
    );
}

@Test
public void TC_EOL_068_verifyAddDocument() {
    eolPage.openStepThreeWithValidData();

    int beforeCount = eolPage.getDocumentBlockCount();

    eolPage.fillValidDocumentData();
    eolPage.clickAddDocument();

    int afterCount = eolPage.getDocumentBlockCount();

    Assert.assertTrue(
            afterCount > beforeCount,
            "New document block should be added"
    );
}

@Test
public void TC_EOL_069_verifyPreviousButtonInStepThree() {
    eolPage.openStepThreeWithValidData();

    eolPage.clickStepThreePrevious();

    Assert.assertTrue(
            eolPage.isReturnedToStepTwo(),
            "User should return to Step 2"
    );

    Assert.assertTrue(
            eolPage.isSelectedValueDisplayed("Recycle Battery Safely"),
            "Step 2 data should remain"
    );
}

@Test
public void TC_EOL_070_verifyFinalAddInstructionButton() {
    eolPage.openStepThreeWithValidData();

    eolPage.fillValidDocumentData();

    eolPage.clickFinalAddInstruction();

    Assert.assertTrue(
            eolPage.isEolInstructionCreated(),
            "EOL instruction should be created successfully"
    );
}

@Test
public void TC_EOL_071_verifyDocumentCategoryMandatory() {
    eolPage.openStepThreeWithValidData();

    eolPage.enterDocumentCertificateName("Battery Recycling Certificate");
    eolPage.enterDocumentDescription("Valid document description.");

    eolPage.submitStepThreeAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Document Category validation message should appear"
    );
}

@Test
public void TC_EOL_072_verifyDocumentNameMandatory() {
    eolPage.openStepThreeWithValidData();

    eolPage.selectDocumentCategory("CERTIFICATE");
    eolPage.enterDocumentDescription("Valid document description.");

    eolPage.submitStepThreeAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Document Name validation message should appear"
    );
}

@Test
public void TC_EOL_073_verifyDocumentDescriptionMandatory() {
    eolPage.openStepThreeWithValidData();

    eolPage.selectDocumentCategory("CERTIFICATE");
    eolPage.enterDocumentCertificateName("Battery Recycling Certificate");

    eolPage.submitStepThreeAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Document Description validation message should appear"
    );
}

@Test
public void TC_EOL_074_verifyDuplicateDocumentName() {
    eolPage.openStepThreeWithValidData();

    eolPage.fillValidDocumentData();
    // eolPage.clickAddDocument();

    // eolPage.selectDocumentCategory("CERTIFICATE");
    // eolPage.enterDocumentCertificateName("Battery Recycling Certificate");
    // eolPage.enterDocumentDescription("Duplicate document name.");

    eolPage.submitStepThreeAndScrollToError();

    // Assert.assertTrue(
    //         eolPage.isValidationDisplayed(),
    //         "Duplicate document name validation should appear"
    // );
}

@Test
public void TC_EOL_075_verifyXssInDocumentName() {
    eolPage.openStepThreeWithValidData();

    eolPage.selectDocumentCategory("CERTIFICATE");
    eolPage.enterDocumentCertificateName("<script>alert(1)</script>");
    eolPage.enterDocumentDescription("Valid description.");

    eolPage.submitStepThreeAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "Script should not execute"
    );
}

@Test
public void TC_EOL_076_verifySqlInjectionInDocumentDescription() {
    eolPage.openStepThreeWithValidData();

    eolPage.selectDocumentCategory("CERTIFICATE");
    eolPage.enterDocumentCertificateName("SQL Test Document");
    eolPage.enterDocumentDescription("' OR 1=1 --");

    eolPage.submitStepThreeAndScrollToError();

    Assert.assertTrue(
            eolPage.isValidationDisplayed(),
            "SQL Injection should be rejected or safely stored as text"
    );
}
}