package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class GetFireFoxDriver {
    private static final Logger log = LogManager.getLogger(GetFireFoxDriver.class);
    private static WebDriver driver = null;

    public static WebDriver getFireFoxDriver() {
        log.trace("Entering getFireFoxDriver()");
        if (driver == null) {
            log.info("No existing Firefox driver - creating a new one");
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments(new String[]{"--private"});
            log.debug("Firefox options: {}", firefoxOptions);
            try {
                driver = new FirefoxDriver(firefoxOptions);
                log.info("Firefox driver started successfully");
            } catch (RuntimeException e) {
                // FATAL: Firefox could not be started, no test can run
                log.fatal("Failed to start Firefox driver - tests cannot run", e);
                throw e;
            }
        } else {
            // The same instance is shared (singleton), so a second call returns the open browser
            log.warn("Firefox driver already exists - returning the existing instance");
        }
        return driver;
    }

    public static void quitDiver() {
        log.trace("Entering quitDiver()");
        if (driver != null) {
            log.info("Quitting Firefox driver");
            try {
                driver.quit();
                log.debug("Firefox driver quit successfully");
            } catch (RuntimeException e) {
                log.error("Error while quitting Firefox driver", e);
                throw e;
            } finally {
                // Always reset, otherwise a broken driver would be returned next time
                driver = null;
                log.debug("Firefox driver reference reset to null");
            }
        } else {
            log.warn("quitDiver() called but there is no Firefox driver to quit");
        }
    }
}