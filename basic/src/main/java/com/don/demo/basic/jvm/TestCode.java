package com.don.demo.basic.jvm;

/**
 * try catch模块怎么走，finally模块会重复走，还有不知名异常
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月19日 上午 8:33
 */
public class TestCode {
	public int foo() {
		int x;
		try {
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
		}
	}

	public static void main(String[] args) {
		int j = 0;
		for (int i = 0; i < 100; i++)
			j = ++j;
		System.out.println(j);
	}
}
