package com.don.demo.concurrent.sharedata.differentrunnable1;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName ShareData
 * @date 2019年08月15日 下午 7:32
 */
public class ShareData {
	private int num = 1;

	public synchronized void increase() {
		num++;
		System.out.println(Thread.currentThread().getName() + ": invoke edit method num =" + num);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void decrease() {
		num++;
		System.out.println(Thread.currentThread().getName() + ": invoke edit method num =" + num);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
