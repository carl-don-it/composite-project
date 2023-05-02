package com.don.demo.utils.work;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * 用于转换代码点和二进制编码
 *
 * @author Carl Don
 * @Date 2020/5/9  15:38
 * @Version 1.0
 */
public class CodePoint2Bin {
// 引用了 Apache Commons Lang3

    /**
     * 把代码点转换为相应的字符
     */
    static String codePoint2String(int codePoint) {
        return new String(Character.toChars(codePoint));
    }

    /**
     * 传入字符和相应的编码，返回计算机使用的二进制编码
     * 只为演示，未优化方法，未处理 RuntimeException
     */
    static String binStr(String str, String encoding)
            throws UnsupportedEncodingException {
        Byte[] bytes = ArrayUtils.toObject(str.getBytes(encoding));
        //大小端
        if (Byte.toUnsignedInt(bytes[0]) == 0xfe && Byte.toUnsignedInt(bytes[1]) == 0xff) {
            bytes = Arrays.copyOfRange(bytes, 2, bytes.length);
        }
        return Arrays.stream(bytes).collect(
                StringBuilder::new,
                (sb, b) -> {
                    if (sb.length() > 0) {
                        sb.append(' ');
                    }
                    String s = Integer.toBinaryString(Byte.toUnsignedInt(b));
                    sb.append(StringUtils.leftPad(s, 8, '0'));
                },
                (sb1, sb2) -> {
                    sb1.append(' ').append(sb2);
                }
        ).toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        //
        String s = codePoint2String(0x61);
        String s2 = codePoint2String(0x5ff0);
        String s3 = codePoint2String(0xffff);
        String s4 = codePoint2String(0x10ffff);//超出utf-16普通平面了
        char c = 0x5fff;
        // char c1 =0x100000;

        String s1 = binStr(s2, "UTF-16");

        System.out.println();
    }
}
