package com.don.demo.concurrent.sharedata.differentrunnable2;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName ShareData
 * @date 2019年08月15日 下午 8:16
 */
public class ShareData {

	private int num = 11;

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
		num--;
		System.err.println(Thread.currentThread().getName() + ": invoke decrease method num =" + num);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//公共数据
		final ShareData shareData = new ShareData();
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i < 5; i++) {
							shareData.increase();
						}
					}
				}, "Thread " + i).start();
			} else {
				new Thread(new Runnable() {
					@Override
					public void run() {
						for (int i = 0; i < 5; i++) {
							shareData.decrease();
						}
					}
				}, "Thread " + i).start();
			}
		}
	}
}
