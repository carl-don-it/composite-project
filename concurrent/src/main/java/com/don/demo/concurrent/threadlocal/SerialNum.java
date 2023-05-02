package com.don.demo.concurrent.threadlocal;

/**
 * 1.1 私有静态 ThreadLocal 实例
 * <p>
 * 在下面的类中，私有静态 threadlocal 实例（serialNum）为调用该类的静态 SerialNum.get() 方法的每个
 * 线程维护了一个“序列号” ，该方法将返回当前线程的序列号。（线程的序列号是在第一次调用 SerialNum.get() 时
 * 分配的，并在后续调用中不会更改。）
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName SerialNum
 * @date 2019年08月14日 下午 11:05
 */
public class SerialNum {
	// The next serial number to be assigned
	private static int nextSerialNum = 0;

	private static ThreadLocal serialNum = new ThreadLocal() {

		@Override
		//因为需要修改nextSerialNum，所以需要同步锁
		protected synchronized Object initialValue() {
			return nextSerialNum++;
		}

	};

	//每个线程都会创建自己的Map，然后把initialValue值塞进去
	public static int get() {
		return (Integer) (serialNum.get());
	}

	//测试
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				//每个线程的SerialNum（i）都是不同的
				System.out.println(Thread.currentThread().getName() + " : " + SerialNum.get());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}

	}
}
