package com.don.review.se_senior.devisemode.factory.factorymetohd.common;

/**
 * 普通工厂模式，就是建立一个工厂类，对实现了同一接口的一些类进行实例的创建。
 * 这里根据字符串来创建实例
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName SendFactory
 * @date 2019年08月17日 下午 6:09
 */
public class SendFactory {
	public Sender produce(String type) {
		if ("mail".equals(type)) {
			return new MailSender();
		} else if ("sms".equals(type)) {
			return new SmsSender();
		} else {
			System.out.println("请输入正确的类型!");
			return null;
		}
	}
}