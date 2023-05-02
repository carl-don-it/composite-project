package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.atomic.LongAdder;

/**
 * 我们知道，AtomicLong的实现原理是：利用底层操作系统的CAS来保证原子性，在一个死循环内不断执行CAS操作，直到操作成功。不过，CAS操作的一个问题是在并发量比较大的时候，可能很多次的执行CAS操作都不成功，这样性能就受到较大影响。
 * <p>
 * 那我们知道，在ConcurrentHashMap中，对Map分割成多个segment，这样多个Segment的操作就可以并行执行，从而可以提高性能。在JDK8中，LongAdder与ConcurrentHashMap类似，将内部操作数据value分离成一个Cell数组，每个线程访问时，通过Hash等算法映射到其中一个Cell上。
 * <p>
 * 计算最终的数据结果，则是各个Cell数组的累计求和。
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月05日 下午 4:21
 */
public class LongAdderTest {
	static java.util.concurrent.atomic.LongAdder count = new LongAdder();

	static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 100000; i++) {
				count.increment();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(count.intValue());
	}
}