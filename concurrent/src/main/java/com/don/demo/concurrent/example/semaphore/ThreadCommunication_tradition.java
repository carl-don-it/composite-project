package com.don.demo.concurrent.example.semaphore;

/**
 * 三个线程 a、 b、 c 并发运行， b,c 需要 a 线程的数据怎么实现（上海 3 期学员提供）
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName ThreadCommunication_tradition
 * @date 2019年08月16日 下午 9:36
 */
public class ThreadCommunication_tradition {

	private static int num;//定义一个变量作为数据
	private static boolean flag = false;

	private static void test2() {
		synchronized (ThreadCommunication_tradition.class) {
			if (!flag) {
				try {
					ThreadCommunication_tradition.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);

		}
	}

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (ThreadCommunication_tradition.class) {
					if (!flag) {
						try {
							//模拟耗时操作之后初始化变量 num
							Thread.sleep(1000);
							num = 1;
							flag = true;
							ThreadCommunication_tradition.class.notifyAll();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		});

		Thread threadB = new Thread(new Runnable() {

			@Override
			public void run() {
				test2();
			}
		});

		Thread threadC = new Thread(new Runnable() {

			@Override
			public void run() {
				test2();
			}

		});
		//同时开启 3 个线程
		threadA.start();
		threadB.start();
		threadC.start();

	}
}
