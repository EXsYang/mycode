package com.atguigu.com;

public class B extends AAA{
		private int age;
		public void a(){//Void是变量a的无效类型
			System.out.println("子类的a方法");
		}
		public void b() {
			a();
			System.out.println("我是子类的b方法");
		}
}
