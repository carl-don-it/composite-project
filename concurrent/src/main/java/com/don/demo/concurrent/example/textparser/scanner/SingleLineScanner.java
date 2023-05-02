package com.don.demo.concurrent.example.textparser.scanner;

import com.don.demo.concurrent.example.textparser.TextParser;
import com.don.demo.concurrent.example.textparser.TextScanner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Carl Don
 * @version V1.0
 */
public class SingleLineScanner implements TextScanner {
	private Map<String, Integer> temp_wordFrequency = new HashMap<>();        //该文本的词频表，先缓存

	private File file;
	private TextParser textParser;

	public SingleLineScanner(File file, TextParser textParser) {
		this.file = file;
		this.textParser = textParser;
	}

	@Override
	public void run() {
		parseFile();
	}

	@Override
	public void parseFile() {
		//1. 读取文本到temp_wordFrequency
		parseFileToTempMap();
		//2. 把该文本的词频表同步进总的词频表中
		synchronizeMap();
		//3. 最后一个线程输出统计结果
		printlnResult();
	}

	private void parseFileToTempMap() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			// 循环读取,读取到最后返回null
			while ((line = br.readLine()) != null) {
				//词已经存在，+1
				Integer value = temp_wordFrequency.get(line);
				if (value != null) {
					temp_wordFrequency.put(line, value + 1);
				} else {
					//不存在，新建
					temp_wordFrequency.put(line, 1);
				}
			}

			System.out.println(Thread.currentThread() + "完成文本读取;文本是" + file.getAbsolutePath()
					+ ",长度是：" + temp_wordFrequency.size());

		} catch (IOException e) {
			System.out.println("该文件读取失败");//可以存储起来之后再次进行读取
		}
	}

	private void synchronizeMap() {
		for (Map.Entry<String, Integer> temp_entry : temp_wordFrequency.entrySet()) {
			//同步进去总的词频表是原子操作
			synchronized (textParser.getWordFrequency()) {
				//词已经存在,数量加上去
				Integer value = textParser.getWordFrequency().get(temp_entry.getKey());
				if (value != null) {
					textParser.getWordFrequency().put(temp_entry.getKey(), value + temp_entry.getValue());
				} else {
					//不存在，新建
					textParser.getWordFrequency().put(temp_entry.getKey(), temp_entry.getValue());
				}

			}
		}
	}

	private void printlnResult() {
		synchronized (textParser) {
			//当前线程不是最后一个
			if (textParser.getResidueFile() != 1) {
				textParser.setResidueFile(textParser.getResidueFile() - 1);
				return;
			}
		}
		//是最后一个线程，负责输出词频表
		textParser.setResidueFile(0);
		for (Map.Entry<String, Integer> entry : textParser.getWordFrequency().entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

	}

}
