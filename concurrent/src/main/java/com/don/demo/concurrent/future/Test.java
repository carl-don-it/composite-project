package com.don.demo.concurrent.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * 有返回值的线程
 */
@SuppressWarnings("unchecked")
public class Test {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		System.out.println("----程序开始运行----");
		Date date1 = new Date();

		int taskSize = 5;
		// 创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		List<Future> list = new ArrayList<Future>();

		// 执行任务并获取 Future 对象
		for (int i = 0; i < taskSize; i++) {
			Callable c = new MyCallable(i + " ");
			Future f = pool.submit(c);
			list.add(f);
		}
		// 关闭线程池
		pool.shutdown();

		System.out.println("关闭线程池");

		for (Future f : list) {
			// 从 Future 对象上获取任务的返回值，并输出到控制台
			System.out.println(">>>" + f.get().toString());
		}

		Date date2 = new Date();
		System.out.println("----程序结束运行----，程序运行时间【"
				+ (date2.getTime() - date1.getTime()) + "毫秒】 ");
	}
}

class MyCallable implements Callable<Object> {
	private String taskNum;

	MyCallable(String taskNum) {
		this.taskNum = taskNum;
	}

	public Object call() throws Exception {
		System.out.println(">>>" + taskNum + "任务启动");
		Date dateTmp1 = new Date();
		Thread.sleep(5000);
		Date dateTmp2 = new Date();
		long time = dateTmp2.getTime() - dateTmp1.getTime();
		System.out.println(">>>" + taskNum + "任务终止");
		return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】 ";
	}
}