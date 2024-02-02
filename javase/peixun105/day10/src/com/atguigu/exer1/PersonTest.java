package com.atguigu.exer1;

public class PersonTest {
public static void main(String[] args) {
	Person b = new Person();
	//b.age;
	//age是私有的private
	b.setAge(16);
	Person c = new Person(66,"小杨");
	int a = b.getAge();
	System.out.println("对象b的年龄是："+a);
	String d = c.getName();
	System.out.println(d);
}
}
