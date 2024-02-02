package com.atguigu.exer;

public class PersonTest {
public static void main(String[] args) {
	Person p1 = new Person();
	
	p1.name = "小杨";
	p1.age = 23;
	p1.sex = 0;
	
	p1.study();//按住CTRL鼠标点击进去方法
	p1.showAge();
	int newAge = p1.addAge(2);
	System.out.println("小杨的新年龄为："+newAge);

	System.out.println(p1.age);
}
}
 