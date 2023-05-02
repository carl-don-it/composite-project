package com.don.demo.concurrent.thread;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName Counter
 * @date 2019年08月16日 下午 8:16
 */
public class Counter {
	private volatile int count = 0;

	public void inc() {
		try {
			Thread.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		count++;
	}

	@Override
	public String toString() {
		return "[count=" + count + "]";
	}

	//---------------------------------华丽的分割线-----------------------------

	public static void main(String[] args) {
		final Counter counter = new Counter();
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					counter.inc();
				}
			}).start();
		}
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(counter);
	}
}
