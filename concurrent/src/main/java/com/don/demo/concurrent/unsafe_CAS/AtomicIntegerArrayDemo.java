package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 启动10条线程对数组中的元素进行自增操作，执行结果符合预期。使用方式比较简单，
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月30日 下午 3:44
 */
public class AtomicIntegerArrayDemo {
	static AtomicIntegerArray arr = new AtomicIntegerArray(10);

	public static class AddThread implements Runnable {
		public void run() {
			for (int k = 0; k < 10000; k++)
				//执行数组中元素自增操作,参数为index,即数组下标
				arr.getAndIncrement(k % arr.length());
		}
	}

	public static void main(String[] args) throws InterruptedException {

		Thread[] ts = new Thread[10];
		//创建10条线程
		for (int k = 0; k < 10; k++) {
			ts[k] = new Thread(new AddThread());
		}
		//启动10条线程
		for (int k = 0; k < 10; k++) {
			ts[k].start();
		}
		for (int k = 0; k < 10; k++) {
			ts[k].join();
		}
		//执行结果
		//[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
		System.out.println(arr);
	}
}
