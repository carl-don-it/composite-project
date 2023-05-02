package com.don.demo.concurrent.example.lock_question;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized的两个方法都是同一个监视器 qq2.class，两个方法互斥
 *
 * @author Walker_Don
 * @version V1.0v
 * @ClassName qq2
 * @date 2019年08月17日 上午 11:46
 */
public class qq2 {
	private static int count = 0;
	private Lock lock = new ReentrantLock();

	public synchronized static void test1() {
		while (count < 1000) {
			//try {
			//打印是否执行该方法
			System.out.println(Thread.currentThread().getName() + " run1: " + count);
			count = count + 1;
		}
	}

	public synchronized static void test2() {
		while (count < 1000) {
			//try {
			//打印是否执行该方法
			System.out.println(Thread.currentThread().getName() + " run1: " + count);
			count = count + 1;
		}
	}

	public Runnable run1 = new Runnable() {
		public void run() {
			test1();
		}
	};

	public Runnable run2 = new Runnable() {
		public void run() {
			test2();
		}
	};

	public static void main(String[] args) throws InterruptedException {
		qq2 t = new qq2(); //创建一个对象
		new Thread(t.run1).start(); //获取该对象的方法 1
		new Thread(t.run2).start(); //获取该对象的方法 2
	}
}