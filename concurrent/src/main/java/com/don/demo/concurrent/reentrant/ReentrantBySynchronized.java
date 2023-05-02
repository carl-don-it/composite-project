package com.don.demo.concurrent.reentrant;

/**
 * synchronized演示可重入锁
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月15日 下午 4:29
 */
public class ReentrantBySynchronized implements Runnable {

	public synchronized void get() {
		System.out.println(Thread.currentThread().getName());
		set();
	}

	public synchronized void set() {
		System.out.println(Thread.currentThread().getName());
	}

	public void run() {
		get();
	}

	public static void main(String[] args) {
		ReentrantBySynchronized rt = new ReentrantBySynchronized();
		for (; ; ) {
			new Thread(rt).start();
		}
	}

}
