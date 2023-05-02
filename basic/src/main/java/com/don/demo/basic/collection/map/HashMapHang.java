package com.don.demo.basic.collection.map;

import java.util.HashMap;

/**
 * 不知道这个程序干什么的 todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName HashMapHang
 * @date 2019年09月19日 下午 4:14
 */
public class HashMapHang {

	private HashMap map = new HashMap();

	public HashMapHang() {
		Thread t1 = new Thread() {
			public void run() {
				for (int i = 0; i < 50000; i++) {
					map.put(new Integer(i), i);
				}
				System.out.println("t1 over");
			}
		};

		Thread t2 = new Thread() {
			public void run() {
				for (int i = 0; i < 50000; i++) {
					map.put(new Integer(i), i);
				}

				System.out.println("t2 over");
			}
		};

		t1.start();
		t2.start();

	}

	public static void main(String[] args) {
		// new HashMapHang();
		int i = 9;
		boolean b1 = i == (i = 8);
		boolean b = b1;
	}
}

