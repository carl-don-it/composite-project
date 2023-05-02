package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName String
 * @date 2019年08月23日 下午 10:15
 */
public class StringTest2 {
	static final String a = "a";
	static final String b = "a";

	public static void main(String[] args) {
		System.out.println(StringTest.a == StringTest2.a);//true 类文件的静态常量池中转移到了方法区中的
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
