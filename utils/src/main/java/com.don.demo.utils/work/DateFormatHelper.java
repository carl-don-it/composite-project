package com.don.demo.utils.work;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat线程不安全，改造
 */
public class DateFormatHelper {

    private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    //写法一，和上面的初始化一起哦！
    public static Date convert(String source) {
        try {
            Thread.sleep(100);
            return df.get().parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
