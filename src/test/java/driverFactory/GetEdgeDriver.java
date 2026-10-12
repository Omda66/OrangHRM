package driverFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class GetEdgeDriver {
    private static final Logger log = LogManager.getLogger(GetEdgeDriver.class);
    private static WebDriver driver = null;

    public static WebDriver getEdgeDriver() {
        log.trace("Entering getEdgeDriver()");
        if (driver == null) {
            log.info("No existing Edge driver - creating a new one");
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments(new String[]{"--private"});
            log.debug("Edge options: {}", edgeOptions);
            try {
                driver = new EdgeDriver(edgeOptions);
                log.info("Edge driver started successfully");
            } catch (RuntimeException e) {
                // FATAL: Edge could not be started, no test can run
                log.fatal("Failed to start Edge driver - tests cannot run", e);
                throw e;
            }
        } else {
            // The same instance is shared (singleton), so a second call returns the open browser
            log.warn("Edge driver already exists - returning the existing instance");
        }
        return driver;
    }

    public static void quitDiver() {
        log.trace("Entering quitDiver()");
        if (driver != null) {
            log.info("Quitting Edge driver");
            try {
                driver.quit();
                log.debug("Edge driver quit successfully");
            } catch (RuntimeException e) {
                log.error("Error while quitting Edge driver", e);
                throw e;
            } finally {
                // Always reset, otherwise a broken driver would be returned next time
                driver = null;
                log.debug("Edge driver reference reset to null");
            }
        } else {
            log.warn("quitDiver() called but there is no Edge driver to quit");
        }
    }
}