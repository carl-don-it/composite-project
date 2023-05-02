package com.don.demo.basic.io.buffer;

import java.io.*;

public class BufferedDemo {
	public static void main(String[] args) throws FileNotFoundException {
		// 记录开始时间
		long start = System.currentTimeMillis();
		// 创建流对象
		try (
				InputStream bis = new BufferedInputStream(new FileInputStream("io/ea安装包.rar"));
				OutputStream bos = new BufferedOutputStream(new FileOutputStream("io/copy1231.rar"));
		) {
			// 读写数据
			int len;
			byte[] bytes = new byte[80 * 1024];
			while ((len = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, len);
			}
			System.out.println(len = bis.read(bytes));//由于底层是文件，不会阻塞，因此一直输出-1，文件是有终结标志的
			System.out.println(len = bis.read(bytes));

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		System.out.println("缓冲流使用数组复制时间:" + (end - start) + " 毫秒");
	}

}
