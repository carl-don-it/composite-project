package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 引用拿到和更新的合并原子操作，比较鸡肋啊，通常不需要改变引用变量的指向
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月30日 下午 3:21
 */
public class AtomicReferenceDemo2 {
	//其他线程有可能随时修改
	static User user = new User("zejian", 18);

	public static AtomicReference<User> atomicUserRef = new AtomicReference<User>();

	public static void main(String[] args) throws InterruptedException {
		atomicUserRef.set(user);//new的时候设置value，也可以现在set更改
		User updateUser = new User("Shine", 25);

		//其他线程有可能随时修改user的变量
		new Thread(new Runnable() {
			@Override
			public void run() {
				AtomicReferenceDemo2.user = null;
			}
		}).start();

		Thread.sleep(1000);//执行结果:User{name='zejian', age=18},注释结果：User{name='Shine', age=25}

		atomicUserRef.compareAndSet(user, updateUser);
		System.out.println(atomicUserRef.get().toString());
	}

	static class User {
		public String name;
		private int age;

		public User(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "User{" +
					"name='" + name + '\'' +
					", age=" + age +
					'}';
		}
	}
}
