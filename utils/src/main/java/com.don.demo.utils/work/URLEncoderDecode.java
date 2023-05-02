package com.don.demo.utils.work;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 可逆，只是转码
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年03月15日 上午 10:27
 */
public class URLEncoderDecode {
    @Test
    public void test() throws UnsupportedEncodingException {
        String sdfs = URLEncoder.encode("file:///C:/Users/TJR_S/OneDrive/%E7%BC%96%E7%A8%8B/database/1.%20MySQL/%E9%AB%98%E7%BA%A7/html/", "utf-8");
        String decode = URLDecoder.decode(sdfs, "utf-8");
        System.out.println();
    }
}
