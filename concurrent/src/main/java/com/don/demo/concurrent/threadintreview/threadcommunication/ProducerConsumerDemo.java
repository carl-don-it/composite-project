package com.don.review.se_esay.threadintreview.threadcommunication;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName ProducerConsumerDemo
 * @date 2019年08月17日 下午 5:27
 */
public class ProducerConsumerDemo {
	public static void main(String[] args) {
		Resource r = new Resource();
		Producer pro = new Producer(r);
		Consumer con = new Consumer(r);
		Thread t1 = new Thread(pro);
		Thread t2 = new Thread(con);
		t1.start();
		t2.start();
	}
}
