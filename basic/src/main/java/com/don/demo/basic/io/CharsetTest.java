package com.don.demo.basic.io;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * @author Carl Don
 * @Date 2020/9/18  16:22
 * @Version 1.0
 */
public class CharsetTest {

    public static void main(String[] args) {
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        Charset charset = Charset.defaultCharset();
        System.out.println(charset);
    }

}
