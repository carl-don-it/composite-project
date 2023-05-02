package com.don.demo.concurrent.example.semaphore;

/**
 * 三个线程 a、 b、 c 并发运行， b,c 需要 a 线程的数据怎么实现
 * <p>
 * 根据问题的描述，我将问题用以下代码演示， ThreadA、 ThreadB、 ThreadC， ThreadA 用于初始化数据 num，只有当 num 初始化完成之后再让 ThreadB 和 ThreadC 获取到初始化后的变量 num。
 * <p>
 * 现在需要解决两个难题，一是让 ThreadB 和 ThreadC 等待 ThreadA 先执行完，二是 ThreadA 执行完之后给ThreadB 和 ThreadC 发送消息。
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName ThreadCommunication_question
 * @date 2019年08月17日 上午 10:57
 */
public class ThreadCommunication_question {
	private static int num;//定义一个变量作为数据

	public static void main(String[] args) {

		Thread threadA = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					//模拟耗时操作之后初始化变量 num
					Thread.sleep(1000);
					num = 1;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		Thread threadB = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);
			}
		});

		Thread threadC = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + "获取到 num 的值为： " + num);
			}
		});

		//同时开启 3 个线程,这时候三个线程是没有通信的
		threadA.start();
		threadB.start();
		threadC.start();

	}
}
