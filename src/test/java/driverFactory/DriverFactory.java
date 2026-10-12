package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    private static final Logger log = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getWebDriver(String broeserName) {
        log.trace("Entering getWebDriver() with browser name: '{}'", broeserName);
        if (broeserName == null || broeserName.isBlank()) {
            // Original code would throw NullPointerException here; now it is logged first
            log.error("Browser name is null or empty");
            throw new IllegalArgumentException("Browser name must not be null or empty");
        }

        String browser = broeserName.toLowerCase().trim();
        if (!browser.equals(broeserName)) {
            log.warn("Browser name '{}' was normalized to '{}'", broeserName, browser);
        }
        log.info("Creating WebDriver for browser: {}", browser);

        WebDriver driver;
        try {
            switch (browser) {
                case "chrome":
                    driver = GetChromeDriver.getChromDriver();
                    break;
                case "edge":
                    driver = GetEdgeDriver.getEdgeDriver();
                    break;
                case "firefox":
                    driver = GetFireFoxDriver.getFireFoxDriver();
                    break;
                default:
                    log.error("Invalid browser name: '{}'. Supported: chrome, edge, firefox", broeserName);
                    throw new IllegalArgumentException("Invalid browser name : " + broeserName);
            }
        } catch (IllegalArgumentException e) {
            throw e; // already logged above
        } catch (RuntimeException e) {
            // FATAL: the browser could not be started, no test can run
            log.fatal("Failed to start the {} driver - tests cannot run", browser, e);
            throw e;
        }

        if (driver == null) {
            log.fatal("{} driver factory returned null", browser);
        } else {
            log.debug("{} driver created: {}", browser, driver);
        }
        return driver;
    }

    public static void quitWebDriver(String broeserName) {
        log.trace("Entering quitWebDriver() with browser name: '{}'", broeserName);
        if (broeserName == null || broeserName.isBlank()) {
            log.error("Browser name is null or empty - cannot quit the driver");
            throw new IllegalArgumentException("Browser name must not be null or empty");
        }

        String browser = broeserName.toLowerCase().trim();
        log.info("Quitting WebDriver for browser: {}", browser);

        try {
            switch (browser) {
                case "chrome":
                    GetChromeDriver.quitDiver();
                    break;
                case "edge":
                    GetEdgeDriver.quitDiver();
                    break;
                case "firefox":
                    GetFireFoxDriver.quitDiver();
                    break;
                default:
                    log.error("Invalid browser name: '{}'. Supported: chrome, edge, firefox", broeserName);
                    throw new IllegalArgumentException("Invalid browser name : " + broeserName);
            }
            log.debug("{} driver quit successfully", browser);
        } catch (IllegalArgumentException e) {
            throw e; // already logged above
        } catch (RuntimeException e) {
            log.error("Failed to quit the {} driver", browser, e);
            throw e;
        }
    }
}