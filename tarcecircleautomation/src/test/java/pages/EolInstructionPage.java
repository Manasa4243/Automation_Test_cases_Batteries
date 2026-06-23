package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EolInstructionPage {

    WebDriver driver;
    WebDriverWait wait;

    public EolInstructionPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle = By.xpath("//h1[contains(normalize-space(),'Add End-of-Life Instructions')]");

    private By basicDetailsStep = By.xpath("//*[contains(normalize-space(),'Basic Details')]");
    private By instructionsStep = By.xpath("//*[contains(normalize-space(),'Instructions')]");
    private By documentsStep = By.xpath("//*[contains(normalize-space(),'Documents & Certificates')]");

    private By organizationDropdown = By.xpath("//label[normalize-space()='Organization']/following::button[1]");
    private By plantDropdown = By.xpath("//label[normalize-space()='Plant']/following::button[1]");

    private By batteryId = By.xpath("//label[normalize-space()='Battery ID']/following::input[1]");
    private By gtinNumber = By.xpath("//label[contains(normalize-space(),'GTIN')]/following::input[1]");
    private By recyclabilityPercentage = By.xpath("//input[@placeholder='Enter Recyclability Percentage']");

    private By abilityReuse = By.xpath("//label[contains(normalize-space(),'Ability to Reuse')]/following::button[1]");
    private By abilityRepair = By.xpath("//label[contains(normalize-space(),'Ability to Repair')]/following::button[1]");
    private By abilityRemanufacture = By.xpath("//label[contains(normalize-space(),'Ability to Remanufacture')]/following::button[1]");
    private By microplasticRelease = By.xpath("//label[normalize-space()='Microplastic release']/following::button[1]");
    private By microplasticReleaseDuringEol = By.xpath("//label[contains(normalize-space(),'Microplastic release during end-of-life')]/following::button[1]");
    private By easilyRecyclableMaterialsUsed = By.xpath("//label[contains(normalize-space(),'Easily recyclable materials used')]/following::button[1]");

    private By numberDisassembly = By.xpath("//input[@placeholder='Enter Number of Disassembly Process']");
    private By complexityDisassembly = By.xpath("//label[contains(normalize-space(),'Complexity of Disassembly Processes')]/following::button[1]");

    private By refurbishedGuaranteeAvailability = By.xpath("//label[contains(normalize-space(),'Refurbished Product Guarantee Availability')]/following::button[1]");
    private By toolsRequired = By.xpath("//input[@placeholder='Enter Number of tools required']");
    private By repairabilityScore = By.xpath("//input[@placeholder='Enter Repairability Score']");
    private By durabilityScore = By.xpath("//input[@placeholder='Enter Durability Score']");

    private By realUseInformationAvailable = By.xpath("//label[contains(normalize-space(),'Real Use Information Available')]/following::button[1]");
    private By resistanceToStress = By.xpath("//label[contains(normalize-space(),'Resistance to Stress')]/following::button[1]");
    private By resistanceToAgingMechanisms = By.xpath("//label[contains(normalize-space(),'Resistance to Aging Mechanisms')]/following::button[1]");

    private By sparePartCharacteristics = By.xpath("//input[@placeholder='Enter Spare Part Characteristics']");
    private By sparePartAvailability = By.xpath("//label[contains(normalize-space(),'Spare Part Availability')]/following::button[1]");
    private By sparePartDeliveryTime = By.xpath("//input[@placeholder='Enter Spare Part Delivery Time (days)']");
    private By sparePartAffordability = By.xpath("//label[contains(normalize-space(),'Spare Part Affordability')]/following::button[1]");

    private By productModularity = By.xpath("//label[contains(normalize-space(),'Product Modularity')]/following::button[1]");
    private By compatibilityWithCommonTools = By.xpath("//label[contains(normalize-space(),'Compatibility with Common Tools')]/following::button[1]");
    private By compatibilityWithCommonSpareParts = By.xpath("//label[contains(normalize-space(),'Compatibility with Common Spare Parts')]/following::button[1]");
    private By repairProcessComplexity = By.xpath("//label[contains(normalize-space(),'Repair Process Complexity')]/following::button[1]");
    private By specializedToolsRequired = By.xpath("//label[contains(normalize-space(),'Specialized Tools Required')]/following::button[1]");
    private By nonDestructiveAccessToComponents = By.xpath("//label[contains(normalize-space(),'Non-destructive Access to Components')]/following::button[1]");
    private By easeOfNonDestructiveDisassembly = By.xpath("//input[contains(@placeholder,'Enter Ease of Non-Destructive Disassembly')]");

    private By nextBtn = By.xpath("//button[normalize-space()='Next']");
    private By cancelBtn = By.xpath("//button[normalize-space()='Cancel']");

    private WebElement scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        return element;
    }

    private void enterText(By locator, String value) {
        WebElement element = scrollToElement(locator);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(value);
    }

    private void jsClick(By locator) {
        WebElement element = scrollToElement(locator);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    private void selectDropdownValue(By dropdown, String value) {
        try {
            WebElement dropdownElement = scrollToElement(dropdown);

            wait.until(ExpectedConditions.elementToBeClickable(dropdownElement));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dropdownElement);

            Thread.sleep(800);

            By optionLocator = By.xpath(
                    "//*[(@role='option' or @role='menuitem' or @cmdk-item='' or contains(@class,'SelectItem')) " +
                    "and normalize-space(.)='" + value + "']"
            );

            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    option
            );

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

            Thread.sleep(500);

        } catch (Exception e) {
            try {
                By fallbackOption = By.xpath("//*[normalize-space(.)='" + value + "']");

                WebElement option = wait.until(ExpectedConditions.elementToBeClickable(fallbackOption));

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);

                Thread.sleep(500);

            } catch (Exception ex) {
                throw new RuntimeException("Dropdown value not selected: " + value, ex);
            }
        }
    }

    public boolean isEolPageOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageTitleDisplayed() {
        return isEolPageOpened();
    }

    public boolean isStepperDisplayed() {
        try {
            return driver.findElement(basicDetailsStep).isDisplayed()
                    && driver.findElement(instructionsStep).isDisplayed()
                    && driver.findElement(documentsStep).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isStepOneActive() {
        return driver.getPageSource().contains("Basic Details");
    }

    public boolean isOrganizationDropdownVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(organizationDropdown)).isDisplayed();
    }

    public boolean isPlantDropdownVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(plantDropdown)).isDisplayed();
    }

    public boolean isBatteryIdVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(batteryId)).isDisplayed();
    }

    public boolean isGtinVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(gtinNumber)).isDisplayed();
    }

    public boolean isRecyclabilityVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(recyclabilityPercentage)).isDisplayed();
    }

    public boolean areRecoveryFieldsVisible() {
        try {
            return driver.findElement(abilityReuse).isDisplayed()
                    && driver.findElement(abilityRepair).isDisplayed()
                    && driver.findElement(abilityRemanufacture).isDisplayed()
                    && driver.findElement(microplasticRelease).isDisplayed()
                    && driver.findElement(microplasticReleaseDuringEol).isDisplayed()
                    && driver.findElement(easilyRecyclableMaterialsUsed).isDisplayed()
                    && driver.findElement(numberDisassembly).isDisplayed()
                    && driver.findElement(complexityDisassembly).isDisplayed()
                    && driver.findElement(toolsRequired).isDisplayed()
                    && driver.findElement(repairabilityScore).isDisplayed()
                    && driver.findElement(durabilityScore).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void openOrganizationDropdown() {
        jsClick(organizationDropdown);
    }

    public void openPlantDropdown() {
        jsClick(plantDropdown);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectOrganization(String orgName) {
        selectDropdownValue(organizationDropdown, orgName);
    }

    public void selectPlant(String plantName) {
        selectDropdownValue(plantDropdown, plantName);
    }

    public boolean isPlantSelected(String plantName) {
        try {
            WebElement plant = wait.until(ExpectedConditions.visibilityOfElementLocated(plantDropdown));
            return plant.getText().contains(plantName);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantDropdownEnabled() {
        try {
            WebElement plant = wait.until(ExpectedConditions.presenceOfElementLocated(plantDropdown));
            return plant.isDisplayed() && plant.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isBatteryIdAutoFilled() {
        return !driver.findElement(batteryId).getAttribute("value").isEmpty();
    }

    public boolean isGtinAutoFilled() {
        return !driver.findElement(gtinNumber).getAttribute("value").isEmpty();
    }

    public void selectAbilityToReuse(String value) {
        selectDropdownValue(abilityReuse, value);
    }

    public void selectAbilityToRepair(String value) {
        selectDropdownValue(abilityRepair, value);
    }

    public void selectAbilityToRemanufacture(String value) {
        selectDropdownValue(abilityRemanufacture, value);
    }

    public void selectMicroplasticRelease(String value) {
        selectDropdownValue(microplasticRelease, value);
    }

    public void selectMicroplasticReleaseDuringEol(String value) {
        selectDropdownValue(microplasticReleaseDuringEol, value);
    }

    public void selectEasilyRecyclableMaterialsUsed(String value) {
        selectDropdownValue(easilyRecyclableMaterialsUsed, value);
    }

    public void selectComplexity(String value) {
        selectDropdownValue(complexityDisassembly, value);
    }

    public void selectRefurbishedGuaranteeAvailability(String value) {
        selectDropdownValue(refurbishedGuaranteeAvailability, value);
    }

    public void selectRealUseInformationAvailable(String value) {
        selectDropdownValue(realUseInformationAvailable, value);
    }

    public void selectResistanceToStress(String value) {
        selectDropdownValue(resistanceToStress, value);
    }

    public void selectResistanceToAgingMechanisms(String value) {
        selectDropdownValue(resistanceToAgingMechanisms, value);
    }

    public void selectSparePartAvailability(String value) {
        selectDropdownValue(sparePartAvailability, value);
    }

    public void selectSparePartAffordability(String value) {
        selectDropdownValue(sparePartAffordability, value);
    }

    public void selectProductModularity(String value) {
        selectDropdownValue(productModularity, value);
    }

    public void selectCompatibilityWithCommonTools(String value) {
        selectDropdownValue(compatibilityWithCommonTools, value);
    }

    public void selectCompatibilityWithCommonSpareParts(String value) {
        selectDropdownValue(compatibilityWithCommonSpareParts, value);
    }

    public void selectRepairProcessComplexity(String value) {
        selectDropdownValue(repairProcessComplexity, value);
    }

    public void selectSpecializedToolsRequired(String value) {
        selectDropdownValue(specializedToolsRequired, value);
    }

    public void selectNonDestructiveAccessToComponents(String value) {
        selectDropdownValue(nonDestructiveAccessToComponents, value);
    }

    public boolean isSelectedValueDisplayed(String value) {
        return driver.getPageSource().contains(value);
    }

    public void fillValidStepOneData() {
        selectOrganization("Exide Industries");
        selectPlant("Exide Plant");

        enterText(recyclabilityPercentage, "80");

        selectAbilityToReuse("Yes");
        selectAbilityToRepair("Yes");
        selectAbilityToRemanufacture("Yes");
        selectMicroplasticRelease("Yes");
        selectMicroplasticReleaseDuringEol("Yes");
        selectEasilyRecyclableMaterialsUsed("Yes");

        enterText(numberDisassembly, "5");
        selectComplexity("LOW");

        selectRefurbishedGuaranteeAvailability("Yes");

        enterText(toolsRequired, "3");
        enterText(repairabilityScore, "80");
        enterText(durabilityScore, "90");

        selectRealUseInformationAvailable("Yes");
        selectResistanceToStress("LOW");
        selectResistanceToAgingMechanisms("Yes");

        enterText(sparePartCharacteristics, "Standard Parts");
        selectSparePartAvailability("Yes");
        enterText(sparePartDeliveryTime, "7");
        selectSparePartAffordability("AFFORDABLE");

        selectProductModularity("FULLY_MODULAR");
        selectCompatibilityWithCommonTools("Yes");
        selectCompatibilityWithCommonSpareParts("Yes");
        selectRepairProcessComplexity("LEVEL_1");
        selectSpecializedToolsRequired("No");
        selectNonDestructiveAccessToComponents("FULLY_ACCESSIABLE");

        enterText(easeOfNonDestructiveDisassembly, "85");
    }

    public void clickNext() {
        jsClick(nextBtn);
    }

    public boolean isStepTwoOpened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(normalize-space(),'END-OF-LIFE INSTRUCTIONS') or contains(normalize-space(),'EOL Instruction')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
public boolean isValidationDisplayed() {
    try {
        String text = driver.getPageSource().toLowerCase();

        return text.contains("required")
                || text.contains("invalid")
                || text.contains("must")
                || text.contains("error")
                || text.contains("please");
    } catch (Exception e) {
        return false;
    }
}

public void submitStepOneAndScrollToError() {
    clickNext();

    try {
        By error = By.xpath(
                "//*[contains(@class,'error') " +
                "or contains(@class,'invalid') " +
                "or contains(@class,'destructive') " +
                "or contains(text(),'Required') " +
                "or contains(text(),'required') " +
                "or contains(text(),'Invalid') " +
                "or contains(text(),'invalid')]"
        );

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(error)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                errorElement
        );

    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }
}

public void enterRecyclabilityPercentageOnly(String value) {
    enterText(recyclabilityPercentage, value);
}

public void enterNumberDisassemblyOnly(String value) {
    enterText(numberDisassembly, value);
}

public void enterToolsRequiredOnly(String value) {
    enterText(toolsRequired, value);
}

public void enterRepairabilityScoreOnly(String value) {
    enterText(repairabilityScore, value);
}

public void enterDurabilityScoreOnly(String value) {
    enterText(durabilityScore, value);
}

public void enterSparePartDeliveryTimeOnly(String value) {
    enterText(sparePartDeliveryTime, value);
}

public void enterEaseOfNonDestructiveDisassemblyOnly(String value) {
    enterText(easeOfNonDestructiveDisassembly, value);
}

public String getRecyclabilityPercentageValue() {
    return driver.findElement(recyclabilityPercentage).getAttribute("value");
}

public String getNumberDisassemblyValue() {
    return driver.findElement(numberDisassembly).getAttribute("value");
}

public String getToolsRequiredValue() {
    return driver.findElement(toolsRequired).getAttribute("value");
}

public String getRepairabilityScoreValue() {
    return driver.findElement(repairabilityScore).getAttribute("value");
}

public String getDurabilityScoreValue() {
    return driver.findElement(durabilityScore).getAttribute("value");
}

public String getSparePartDeliveryTimeValue() {
    return driver.findElement(sparePartDeliveryTime).getAttribute("value");
}

public String getEaseOfNonDestructiveDisassemblyValue() {
    return driver.findElement(easeOfNonDestructiveDisassembly).getAttribute("value");
}
    public void clickCancel() {
        jsClick(cancelBtn);
    }
    // ================= STEP 2 - INSTRUCTIONS =================

private By instructionCategory =
        By.xpath("//label[contains(normalize-space(),'Instruction Category')]/following::button[1]");

private By instructionName =
        By.xpath("//label[contains(normalize-space(),'Instruction Name')]/following::input[1]");

private By instructionDescription =
        By.xpath("//label[contains(normalize-space(),'Description')]/following::textarea[1]");
private By uploadDocuments =
        By.xpath("//*[contains(normalize-space(),'Upload Documents') or contains(normalize-space(),'Upload document')]");

private By addInstructionBtn =
        By.xpath("//button[contains(normalize-space(),'Add Instruction')]");

private By previousBtn =
        By.xpath("//button[contains(normalize-space(),'Previous')]");

private By stepTwoNextBtn =
        By.xpath("//button[normalize-space()='Next']");

private By instructionBlock =
        By.xpath("//*[contains(normalize-space(),'Instruction #') or contains(normalize-space(),'Instruction Category')]");

public void openStepTwoWithValidStepOneData() {
    fillValidStepOneData();
    clickNext();
}

public boolean isStepTwoInstructionsOpened() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(normalize-space(),'Instructions')]")
        )).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isInstructionCategoryVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(instructionCategory)).isDisplayed();
}

public boolean isInstructionNameVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(instructionName)).isDisplayed();
}

