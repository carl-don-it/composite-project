package com.don.demo.concurrent.example.tradition;

/**
 * 子线程运行执行 10 次后，主线程再运行 5 次。这样交替执行三遍，线程之间的通信
 * 锁的粒度要小，
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月04日 下午 8:11
 */
public class Bussiness {
	private boolean subFlag = true;

	//需要同步的部分
	public synchronized void mainMethod() {
		while (subFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()
					+ " : main thread running loop count -- " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		subFlag = true;
		notify();
	}

	//锁的粒度要小，在这里加锁
	public synchronized void subMethod() {
		while (!subFlag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < 10; i++) {
			System.err.println(Thread.currentThread().getName()
					+ " : sub thread running loop count -- " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		subFlag = false;
		notify();
	}

	public static void main(String[] args) {
		final Bussiness bussiness = new Bussiness();
		//子线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 3; i++) {

					bussiness.subMethod();
				}
			}
		}).start();
		//主线程
		for (int i = 0; i < 3; i++) {
			bussiness.mainMethod();
		}
	}
}

