package com.don.demo.basic.exception;

/**
 * try、catch的 return的是本地变量表的新的变量（i的复制），如果是基本类型那么后面无法改变（没有指针），除非是引用变量
 * <p>
 * finally 里面的return会覆盖前面try、catch的return
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月19日 下午 7:50
 */
public class ReturnProblem {
	public static void main(String[] args) {

		System.out.println(test0());//1
		System.out.println(test1());//3
		System.out.println(test2());//2
	}

	//finally没有return,
	private static int test0() {
		int i = 0;
		try {
			System.out.println("Try block executing: " + ++i);//1
			return i;
		} catch (Exception e) {
			System.out.println("Catch Error executing: " + ++i);//跳过
			return i;
		} finally {
			System.out.println("finally executing: " + ++i);//2
		}
	}

	//finally有return，覆盖catch
	private static int test1() {
		int i = 0;
		try {
			System.out.println("Try block executing: " + ++i);//1
			throw new Exception();
		} catch (Exception e) {
			System.out.println("Catch Error executing: " + ++i);//2
			return i;
		} finally {
			System.out.println("finally executing: " + ++i);//3
			return i;
		}
	}

	//finally有return，覆盖try
	private static int test2() {
		int i = 0;
		try {
			System.out.println("Try block executing: " + ++i);//1
			return i;
		} catch (Exception e) {
			System.out.println("Catch Error executing: " + ++i);//不执行
			return i;
		} finally {
			System.out.println("finally executing: " + ++i);//2
			return i;
		}
	}
}
