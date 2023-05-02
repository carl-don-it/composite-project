package com.don.review.interview;

/**
 * 测试栈帧有几个
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName TestFrame
 * @date 2019年09月12日 下午 2:50
 */
public class TestFrame {
	static int i = 1;

	public static void run1() {
		System.out.println(i++);
		//if(i== 100) return ;
		run1();

	}

	public static void main(String[] args) {
		run1();//StackOverflow
	}
}
