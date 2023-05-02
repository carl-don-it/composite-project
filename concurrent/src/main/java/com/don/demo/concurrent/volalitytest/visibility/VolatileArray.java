package com.don.demo.concurrent.volalitytest.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 不加volatile的时候，JMM和cpu在空闲时刷新主存， cpu在空闲的时候也会将int[0]的值从线程缓存刷新到主存，只是while(true)太快了，导致cpu一直没空 ,开放注释就可以
 * volatile 修饰对象和数组能保证其内部元素的可见性。
 * volatile就可以强制从内存读取
 * https://segmentfault.com/q/1010000017341320
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 5:23
 */
public class VolatileArray {
	//public static int[] objects = new int[5];
	public static volatile int[] ints = new int[5];

	public static void main(String[] args) throws Exception {
		Object o = new Object();

		new Thread(() -> {
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ints[0] = 2;//volatile修饰数组后，会立马写回主存；即便没有，线程结束也会写回主存，慢一点而已。----可见性
		}).start();

		new Thread(() -> {
			while (true) {
				//System.out.println(1); while(true)太快了，导致cpu一直没空，没有刷新内存
				if (ints[0] == 2) {//volatile修饰数组后，每次进入都会从主存拿，因为是从数组根索引进去；没有volatile就会重复读缓存，导致无法读到新值2。--可见性
					System.out.println("结束");
					break;
				}
			}
		}).start();
	}
}
