package com.atguigu.exer3;

public class StudentTest {
public static void main(String[] args) {
	Student p1 = new Student("杨达",23);
	String a = p1.name;
	System.out.println(a);
	int a1 = p1.age;
	System.out.println(a1);
	
	Student p2 = new Student("小杨",22,"育才学校");
	System.out.println(p2.name+p2.age+p2.school);	
	
	Student p3 = new Student("汪峰",45,"清华","音乐");
	System.out.println(p3.name+p3.age+p3.school+p3.major);
}
}
