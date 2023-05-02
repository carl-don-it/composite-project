package com.don.demo.basic.collection.IteratorTest;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Carl Don
 * @version V1.0
 */
public class IteratorTest {

	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		strings.add("1");
		strings.add("2");
		strings.add("3");
		strings.add("4");

		Iterator<String> iterator = strings.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			if (next.equals("1")) {
				iterator.remove();
			}
		}
	}
}
