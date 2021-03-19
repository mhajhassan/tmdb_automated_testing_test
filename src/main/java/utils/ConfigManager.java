package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static ConfigManager manager;

    private static final Properties prop = new Properties();

    private ConfigManager() {
        InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigManager getInstance() {
        if (manager == null) {
            synchronized (ConfigManager.class) {
                manager = new ConfigManager();
            }
        }
        return manager;
    }

    private String getString(String key) {
        return System.getProperty(key, prop.getProperty(key));
    }

    public String getPropertyValue(String key) {
        String value = getString(key);
        if (value != null)
            return value;
        else
            throw new RuntimeException(key + "is not specified in the configuration.properties file.");
    }
}