public boolean isInstructionDescriptionVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(instructionDescription)).isDisplayed();
}

public boolean isUploadDocumentsVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadDocuments)).isDisplayed();
}

public boolean isAddInstructionButtonVisible() {
    return wait.until(ExpectedConditions.visibilityOfElementLocated(addInstructionBtn)).isDisplayed();
}

public void openInstructionCategoryDropdown() {
    jsClick(instructionCategory);
}

public void selectInstructionCategory(String value) {
    selectDropdownValue(instructionCategory, value);
}

public void enterInstructionName(String value) {
    enterText(instructionName, value);
}

public void enterInstructionDescription(String value) {
    enterText(instructionDescription, value);
}

public void fillValidInstructionData() {
    selectInstructionCategory("RECYCLING");
    enterInstructionName("Recycle Battery Safely");
    enterInstructionDescription("Follow proper recycling process for battery end-of-life.");
}

public boolean isInstructionDataAccepted(String value) {
    return driver.getPageSource().contains(value);
}

public void clickAddInstruction() {
    jsClick(addInstructionBtn);
}

public int getInstructionBlockCount() {
    try {
        return driver.findElements(instructionBlock).size();
    } catch (Exception e) {
        return 0;
    }
}

public void clickPrevious() {
    jsClick(previousBtn);
}

