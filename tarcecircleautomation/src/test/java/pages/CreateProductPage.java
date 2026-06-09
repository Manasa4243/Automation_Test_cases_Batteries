package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProductPage {

    WebDriver driver;
    WebDriverWait wait;

    public CreateProductPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By pageTitle =
        By.xpath("//*[normalize-space()='Create Battery']");

    private By organizationDropdown =
            By.xpath("//label[contains(normalize-space(),'Organization')]/following::button[1]");

    private By plantDropdown =
            By.xpath("//label[contains(normalize-space(),'Plant')]/following::button[1]");

    private By bpanInput =
            By.xpath("//label[contains(normalize-space(),'BPAN')]/following::input[1]");

    private By productNameInput =
            By.xpath("//label[contains(normalize-space(),'Product Name')]/following::input[1]");

    private By batteryTypeDropdown =
            By.xpath("//label[contains(normalize-space(),'Battery Type')]/following::button[1]");

    private By batteryCategoryDropdown =
            By.xpath("//label[contains(normalize-space(),'Battery Category')]/following::button[1]");

    private By capacityInput =
            By.xpath("//label[contains(normalize-space(),'Capacity')]/following::input[1]");

    private By chemistryDropdown =
            By.xpath("//label[contains(normalize-space(),'Chemistry')]/following::button[1]");

    private By voltageInput =
            By.xpath("//label[contains(normalize-space(),'Voltage')]/following::input[1]");

    private By cellOriginDropdown =
            By.xpath("//label[contains(normalize-space(),'Cell Origin')]/following::button[1]");

    private By extinguisherClassDropdown =
            By.xpath("//label[contains(normalize-space(),'Extinguisher')]/following::button[1]");

    private By nextBtn =
            By.xpath("//button[contains(normalize-space(),'Next')]");

    private By previousBtn =
            By.xpath("//button[contains(normalize-space(),'Previous')]");

    private By cancelBtn =
            By.xpath("//button[contains(normalize-space(),'Cancel')]");

    private By createBatteryBtn =
            By.xpath("//button[contains(normalize-space(),'Create Battery') or contains(normalize-space(),'Create Product')]");

    private By tacNumberInput =
            By.xpath("//label[contains(normalize-space(),'TAC')]/following::input[1]");

    private By numberOfCellsInput =
            By.xpath("//label[contains(normalize-space(),'Number of Cells')]/following::input[1]");

    private By internalResistanceInput =
            By.xpath("//label[contains(normalize-space(),'Internal Resistance')]/following::input[1]");

    private By batteryWeightInput =
            By.xpath("//label[contains(normalize-space(),'Battery Weight')]/following::input[1]");

    private By warrantyInput =
            By.xpath("//label[contains(normalize-space(),'Warranty')]/following::input[1]");

    private By cellTypeDropdown =
            By.xpath("//label[contains(normalize-space(),'Cell Type')]/following::button[1]");

    private By cellFormFactorDropdown =
            By.xpath("//label[contains(normalize-space(),'Cell Form Factor')]/following::button[1]");

   

    private By addProcessBtn =
            By.xpath("//button[contains(normalize-space(),'Add Process')]");

    private By downloadTemplateBtn =
            By.xpath("//button[contains(normalize-space(),'Download Template')]");

    private By fileUploadInput =
            By.xpath("//input[@type='file']");
private By dppManagementMenu =
        By.xpath("//*[normalize-space()='DPP Management' or normalize-space()='Dpp Management']");

private By productsMenu =
        By.xpath("//*[normalize-space()='Products']");
private By dateOfManufacturingInput =
        By.xpath("//label[contains(normalize-space(),'Date of Manufacturing')]/following::input[1]");

private By factoryCodeInput =
        By.xpath("//label[contains(normalize-space(),'Factory Code')]/following::input[1]");

private By sequentialProductionNumberInput =
        By.xpath("//label[contains(normalize-space(),'Sequential Production Number')]/following::input[1]");

private By importerNameInput =
        By.xpath("//label[contains(normalize-space(),'Importer Name')]/following::input[1]");

private By importerLocationInput =
        By.xpath("//label[contains(normalize-space(),'Importer Location')]/following::input[1]");

private By numberOfMaterialsUsedInput =
        By.xpath("//label[contains(normalize-space(),'Number of Materials Used')]/following::input[1]");

private By numberOfComponentsUsedInput =
        By.xpath("//label[contains(normalize-space(),'Number of Components Used')]/following::input[1]");

private By materialCompositionInput =
        By.xpath("//label[contains(normalize-space(),'Material Composition')]/following::input[1]");

private By materialHomogeneityInput =
        By.xpath("//label[contains(normalize-space(),'Material Homogeneity')]/following::input[1]");

private By useOfStandardComponentsDropdown =
        By.xpath("//label[contains(normalize-space(),'Use of Standard Components')]/following::button[1]");

private By componentCodingStandardInput =
        By.xpath("//label[contains(normalize-space(),'Component Coding Standard')]/following::input[1]");

private By materialCodingStandardInput =
        By.xpath("//label[contains(normalize-space(),'Material Coding Standard')]/following::input[1]");

private By useOfRecycledMaterialDropdown =
        By.xpath("//label[contains(normalize-space(),'Use of Recycled Material')]/following::button[1]");

private By recycledMaterialContentInput =
        By.xpath("//label[contains(normalize-space(),'Recycled Material Content')]/following::input[1]");

private By recoveryOfMaterialsInput =
        By.xpath("//label[contains(normalize-space(),'Recovery of Materials')]/following::input[1]");

private By criticalRawMaterialsDropdown =
        By.xpath("//label[contains(normalize-space(),'Critical raw materials included')]/following::button[1]");

private By hazardousSubstanceInput =
        By.xpath("//label[contains(normalize-space(),'Hazardous substance')]/following::input[1]");

private By highPuritySortingCapabilityInput =
        By.xpath("//label[contains(normalize-space(),'High-purity Sorting Capability')]/following::input[1]");
        private By electrolyteInput =
        By.xpath("//label[contains(normalize-space(),'Electrolyte')]/following::input[1]");

private By separatorInput =
        By.xpath("//label[contains(normalize-space(),'Separator')]/following::input[1]");

private By currentCollectorInput =
        By.xpath("//label[contains(normalize-space(),'Current Collector')]/following::input[1]");

private By batteryCasingInput =
        By.xpath("//label[contains(normalize-space(),'Battery Casing')]/following::input[1]");

private By pottingInput =
        By.xpath("//label[contains(normalize-space(),'Potting')]/following::input[1]");

private By material1ContentInput =
        By.xpath("//label[contains(normalize-space(),'Material 1 Content')]/following::input[1]");

private By material2ContentInput =
        By.xpath("//label[contains(normalize-space(),'Material 2 Content')]/following::input[1]");

private By material3ContentInput =
        By.xpath("//label[contains(normalize-space(),'Material 3 Content')]/following::input[1]");

private By material4ContentInput =
        By.xpath("//label[contains(normalize-space(),'Material 4 Content')]/following::input[1]");

private By processNameDropdown =
        By.xpath("//label[contains(normalize-space(),'Process Name')]/following::button[1]");

private By locationInput =
        By.xpath("//label[contains(normalize-space(),'Location')]/following::input[1]");

private By productImageUrlInput =
        By.xpath("//label[contains(normalize-space(),'Product Image URL')]/following::input[1]");

private By productVideoUrlInput =
        By.xpath("//label[contains(normalize-space(),'Product Video URL')]/following::input[1]");

private By createProductBtn =
        By.xpath("//button[contains(normalize-space(),'Create Product')]");
        private By batteryPackConstructionTypeDropdown =
    By.xpath("//label[contains(normalize-space(),'Battery Pack Construction Type')]/following::button[1]");

private By moduleConstructionTypeDropdown =
    By.xpath("//label[contains(normalize-space(),'Module Construction Type')]/following::button[1]");

private By coolingSystemDropdown =
    By.xpath("//label[contains(normalize-space(),'Cooling System')]/following::button[1]");

private By stateOfHealthInput =
    By.xpath("//label[contains(normalize-space(),'State Of Health')]/following::input[1]");

private By power80SocInput =
    By.xpath("//label[contains(normalize-space(),'Power @ 80% SOC')]/following::input[1]");

private By power50SocInput =
    By.xpath("//label[contains(normalize-space(),'Power @ 50% SOC')]/following::input[1]");

private By anodeMaterialInput =
    By.xpath("//label[contains(normalize-space(),'Anode')]/following::input[1]");

private By cathodeMaterialInput =
    By.xpath("//label[contains(normalize-space(),'Cathode')]/following::input[1]");
private By createBatteryNavigationBtn =
        By.xpath(
            "//button[contains(normalize-space(),'Create Battery')] | " +
            "//a[contains(normalize-space(),'Create Battery')] | " +
            "//span[contains(normalize-space(),'Create Battery')]"
        );
    private void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                element
        );
    }

    private void enterText(By locator, String value) {

    try {
        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );

        wait.until(ExpectedConditions.elementToBeClickable(locator));

        element.clear();
        element.sendKeys(value);

    } catch (Exception e) {
        throw new RuntimeException("Unable to enter text: " + locator, e);
    }
}
private void selectYesNoDropdown(By dropdown) {

    WebElement dropdownElement = wait.until(
            ExpectedConditions.elementToBeClickable(dropdown)
    );

    dropdownElement.click();

    WebElement yesOption = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[normalize-space()='Yes']")
            )
    );

    yesOption.click();
}
    private void selectDropdownValue(By dropdown, String value) {

    try {
        WebElement dropdownElement = wait.until(
                ExpectedConditions.elementToBeClickable(dropdown)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                dropdownElement
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                dropdownElement
        );

        Thread.sleep(700);

        By option = By.xpath(
                "//div[@role='option' and contains(normalize-space(),'" + value + "')]"
                + " | //li[contains(normalize-space(),'" + value + "')]"
                + " | //span[contains(normalize-space(),'" + value + "')]"
                + " | //*[@cmdk-item and contains(normalize-space(),'" + value + "')]"
                + " | //*[@role='option' and contains(normalize-space(),'" + value + "')]"
        );

        WebElement optionElement = wait.until(
                ExpectedConditions.elementToBeClickable(option)
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                optionElement
        );

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                optionElement
        );

        Thread.sleep(500);

    } catch (Exception e) {
        throw new RuntimeException(
                "Dropdown value not selected: " + value,
                e
        );
    }
}
private void selectYesNoDropdownValue(By dropdown, String value) {

    WebElement dropdownElement = wait.until(
            ExpectedConditions.elementToBeClickable(dropdown)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            dropdownElement
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            dropdownElement
    );

    WebElement option = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[normalize-space()='" + value + "']")
            )
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            option
    );
}
    public boolean isCreateProductPageOpened() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(pageTitle),
                    ExpectedConditions.visibilityOfElementLocated(organizationDropdown),
                    ExpectedConditions.visibilityOfElementLocated(productNameInput),
                    ExpectedConditions.visibilityOfElementLocated(nextBtn)
            ));
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isOrganizationSelected(String orgName) {

    try {

        WebElement orgDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(organizationDropdown)
        );

        String selectedText = orgDropdown.getText();
        String textContent = orgDropdown.getAttribute("textContent");

        System.out.println("Selected Organization = " + selectedText);
        System.out.println("Text Content = " + textContent);

        return (selectedText != null &&
                selectedText.toLowerCase().contains(orgName.toLowerCase()))
            || (textContent != null &&
                textContent.toLowerCase().contains(orgName.toLowerCase()));

    } catch (Exception e) {
        return false;
    }
}
public void openPlantDropdown() {

    WebElement plant = wait.until(
            ExpectedConditions.elementToBeClickable(plantDropdown)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            plant
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            plant
    );
}
public boolean isPlantDropdownOpened() {

    try {

        By plantOptions = By.xpath(
                "//div[@role='option']"
                + " | //li[@role='option']"
                + " | //div[contains(@class,'select-content')]"
                + " | //div[contains(@class,'popover-content')]"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantOptions)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}
public boolean isPlantOptionVisible(String plantName) {

    try {

        By option = By.xpath(
                "//*[contains(normalize-space(),'" + plantName + "')]"
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(option)
        ).isDisplayed();

    } catch (Exception e) {
        return false;
    }
}
public boolean isPlantSelected(String plantName) {

    try {

        WebElement plantDropdownElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(plantDropdown)
        );

        String selectedText = plantDropdownElement.getText();
        String textContent = plantDropdownElement.getAttribute("textContent");

        System.out.println("Selected Plant = " + selectedText);
        System.out.println("Text Content = " + textContent);

        return (selectedText != null &&
                selectedText.toLowerCase().contains(plantName.toLowerCase()))
            || (textContent != null &&
                textContent.toLowerCase().contains(plantName.toLowerCase()));

    } catch (Exception e) {
        return false;
    }
}
public void clickDppManagement() {

    WebElement dpp = wait.until(
            ExpectedConditions.elementToBeClickable(dppManagementMenu)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            dpp
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            dpp
    );
}

