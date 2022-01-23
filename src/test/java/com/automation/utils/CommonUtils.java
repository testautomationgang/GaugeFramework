package com.automation.utils;

public class CommonUtils {

    public static String removeVariableSymbolIfPresent(String key){
        if(key.startsWith("$")){
            key = key.substring(1);
        }
        return key;
    }

    public static String checkIfVariableThenGetValue(String key){
        if(key.startsWith("$")){
            key = key.substring(1);
            DatastoreFactory.getValueFromDatastore(key);
        }
        return key;
    }
}
