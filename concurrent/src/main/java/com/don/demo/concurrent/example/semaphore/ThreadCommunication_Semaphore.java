package com.don.demo.concurrent.example.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author Walker_Don
 * @version V1.0
 * @ClassName ThreadCommunication_Semaphore
 * @date 2019年08月17日 上午 10:57
 */
public class ThreadCommunication_Semaphore {

	private static int num;
	/**
	 * 定义一个信号量，该类内部维持了多个线程锁，可以阻塞多个线程，释放多个线程，
	 * 线程的阻塞和释放是通过 permit 概念来实现的
	 * 线程通过 semaphore.acquire()方法获取 permit，如果当前 semaphore 有 permit 则分配给该线程，
	 * 如果没有则阻塞该线程直到 semaphore
	 * 调用 release（）方法释放 permit。
	 * 构造函数中参数： permit（允许） 个数，
	 */
	private static Semaphore semaphore = new Semaphore(0);

	public static void main(String[] args) {

		Thread threadA = new Thread(() -> {
			try {
				//模拟耗时操作之后初始化变量 num
				Thread.sleep(1000);
				num = 1;

				//初始化完参数后释放两个 permit
				semaphore.release(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		Thread threadB = new Thread(() -> {
			try {
				//获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);
		});

		Thread threadC = new Thread(() -> {
			try {
				//获取 permit，如果 semaphore 没有可用的 permit 则等待，如果有则消耗一个
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);
		});

		//同时开启 3 个线程
		threadA.start();
		threadB.start();
		threadC.start();

	}
}

