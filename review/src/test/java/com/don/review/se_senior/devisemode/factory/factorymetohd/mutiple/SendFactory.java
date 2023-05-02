package com.don.review.se_senior.devisemode.factory.factorymetohd.mutiple;

/**
 * 该模式是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象，而
 * 多个工厂方法模式是提供多个工厂方法，分别创建对象。
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName SendFactory
 * @date 2019年08月17日 下午 6:19
 */

import com.don.review.se_senior.devisemode.factory.factorymetohd.common.MailSender;
import com.don.review.se_senior.devisemode.factory.factorymetohd.common.Sender;
import com.don.review.se_senior.devisemode.factory.factorymetohd.common.SmsSender;

public class SendFactory {
	public Sender produceMail() {
		return new MailSender();
	}

	public Sender produceSms() {
		return new SmsSender();
	}

	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produceMail();
		sender.send();
	}
}