public boolean isReturnedToStepOne() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(normalize-space(),'Basic Details')]")
        )).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void clickStepTwoNext() {
    jsClick(stepTwoNextBtn);
}

public boolean isStepThreeOpened() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(normalize-space(),'Documents') or contains(normalize-space(),'Certificates')]")
        )).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void submitStepTwoAndScrollToError() {
    clickStepTwoNext();

    try {
        By error = By.xpath(
                "//*[contains(@class,'error') " +
                "or contains(@class,'invalid') " +
                "or contains(@class,'destructive') " +
                "or contains(text(),'Required') " +
                "or contains(text(),'required') " +
                "or contains(text(),'Invalid') " +
                "or contains(text(),'invalid')]"
        );

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(error)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                errorElement
        );

    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }
}
// ================= STEP 3 - DOCUMENTS & CERTIFICATES =================

private By stepThreeHeader =
        By.xpath("//*[contains(normalize-space(),'Documentation & Certifications') " +
                "or contains(normalize-space(),'Documents & Certificates')]");

private By documentCategory =
        By.xpath("//label[contains(normalize-space(),'Document / Certificate Category')]/following::button[@role='combobox'][1]");

private By documentCertificateName =
        By.xpath("//input[@placeholder='Enter Document / Certificate Name']");

private By documentDescription =
        By.xpath("//textarea[contains(@placeholder,'Describe the document/certificate')]");

