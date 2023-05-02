package com.don.demo.basic.exception;

/**
 * 如果存在finally语句而且catch发生抛出异常（并未真正抛出，还要等待finally块执行之后），此时finally如果正好也抛出了异常，则真正抛出的异常就是finally中的异常，同时程序也就结束。所以catch发生抛出异常并未真正抛出。
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月13日 下午 7:36
 */
public class ThrowProblem {
	public static void main(String[] args) throws Exception {
		try {
			throw new MyException("异常1");
		} catch (MyException e) {
			throw e;
		} finally {
			throw new ArithmeticException("异常3");
		}
	}

	private static class MyException extends Exception {
		MyException(String message) {
		}
	}
}