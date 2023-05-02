package com.don.demo.basic.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * pipe，线程之间通信
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月09日 上午 7:56
 */
public class PipeExample {

	public static void main(String[] args) throws IOException {

		final PipedOutputStream output = new PipedOutputStream();
		final PipedInputStream input = new PipedInputStream(output);

		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					output.write(("Hello world, pipe!" + Thread.currentThread().getName()).getBytes());
				} catch (IOException e) {
				}
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					int data = input.read();
					while (data != -1) {
						System.err.print((char) data);
						data = input.read();
					}
				} catch (IOException e) {
				}
			}
		});

		thread1.start();
		thread2.start();

	}
}
