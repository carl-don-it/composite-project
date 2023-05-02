package com.don.demo.concurrent.deadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 再在外围加上一层锁，外层锁lock控制时间
 * <p>
 * 加锁时限（线程尝试获取锁的时候加上一定的时限，超过时限则放弃对该锁的请求，并释放自己占有的锁）
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName DeadLock_solve2
 * @date 2019年08月17日 下午 3:51 2019-12-08 21:37:39
 */
public class DeadLock_solve2 {
	public int flag = 1;
	//静态对象是类的所有对象共享的
	private static Object o1 = new Object(), o2 = new Object();

	public void money(int flag) throws InterruptedException {
		this.flag = flag;
		if (flag == 1) {
			synchronized (o1) {
				Thread.sleep(500);
				synchronized (o2) {
					System.out.println("当前的线程是" +
							Thread.currentThread().getName() + " " + "flag 的值" + "1");
				}
			}
		}
		if (flag == 0) {
			synchronized (o2) {
				Thread.sleep(6000);        //如果thread2先开始，会超过6000ms，thread获取不到锁
				synchronized (o1) {
					System.out.println("当前的线程是" +
							Thread.currentThread().getName() + " " + "flag 的值" + "0");
				}
			}
		}
	}

	public static void main(String[] args) {
		final Lock lock = new ReentrantLock();

		final DeadLock_solve2 td1 = new DeadLock_solve2();
		final DeadLock_solve2 td2 = new DeadLock_solve2();

		td1.flag = 1;
		td2.flag = 0;

		final Thread t1 = new Thread(new Runnable() {
			public void run() {
				String threadName = Thread.currentThread().getName();
				td1.flag = 1;
				{//外围加lock锁
					try {
						//获取不到锁，就等 5 秒，如果 5 秒后还是获取不到就返回 false
						if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
							System.out.println(threadName + "获取到锁！ ");
						} else {
							System.out.println(threadName + "获取不到锁！ ");
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						td1.money(1);
					} catch (Exception e) {
						System.out.println(threadName + "出错了！！！ ");
					} finally {
						System.out.println("当前的线程是" + Thread.currentThread().getName() + "释放锁！！");
						lock.unlock();
					}
				}

			}
		}, "thread1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				String threadName = Thread.currentThread().getName();
				td1.flag = 1;
				{//外围加lock锁控制外围锁的时间
					try {
						//获取不到锁，就等 5 秒，如果 5 秒后还是获取不到就返回 false
						if (lock.tryLock(5000, TimeUnit.MILLISECONDS)) {
							System.out.println(threadName + "获取到锁！ ");
						} else {
							System.out.println(threadName + "获取不到锁！ ");
							return;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						td2.money(0);  //Thread.sleep(6000);//替换，一样的效果
					} catch (Exception e) {
						System.out.println(threadName + "出错了！！！ ");
					} finally {
						System.out.println("当前的线程是" + Thread.currentThread().getName() + "释放锁！！");
						lock.unlock();
					}
				}
			}
		}, "thread2");

		t1.start();
		t2.start();
	}
}

