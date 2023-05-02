package com.don.review;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName CallableTest
 * @date 2019年10月08日 下午 8:12
 */
public class CallableTest {
	public static void main(String[] args) throws Exception {
		System.out.println(new Date());
		Callable<String> callable = new Callable<>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				System.out.println("callable");
				return "完成";
			}
		};

		//System.out.println(callable.call());
		System.out.println("结束");
	}
}
