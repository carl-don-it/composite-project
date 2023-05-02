package com.don.demo.concurrent.deadlock;

/**
 * join ,等待另一个线程die
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName DeadLock_solve1
 * @date 2019年08月17日 下午 3:24  2019-12-08 21:37:43
 */
public class DeadLock_solve1 {
	public int flag = 1;
	//静态对象是类的所有对象共享的
	private static Object o1 = new Object(), o2 = new Object();

	public void money(int flag) {
		this.flag = flag;
		if (flag == 1) {
			synchronized (o1) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o2) {
					System.out.println("当前的线程是" +
							Thread.currentThread().getName() + " " + "flag 的值" + "1");
				}
			}
		}
		if (flag == 0) {
			synchronized (o2) {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (o1) {
					System.out.println("当前的线程是" +
							Thread.currentThread().getName() + " " + "flag 的值" + "0");
				}
			}
		}
	}

	public static void main(String[] args) {
		final DeadLock_solve1 td1 = new DeadLock_solve1();
		final DeadLock_solve1 td2 = new DeadLock_solve1();
		td1.flag = 1;
		td2.flag = 0;

		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				td1.flag = 1;
				td1.money(1);
			}
		});

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					//让 t2 等待 t1 执行完
					t1.join();// focus 核心代码，让 t1 执行完后 t2 才会执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				td2.flag = 0;
				td1.money(0);
			}
		});

		t2.start();
		t1.start();
	}
}
