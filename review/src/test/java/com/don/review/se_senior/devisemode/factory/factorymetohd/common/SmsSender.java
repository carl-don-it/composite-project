package com.don.review.se_senior.devisemode.factory.factorymetohd.common;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName SmsSender
 * @date 2019年08月17日 下午 6:09
 */
public class SmsSender implements Sender {

	@Override
	public void send() {
		System.out.println("this is sms sender!");
	}
}
