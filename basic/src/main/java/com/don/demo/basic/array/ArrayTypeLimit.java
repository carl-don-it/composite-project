package com.don.demo.basic.array;

import org.junit.Test;

/**
 * Array的类型是限制的，只能赋给该类和子类
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月15日 下午 2:09
 */
public class ArrayTypeLimit {

	//不可以把元素设为类型的父类
	@Test
	public void test1() {
		User[] users = new User[]{
				new User(),
				new User(),
				new User()
		};

		Object[] target = users;
		System.out.println(target.getClass());  // class [Ltest.User;
		target[0] = new Object();   // 异常java.lang.ArrayStoreException: java.lang.Object
	}

	//可以把元素设为类型的子类
	@Test
	public void test2() {
		Object[] target = new Object[11];
		target[0] = new User();
		System.out.println(target);
	}

	private static class User {
	}

}
