package com.don.review.se_esay.threadintreview.threadcommunication;

/**
 * todo
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName Producer
 * @date 2019年08月17日 下午 5:26
 */
//生产者
class Producer implements Runnable {
	private Resource res;

	Producer(Resource res) {
		this.res = res;
	}

	//生产者生产资源
	public void run() {
		while (true) {
			res.set("商品");
		}
	}
}
