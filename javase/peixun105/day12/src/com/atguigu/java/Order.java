package com.atguigu.java;

public class Order {

	
	//1.私有化构造器
	private Order() {
		
	}
	
	
	//2.声明当前类对象，没有初始化
	//4.此对象也必须为static的
	private static Order instance = null;
	
	
	//3.声明public、static的返回当前类对象的方法
	public static Order getInstance() {
		if(instance == null) {
			instance = new Order();
		}
			return instance;
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
