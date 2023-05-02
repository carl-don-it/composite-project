package com.don.demo.concurrent.sharedata.samerunnable;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName RunnableCusToInc
 * @date 2019年08月15日 下午 7:03
 */
public class RunnableCusToInc implements Runnable {
	private ShareData shareData;

	public RunnableCusToInc(ShareData data) {
		this.shareData = data;
	}

	@Override
	public void run() {
		//每个线程对shareData处理五次,shareData是同一个对象
		for (int i = 0; i < 50; i++) {
			shareData.edit();
		}
	}

	/**
	 * 结果并不是随机切换的
	 **/
	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		RunnableCusToInc target = new RunnableCusToInc(shareData);
		for (int i = 0; i < 4; i++) {
			new Thread(target, "Thread " + i).start();
		}
	}
}
