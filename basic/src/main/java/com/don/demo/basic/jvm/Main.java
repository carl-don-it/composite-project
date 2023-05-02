package com.don.demo.basic.jvm;

/**
 * 看看字节码文件是如何的
 * <p>
 * " javac -encoding utf-8 Main.java "
 * " javap -verbose -p Main.class "
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月19日 上午 11:25
 */
public class Main {
	private String string = "string";

	private int m;

	private String neverUser = "goodbye";

	public int inc() {
		return m + 1;
	}

	public int inc1() {
		m = m + 1;
		m = m + 1;
		return m + 1;
	}

	public int inc2() {
		return inc();
	}

	//返回
	public int inc3() {
		return inc2() + 1 + m;
	}

	public int inc4() {
		System.out.println(string);
		System.out.println("13");
		System.out.println(Main.class.getName().getClass().getName());
		System.out.println(m);
		return inc3() + 1 + m;
	}

	//无限循环，
	public void inc5() {
		inc5();
	}

	//调用，没有返回
	public void inc46() {
		inc5();
	}

	//查看本地变量表的load和store情况，只有一个本地变量表，先来后到原则
	public void fun2() {
		int i = 2;
		i++;

		String a = "hello";
		String b = "buy";
		a = a + "sf";

		System.out.println(i);
		System.out.println(b);
		System.out.println(a);
	}
}
