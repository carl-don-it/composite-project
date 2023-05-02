package com.don.demo.utils.work;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DonStringUtils {

    /**
     * 特殊空格转化为普通空格
     */
    public static String formatSpecialWhiteSpace(String str) {
        return str.replaceAll("\\s", " ");
    }

    /**
     * 两个空格以上转换为一个普通空格
     */
    public static String removeExtraWhiteSpace(String str) {
        return str.replaceAll("\\s{2,}", " ");
    }

    /**
     * 去掉 / \
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("sf\nd//s df\\".replaceAll("[\\\\/\\s]", ""));
    }

    /**
     * 把字符串的中隐含的数字全部提取出来，包括 "."，然后转换成BigDecimal
     */
    public static BigDecimal ParseDecimal(String price) {
        if (StringUtils.isBlank(price)) return null;
        price = price.replaceAll("[^0-9.]", "");
        if (StringUtils.isBlank(price)) return null;
        return new BigDecimal(price);
    }

    /**
     * 把字符串的中日期转换成date，把数字以外的东西全部去掉
     */
    public static Date ParseDate(SimpleDateFormat dateFormat, String date) {
        Date result = null;
        try {
            result = dateFormat.parse(date.replaceAll("[^0-9]", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 把输入的字符串解析出数字，允许包含空格，会处理空格，否则报错
     */
    public Integer ParseInt(String intString) {
        return StringUtils.isNotBlank(intString) ? Integer.valueOf(StringUtils.deleteWhitespace(intString)) : null;
    }


}
