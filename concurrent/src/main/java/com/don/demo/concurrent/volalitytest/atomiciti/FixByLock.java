package com.don.demo.concurrent.volalitytest.atomiciti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月23日 上午 10:32
 */
public class FixByLock {
	static class Test {
		public int inc = 0;
		Lock lock = new ReentrantLock();

		public void increase() {
			lock.lock();
			try {
				inc++;
			} finally {
				lock.unlock();
			}
		}

	}

	public static void main(String[] args) {
		final FixByLock.Test test = new FixByLock.Test();
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
