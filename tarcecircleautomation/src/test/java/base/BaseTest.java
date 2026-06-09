package base;

import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.OrganizationListPage;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected final String BASE_URL = "http://localhost:5173";

    protected final String LOGIN_URL = BASE_URL + "/login";
    protected final String ORGANIZATION_LIST_URL = BASE_URL + "/organizations";
    protected final String CREATE_ORG_URL = BASE_URL + "/organizations/new";

    // Set true only when running Selenium + OWASP ZAP
    protected boolean enableZapProxy = true;

    // Your ZAP proxy port from screenshot is 8081
    protected final String ZAP_PROXY = "localhost:8081";

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        if (enableZapProxy) {
            Proxy zapProxy = new Proxy();
            zapProxy.setHttpProxy(ZAP_PROXY);
            zapProxy.setSslProxy(ZAP_PROXY);

            options.setProxy(zapProxy);
            options.addArguments("--ignore-certificate-errors");
            options.addArguments("--allow-insecure-localhost");
        }

        options.addArguments("--start-maximized");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--force-device-scale-factor=1");
        options.addArguments("--high-dpi-support=1");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().deleteAllCookies();

        ((JavascriptExecutor) driver)
                .executeScript("document.body.style.zoom='100%'");
    }

    protected void openLoginPage() {
        driver.get(LOGIN_URL);
    }

    protected OrganizationListPage openOrganizationListPage() {
        driver.get(ORGANIZATION_LIST_URL);
        return new OrganizationListPage(driver, wait);
    }

    protected void openCreateOrganizationPage() {
        driver.get(CREATE_ORG_URL);
    }

    protected void refreshPage() {
        driver.navigate().refresh();
        waitTime(2000);
    }

    protected void waitTime(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {

        try {
            if (driver != null) {
                Thread.sleep(2000);
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}