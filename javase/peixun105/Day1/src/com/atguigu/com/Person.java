package com.atguigu.com;

public class Person {

	public static void main(String[] args) {
		Son s = new Son();
		s.b();
	}
	
}
class Son extends Person{//Son类继承了Person类的main方法
	private int age;
	public void a(){//Void是变量a的无效类型
		System.out.println("子类的a方法");
	}
	public void b() {
		a();
	}
}
