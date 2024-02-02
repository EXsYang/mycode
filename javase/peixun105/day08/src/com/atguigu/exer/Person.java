package com.atguigu.exer;

public class Person {
	
	String name;
	int age =1;
	/**
	 * sex = 0 为男性
	 * sex = 1为女性
	 */
	int sex;
	
	public void study() {//按ALT + 左←：回去；
						//	按ALT +右 → 回来
		System.out.println("studying");
	}
	public void showAge() {
		System.out.println(age);
	}
	public int addAge(int i) {
		age += i;
		 return age;
		 
	}
}
