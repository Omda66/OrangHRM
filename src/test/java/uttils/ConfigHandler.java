package uttils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private static final Logger log = LogManager.getLogger(ConfigHandler.class);

    Properties properties;

    public ConfigHandler(String filePath) {
        log.trace("Entering ConfigHandler constructor with file: {}", filePath);
        properties = new Properties();
        // try-with-resources: the stream is now closed automatically
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
            log.info("Config file loaded: {} ({} properties)", filePath, properties.size());
            log.debug("Config keys: {}", properties.stringPropertyNames());
            if (properties.isEmpty()) {
                log.warn("Config file is empty: {}", filePath);
            }
        } catch (FileNotFoundException e) {
            // FATAL: nothing can run without the config file (same behaviour as before: not re-thrown)
            log.fatal("Config file NOT found: {}", filePath, e);
        } catch (IOException e) {
            log.fatal("Could not read config file: {}", filePath, e);
        } catch (Exception e) {
            log.fatal("Unexpected error while loading config file: {}", filePath, e);
        }
    }

    public String getValue(String key) {
        log.trace("Entering getValue() with key: '{}'", key);
        String value = properties.getProperty(key);
        if (value == null) {
            log.warn("Key '{}' was NOT found in the config file", key);
        } else if (value.isBlank()) {
            log.warn("Key '{}' exists but its value is empty", key);
        } else {
            // Never log secrets
            boolean secret = key != null && key.toLowerCase().contains("pass");
            log.debug("Config value: {} = {}", key, secret ? "****" : value);
        }
        return value;
    }
}