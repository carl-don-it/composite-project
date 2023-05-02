package com.don.demo.basic.numeric;

/**
 * 如果一个数大于maxInt会如何转化为int，截取后面32位
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月03日 下午 4:21
 */
public class ExtraMaxInt {
	public static void main(String[] args) {

		System.out.println(Integer.MAX_VALUE);

		int j = (int) (1L << 32);
		System.out.println(j);//

		long i = (1L << 32) * 43 + 7987;
		System.out.println(i);//打印处准确的long
		System.out.println((int) i); // 截取后面32位

	}
}
