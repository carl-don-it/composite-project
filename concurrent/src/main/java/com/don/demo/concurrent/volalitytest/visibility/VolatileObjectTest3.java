package com.don.demo.concurrent.volalitytest.visibility;

/**
 * 对象没有volatile，域具有volatile
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 7:20
 */
public class VolatileObjectTest3 implements Runnable {
	private ObjectA a;

	@Override
	public void run() {
		long i = 0;
		while (a != null) {//b没有volatile，就不会取读主存的新值，重复读缓存
			if (a.isFlag())//但是现在会读吃一次
				i++;
		}
		System.out.println("stop My Thread " + i);
	}

	public void stop() {
		System.out.println("stop");
		a.setFlag(true);
		a = null;
	}

	public static void main(String[] args) throws InterruptedException {
		// 如果启动的时候加上-server 参数则会 输出 Java HotSpot(TM) Server VM
		System.out.println(System.getProperty("java.vm.name"));

		VolatileObjectTest3 test = new VolatileObjectTest3(new ObjectA());
		new Thread(test).start();

		Thread.sleep(1000);
		test.stop();
		Thread.sleep(1000);
		System.out.println("Main Thread ");
	}

	static class ObjectA {
		private volatile boolean flag = false;//如果不加volatile，就会不可见，线程不结束

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	}

	public VolatileObjectTest3(ObjectA a) {
		this.a = a;
	}

	public ObjectA getA() {
		return a;
	}

	public void setA(ObjectA a) {
		this.a = a;
	}
}
