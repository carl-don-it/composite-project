package com.don.demo.basic.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Returns x's Class if it is of the form "class C implements Comparable<C> ,else null
 * hashmap1.8的方法
 * <p>
 * Type是一种类型：“ interface java.lang.Iterable ”
 * ParameterizedTypes是泛型类“ java.lang.Comparable<com.don.demo.reflect.GenericTest> ”，是Type的子类
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年01月04日 下午 3:00
 */
public class GenericTest extends ArrayList implements Iterable, Comparable<GenericTest>, FunctionalInterface {

	/**
	 * String会返回java.lang.String.class, integer会返回java.lang.Integer.class
	 */
	static Class<?> comparableClassFor(Object x) {
		// 1.判断x是否实现了Comparable接口，
		if (x instanceof Comparable) {
			Class<?> c;
			Type[] ts, as;
			Type t;
			ParameterizedType p;
			// 2.校验x是否为String类型
			if ((c = x.getClass()) == String.class) // bypass checks
				return c;
			if ((ts = c.getGenericInterfaces()) != null) {
				// 3.遍历x实现的所有接口
				for (int i = 0; i < ts.length; ++i) {
					// 4.如果x实现了Comparable接口并且泛型参数是自己x，则返回x的Class
					if (((t = ts[i]) instanceof ParameterizedType) &&  //是泛型类型？
							((p = (ParameterizedType) t).getRawType() ==
									Comparable.class) &&            //泛型类型的原始类型是Comparable.class？
							(as = p.getActualTypeArguments()) != null && // 泛型参数的数组不为null；只有一个；就是自己c，和自己比较
							as.length == 1 && as[0] == c) // type arg is c ，和自己比较
						return c;
				}
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Class<?> aClass = comparableClassFor(1);
		System.out.println(aClass);
		System.out.println("123可以".compareTo("123可以"));
	}

	public Iterator iterator() {
		return null;
	}

	public Class<? extends Annotation> annotationType() {
		return null;
	}

	@Override
	public int compareTo(GenericTest o) {
		return 0;
	}
}
