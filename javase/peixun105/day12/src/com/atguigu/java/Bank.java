package com.atguigu.java;

public class Bank {

	//饿汉式单例模式
	//1.私有化构造器
	private Bank() {
		
	}
	
	//2.内部创建类对象
	//4.要求此对象也必须声明为静态的
	private static Bank instance = new Bank();
	
	//3.提供公共的静态的方法，返回类的对象
	public static Bank getInstance() {
		return instance;
	}
	
	
	
}
