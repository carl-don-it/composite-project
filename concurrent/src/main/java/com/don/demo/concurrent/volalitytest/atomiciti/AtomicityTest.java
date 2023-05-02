package com.don.demo.concurrent.volalitytest.atomiciti;

/**
 * volatile无法保证原子性
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月23日 上午 10:31
 */
public class AtomicityTest {

	public volatile int inc = 0;

	public void increase() {
		inc++;
	}

	public static void main(String[] args) {
		final AtomicityTest test = new AtomicityTest();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				}

				;
			}.start();
		}

		while (Thread.activeCount() > 2)  //保证前面的线程都执行完,至少有两条线程
			Thread.yield();
		System.out.println(test.inc);
	}

}
