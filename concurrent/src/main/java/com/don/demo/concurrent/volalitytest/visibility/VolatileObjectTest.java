package com.don.demo.concurrent.volalitytest.visibility;

/**
 * 验证volatile对对象内部元素的可见性
 * <p>
 * 1. 主线程改动a.flag,次线程能否看到
 * <p>
 * volatile修饰的变量如果是对象或数组之类的，其含义是对象获数组的地址具有可见性。
 * eg：以下代码要以-server模式运行，强制虚拟机开启优化
 * 加volatile关键字，子线程是可以退出循环的，不加的话，子线程没法退出循环，
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 7:20
 */
public class VolatileObjectTest implements Runnable {
	private ObjectA a;  //	加上volatile 就可以正常结束While循环了

	@Override
	public void run() {
		long i = 0;

		while (a.isFlag()) {//如果没有volatile，就不会读取主存的新值，重复读缓存
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

		VolatileObjectTest test = new VolatileObjectTest(new ObjectA());
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

	public VolatileObjectTest(ObjectA a) {
		this.a = a;
	}

	public ObjectA getA() {
		return a;
	}

	public void setA(ObjectA a) {
		this.a = a;
	}
}
