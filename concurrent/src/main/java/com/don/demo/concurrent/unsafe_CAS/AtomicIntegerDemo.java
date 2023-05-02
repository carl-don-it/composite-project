package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger可以是自增保持原子性
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月23日 上午 10:23
 */
public class AtomicIntegerDemo {
	public AtomicInteger inc = new AtomicInteger();

	public void increase() {
		inc.getAndIncrement();
	}

	public static void main(String[] args) throws InterruptedException {
		final AtomicIntegerDemo test = new AtomicIntegerDemo();
		for (int i = 0; i < 100; i++) {
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
