package com.automation.utils;

import com.thoughtworks.gauge.Gauge;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static Properties properties;

    static {
        properties = new Properties();
        File file = new File(GlobalConstants.APPLICATION_PROPERTY_PATH);
        try {
            FileInputStream inputStream = new FileInputStream(file);
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyValue(String key){
        String value =  properties.getProperty(key);
        if(value == null){
            Gauge.writeMessage("Property value not found for : " +key);
        }
        return value;
    }
}
