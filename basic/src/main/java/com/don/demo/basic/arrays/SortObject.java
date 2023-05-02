package com.don.demo.basic.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 对引用类型的数组排序
 * 我们举个例子，对User类型的数组，根据年龄进行排序，此处用到Comparator接口
 *
 * @author Carl Don
 * @version V1.0
 * @date 2020年02月12日 下午 4:35
 */
public class SortObject {

	public static void main(String[] args) {
		User[] users = new User[]{new User("egg", "male", 26), new User("Kity", "Female", 25), new User("Pole", "male", 23), new User("Jack", "male", 28)};

		Arrays.sort(users, new UserComparator());

		for (User user : users) {
			System.out.println("name: " + user.getName() + " ,age: " + user.getAge());
		}
	}

	static class UserComparator implements Comparator<User> {

		@Override
		public int compare(User o1, User o2) {
			return o1.getAge() - o2.getAge();
		}

	}

	static class User {
		private String name;
		private String gender;
		private int age;

		public User(String name, String gender, int age) {
			this.name = name;
			this.gender = gender;
			this.age = age;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}

		/**
		 * @param gender the gender to set
		 */
		public void setGender(String gender) {
			this.gender = gender;
		}

		/**
		 * @return the age
		 */
		public int getAge() {
			return age;
		}

		/**
		 * @param age the age to set
		 */
		public void setAge(int age) {
			this.age = age;
		}

	}
}
