package com.example.basiclayour.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileService implements ConfigurationService {

    private static final Logger logger = LogManager.getLogger(PropertiesFileService.class);
    public static String readFromConfigFile(String key){

        String systemPropertiesPath = "system.properties";

        try {
            Properties systemProperties = new Properties();
            //load a properties file from class path, inside static method
            systemProperties.load(new FileInputStream(systemPropertiesPath));

            // check if mandatory config parameters are present
            if (!systemProperties.containsKey(key)) {
                logger.error("Mandatory parameter: " + key + " is missing.");
            }

            //get the property value and print it out
            return systemProperties.getProperty(key);
        }
        catch (IOException e) {
            logger.error("Config file not available" + e);
            throw new RuntimeException("Config file not available - abort");
        }
    }


    @Override
    public String getApiKey(){
        return readFromConfigFile("mapquestapikey");
    }
}
