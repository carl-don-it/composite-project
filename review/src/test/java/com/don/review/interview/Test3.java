package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName Test3
 * @date 2019年08月25日 下午 5:37
 */
public class Test3 {
	public static void main(String[] args) {
		new Test3().m1();
		new Test3().m2();
	}

	public void m1() {
		String s1 = "1贷款23";
		String s2 = "456";
		String s3 = s1 + s2;
		System.out.println(s3);
	}

	public void m2() {
		String s1 = "123";
		String s2 = "456";
		StringBuilder sb = new StringBuilder();
		sb.append(s1);
		sb.append(s2);
		String s3 = sb.toString();
		System.out.println(s3);
	}
}