private By uploadDocumentSection =
        By.xpath("//*[contains(normalize-space(),'Upload Document')]");

private By uploadDocumentInput =
        By.xpath("//input[@type='file']");

private By addDocumentBtn =
        By.xpath("//button[contains(normalize-space(),'Add Document')]");

private By finalAddInstructionBtn =
        By.xpath("//button[contains(normalize-space(),'Add Instruction')]");

private By stepThreePreviousBtn =
        By.xpath("//button[contains(normalize-space(),'Previous')]");

private By documentBlock =
        By.xpath("//*[contains(normalize-space(),'Documentation & Certification')]");

public void openStepThreeWithValidData() {
    openStepTwoWithValidStepOneData();

    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[contains(normalize-space(),'END-OF-LIFE INSTRUCTIONS') or contains(normalize-space(),'EOL Instruction')]")
    ));

    fillValidInstructionData();

    clickStepTwoNext();

    wait.until(ExpectedConditions.visibilityOfElementLocated(stepThreeHeader));
}

public boolean isStepThreeDocumentsOpened() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(stepThreeHeader)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isDocumentCategoryVisible() {
    try {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(documentCategory)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        return element.isDisplayed();

    } catch (Exception e) {
        System.out.println("Step 3 page text: " + driver.findElement(By.tagName("body")).getText());
        return false;
    }
}

