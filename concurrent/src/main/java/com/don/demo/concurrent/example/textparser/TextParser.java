package com.don.demo.concurrent.example.textparser;

import com.don.demo.concurrent.example.textparser.scanner.SingleLineScanner;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Carl Don
 * @version V1.0
 */
public class TextParser {

	private final Map<String, Integer> wordFrequency = new HashMap<>(2 << 16);    //词频表,

	private File[] fileList;
	;    //所有需要扫描的file

	private int residueFile = 0;    //剩下多少个file需要扫描

	public void parseText() {
		//1. 从系统读取文本地址和线程数量，并且初始化线程数量
		String text_dir = System.getProperty("text_dir");
		String thread_number_string = System.getProperty("thread_number");
		int thread_number = Integer.parseInt(thread_number_string);
		if (thread_number == 0) thread_number = 10;

		//2. 读取文本地址
		File text_directory = new File(text_dir);
		if (!text_directory.isDirectory())
			throw new RuntimeException("文件地址不是文件夹");

		//把所有的txt文件存入list中
		fileList = text_directory.listFiles(new FileFilter() {
			@Override
			public boolean accept(File file) {
				return file.getName().endsWith(".txt");
			}
		});

		residueFile = fileList.length;

		//3. 获取线程池
		ExecutorService service = getThreadPool(thread_number);

		//4. 对每个文本进行解析，启用一个新的线程
		for (File file : fileList) {
			TextScanner textScanner = getTextScanner(file, this);
			service.submit(textScanner);
		}
		//5. 关闭线程池
		service.shutdown();
	}

	// 创建线程池对象,子类可以按需重写
	public ExecutorService getThreadPool(int thread_number) {
		return Executors.newFixedThreadPool(thread_number);
	}

	// 对每一个文件采用一个textScanner,子类可以按需重写
	public TextScanner getTextScanner(File file, TextParser textParser) {
		return new SingleLineScanner(file, textParser);
	}

	public Map<String, Integer> getWordFrequency() {
		return wordFrequency;
	}

	public void setWordFrequency(Map<String, Integer> wordFrequency) {
		this.wordFrequency.putAll(wordFrequency);
	}

	public File[] getFileList() {
		return fileList;
	}

	public void setFileList(File[] fileList) {
		this.fileList = fileList;
	}

	public int getResidueFile() {
		return residueFile;
	}

	public void setResidueFile(int residueFile) {
		this.residueFile = residueFile;
	}

}
