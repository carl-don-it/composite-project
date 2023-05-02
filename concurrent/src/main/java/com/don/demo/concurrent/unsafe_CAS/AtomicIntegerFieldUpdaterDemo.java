package com.don.demo.concurrent.unsafe_CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @date 2019年12月30日 下午 4:49
 */
public class AtomicIntegerFieldUpdaterDemo {
	public static class Candidate {
		int id;
		volatile int score;
	}

	public static class Game {
		int id;
		volatile String name;

		public Game(int id, String name) {
			this.id = id;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Game{" +
					"id=" + id +
					", name='" + name + '\'' +
					'}';
		}
	}

	static AtomicIntegerFieldUpdater<Candidate> atIntegerUpdater
			= AtomicIntegerFieldUpdater.newUpdater(Candidate.class, "score");

	static AtomicReferenceFieldUpdater<Game, String> atRefUpdate =
			AtomicReferenceFieldUpdater.newUpdater(Game.class, String.class, "name");

	//用于验证分数是否正确
	public static AtomicInteger allScore = new AtomicInteger(0);

	public static void main(String[] args) throws InterruptedException {
		final Candidate stu = new Candidate();
		int threadNum = 100;
		Thread[] t = new Thread[threadNum];
		//开启10000个线程
		for (int i = 0; i < threadNum; i++) {
			t[i] = new Thread() {
				public void run() {
					for (int i = 0; i < 1000; i++) {
						stu.id++; // 没有同步机制
						atIntegerUpdater.incrementAndGet(stu); //使用同步器
						allScore.incrementAndGet();//这个是检验机制
					}
				}
			};
		}

		for (int i = 0; i < threadNum; i++) {
			t[i].start();
		}

		for (int i = 0; i < threadNum; i++) {
			t[i].join();
		}

		System.out.println("最终id=" + stu.id);
		System.out.println("最终分数score=" + stu.score);
		System.out.println("校验分数allScore=" + allScore);

		//AtomicReferenceFieldUpdater 简单的使用
		Game game = new Game(2, "zh");
		atRefUpdate.compareAndSet(game, game.name, "JAVA-HHH");
		System.out.println(game.toString());

		/**
		 * 输出结果:
		 * 最终分数score=5976
		 校验分数allScore=5976
		 Game{id=2, name='JAVA-HHH'}
		 */
	}
}
