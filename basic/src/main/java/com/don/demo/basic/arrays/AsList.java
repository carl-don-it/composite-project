package com.don.demo.basic.arrays;

import java.util.Arrays;
import java.util.List;

/**
 * 测试AsList方法，泛型是不能包括基本类型的，但是可以包括数组类型
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月12日 下午 6:59
 */
public class AsList {
	public static void main(String[] args) {
		int[] basic = {1, 2, 3, 4, 5, 5};
		Integer[] refer = {1111, 2222, 3333, 4444, 5555, 66665};
		//传入数组如果是一个基本类型数组（如int[]），那么将返回一个大小为1的列表，且列表元素就是传入的这个数组；
		List<int[]> ints = Arrays.asList(basic);
		List<Integer> integers = Arrays.asList(refer);
		List<Integer> integers1 = Arrays.asList(1111, 2222, 3333, 4444, 5555, 66665);

		//没有重写该方法，报错
		//integers.add(4);

		System.out.println(ints);
		System.out.println(integers);
		System.out.println(integers1);
	}
}
