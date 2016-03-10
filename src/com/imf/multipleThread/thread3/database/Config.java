package com.imf.multipleThread.thread3.database;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.collections.bag.SynchronizedSortedBag;

public class Config {
    private static Properties prop = new Properties();    
    static{        
        try {
            //加载dbconfig.properties配置文件
            prop.load(Config.class.getResourceAsStream("/dbconfig.properties"));
            System.out.println(prop.getProperty("DRIVER"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    //设置常量
    public static final String DRIVER = prop.getProperty("DRIVER");
    public static final String URL = prop.getProperty("URL");
    public static final String USERNAME = prop.getProperty("USERNAME");
    public static final String PASSWORD = prop.getProperty("PASSWORD");
    
}