package com.don.demo.concurrent.threadlocal;

import lombok.Getter;
import lombok.Setter;

/**
 * 1.2 私有静态 ThreadLocal 实例
 */
@Getter
@Setter
public class ThreadContext {
	private String userId;
	private Long transactionId;

	private static ThreadLocal<ThreadContext> threadLocal = new ThreadLocal() {
		@Override
		protected ThreadContext initialValue() {
			return new ThreadContext();
		}
	};

	public static ThreadContext get() {
		return threadLocal.get();
	}

	//测试
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				//每个线程的ThreadContext都不一样
				ThreadContext threadContext = ThreadContext.get();
				System.out.println(Thread.currentThread().getName() + " : " + threadContext);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}

	}
}
