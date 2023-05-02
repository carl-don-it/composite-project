package com.don.review.se_senior.devisemode.factory.factorymetohd.static1;

/**
 * 静态工厂方法模式，将上面的多个工厂方法模式里的方法置为静态的，不需要创建实例，直接调用即可。
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
	public static Sender produceMail() {
		return new MailSender();
	}

	public static Sender produceSms() {
		return new SmsSender();
	}

	public static void main(String[] args) {
		Sender sender = SendFactory.produceMail();
		sender.send();
	}
}
