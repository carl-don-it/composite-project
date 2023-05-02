package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName TestCode
 * @date 2019年08月25日 下午 2:57
 */
public class TestCode {
	public int foo() {
		int x;
		try {
			//    1 / 0
			x = 1;
			return x;
		} catch (Exception e) {
			x = 2;
			return x;
		} finally {
			x = 3;
			return x;
			//    System.out.println(x);
		}
	}

	public static void main(String[] args) {
		System.out.println(new TestCode().foo());
	}
}
