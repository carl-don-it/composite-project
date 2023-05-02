package com.don.demo.concurrent.example.textparser;

/**
 * 解析文本的接口
 *
 * @author Carl Don
 * @version V1.0
 */
public interface TextScanner extends Runnable {

	void parseFile();

}
