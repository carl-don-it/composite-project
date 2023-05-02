package com.don.demo.basic.generic;

import org.junit.Test;

import java.util.*;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName Test1
 * @date 2019年09月16日 下午 7:38
 */
public class MyGenericMethod {
	public <MVP> void show(MVP mvp) {
		System.out.println(mvp.getClass());
	}

	public <MVP> MVP show2(MVP mvp) {
		return mvp;
	}

	//定义一个含有泛型的方法，返回值确定泛型

	public <M> List<M> method03() {
		return new ArrayList<M>();
	}

	public static void main(String[] args) {
		// 创建对象
		MyGenericMethod mm = new MyGenericMethod();
		// 演示看方法提示
		mm.show("aaa");
		Integer integer = mm.show2(123);
		Double aDouble = mm.show2(12.45);

		List<Object> objects = mm.method03();
		List<String> strings = mm.<String>method03();
	}

	@SuppressWarnings("unchecked")
	@Test
	//测试泛型通配符？如何使用，一般用来作接收类型
	public void test3() {
		//普通的ArrayList对象
		ArrayList<LinkedHashMap<String, String>> linkedHashMaps = new ArrayList<>();
		//添加元素
		linkedHashMaps.add(new LinkedHashMap<String, String>());
		linkedHashMaps.add(new LinkedHashMap<String, String>());
		//赋值给有上限的泛型集合
		ArrayList<? extends HashMap<String, String>> hashMaps = linkedHashMaps;
		//strings.add(new LinkedHashMap()); 错误，不能添加元素，只能遍历
		System.out.println(Arrays.toString(hashMaps.toArray()));
		//或者强转后可以使用
		linkedHashMaps = (ArrayList<LinkedHashMap<String, String>>) hashMaps;
		linkedHashMaps.add(new LinkedHashMap<String, String>());

	}

	@Test
	//测试泛型数组如何创建
	public void test2() {
		//创建普通数组
		Integer[] integers = new Integer[3];
		ArrayList[] arrayLists = new ArrayList[3];

		//创建集合数组，但是不能使用泛型来创建  List<M>[] list =  new ArrayList<M>[3] 错的
		List<String>[] list = new ArrayList[3];
		//使用泛型类型变量来接收，只能add进String元素
		list[0] = new ArrayList<>();
		List<String> strings = list[0];
		list[0].add("strin1g");

		System.out.println(list);
	}

	@Test
	//测试一下这两个不同泛型集合的类型是否相同
	public void test1() {
		ArrayList<String> strings = new ArrayList<>();
		ArrayList<Integer> integers = new ArrayList<>();

		System.out.println(integers.getClass());//class java.util.ArrayList
		System.out.println(strings.getClass());//class java.util.ArrayList

		System.out.println(strings.getClass() == integers.getClass());//true
	}
}
