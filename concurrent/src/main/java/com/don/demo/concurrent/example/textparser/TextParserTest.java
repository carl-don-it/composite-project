package com.don.demo.concurrent.example.textparser;

/**
 * 测试项目
 *
 * @author Carl Don
 * @version V1.0
 */
public class TextParserTest {

	//junit不支持多线程单元测试，所以用main函数测试  " -Dtext_dir=D:\otherVariable  -Dthread_number=6  "
	public static void main(String[] args) {
		new TextParser().parseText();
	}

}
