package com.don.demo.basic.io;

import java.io.*;

/**
 * 也是一个filter stream , 拿出底层的inputStream，然后在底层byte和上层primitive Java data types 之间进行转换，
 * 可以和 bufferedInputStream 等filter进行组合使用，bufferedInputStream在底层
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月09日 上午 11:28
 */
public class DataOutPutTest {

    public static void main(String[] args) throws Exception {
        File file = new File("io/123.txt");
        File binary = new File("io/binary.txt");

        DataInputStream input = new DataInputStream(new FileInputStream(binary));

        int aByte = input.read();//b在utf-8中就是100，就是读一个buty
        int anInt = input.readInt();//读4个byte，转化为java 的int
        float aFloat = input.readFloat();
        double aDouble = input.readDouble();//etc.

        input.close();

        DataOutputStream output = new DataOutputStream(new FileOutputStream("io/binary.txt"));

        output.write(45);
        output.writeInt(454545);
        output.writeDouble(109.123);
        output.write("45".getBytes());

        output.close();
    }
}
