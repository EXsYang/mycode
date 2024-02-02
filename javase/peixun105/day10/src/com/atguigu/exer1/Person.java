package com.atguigu.exer1;

public class Person {
private int age;
private String name;

public Person() {
	age = 18;
}
public Person(int i,String j) {
	age = i;
	name = j;
}
public void setAge(int i) {
	
	if(i>0&&i<130) {
		age = i;
	}else {
		System.out.println("年龄不合法");
	}
}
public int getAge() {
	return age;
}

public void setName(String i) {
	name = i;
}

public String getName() {
	return name;
}
}
