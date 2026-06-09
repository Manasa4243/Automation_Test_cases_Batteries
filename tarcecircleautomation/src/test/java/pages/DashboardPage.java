package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {

    WebDriver driver;
    WebDriverWait wait;

    public DashboardPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    private By sidebarDashboard =
            By.xpath("//span[normalize-space()='Dashboard'] | //a[contains(@href,'dashboard')]");

    private By organizationManagementMenu =
            By.xpath("//span[normalize-space()='Org Management']");

    private By organizationsLink =
            By.xpath("//a[contains(@href,'organizations')] | //span[normalize-space()='Organizations'] | //p[normalize-space()='Organizations'] | //div[normalize-space()='Organizations']");

    private By createOrganizationBtn =
            By.xpath("//button[contains(normalize-space(),'Create Organization')]");

    private By plantsLink =
            By.xpath("//span[normalize-space()='Plants'] | //a[contains(@href,'plants')]");

    private By createPlantBtn =
            By.xpath("//button[contains(normalize-space(),'Create Plant')]");

    private By employeesLink =
            By.xpath("//span[normalize-space()='Employees'] | //a[contains(@href,'employees')]");

    private By createEmployeeBtn =
            By.xpath("//button[contains(@class,'bg-emerald-700') and contains(.,'Add Employee')]");

    private By employeeRoleLink =
            By.xpath("//span[normalize-space()='Employee + Role'] | //a[contains(@href,'employee-role')]");

    private By dppManagementMenu =
            By.xpath("//span[normalize-space()='Dpp Management']");

    private By productsLink =
           By.xpath("//span[normalize-space()='Products']");

   private By createBatteryBtn =
       By.xpath("//button[normalize-space()='Create Battery']");
 private By batchLink =
            By.xpath("//span[normalize-space()='Batchs'] | //a[contains(@href,'Batchs')]");

private By createBatchBtn =
        By.xpath("//button[contains(normalize-space(),'Create Batch')] | //button[contains(normalize-space(),'New Batch')]");
private By batchAndProductLink =
        By.xpath("//span[normalize-space()='Batch and Product'] | //a[contains(@href,'batch-product')]");

private By mapProductBtn =
        By.xpath("//button[contains(normalize-space(),'Map Product')]");

public void clickBatchAndProduct() {
    jsClick(batchAndProductLink);
}

public void clickMapProduct() {
    jsClick(mapProductBtn);
}

public void openMapProductPage() {
    clickDppManagement();
    clickBatchAndProduct();
    clickMapProduct();

    wait.until(ExpectedConditions.or(
            ExpectedConditions.urlContains("map"),
            ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//h1[contains(normalize-space(),'Map Product')]")
            )
    ));
}
public void clickBatches() {
    jsClick(batchLink);
}

public void clickCreateBatch() {
    jsClick(createBatchBtn);
}

public void openCreateBatteryPage() {

    WebElement dppMenu = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='DPP Management']/ancestor::button")
    ));
    dppMenu.click();

    WebElement productsMenu = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='Products']/ancestor::a | //span[normalize-space()='Products']/ancestor::button")
    ));
    productsMenu.click();

    WebElement createBattery = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='Create Battery']/ancestor::a | //span[normalize-space()='Create Battery']/ancestor::button")
    ));
    createBattery.click();
}
    private void jsClick(By locator) {

        try {
            WebElement element = wait.until(
                    ExpectedConditions.presenceOfElementLocated(locator)
            );

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
            );

            Thread.sleep(700);

            wait.until(ExpectedConditions.visibilityOf(element));

            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].click();",
                    element
            );

        } catch (Exception e) {
            throw new RuntimeException("Unable to click element: " + locator, e);
        }
    }

    public void waitForSidebarAfterLogin() {
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(sidebarDashboard),
                ExpectedConditions.urlContains("dashboard")
        ));
    }

    public void clickOrganizationManagement() {
        waitForSidebarAfterLogin();
        jsClick(organizationManagementMenu);
    }

    public void clickOrganizations() {
        jsClick(organizationsLink);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("organizations"),
                ExpectedConditions.presenceOfElementLocated(createOrganizationBtn)
        ));
    }

    public void openOrganizationsPage() {
        clickOrganizationManagement();
        clickOrganizations();
    }

    public void clickCreateOrganization() {
        jsClick(createOrganizationBtn);

        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("organizations/new"),
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//h1[contains(normalize-space(),'New Organization')]")
                )
        ));
    }

    public void openCreateOrganizationPage() {
        openOrganizationsPage();
        clickCreateOrganization();
    }

    public void clickPlants() {
        jsClick(plantsLink);
    }

    public void clickCreatePlant() {
        jsClick(createPlantBtn);
    }

    public void openCreatePlantPage() {
        clickOrganizationManagement();
        clickPlants();
        clickCreatePlant();
    }

    public void clickEmployees() {
        jsClick(employeesLink);
    }

    public void clickCreateEmployee() {
        jsClick(createEmployeeBtn);
    }

    public void openCreateEmployeePage() {
        clickOrganizationManagement();
        clickEmployees();
        clickCreateEmployee();
    }

    public void clickEmployeeRole() {
        jsClick(employeeRoleLink);
    }

    public void openEmployeeRoleAssignmentPage() {
        clickOrganizationManagement();
        clickEmployeeRole();
    }

    public void clickDppManagement() {
        waitForSidebarAfterLogin();
        jsClick(dppManagementMenu);
    }

    public void clickProducts() {
    WebElement products = wait.until(
            ExpectedConditions.elementToBeClickable(productsLink)
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


    public void clickCreateBattery() {
    WebElement createBattery = wait.until(
            ExpectedConditions.elementToBeClickable(createBatteryBtn)
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

    public void openCreateProductPage() {
        clickDppManagement();
        clickProducts();
        clickCreateBattery();
    }
}