package com.don.review.interview;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName BitCalculate
 * @date 2019年09月01日 上午 10:55
 */
public class BitCalculate {
	public static void main(String[] args) {
		//System.out.println(5 << 3);
		//System.out.println(-41111 << 3);
		//System.out.println(Integer.MAX_VALUE);
		//System.out.println(0b00110001);
		//System.out.println(-1610612737<<1<<1<<1<<1);
		//S4ystem.out.println(-1610612737<<1);
		//System.out.println(1073741823<<3);
		//System.out.println("--");
		//System.out.println(-5>>1);
		//System.out.println(-16>>3);
		//System.out.println(-17>>3);
		//System.out.println(-18>>3);
		//System.out.println(-19>>3);
		//System.out.println(-20>>3);
		//System.out.println(-21>>3);
		//System.out.println(-22>>3);
		//System.out.println(-23>>3);
		//System.out.println(-24>>3);
		//System.out.println(-25>>3);
		//System.out.println(-21|-1);
		int intValue = 733183670;//随意写一个数
		System.out.println("intValue：" + (intValue));//打印intValue
		System.out.println("intValue右移1位：" + (intValue >> 1));//右移1位
		System.out.println("intValue右移8位：" + (intValue >> 8));//右移8位
//当int类型右移位数大于等于32位操作时，会先求余后再进行移位操作
		System.out.println("intValue右移32位：" + (intValue >> 32));//求余为32%32=0，相当于右移0位（不移位）
		System.out.println("intValue右移40位：" + (intValue >> 40));//求余为40%32=8，相当于右移8位
		System.out.println("intValue右移64位：" + (intValue >> 64));//求余为64%32=0，相当于右移0位（不移位）

		long longValue = 733183670L;
		System.out.println("longValue：" + (longValue));//打印longValue
		System.out.println("longValue右移1位：" + (longValue >> 1));//右移1位
		System.out.println("longValue右移8位：" + (longValue >> 8));//右移8位
//当long类型右移位数大于等于64位操作时，会先求余后再进行移位操作
		System.out.println("longValue右移64位：" + (longValue >> 64));//求余为64%64=0，相当于右移0位（不移位）
		System.out.println("longValue右移72位：" + (longValue >> 72));//求余为72%64=8，相当于右移8位
		System.out.println("longValue右移128位：" + (longValue >> 128));//求余为128%64=0，相当于右移0位（不移位）

	}
}
