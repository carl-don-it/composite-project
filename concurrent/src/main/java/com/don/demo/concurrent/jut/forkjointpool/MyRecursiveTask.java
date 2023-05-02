package com.don.demo.concurrent.jut.forkjointpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * MyRecursiveTask 类 继 承 自
 * RecursiveTask<Long>，这也就意味着它将返回一个 Long 类型的结果
 *
 * @author Walker_Don
 * @version V1.0
 * @ClassName MyRecursiveTask
 * @date 2019年08月16日 下午 5:37
 */
public class MyRecursiveTask extends RecursiveTask<Long> {
	private long workLoad = 0;

	public MyRecursiveTask(long workLoad) {
		this.workLoad = workLoad;
	}

	@Override
	protected Long compute() {
		//if work is above threshold, break tasks up into smaller tasks
		if (this.workLoad > 16) {
			System.out.println("Splitting workLoad : " + this.workLoad);

			List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
			subtasks.addAll(createSubtasks());

			for (MyRecursiveTask subtask : subtasks) {
				subtask.fork();
			}
			long result = 0;
			for (MyRecursiveTask subtask : subtasks) {
				result += subtask.join();
			}
			return result;
		} else {
			System.out.println("Doing workLoad myself: " + this.workLoad);
			return workLoad * 3;
		}
	}

	private List<MyRecursiveTask> createSubtasks() {
		List<MyRecursiveTask> subtasks =
				new ArrayList<MyRecursiveTask>();
		MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
		MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);
		subtasks.add(subtask1);
		subtasks.add(subtask2);
		return subtasks;
	}

	public static void main(String[] args) {
		//创建了一个并行级别为 4 的 ForkJoinPool
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		//创建一个有返回值的任务
		MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);//线程池执行并返回结果
		long mergedResult = forkJoinPool.invoke(myRecursiveTask);
		System.out.println("mergedResult = " + mergedResult);
	}
}