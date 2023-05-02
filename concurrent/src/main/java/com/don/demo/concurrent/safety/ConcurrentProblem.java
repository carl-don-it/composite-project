package com.don.demo.concurrent.safety;

/**
 * 线程安全问题，缓存一致性问题导致线程不安全：hello的新值不能及时写回主内存，因此结果不会是20000
 * <p>
 * 如果去掉循环内的注释语句，System.out.println是加锁的，有可能带来内部锁粗化优化，也有可能不会，达到20000的可能性大大提高，但是不建议使用
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月15日 下午 6:25
 */
public class ConcurrentProblem implements Runnable {

	private static int hello = 0;//共享变量

	private static void work() {
		//   private synchronized static void work() { // 线程安全
		for (int i = 0; i < 10000; i++) {
			hello++;
			//System.out.println(hello);
		}

		System.out.println(hello);
	}

	@Override
	public void run() {
		work();
	}

	public static void main(String[] args) {
		new Thread(new ConcurrentProblem()).start();
			new Thread(new ConcurrentProblem()).start();
	}

}
