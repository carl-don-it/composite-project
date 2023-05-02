package com.don.demo.concurrent.example.semaphore;

import java.util.concurrent.Semaphore;

/**
 * 如何控制某个方法允许并发访问线程的个数
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月08日 下午 5:30
 */
public class SemaphoreTest {

	private static Semaphore semaphore = new Semaphore(5, true);

	private static void test() {
		try {
			//申请一个请求
			semaphore.acquire();
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "进来了");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "走了");
		//释放一个请求,如果不释放，后面的线程一直阻塞
		semaphore.release();
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					test();
				}
			}).start();
		}

	}
}
