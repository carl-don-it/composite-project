package com.don.demo.basic.path;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * 获取类路径，内部路径
 * 1. getResource 获取URL，有protocol，本地文件的protocol是file:// ；都是target路径 ；底层依赖classLoader，不同的classLoader有不同的反应
 * 2. getResourceAsStream 底层就是先获取URL，然后openStream
 */
public class ClassPathTest {

    //获取URL
    @Test
    public void getURL() throws Exception {
        // 获取classpath ： classes/(重要)，
        URL s1 = ClassPathTest.class.getResource("/");
        URL s2 = ClassPathTest.class.getResource("/server-node.properties");
        System.out.println(s1);
        System.out.println(s2);

        // 获取PathAndResources.class类文件所在的路径(重要)
        URL s3 = ClassPathTest.class.getResource("");
        URL s4 = ClassPathTest.class.getResource("SystemEvnAndProperties.class");
        System.out.println(s3);
        System.out.println(s4);

        // 从来都是null
        URL s5 = ClassPathTest.class.getClassLoader().getResource("/");
        System.out.println(s5);

        // 获取类的根路径classes/
        URL s6 = ClassPathTest.class.getClassLoader().getResource("");
        System.out.println(s6);

        // 获取类的根路径classes/
        URL s7 = Thread.currentThread().getContextClassLoader().getResource("");
        System.out.println(s7);

    }

    public static void main(String[] args) throws Exception {
        new ClassPathTest().getStream();
    }
    //--------------取流InputStream-----------------------
    @Test
    public void getStream() throws Exception {

        // 从类的根路径classes/下读取配置文件(重要) 有
        InputStream is = ClassPathTest.class.getResourceAsStream("/server-node.properties");
        byte[] bytes = new byte[1024];
        is.read(bytes);
        System.out.println(new String(bytes));
        System.out.println("InputStream is:" + (is == null ? "null" : "not null"));

        // 在PathAndResources类所在目录找文件(重要) null
        InputStream is7 = ClassPathTest.class.getResourceAsStream("server-node.properties");
        System.out.println("InputStream is:" + (is7 == null ? "null" : "not null"));

        // 从来都是null
        InputStream is2 = ClassPathTest.class.getClassLoader().getResourceAsStream("/server-node.properties");
        System.out.println("InputStream is:" + (is2 == null ? "null" : "not null"));

        // 从类的根路径classes/下读取配置文件
        InputStream is3 = ClassPathTest.class.getClassLoader().getResourceAsStream("server-node.properties");
        System.out.println("InputStream is:" + (is3 == null ? "null" : "not null"));

    }

    //--------------URLEncode-----------------------
    @Test
    public void URLEncode() throws Exception {

        //-------------------%20 转换为 空格--------------------
        String path = "file:/E:/workspace3.6/configure%20huido/web/WEB-INF/classes/server-node.properties";
        path = java.net.URLDecoder.decode(path, "UTF-8");
        System.out.println(path);
    }

    /**
     * 测试使用getResourceAsStream生成stream
     */
    @Test
    public void properties() throws Exception {
        Properties properties = new Properties();
        InputStream resourceAsStream = ClassPathTest.class.getResourceAsStream("/server-node.properties");
        properties.load(resourceAsStream);
        System.out.println(properties);
    }


}
