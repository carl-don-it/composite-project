package com.don.demo.concurrent.volalitytest.visibility;

/**
 * volatile对内存的影响，
 * b =null 在 a=null 前后调用有很大区别啊？在后面的话，会经过break退出，i=1；证明调用volatile后才会有可见性；但是如果在前面调用，则会由while判断退出，没有经过volatile变量的读取，怎么得到b的可见性？
 * 循环体读到b = nul和a=null的次序都有可能出现，
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月24日 下午 7:20
 */
public class VolatileObjectTest4 implements Runnable {
	public volatile Object a;
	public Object b = new Object();
	public Object c;

	public VolatileObjectTest4() {

	}


	public VolatileObjectTest4(Object a) {
		this.a = a;
	}

	@Override
	public void run() {
		long i = 0;
		while (b != null) {
			//c = a;i=1;
			if (a == null) { //如果我注释这四行，循环不会结束，但是读取volatile变量后，循环肯定会结束。
				i = 1;
				break;
			}
			i++;
		}
		System.out.println("stop My Thread " + i);
	}

	public void stop() {
		System.out.println("stop");
		//b = null; // if comment this line, i = 1
		a = null;
		b = null;  //  if comment this line, i != 1

	}

	public static void main(String[] args) throws InterruptedException {

		VolatileObjectTest4 test = new VolatileObjectTest4(new Object());
		new Thread(test).start();

		Thread.sleep(1000);
		test.stop();
		Thread.sleep(1000);
		System.out.println("Main Thread " + test.a + ";" + test.b);
	}

}
