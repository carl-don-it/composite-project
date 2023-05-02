package com.don.demo.concurrent.reentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock演示可重入锁
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月15日 下午 4:29
 */
public class ReentrantByReentrantLock implements Runnable {

	private ReentrantLock reentrantLock = new ReentrantLock();

	public void get() {
		reentrantLock.lock();
		System.out.println(Thread.currentThread().getName());
		set();
		reentrantLock.unlock();
	}

	public void set() {
		reentrantLock.lock();
		System.out.println(Thread.currentThread().getName());
		reentrantLock.unlock();
	}

	public void run() {
		get();
	}

	public static void main(String[] args) {
		ReentrantByReentrantLock rt = new ReentrantByReentrantLock();
		for (; ; ) {
			new Thread(rt).start();
		}
	}

}
