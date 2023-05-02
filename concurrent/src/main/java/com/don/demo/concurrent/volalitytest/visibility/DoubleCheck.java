package com.don.demo.concurrent.volalitytest.visibility;

/**
 * 延迟加载，单例模式，懒汉式，volatile关键字，对象逃逸
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月23日 下午 1:48
 */
public class DoubleCheck {
	static class Singleton {
		private volatile static Singleton instance = null;//需要volatile来禁止指令重排序

		private Singleton() {

		}

		public static Singleton getInstance() {
			if (instance == null) {
				synchronized (Singleton.class) {
					if (instance == null)
						instance = new Singleton();
				}
			}
			return instance;
		}
	}
}
