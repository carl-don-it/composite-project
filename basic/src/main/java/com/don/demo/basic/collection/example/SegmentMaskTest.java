package com.don.demo.basic.collection.example;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * (hash >>> segmentShift) & segmentMask 的 segmentMask是否有必要存在
 * 只有concurrentLevel为1的时候才需要
 * 或者后续jdk优化
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 上午 9:46
 */
public class SegmentMaskTest {
	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int concurrencyLevel;
		for (int i = 1; i <= 1 << 16; i++) {
			//迭代测试所有可能的concurrencyLevel
			concurrencyLevel = i;

			int segmentShift;
			int segmentMask;

			int sshift = 0;
			int ssize = 1;
			while (ssize < concurrencyLevel) {
				++sshift;
				ssize <<= 1;
			}
			segmentShift = 32 - sshift;
			segmentMask = ssize - 1;

			int hash = new Object().hashCode();

			System.out.println(concurrencyLevel);
			//如果不相等，那么就存起来最后观察结果
			if (((hash >>> segmentShift) & segmentMask) != (hash >>> segmentShift)) {
				Integer integer = map.get(concurrencyLevel);
				if (integer != null) {
					map.put(concurrencyLevel, integer + 1);
				} else {
					map.put(concurrencyLevel, 1);
				}

			}
		}
		System.out.println(map);

	}

	@Test
	public void test() {
		Map<Integer, Integer> map = new ConcurrentHashMap<Integer, Integer>(1444, 1, 1);
		System.out.println(1);
	}
}
