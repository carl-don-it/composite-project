package com.don.demo.basic.path;

import java.io.File;
import java.io.IOException;

/**
 * 找出工作目录，可以打成jar看看
 *
 * @author Carl Don
 * @Date 2020/5/10  19:09
 * @Version 1.0
 */
public class WorkingDirectoryTest {

    public static void main(String[] args) throws IOException {

        //在工作目录下创建一个txt文件
        File file = new File("hello.txt");
        file.createNewFile();

        //工作目录路径和txt文件路径（父子关系）
        System.out.println(new File("").getAbsolutePath());
        System.out.println(file.getAbsolutePath());
    }
}
