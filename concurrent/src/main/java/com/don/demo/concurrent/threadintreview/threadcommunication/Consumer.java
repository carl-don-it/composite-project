package com.don.review.se_esay.threadintreview.threadcommunication;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName Consumer
 * @date 2019年08月17日 下午 5:27
 */
//消费者消费资源
class Consumer implements Runnable {
	private Resource res;

	Consumer(Resource res) {
		this.res = res;
	}

	public void run() {
		while (true) {
			res.out();
		}
	}
}