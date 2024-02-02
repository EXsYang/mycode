package com.atguigu.com;

public class SingletonTest1 {

}
class Order{
	//1.私有化类的构造器
	private Order() {
		
	}
	
	//2.声明当前类的对象，没初始化
	//4.此对象也必须声明为static的
	private static Order instance = null;
	
	//3.声明public、static的返回当前类对象的方法	
	//public static Order getInstance() {
	//同步方法的方式：改成线程安全的，直接在方法上加synchronized
//	public static synchronized Order getInstance() {
//		if(instance == null) {
//			instance = new Order();
//		}
//		return instance;
//	}
	public static Order getInstance() {
		//方式一：效率稍差
//		synchronized (Order.class) {
//			if (instance == null) {
//				instance = new Order();
//			}
//			return instance;
//		}
		//方式二：效率稍高
		
		if(instance == null) {
			synchronized (Order.class) {
				if (instance == null) {
					instance = new Order();
				}
		}
		}
		return instance;
	}
	
	
	
	
}