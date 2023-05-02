package com.don.demo.basic.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName DateParseFormatExample
 * @date 2019年08月11日 下午 4:20
 */
public class DateParseFormatExample {
	public static void main(String[] args) {
		//Format examples

        /*
        LocalDate
         */
		LocalDate date = LocalDate.now();
		//default format
		System.out.println("Default format of LocalDate=" + date);
		//specific format
		System.out.println(date.format(DateTimeFormatter.ofPattern("d::MMM::uuuu")));
		System.out.println(date.format(DateTimeFormatter.BASIC_ISO_DATE));

        /*
        LocalDateTime
         */
		LocalDateTime dateTime = LocalDateTime.now();
		//default format
		System.out.println("Default format of LocalDateTime=" + dateTime);
		//specific format
		System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d:MMM::uuuu HH::mm::ss")));
		System.out.println(dateTime.format(DateTimeFormatter.BASIC_ISO_DATE));

        /*
        Instant
         */
		Instant timestamp = Instant.now();
		//default format
		System.out.println("Default format of Instant=" + timestamp);

		//Parse examples
		LocalDateTime dt = LocalDateTime.parse("27::八月::2014 21::39::48",
				DateTimeFormatter.ofPattern("d::MMM::yyyy HH::mm::ss"));
		System.out.println("Default format after parsing = " + dt);
	}
}
