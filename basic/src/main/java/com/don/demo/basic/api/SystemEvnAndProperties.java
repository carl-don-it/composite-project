package com.don.demo.basic.api;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * 获取环境变量和系统属性
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName GetSystemEvnTest
 * @date 2019年09月14日 上午 8:28
 */
public class SystemEvnAndProperties {
	public static void main(String[] args) {
		for (Map.Entry entry : System.getenv().entrySet()) {
			System.out.println(entry.getKey() + "-->" + entry.getValue());
		}

		System.out.println("-----------------");
		Properties p = System.getProperties();

		for (Iterator it = p.keySet().iterator(); it.hasNext(); ) {
			String key = (String) it.next();
			String value = (String) p.get(key);
			System.out.println(key + ":" + value);
		}

		String param1 = System.getenv("JAVA_HOME");
		System.out.println(param1);
		String param2 = System.getProperty("param2");
		System.out.println(param2);
	}
}
