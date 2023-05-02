package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName String
 * @date 2019年08月23日 下午 10:15
 */
public class StringTest {
	static final String a = "a";
	static final String b = "b";

	public static void main(String[] args) {
		StringTest stringTest = new StringTest();
		stringTest.test1();
		stringTest.test2();

	}

	private void test1() {
		String hello = "hello";
		String hel = "hel";
		String lo = "lo";

		//变量相加
		System.out.println(hello == hel + lo);  //false
		System.out.println(hello == "hel" + lo); //false

		//常量折叠
		System.out.println("hello" == "hel" + "l" + "o"); //true
		System.out.println(hello == "hel" + "lo");//true
	}

	private void test2() {
		final String hello = "hello";
		final String hel = "hel";
		final String lo = "lo";

		//final常量折叠
		System.out.println(hello == hel + lo);  //true
		System.out.println(hello == "hel" + lo); //true

		System.out.println("hello" == "hel" + "lo"); //true
		System.out.println(hello == "hel" + "lo");//true
	}
}
