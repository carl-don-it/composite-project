package com.don.demo.utils.work;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 都是调用jdk的代码，两个方法结果都一样，时间差不多
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年03月15日 上午 10:05
 */
public class Md5Util {

    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    public static String getMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = str.getBytes("utf-8");
            byte[] m = md.digest(input); //加密完成了
            BigInteger big = new BigInteger(1, m);
            return big.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出现错误");
        }
    }

    //测试两者的时间，差不多的，结果一样
    @Test
    public void test() throws Exception {
        String source;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            sb.append("sddfsdfsdfs");
        }
        source = sb.toString();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            String md5 = getMD5(source);
            String s = MD5(source, "utf-8");

        }
        long finish = System.currentTimeMillis();

        System.out.println(finish - begin);
    }
}
