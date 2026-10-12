package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GetChromeDriver {
    private static final Logger log = LogManager.getLogger(GetChromeDriver.class);
    private static WebDriver driver = null;

    public static WebDriver getChromDriver() {
        log.trace("Entering getChromDriver()");
        if (driver == null) {
            log.info("No existing Chrome driver - creating a new one");
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments(new String[]{"--incognito"});
            log.debug("Chrome options: {}", chromeOptions);
            try {
                driver = new ChromeDriver(chromeOptions);
                log.info("Chrome driver started successfully");
            } catch (RuntimeException e) {
                // FATAL: Chrome could not be started, no test can run
                log.fatal("Failed to start Chrome driver - tests cannot run", e);
                throw e;
            }
        } else {
            // The same instance is shared (singleton), so a second call returns the open browser
            log.warn("Chrome driver already exists - returning the existing instance");
        }
        return driver;
    }

    public static void quitDiver() {
        log.trace("Entering quitDiver()");
        if (driver != null) {
            log.info("Quitting Chrome driver");
            try {
                driver.quit();
                log.debug("Chrome driver quit successfully");
            } catch (RuntimeException e) {
                log.error("Error while quitting Chrome driver", e);
                throw e;
            } finally {
                // Always reset, otherwise a broken driver would be returned next time
                driver = null;
                log.debug("Chrome driver reference reset to null");
            }
        } else {
            log.warn("quitDiver() called but there is no Chrome driver to quit");
        }
    }
}