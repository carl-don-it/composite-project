package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName Test4
 * @date 2019年08月25日 下午 6:04
 */
public class Test4 {
	public static void main(String[] args) {
		new Test4().m1();
		new Test4().m2();
	}

	public void m1() {
		String str = "";
		for (int i = 0; i < 5; i++) {
			str = str + i;
		}
		System.out.println(str);
	}

	public void m2() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 5; i++) {
			sb.append(i);
		}
		System.out.println(sb.toString());
	}
}
