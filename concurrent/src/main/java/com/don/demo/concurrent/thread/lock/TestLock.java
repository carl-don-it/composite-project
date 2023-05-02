package com.don.demo.concurrent.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试一下lock是怎么用的
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName TestLock
 * @date 2019年09月19日 下午 4:39
 */
public class TestLock {
	static boolean flag = false;
	private static Lock lock = new ReentrantLock();
	private int i = 0;

	public void add() {
		lock.lock();
		i++;
		if (!flag) {
			flag = true;
			System.out.println("卡" + Thread.currentThread().getName());
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(i + ":" + Thread.currentThread().getName());

		lock.unlock();
	}

	public static void main(String[] args) {
		TestLock testLock = new TestLock();
		TestLock testLock2 = new TestLock();

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < 500; i++) {
					testLock.add();

				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (int i = 0; i < 500; i++) {
					testLock2.add();

				}
			}
		}).start();
	}

	{
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
		ScheduledFuture scheduledFuture = scheduledExecutorService.schedule(new Callable() {
			public Object call() throws Exception {
				System.out.println("Executed!");
				return "Called!";
			}
		}, 5, TimeUnit.SECONDS);//5 秒后执行

	}
}
