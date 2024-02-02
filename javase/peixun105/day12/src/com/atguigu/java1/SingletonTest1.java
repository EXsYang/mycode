package com.atguigu.java1;

public class SingletonTest1 {

	public static void main(String[] args) {
		Singleton s = Singleton.getSingleton();
		Singleton s1 = Singleton.getSingleton();
		
		System.out.println(s == s1);//是同一个对象
	}
	
}
class Singleton{//单例模式-饿汉式，线程安全
	
	
	
	//①私有化构造器
	private Singleton() {
		
	}
	
	//②声明当前类对象，初始化
	//④此对象也必须声明为static的
	private static Singleton s = new Singleton();
	
	//声明public、static 返回当前类对象的方法
	public static Singleton getSingleton() {
		return s;
	}
	
	
	
}