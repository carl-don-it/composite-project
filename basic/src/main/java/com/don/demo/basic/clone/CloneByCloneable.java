package com.don.demo.basic.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

/**
 * 实现 Cloneable 接口并重写 Object 类中的 clone()方法；
 * 浅clone，只会clone八大基本类型，对象只会clone指针，递归重写实现深度clone；
 * String是不变类，没必要多创建一个对象，用同一个对象就可以。
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName CloneByCloneable
 * @date 2019年08月09日 下午 6:20
 */

public class CloneByCloneable {
	//clone
	@Test
	public void test1() throws CloneNotSupportedException {
		Person p1 = new Person("awesome", 12, new Head(10, "头"));
		Person p2 = (Person) p1.clone();

		System.out.println(p1);
		System.out.println(p2);

		System.out.println(p1.getName() == p2.getName());//对象只会clone指针，因此是相等的
		System.out.println(p1.getHead() == p2.getHead());
	}

	@Data
	@ToString
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Person implements Cloneable {

		private String name;
		private int age;
		private Head head;

		@Override
		public Object clone() throws CloneNotSupportedException {
			//自身需要clone，浅clone，只会clone八大基本类型，对象只会clone指针，String是不变类，没必要多创建一个对象，用同一个对象就可以
			Person clone2 = (Person) super.clone();

			//以下是深度clone需要的
			Object clone1 = head.clone();
			clone2.setHead((Head) clone1);

			return clone2;
		}

	}

	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Head implements Cloneable {
		private int size;
		private String string;

		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

	}
}
