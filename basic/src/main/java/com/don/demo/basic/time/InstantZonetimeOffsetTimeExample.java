package com.don.demo.basic.time;

import java.time.*;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName InstantZonetimeOffsetTimeExample
 * @date 2019年08月11日 下午 2:44
 */
public class InstantZonetimeOffsetTimeExample {
	public static void main(String[] args) {
		//Current timestamp Gmt时区
		Instant ins = Instant.now();
		System.out.println(ins);

		//自定义设置偏移时间
		OffsetDateTime time = ins.atOffset(ZoneOffset.ofHours(8));
		System.out.println(time);

		//默认时区的偏移时间
		OffsetDateTime now = OffsetDateTime.now();
		System.out.println(now);

		//获取系统默认时区时间
		ZonedDateTime systemDefaultzoneDateTime = ins.atZone(ZoneId.systemDefault());
		System.out.println(systemDefaultzoneDateTime);

		//获取特定时区时间
		ZonedDateTime zoneDateTime = ins.atZone(ZoneId.of("Asia/Kolkata"));
		System.out.println(zoneDateTime);

		//获取从1970-01-01  00：00：00到当前时间的秒值，区别？
		System.out.println(ins.getEpochSecond());
		System.out.println(ins.toEpochMilli());

		//Instant from timestamp
		Instant specificTime = Instant.ofEpochMilli(ins.toEpochMilli());
		System.out.println(specificTime);

		//Duration example
		Duration thirtyDay = Duration.ofDays(30);
		System.out.println(thirtyDay);
	}
}
