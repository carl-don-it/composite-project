package com.don.demo.concurrent.sharedata.samerunnable;

/**
 * 如果每个线程执行的代码相同，可以使用同一个 Runnable 对象，这个 Runnable 对象中有那个共享数据，例如，
 * 买票系统就可以这么做。
 * <p>
 * 共享数据类
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName ShareData
 * @date 2019年08月15日 下午 7:02
 */
public class ShareData {
	private int num = 1;

	//对每个线程进行处理
	public synchronized void edit() {
		num++;
		System.out.println(Thread.currentThread().getName() + ": invoke edit method num =" + num);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
