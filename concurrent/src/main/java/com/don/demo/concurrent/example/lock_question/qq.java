package com.don.demo.concurrent.example.lock_question;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 相同的lock对象,因此两个方法互斥
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName qq
 * @date 2019年08月17日 上午 10:54
 */
public class qq {

	private int count = 0;
	private Lock lock = new ReentrantLock();//设置 lock 锁
	//方法 1
	public Runnable run1 = new Runnable() {
		public void run() {
			lock.lock(); //加锁
			while (count < 1000) {
				try {
					//打印是否执行该方法
					System.out.println(Thread.currentThread().getName() + " run1: " + count++);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
	};
	//方法 2
	public Runnable run2 = new Runnable() {
		public void run() {
			lock.lock();
			while (count < 1000) {
				try {
					System.out.println(Thread.currentThread().getName() +
							" run2: " + count++);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			lock.unlock();
		}
	};

	public static void main(String[] args) throws InterruptedException {
		qq t = new qq(); //创建一个对象
		new Thread(t.run1).start();//获取该对象的方法 1
		new Thread(t.run2).start();//获取该对象的方法 2
	}
}
