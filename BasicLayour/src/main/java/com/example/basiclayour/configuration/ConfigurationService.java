package com.example.basiclayour.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationService implements IConfiguration {

    public static String readFromConfigFile(String key){

        String systemPropertiesPath = "system.properties";

        try {
            Properties systemProperties = new Properties();
            //load a properties file from class path, inside static method
            systemProperties.load(new FileInputStream(systemPropertiesPath));

            // check if mandatory config parameters are present


            //get the property value and print it out
            return systemProperties.getProperty(key);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Config-File not found");
        }
    }


    @Override
    public String getApiKey(){
        return readFromConfigFile("mapquestapikey");
    }
}
