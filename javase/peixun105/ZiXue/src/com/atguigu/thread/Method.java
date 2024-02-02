package com.atguigu.thread;

public class Method {
	
	//int a;
	static int a;
	int b;
	
	public static void M1() {
		//int a = 1;
		//System.out.println(a);//Cannot make a static reference to the non-static field a不能对非静态字段进行静态引用 
		System.out.println(a);
		a = 3;
	}
	public void M2() {
		b = 2;
		System.out.println(b);
		M1();
		b = 5;
		System.out.println(b);
		int c = 3;
		System.out.println(c);
		
		//System.out.println(a);
		
		
	}
	
	public static void main(String[] args) {
		Method m = new Method();
		m.M2();
		System.out.println(m.a);
		System.out.println(m.b);
	}
	
	

}
