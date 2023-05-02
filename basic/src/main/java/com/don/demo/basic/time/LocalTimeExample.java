package com.don.demo.basic.time;

import java.time.LocalTime;
import java.time.ZoneId;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName LocalTimeExample
 * @date 2019年08月11日 下午 2:19
 */
public class LocalTimeExample {
    public static void main(String[] args) {
        //Current Time
        LocalTime time = LocalTime.now();
        System.out.println("Current Time=" + time);

        //Creating LocalTime by providing input arguments
        LocalTime specificTime = LocalTime.of(12, 20, 25, 40);
        System.out.println("Specific Time of Day=" + specificTime);

        //Try creating time by providing invalid inputs
        //LocalTime invalidTime = LocalTime.of(25,20);
        //Exception in semaphore "main" java.time.DateTimeException:
        //Invalid value for HourOfDay (valid values 0 - 23): 25

        //Current date in "Asia/Kolkata", you can get it from ZoneId javadoc
        LocalTime timeKolkata = LocalTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println("Current Time in IST=" + timeKolkata);

        //java.time.zone.ZoneRulesException: Unknown time-zone ID: IST
        //LocalTime todayIST = LocalTime.now(ZoneId.of("IST"));

        //Getting date from the base date i.e 01/01/1970
        LocalTime specificSecondTime = LocalTime.ofSecondOfDay(10000);
        System.out.println("10000th second time= " + specificSecondTime);
    }
}
