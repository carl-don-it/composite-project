package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference原子类是一个带有时间戳的对象引用，在每次修改后，AtomicStampedReference不仅会设置新值而且还会记录更改的时间。当AtomicStampedReference设置对象值时，对象值以及时间戳都必须满足期望值才能写入成功，这也就解决了反复读写时，无法预知值是否已被修改的窘境，测试demo如下
 */
public class ABADemo {

	static AtomicInteger atIn = new AtomicInteger(100);

	//初始化时需要传入一个初始值和初始时间
	static AtomicStampedReference<Integer> atomicStampedR = new AtomicStampedReference<Integer>(100, 0);

	/*
	  t1，和t2不一定能证明aba问题存在，也许t1先行执行完成
	 */
	static Thread t1 = new Thread(new Runnable() {
		@Override
		public void run() {
			//更新为200
			atIn.compareAndSet(100, 200);
			//更新为100
			atIn.compareAndSet(200, 100);
		}
	});

	static Thread t2 = new Thread(new Runnable() {
		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean flag = atIn.compareAndSet(100, 500);
			System.out.println("flag:" + flag + ",newValue:" + atIn);
		}
	});

	/*
	可能结果
	time1: 0; time2: 1; now: 2
	100
	time3：0
	flag1:false,newValue:100
	有可能aba之后失败，也有可能只是单纯被a抢先之后失败，但绝对能避免aba问题
	 */
	static Thread t3 = new Thread(new Runnable() {
		@Override
		public void run() {
			int time1 = atomicStampedR.getStamp();
			//更新为200
			atomicStampedR.compareAndSet(100, 10, time1, time1 + 1);
			//更新为100
			int time2 = atomicStampedR.getStamp();
			atomicStampedR.compareAndSet(10, 100, time2, time2 + 1);
			System.out.println("time1: " + time1 + "; time2: " + time2 + "; now: " + atomicStampedR.getStamp());
			System.out.println(atomicStampedR.getReference());

		}
	});

	static Thread t4 = new Thread(new Runnable() {
		@Override
		public void run() {
			int time3 = atomicStampedR.getStamp();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			boolean flag = atomicStampedR.compareAndSet(100, 27, time3, time3 + 1);
			System.out.println("time3：" + time3);
			System.out.println("flag1:" + flag + ",newValue:" + atomicStampedR.getReference());
		}
	});

	public static void main(String[] args) throws InterruptedException {
		t1.start();
		t2.start();
		t1.join();
		t2.join();

		System.out.println("---------------------");

		t3.start();
		t4.start();
	}
}
