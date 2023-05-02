package com.don.demo.concurrent.sharedata.differentrunnable1;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName RunnableCusToDec
 * @date 2019年08月15日 下午 7:31
 */
//线程1
class RunnableCusToDec implements Runnable {
	//封装共享数据
	private ShareData shareData;

	public RunnableCusToDec(ShareData data) {
		this.shareData = data;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			shareData.decrease();
		}
	}
}
