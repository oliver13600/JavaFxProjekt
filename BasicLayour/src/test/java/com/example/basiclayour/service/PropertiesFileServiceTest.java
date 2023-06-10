package com.example.basiclayour.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.*;
import java.util.Properties;

import static com.example.basiclayour.service.PropertiesFileService.readFromConfigFile;
import static org.junit.jupiter.api.Assertions.*;

public class PropertiesFileServiceTest {

    private PropertiesFileService propertiesFileService;

    @Before
    public void setUp() {
        propertiesFileService = new PropertiesFileService();

    }

    @Test
    public void testGetApiKey_KeyPresent() {

        try {
            FileWriter fileWritter = new FileWriter("system.properties", true);
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            bufferWritter.append("mapquestapikeyTest=test-api-key");
            bufferWritter.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        String apiKey = readFromConfigFile("mapquestapikeyTest");

        assertEquals("test-api-key", apiKey);
    }

    @After
    public void DeleteTestKey(){
        try {
            File myFile = new File("system.properties");
            Properties properties = new Properties();
            properties.load(new FileInputStream(myFile));
            properties.remove("mapquestapikeyTest");
            OutputStream out = new FileOutputStream(myFile);
            properties.store(out, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetApiKey_KeyMissing() {

        String apiKey = readFromConfigFile("mapquestapikeyTest");

        assertEquals(null, apiKey);
    }

}