package com.VVa;

public class test {
	public static void main(String[] args) {
//		�����������͵Ĵ���
		int i = 0;
		show(i);//1
		System.out.println(i);//0
		
		//�����������͵Ĵ���
//		Student s = new Student();
		Student x = new Student();
//		show01(s);
		show3(i);
//		s.setName("����¡");
		x.setA(3);
		System.out.println(x.getName());
		
////		���������
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
		s.setName("��·��");
	}
	
	public static void show02(String name){
		name="sakura";
	}

}
