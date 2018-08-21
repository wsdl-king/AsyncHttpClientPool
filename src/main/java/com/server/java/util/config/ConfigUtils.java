package com.server.java.util.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author qiwenshuai
 * @note
 * @since 18-8-19 10:14 by jdk 1.8
 */
public class ConfigUtils {

    private static Properties prop = null;
    static {
        try {
            prop = new Properties();
            prop.load(new InputStreamReader(ConfigUtils.class.getClassLoader().getResourceAsStream("http.properties"),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getPropValues(String propName){

        return prop.getProperty(propName);
    }
}
