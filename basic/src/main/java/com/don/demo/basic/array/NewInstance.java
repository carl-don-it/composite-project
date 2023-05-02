package com.don.demo.basic.array;

import org.junit.Test;

import java.lang.reflect.Array;

/**
 * 只能反射创建数组
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月15日 下午 2:11
 */
public class NewInstance {

	public <T> T[] getArray(Class<? extends T> componentClass, int length) {
		return (T[]) Array.newInstance(componentClass, length);
	}

	@Test
	public void test1() {
		String[] array = getArray(String.class, 10);
		System.out.println(array.length);//10
	}
}
