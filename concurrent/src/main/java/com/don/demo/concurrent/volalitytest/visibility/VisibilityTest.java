package com.don.demo.concurrent.volalitytest.visibility;

/**
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月22日 下午 4:54
 */
public class VisibilityTest {
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
