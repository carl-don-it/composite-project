package com.don.demo.concurrent.sharedata.differentrunnable1;

/**
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月06日 上午 10:39
 */
public class Test {
	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		for (int i = 0; i < 4; i++) {
			if (i % 2 == 0) {
				new Thread(new RunnableCusToInc(shareData), "Thread " + i).start();
			} else {
				new Thread(new RunnableCusToDec(shareData), "Thread " + i).start();
			}
		}
	}
}
