package BaseTest;

import driverFactory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import uttils.ConfigHandler;
import uttils.ExcelFile;
import uttils.JSONFileManger;
import uttils.ScreenShot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    public ConfigHandler configHandler;
    public WebDriver driver;
    public JSONFileManger jsonFileManger;
    public JSONFileManger newEmployee;
    public ExcelFile excelFile;
    @BeforeMethod
    public void setuptall() {
        log.info("==================== Test setup started ====================");
        try {
            log.trace("Loading config file: src/main/resources/cong.properties");
            configHandler = new ConfigHandler("src/main/resources/cong.properties");
            log.debug("Config file loaded");
            log.trace("Loading JSON files: product.json and user.json");
            jsonFileManger = new JSONFileManger("src/main/resources/EmployeeInfo.json");
            newEmployee = new JSONFileManger("src/main/resources/newEmployee.json");
            log.debug("JSON files loaded");
            excelFile=new ExcelFile("src/main/resources/EmployeeEmergency.xlsx",0);

        } catch (RuntimeException e) {
            log.error(" Failed to load config / data files", e);
            throw e;
        }
//        System.out.println("***********************************************sd*********************************");
//        System.out.println(csvFileManger.frequencyWord(0));
//        System.out.println("***********************************************sd*********************************");

        String browser = configHandler.getValue("browser");
        String url = configHandler.getValue("url");
        log.info("Browser: {} | URL: {}", browser, url);
        if (browser == null || browser.isBlank()) {
            log.warn("'browser' value is missing in the config file");
        }
        if (url == null || url.isBlank()) {
            log.warn("'url' value is missing in the config file");
        }

        try {
            log.info("Launching browser: {}", browser);
            driver = DriverFactory.getWebDriver(browser);
            log.debug("Driver created: {}", driver);
        } catch (RuntimeException e) {
            // FATAL: without a driver no test can run
            log.fatal("Could not create the WebDriver for browser '{}' - tests cannot run", browser, e);
            throw e;
        }

        driver.manage().window().maximize();
        log.debug("Browser window maximized");

        try {
            driver.get(url);
            log.info("Navigated to: {}", url);
        } catch (RuntimeException e) {
            log.error("Failed to open URL: {}", url, e);
            throw e;
        }
        log.info("==================== Test setup finished ====================");
    }
    @AfterMethod
    public void failedTestCase(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE){
            File image= ScreenShot.takeScreenShot(driver);
            FileInputStream fis=new FileInputStream(image);
            Allure.addAttachment("Screenshot","image/png",fis,"png");
        }
    }
//    @AfterMethod
//    public void trim()  {
//        DriverFactory.quitWebDriver(configHandler.getValue("browser"));
//        driver = null;
//    }
}

