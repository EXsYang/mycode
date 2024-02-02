package com.atguigu.com;

public class SingletonTest {
public static void main(String[] args) {
	
}
}
//饿汉式，先创建出对象的方式

class Bank{
	//1.私有化类的构造器
	private Bank(){
		
	}
	//2.内部创建类的对象
	//4.要求此对象也必须声明为静态的
	private static Bank instance = new Bank();
	//3.提供公共的静态的方法，返回类的对象
	public static Bank getInstance() {
		
		return instance;
		
	}
	
	//饿汉式方式二：使用代码块的方式
	//1.私有化类的构造器
		//private Bank(){
			
		//}
		//2.内部创建类的对象
		//4.要求此对象也必须声明为静态的
		//private static Bank instance = null;
		//3.提供公共的静态的方法，返回类的对象
		
		static {																			
			instance = new Bank();
		}
		
		public static Bank getInstance1() {
			
			return instance;
			
		}
	
	
	
	
	
	
}