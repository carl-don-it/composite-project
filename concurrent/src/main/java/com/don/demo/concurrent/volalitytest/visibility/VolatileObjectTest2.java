package com.don.demo.concurrent.volalitytest.visibility;

/**
 * 和test一样，不过需要再次赋值，a变成volatile
 * <p>
 * 把volatile的a 赋给局部变量没有volatile的b，那么b就没有可见性，都是引用地址
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 7:20
 */
public class VolatileObjectTest2 implements Runnable {
	private volatile ObjectA a;// 加上volatile，也不可以正常结束While循环

	@Override
	public void run() {
		long i = 0;
		ObjectA b = a; //即便a是volatile，换一个变量就不可以了
		while (b.isFlag()) {//b没有volatile，就不会取读主存的新值，重复读缓存
			i++;
			//System.out.println("------------------");
		}
		System.out.println("stop My Thread " + i);
	}

	public void stop() {
		System.out.println("stop");
		a.setFlag(false);
	}

	public static void main(String[] args) throws InterruptedException {
		// 如果启动的时候加上-server 参数则会 输出 Java HotSpot(TM) Server VM
		System.out.println(System.getProperty("java.vm.name"));

		VolatileObjectTest2 test = new VolatileObjectTest2(new ObjectA());
		new Thread(test).start();

		Thread.sleep(1000);
		test.stop();
		Thread.sleep(1000);
		System.out.println("Main Thread " + test.getA().isFlag());
	}

	static class ObjectA {
		private boolean flag = true;

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	}

	public VolatileObjectTest2(ObjectA a) {
		this.a = a;
	}

	public ObjectA getA() {
		return a;
	}

	public void setA(ObjectA a) {
		this.a = a;
	}
}
