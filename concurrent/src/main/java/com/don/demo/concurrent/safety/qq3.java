package com.don.demo.concurrent.safety;

/**
 * 匿名内部类的this是内部类的对象，监视器this不一样，因此是两个独立的方法，不互斥,因此对count的修改有线程安全问题
 * <p>
 * focus 但count没有出现线程安全问题？ 解析：由于java的锁优化机制，System.out.println()是加锁的，但是很不稳定，count++
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月08日 下午 7:36
 */

public class qq3 {
	private static int count = 0;

	public static void main(String[] args) {
		Runnable run1 = new Runnable() {
			public void run() {
				synchronized (this) { //设置关键字 synchronized，以当前类为锁
					//synchronized (qq3.this) { //同一把锁
					while (count < 10000) {
						try {
							//打印是否执行该方法
							System.out.println(Thread.currentThread().getName() + " run1: " + count++);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		Runnable run2 = new Runnable() {
			@Override
			public void run() {
				synchronized (this) {
					//	synchronized (qq3.this) {//同一把锁
					while (count < 10000) {
						try {
							System.out.println(Thread.currentThread().getName() + " run2: " + count++);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		};
		new Thread(run1).start(); //获取该对象的方法 1
		new Thread(run2).start(); //获取该对象的方法 2
	}
}
