package com.VVa;

public class test {
	public static void main(String[] args) {
//		基本数据类型的传递
		int i = 0;
		show(i);//1
		System.out.println(i);//0
		
		//引用数据类型的传递
//		Student s = new Student();
		Student x = new Student();
//		show01(s);
		show3(i);
//		s.setName("刘索隆");
		x.setA(3);
		System.out.println(x.getName());
		
////		特殊的类型
//		Student s = new Student();
//		show02(s.getName());
//		System.out.println(s.getName())
	}

	public static void show3(int i) {
		i++;
		System.out.println(i);
	}
	public static void show(int i) {
		i++;									
		System.out.println(i);
	}
	
	public static void show01(Student s){
		s.setName("王路飞");
	}
	
	public static void show02(String name){
		name="sakura";
	}

}
