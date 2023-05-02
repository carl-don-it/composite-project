package com.don.demo.basic.grammar;

/**
 * 静态方法不能使用普通内部类，没有this指针，无法调用普通构造器，这个时候普通内部类的构造器就类似普通方法
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月08日 下午 12:40
 */
public class InnerClass {
	class CommonInnerClass {
	}

	static class StaticInnerClass {
	}

	public static void foo() {
		//new CommonInnerClass();静态方法不能使用普通内部类，报错
	}

	public void foo2() {
		new CommonInnerClass();
	}

	public static void main(String[] args) {
		//new CommonInnerClass(); main函数，静态方法不能使用普通内部类，报错
		new InnerClass().new CommonInnerClass();
		new StaticInnerClass(); // 普通类使用
	}

}
