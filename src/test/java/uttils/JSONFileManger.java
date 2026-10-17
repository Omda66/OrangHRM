package uttils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;

public class JSONFileManger {
    private static final Logger log = LogManager.getLogger(JSONFileManger.class);

    public LinkedHashMap<String, Object> data;

    public JSONFileManger(String filePath) {
        log.trace("Entering JSONFileManger constructor with file: {}", filePath);
        // try-with-resources: the reader is now closed automatically
        try (FileReader reader = new FileReader(filePath)) {
            Type type = new TypeToken<LinkedHashMap<String, Object>>() {}.getType();
            data = new Gson().fromJson(reader, type);

            if (data == null) {
                log.warn("JSON file is empty or has no content: {}", filePath);
            } else {
                log.info("JSON file loaded: {} ({} keys)", filePath, data.size());
                log.debug("JSON keys: {}", data.keySet());
            }
        } catch (FileNotFoundException e) {
            // FATAL: the test data is missing (same behaviour as before: not re-thrown)
            log.fatal("JSON file NOT found: {}", filePath, e);
        } catch (JsonParseException e) {
            log.fatal("JSON file has invalid format: {}", filePath, e);
        } catch (IOException e) {
            log.fatal("Could not read JSON file: {}", filePath, e);
        } catch (Exception e) {
            log.fatal("Unexpected error while loading JSON file: {}", filePath, e);
        }
    }

    public Object getValue(String key) {
        log.trace("Entering getValue() with key: '{}'", key);
        if (data == null) {
            log.error("Cannot read key '{}' because the JSON data was not loaded", key);
            throw new IllegalStateException("JSON data was not loaded - cannot read key: " + key);
        }
        Object value = data.get(key);
        if (value == null) {
            log.warn("Key '{}' was NOT found in the JSON file", key);
        } else {
            log.debug("JSON value: {} = {}", key, value);
        }
        return value;
    }

}