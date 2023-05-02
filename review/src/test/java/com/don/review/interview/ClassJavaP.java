package com.don.review.interview;

/**
 * javap看看编译出来的东西
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName ClassJavaP
 * @date 2019年09月13日 下午 5:48
 */
public class ClassJavaP {
	//String abc = "可以";
	//static String huge = "Buxing";
	//final static String big = "boxing";

	int i = 3;
	Integer hello = 10;

	Test3 test3 = new Test3();

	public static void main(String[] args) {
		System.out.println("Buxing");
		//System.out.println(huge);
		//System.out.println(big);
		System.out.println(new Test3());
	}
}
