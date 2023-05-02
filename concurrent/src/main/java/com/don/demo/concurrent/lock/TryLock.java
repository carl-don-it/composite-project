package com.don.demo.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()会马上退出
 * 而lock()会阻塞
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月01日 上午 10:13
 */
public class TryLock {

	private static Lock lock = new ReentrantLock();

	public static void main(String[] args) {
		Runnable run1 = new Runnable() {
			public void run() {
				lock.lock();
				try {
					Thread.sleep(00);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				lock.unlock();
			}
		};
		Runnable run2 = new Runnable() {
			@Override
			public void run() {

				if (lock.tryLock()) {
					try {
						// manipulate protected state
						System.out.println("try lock successfully");
					} finally {
						lock.unlock();
					}
				} else {
					// perform alternative actions
					System.out.println("try lock fail");
				}
			}
		};
		new Thread(run1).start(); //获取该对象的方法 1
		new Thread(run2).start(); //获取该对象的方法 2
	}
}