public void clickProducts() {

    WebElement products = wait.until(
            ExpectedConditions.elementToBeClickable(productsMenu)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            products
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            products
    );
}

public void clickCreateBatteryNavigation() {

    WebElement createBattery = wait.until(
            ExpectedConditions.elementToBeClickable(createBatteryNavigationBtn)
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            createBattery
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            createBattery
    );
}

public boolean areStep1FieldsDisplayed() {

    return

        isDisplayedWithScroll(organizationDropdown) &&
        isDisplayedWithScroll(plantDropdown) &&
        isDisplayedWithScroll(bpanInput) &&
        isDisplayedWithScroll(productNameInput) &&
        isDisplayedWithScroll(batteryTypeDropdown) &&
        isDisplayedWithScroll(batteryCategoryDropdown) &&
        isDisplayedWithScroll(capacityInput) &&
        isDisplayedWithScroll(chemistryDropdown) &&
        isDisplayedWithScroll(voltageInput) &&
        isDisplayedWithScroll(cellOriginDropdown) &&
        isDisplayedWithScroll(extinguisherClassDropdown) &&

        isDisplayedWithScroll(dateOfManufacturingInput) &&
        isDisplayedWithScroll(factoryCodeInput) &&
        isDisplayedWithScroll(sequentialProductionNumberInput) &&
        isDisplayedWithScroll(importerNameInput) &&
        isDisplayedWithScroll(importerLocationInput) &&

        isDisplayedWithScroll(numberOfMaterialsUsedInput) &&
        isDisplayedWithScroll(numberOfComponentsUsedInput) &&
        isDisplayedWithScroll(materialCompositionInput) &&
        isDisplayedWithScroll(materialHomogeneityInput) &&
        isDisplayedWithScroll(useOfStandardComponentsDropdown) &&
        isDisplayedWithScroll(componentCodingStandardInput) &&
        isDisplayedWithScroll(materialCodingStandardInput) &&
        isDisplayedWithScroll(useOfRecycledMaterialDropdown) &&
        isDisplayedWithScroll(recycledMaterialContentInput) &&
        isDisplayedWithScroll(recoveryOfMaterialsInput) &&
        isDisplayedWithScroll(criticalRawMaterialsDropdown) &&
        isDisplayedWithScroll(hazardousSubstanceInput) &&
        isDisplayedWithScroll(highPuritySortingCapabilityInput) &&

        isDisplayedWithScroll(nextBtn) &&
        isDisplayedWithScroll(cancelBtn);
}
    public void selectOrganization(String orgName) {
        selectDropdownValue(organizationDropdown, orgName);
    }

    public void selectPlant(String plantName) {
        selectDropdownValue(plantDropdown, plantName);
    }

    public void selectBatteryType(String batteryType) {
        selectDropdownValue(batteryTypeDropdown, batteryType);
    }

    public void selectBatteryCategory(String category) {
        selectDropdownValue(batteryCategoryDropdown, category);
    }

    public void selectBatteryChemistry(String chemistry) {
        selectDropdownValue(chemistryDropdown, chemistry);
    }

    public void selectCellOrigin(String origin) {
        selectDropdownValue(cellOriginDropdown, origin);
    }

    public void selectExtinguisherClass(String extinguisherClass) {
        selectDropdownValue(extinguisherClassDropdown, extinguisherClass);
    }

    public boolean isDropdownOptionVisible(String value) {
        try {
            By option = By.xpath("//*[contains(normalize-space(),'" + value + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(option)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPlantDropdownEnabled() {
        try {
            WebElement plant = wait.until(ExpectedConditions.presenceOfElementLocated(plantDropdown));
            return plant.isEnabled();
        } catch (Exception e) {
            return false;   
        }
    }

   public void fillStep1ValidData() {

    selectOrganization("Battery_Org");
    selectPlant("Battery_plant");

    enterText(bpanInput, "BPAN-2026-001");
    enterText(productNameInput, "Battery Product");

    selectBatteryType("EV");
    selectBatteryCategory("EV 2 Wheeler");

    enterText(capacityInput, "75");
    selectBatteryChemistry("Lithium Ion");
    enterText(voltageInput, "48");

    selectCellOrigin("India");
    selectExtinguisherClass("Class D");

    enterText(dateOfManufacturingInput, "01-01-2026");
    enterText(factoryCodeInput, "FAC001");
    enterText(sequentialProductionNumberInput, "1001");
    enterText(importerNameInput, "Battery Importer");
    enterText(importerLocationInput, "Bangalore");

    enterText(numberOfMaterialsUsedInput, "10");
    enterText(numberOfComponentsUsedInput, "20");
    enterText(materialCompositionInput, "Lithium, Nickel, Graphite");
    enterText(materialHomogeneityInput, "95");

    selectStandardComponents("Yes");

    enterText(componentCodingStandardInput, "ISO123");
    enterText(materialCodingStandardInput, "MAT456");

    selectRecycledMaterial();

    enterText(recycledMaterialContentInput, "30");
    enterText(recoveryOfMaterialsInput, "80");

    selectCriticalRawMaterials();

    enterText(hazardousSubstanceInput, "No");
    enterText(highPuritySortingCapabilityInput, "90");
}

    public void clickNext() {
    WebElement next = wait.until(
            ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Next' or contains(normalize-space(),'Next')]")
            )
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            next
    );

    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();",
            next
    );
}

    public boolean isStep2Opened() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(tacNumberInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
public void selectStandardComponents() {
    selectYesNoDropdown(useOfStandardComponentsDropdown);
}

public void selectRecycledMaterial() {
    selectYesNoDropdown(useOfRecycledMaterialDropdown);
}

public void selectCriticalRawMaterials() {
    selectYesNoDropdown(criticalRawMaterialsDropdown);
}
    public void openCreateBatteryPage() {

    By dppManagement = By.xpath(
        "//*[normalize-space()='Dpp Management' or normalize-space()='DPP Management']/ancestor::*[self::button or self::div][1]"
    );

    By productsMenu = By.xpath(
        "//*[normalize-space()='Products']"
    );

    By createBatteryBtn = By.xpath(
        "//button[normalize-space()='Create Battery' or contains(.,'Create Battery')]"
    );

    try {
        WebElement dpp = wait.until(ExpectedConditions.elementToBeClickable(dppManagement));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", dpp);
    } catch (Exception e) {
        System.out.println("DPP Management already opened or not clickable");
    }

    WebElement products = wait.until(ExpectedConditions.elementToBeClickable(productsMenu));
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", products);

    WebElement createBattery = wait.until(ExpectedConditions.elementToBeClickable(createBatteryBtn));
    ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView({block:'center'});",
            createBattery
    );
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBattery);
}

    public boolean areStep2FieldsDisplayed() {

    return

        isDisplayedWithScroll(tacNumberInput) &&
        isDisplayedWithScroll(numberOfCellsInput) &&
        isDisplayedWithScroll(internalResistanceInput) &&
        isDisplayedWithScroll(batteryWeightInput) &&
        isDisplayedWithScroll(warrantyInput) &&

        isDisplayedWithScroll(cellTypeDropdown) &&
        isDisplayedWithScroll(cellFormFactorDropdown) &&
        isDisplayedWithScroll(batteryPackConstructionTypeDropdown) &&
        isDisplayedWithScroll(moduleConstructionTypeDropdown) &&
        isDisplayedWithScroll(coolingSystemDropdown) &&

        isDisplayedWithScroll(stateOfHealthInput) &&
        isDisplayedWithScroll(power80SocInput) &&
        isDisplayedWithScroll(power50SocInput) &&

        isDisplayedWithScroll(anodeMaterialInput) &&
        isDisplayedWithScroll(cathodeMaterialInput) &&
        isDisplayedWithScroll(electrolyteInput) &&
        isDisplayedWithScroll(separatorInput) &&
        isDisplayedWithScroll(currentCollectorInput) &&
        isDisplayedWithScroll(batteryCasingInput) &&
        isDisplayedWithScroll(pottingInput) &&

        isDisplayedWithScroll(material1ContentInput) &&
        isDisplayedWithScroll(material2ContentInput) &&
        isDisplayedWithScroll(material3ContentInput) &&
        isDisplayedWithScroll(material4ContentInput) &&

        isDisplayedWithScroll(addProcessBtn) &&
        isDisplayedWithScroll(processNameDropdown) &&
        isDisplayedWithScroll(locationInput) &&

        isDisplayedWithScroll(productImageUrlInput) &&
        isDisplayedWithScroll(productVideoUrlInput) &&

        isDisplayedWithScroll(previousBtn) &&
        isDisplayedWithScroll(createProductBtn);
}
private boolean isDisplayedWithScroll(By locator) {

    try {
        for (int i = 0; i < 15; i++) {

            try {
                WebElement element = driver.findElement(locator);

                if (element.isDisplayed()) {
                    return true;
                }

            } catch (Exception ignored) {
            }

            ((JavascriptExecutor) driver).executeScript(
                    "window.scrollBy(0, 500);"
            );

            Thread.sleep(400);
        }

        System.out.println("Field not displayed: " + locator);
        return false;

    } catch (Exception e) {
        System.out.println("Scroll failed for: " + locator);
        return false;
    }
}
    public void clickPrevious() {
        jsClick(previousBtn);
    }

  public void verifyComponentCodingFieldState() {

    WebElement element = driver.findElement(componentCodingStandardInput);

    System.out.println("Enabled = " + element.isEnabled());
    System.out.println("Displayed = " + element.isDisplayed());
    System.out.println("Readonly = " + element.getAttribute("readonly"));
    System.out.println("Disabled = " + element.getAttribute("disabled"));
}
    public void clickCreateProduct() {
        jsClick(createBatteryBtn);
    }

    public boolean isProductCreatedOrSaved() {
        try {
            return wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("products"),
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(),'success') or contains(normalize-space(),'created')]")
                    )
            ));
        } catch (Exception e) {
            return false;
        }
    }

    public void clickCancel() {
        jsClick(cancelBtn);
    }

    public boolean isReturnedToProductList() {
        try {
            return wait.until(ExpectedConditions.urlContains("products"));
        } catch (Exception e) {
            return false;
        }
    }
public void scrollToPerformanceDataSection() {
    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 700);");
}
    public void clickAddProcess() {
        jsClick(addProcessBtn);
    }

    public boolean isProcessSectionAdded() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(normalize-space(),'Process')]")
            )).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDownloadTemplate() {
        jsClick(downloadTemplateBtn);
    }
public void selectStandardComponents(String value) {
    selectYesNoDropdownValue(useOfStandardComponentsDropdown, value);
}

public void selectRecycledMaterial(String value) {
    selectYesNoDropdownValue(useOfRecycledMaterialDropdown, value);
}

public void selectCriticalRawMaterials(String value) {
    selectYesNoDropdownValue(criticalRawMaterialsDropdown, value);
}
    public boolean isDownloadTemplateButtonVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(downloadTemplateBtn)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void uploadFile(String filePath) {
        WebElement upload = wait.until(ExpectedConditions.presenceOfElementLocated(fileUploadInput));
        upload.sendKeys(filePath);
    }
}