package com.automation.utils;

import com.thoughtworks.gauge.datastore.DataStoreInitializer;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;

public class DatastoreFactory {

    public static void addValueToDataStoreAgainstKey(String key, String value){
        if(key.startsWith("$")){
            key = key.substring(1);
        }
        ScenarioDataStore.put(key,value);

    }

    public static String getValueFromDatastore(String key){
        if(key.startsWith("$")){
            key = key.substring(1);
        }
        String value = (String) ScenarioDataStore.get(key);
        return value;
    }




}
