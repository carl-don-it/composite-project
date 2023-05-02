package com.don.review.interview;

/**
 * @author Carl Don
 * @version V1.0
 * @ClassName ThreadSort
 * @date 2019年08月27日 下午 11:26
 */
public class ThreadSort implements Runnable {
	private static int NUM = 1;
	private static int FLAG = 1;
	private int threadNo;

	public ThreadSort(int threadNo) {
		this.threadNo = threadNo;
	}

	@Override
	public void run() {
		synchronized (ThreadSort.class) {
			while (true) {
				if (NUM == 76) {
					break;
				}
				if (threadNo == FLAG) {
					for (int i = 0; i < 5; i++) {
						System.out.println(Thread.currentThread().getName() + " : " + NUM++);
					}
					switch (FLAG) {
						case 1:
							FLAG = 2;
							break;
						case 2:
							FLAG = 3;
							break;
						case 3:
							FLAG = 1;
							break;
					}
					ThreadSort.class.notifyAll();
				} else {
					try {
						ThreadSort.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new ThreadSort(1)).start();
		new Thread(new ThreadSort(2)).start();
		new Thread(new ThreadSort(3)).start();
	}
}