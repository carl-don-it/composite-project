package com.don.review.se_senior.reflex;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * todo
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName dynamicproxy
 * @date 2019年08月17日 下午 5:37
 */
public class dynamicproxy {
	public static void main(String[] args) {
		final List<String> list = new ArrayList<String>();

		List<String> proxyInstance =
				(List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(),
						list.getClass().getInterfaces(),
						new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method, Object[] args1) throws Throwable {
								System.out.println("出发原方法");
								return method.invoke(list, args1);
							}
						});
		proxyInstance.add("你好");
		proxyInstance.add("你好");
		proxyInstance.add("你好");
		System.out.println(list.size());
	}
}
