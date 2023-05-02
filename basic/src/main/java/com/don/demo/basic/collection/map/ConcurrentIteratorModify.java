package com.don.demo.basic.collection.map;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 普通集合hashMap的iterate的过程中不能修改集合，因此需要同步
 * 问题在于 java.util.HashMap$ValueIterator.next();   modCount != expectedModCount; throw new ConcurrentModificationException();
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月27日 上午 10:35
 */
public class ConcurrentIteratorModify {
	public static void main(String[] args) {
		//初始化集合
		//HashMap<String, String> map = new HashMap<String, String>();
		Map<String, String> map = new ConcurrentHashMap<String, String>(); //ConcurrentHashMap的iterator不会报错
		for (int i = 0; i < 500; i++) {
			map.put("t" + i, "test" + i);
		}

		Set<Map.Entry<String, String>> entries = map.entrySet();
		Iterator<Map.Entry<String, String>> iterator = entries.iterator();
		Thread modifyThread = new Thread(new ModifyMap(map));

		//另一线程修改集合
		modifyThread.start();

		//主线程迭代集合
		for (; iterator.hasNext(); ) {
			System.out.println(iterator.next().getValue());
		}
	}

	static class ModifyMap implements Runnable {
		Map<String, String> map;

		public ModifyMap(Map<String, String> map) {
			this.map = map;
		}

		public void run() {
			//以下任何一个方法都很大可能报错
			System.out.println(map.remove("t5")+"---------------");
			System.out.println(map.put("t1000", "test5")+"------------");
		}
	}
}