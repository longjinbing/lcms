package com.ljb.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/8<br>
 * 描述: <br>
 */
public class ConfigUtils {
    public static Configuration mimeConfig(){
        Configuration configuration=null;
        try {
            configuration=new PropertiesConfiguration("mime.properties");
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
