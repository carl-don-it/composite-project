package com.don.demo.concurrent.volalitytest.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 这次使用对象数组
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 5:23
 */
public class VolatileArray2 {
	//public static  Object[] objects = new Object[5];
	public static volatile Object[] objects = new Object[5];

	public static void main(String[] args) throws Exception {

		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			objects[0] = new Object();
		}).start();

		new Thread(() -> {
			//Object[] objects = VolatileArray2.objects; //放开注释不会退出
			while (true) {
				if (objects[0] != null) {
					System.out.println("结束");
					break;
				}
			}
		}).start();
	}
}
