package com.don.demo.basic.array;

import java.util.Arrays;

/**
 * 在分析ArrayList源码的时候，在clone（）方法中对System.arraycopy()是如何拷贝数据的产生了疑问?网上的答案是浅拷贝，看不到代码的具体实现就做几个例子测试一下

 答案 ，对System.arraycopy()确实是浅拷贝，不会进行递归拷贝，所以产生的结果是基本数据类型是值拷贝，对象只是引用拷贝

 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月12日 下午 7:32
 */
public class SystemArrayCopyTest {
	public static void main(String[] args) {
		Integer[] refer = {1111, 2222, 3333, 4444, 5555, 66665};
		Integer[] integers = Arrays.copyOf(refer, refer.length);
		System.out.println(integers);
	}
}
