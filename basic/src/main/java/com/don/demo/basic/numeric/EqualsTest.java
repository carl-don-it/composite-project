package com.don.demo.basic.numeric;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月08日 下午 4:22
 */
public class EqualsTest {
	public static void main(String[] args) {

		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		Long h = 2L;

		System.out.println(c == d);
		System.out.println(e == f);
		System.out.println(c == (a + b));
		System.out.println(c.equals(a + b));
		System.out.println(g == (a + b));
		System.out.println(g.equals(a + b));
		System.out.println(g.equals(a + h));
	}
}
