package com.don.demo.concurrent.timetask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器demo
 *
 * @version V1.0
 * @ClassName TimeTaskDemo
 * @date 2019年08月14日 下午 5:22
 */
public class TimeTaskDemo {
	//控制定时的倍数
	private static volatile int count = 0;

	static class TimerTaskCus extends TimerTask {

		//定时任务内容
		@Override
		public void run() {
			System.out.println("任务开始");
			count = (count + 1) % 2;
			System.out.println("Boob boom ");
			//再次重复创建一个新的timer任务，时间是2s或者4秒
			new Timer().schedule(new TimerTaskCus(), 2000 + 2000 * count);
		}
	}

	public static void main(String[] args) {
		// 创建一个2s后运行的任务
		Timer timer = new Timer();
		timer.schedule(new TimerTaskCus(), 2000 + 2000 * count);

		// 每一秒打印出当前的秒数
		while (true) {
			System.out.println("现在是第几秒：" + new Date().getSeconds());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
