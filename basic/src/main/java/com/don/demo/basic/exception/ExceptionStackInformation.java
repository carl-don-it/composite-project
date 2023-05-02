package com.don.demo.basic.exception;

/**
 * 异常信息栈是怎么打出来的
 * Exception in thread "main" java.lang.RuntimeException: 3  //造成程序结束的最外面的异常
 *      at com.don.review.exception.ExcpetiionStackInformation.ex3(ExcpetiionStackInformation.java:28)//异常最开始抛出的位置 ： ex3()的28行，没有处理，接着往上抛
 *      at com.don.review.exception.ExcpetiionStackInformation.main(ExcpetiionStackInformation.java:35)//抛出异常的方法在哪里被调用：main函数调用了ex3(),位置是35行
 * Caused by: java.lang.RuntimeException: 2 // 上面异常是由本异常引起抛出的
 * <p>
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年10月25日 下午 2:54
 */
public class ExceptionStackInformation {
	public void ex1() {
		throw new RuntimeException("1");
	}

	public void ex2() {
		try {
			ex1();
		} catch (Exception e) {
			throw new RuntimeException("2", e);
		}

	}

	public void ex3() {
		try {
			ex2();
		} catch (Exception e) {
			throw new RuntimeException("3", e);
		}

	}

	public static void main(String[] args) {
		ExceptionStackInformation nestedException = new ExceptionStackInformation();
		nestedException.ex3();
	}
}
