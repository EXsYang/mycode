package com.atguigu.java1;

public class SingletonTest2 {

	public static void main(String[] args) {
		
		Singleton2 s = Singleton2.getSingleton();
		Singleton2 s1 = Singleton2.getSingleton();
		
		System.out.println(s==s1);//是同一个对象
		
	}
	
}
class Singleton2{//单例模式-懒汉式，线程不安全
	
	//①私有化构造器
	private Singleton2() {
		
	}
	//②声明当前类对象，没有初始化
	//④此对象也必须声明为static的
	private static Singleton2 s1 = null;
	
	//③声明public、static 的返回当前类对象方法
	public static Singleton2 getSingleton() {
		if(s1 == null) {
			s1 = new Singleton2();
		}
		return s1;
	}
	
	
	
}