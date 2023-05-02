package com.don.demo.concurrent.volalitytest.atomiciti;

/**
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月23日 上午 10:34
 */
public class FixBySynchronized {
	public static class Test {
		public int inc = 0;

		public synchronized void increase() {
			inc++;
		}

	}

	public static void main(String[] args) {
		final FixBySynchronized.Test test = new FixBySynchronized.Test();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				}

				;
			}.start();
		}

		while (Thread.activeCount() > 2)  //保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
	}
}
