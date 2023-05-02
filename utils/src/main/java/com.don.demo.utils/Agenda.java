package com.don.demo.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 运行自己的定制化软件
 * 并且使用jar包目录
 */
public class Agenda {
    public static void main(String[] args) throws IOException, InterruptedException {

        // String path = System.getProperty("java.class.path");
        // System.out.println(path);
        //
        // int firstIndex = path.lastIndexOf(System.getProperty("path.separator")) + 1;
        // int lastIndex = path.lastIndexOf(File.separator) + 1;
        // path = path.substring(firstIndex, lastIndex);
        // System.out.println(path);
        //
        // Properties properties = new Properties();
        // InputStream resourceAsStream = Agenda.class.getResourceAsStream("/server-node.properties");
        // properties.load(resourceAsStream);
        //
        // System.out.println(properties);


        new SoftwarePlan().openApp();
        new LockWindow().lockWindow();
    }
}
