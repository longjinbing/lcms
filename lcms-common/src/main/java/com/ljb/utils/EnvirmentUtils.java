package com.ljb.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.env.Environment;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/10<br>
 * 描述: <br>
 */
public class EnvirmentUtils {
    private static Environment environment;

    public static String getProperty(String key){
         return environment.getProperty(key);
    }

    public static T getProperty(String key,Class<T> t){
        return environment.getProperty(key, t);
    }

    public static Boolean isDev(){
        return environment.getProperty("spring.profiles.active").equals("dev");
    }


    public static void setEnvironment(Environment environment) {
        EnvirmentUtils.environment = environment;
    }
}
