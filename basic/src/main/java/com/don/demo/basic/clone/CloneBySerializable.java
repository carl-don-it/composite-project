package com.don.demo.basic.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.io.*;

/**
 * 递归实现 Serializable 接口，通过对象的序列化和反序列化(IO流)实现克隆，可以实现真正的深度克隆，
 * <p>
 * 注意：基于序列化和反序列化实现的克隆不仅仅是深度克隆，更重要的是通过泛型限定，可以检查出要克隆的对
 * 象是否支持序列化，这项检查是编译器完成的，不是在运行时抛出异常，这种是方案明显优于使用 Object 类的 clone
 * 方法克隆对象。让问题在编译的时候暴露出来总是好过把问题留到运行时。
 *
 * @author Carl Don
 * @version V1.0
 * @ClassName CloneBySerializable
 * @date 2019年08月12日 上午 10:43
 */
public class CloneBySerializable {
	public CloneBySerializable() {

	}

	@Test
	public void test1() {
		try {
			Person p1 = new Person("Hao LUO", 33, new Car("Benz", 300));
			Person p2 = clone(p1); // 深度克隆
			p2.getCar().setBrand("BYD");
			// 修改克隆的 Person 对象 p2 关联的汽车对象的品牌属性
			// 原来的 Person 对象 p1 关联的汽车不会受到任何影响
			// 因为在克隆 Person 对象时其关联的汽车对象也被克隆了
			System.out.println(p1);
			System.out.println(p2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T clone(T obj) throws Exception {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bout);
		oos.writeObject(obj);

		ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bin);
		return (T) ois.readObject();
		// 说明：调用 ByteArrayInputStream 或 ByteArrayOutputStream 对象的 close 方法没有任何意义
		// 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资源（如文件流）的释放
	}

	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	static class Person implements Serializable {
		private static final long serialVersionUID = -9102017020286042305L;
		private String name; // 姓名
		private int age; // 年龄
		private Car car; // 座驾
	}

	@Data
	@ToString
	@AllArgsConstructor
	@NoArgsConstructor
	static class Car implements Serializable {
		private static final long serialVersionUID = -5713945027627603702L;
		private String brand; // 品牌
		private int maxSpeed; // 最高时速
	}
}