public boolean isDocumentCertificateNameVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(documentCertificateName)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isDocumentDescriptionVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(documentDescription)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isUploadDocumentSectionVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(uploadDocumentSection)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public boolean isAddDocumentButtonVisible() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(addDocumentBtn)).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void openDocumentCategoryDropdown() {
    jsClick(documentCategory);
}

public void selectDocumentCategory(String value) {
    selectDropdownValue(documentCategory, value);
}

public void enterDocumentCertificateName(String value) {
    enterText(documentCertificateName, value);
}

public void enterDocumentDescription(String value) {
    enterText(documentDescription, value);
}

public void fillValidDocumentData() {
    selectDocumentCategory("CE_CERTIFICATE");
    enterDocumentCertificateName("Battery Recycling Certificate");
    enterDocumentDescription("Valid certificate document for EOL process.");
}

public boolean isDocumentDataAccepted(String value) {
    return driver.getPageSource().contains(value);
}

public void clickAddDocument() {
    jsClick(addDocumentBtn);
}

public int getDocumentBlockCount() {
    try {
        return driver.findElements(documentBlock).size();
    } catch (Exception e) {
        return 0;
    }
}

public void clickStepThreePrevious() {
    jsClick(stepThreePreviousBtn);
}

public boolean isReturnedToStepTwo() {
    try {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(normalize-space(),'END-OF-LIFE INSTRUCTIONS') or contains(normalize-space(),'EOL Instruction')]")
        )).isDisplayed();
    } catch (Exception e) {
        return false;
    }
}

public void clickFinalAddInstruction() {
    jsClick(finalAddInstructionBtn);
}

public boolean isEolInstructionCreated() {
    try {
        return wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("eol"),
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(normalize-space(),'success') " +
                                "or contains(normalize-space(),'created') " +
                                "or contains(normalize-space(),'added')]")
                )
        ));
    } catch (Exception e) {
        return false;
    }
}

public void submitStepThreeAndScrollToError() {
    clickFinalAddInstruction();

    try {
        By error = By.xpath(
                "//*[contains(@class,'error') " +
                        "or contains(@class,'invalid') " +
                        "or contains(@class,'destructive') " +
                        "or contains(text(),'Required') " +
                        "or contains(text(),'required') " +
                        "or contains(text(),'Invalid') " +
                        "or contains(text(),'invalid')]"
        );

        WebElement errorElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(error)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                errorElement
        );

    } catch (Exception e) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0);");
    }
}

public void uploadDocument(String filePath) {
    WebElement fileInput = wait.until(
            ExpectedConditions.presenceOfElementLocated(uploadDocumentInput)
    );

    fileInput.sendKeys(filePath);
}
//validations step1//

}