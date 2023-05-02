package com.don.demo.basic.time;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * Calendar设置时间，Date是时间表示，Dateformat是转换Date和String
 * <p>
 * 时间api的一些方法和java8的新方法
 */
public class CommonTimeApiUsage {

    //获取现在的年月日时分秒
    @Test
    public void test1() {
        //calendar
        Calendar cal = Calendar.getInstance();//GregorianCalendar
        System.out.println(cal.get(Calendar.YEAR));
        System.out.println(cal.get(Calendar.MONTH)); // 0 - 11
        System.out.println(cal.get(Calendar.DATE));
        System.out.println(cal.get(Calendar.HOUR_OF_DAY));
        System.out.println(cal.get(Calendar.MINUTE));
        System.out.println(cal.get(Calendar.SECOND));

        System.out.println();
        System.out.println();
        // Java 8
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt.getYear());
        System.out.println(dt.getMonthValue()); // 1 - 12
        System.out.println(dt.getDayOfMonth());
        System.out.println(dt.getHour());
        System.out.println(dt.getMinute());
        System.out.println(dt.getSecond());
    }

    //从 1970 年 1 月 1 日 0 时 0 分 0 秒到现在的毫秒数,时间戳
    @Test
    public void test2() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();//第一种方式
        long timeMillis = System.currentTimeMillis();//第二种方式
        long millis = Clock.systemDefaultZone().millis();// Java 8
        System.out.println(timeInMillis);
        System.out.println(timeMillis);
        System.out.println(millis);
    }

    //某月的一天
    @Test
    public void test3() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为 1 号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:" + first);

        //获取6月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(2013, Calendar.AUGUST, 2);
        ca.set(Calendar.MONTH, 5);
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:" + last);

        //Java 8
        LocalDate today = LocalDate.now();
        System.out.println(today);
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
        //本月的最后一天
        LocalDate lastDay = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("本月的第一天" + firstday);
        System.out.println("本月的最后一天" + lastDay);
    }

    //打印昨天的当前时刻
    @Test
    public void test4() {
        //calendar
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        System.out.println(cal.getTime());
        System.out.println(new Date());
        //java8
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime yesterday = today.minusDays(1);
        System.out.println(yesterday);
    }

    //格式化日期
    @Test
    public void test5() {
        SimpleDateFormat oldFormatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = new Date();
        System.out.println(oldFormatter.format(date1));

        // Java 8
        DateTimeFormatter newFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date2 = LocalDate.now();
        System.out.println(date2.format(newFormatter));
    }

    //calendar可变
    @Test
    public void test6() {
        Calendar birth = Calendar.getInstance();
        birth.set(1975, Calendar.MAY, 26);
        Calendar now = Calendar.getInstance();
        System.out.println(daysBetween(birth, now));
        System.out.println(daysBetween(birth, now)); // 显示 0？
    }

    //算出两个calendar的相差天数，并且改变calendar的值
    private static long daysBetween(Calendar begin, Calendar end) {
        long daysBetween = 0;
        while (begin.before(end)) {
            begin.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }
}
