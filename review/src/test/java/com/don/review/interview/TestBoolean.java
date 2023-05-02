package com.don.review.interview;

/**
 * 测试Boolean包装类是否使用了常量池技术
 * <p>
 * 事实上是有的
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName TestBoolean
 * @date 2019年09月13日 下午 4:11
 */
public class TestBoolean {
	public static void main(String[] args) {
		Boolean a = true;
		Boolean b = true;
		Boolean c = false;
		Boolean d = false;
		System.out.println(a == b);//true
		System.out.println(a == c);//false
		System.out.println(c == d);//true
	}
}
