package com.atguigu.exer;

public class InstanceTest {
public static void main(String[] args) {
	
	InstanceTest test = new InstanceTest();
		test.method(new Student());
//	Person p = new Person();
//	
//	Person s = new Student();
//	
//	Person g = new Graduate();
//	
//	test.method(p);
//
//	test.method(s);
//	
//	test.method(g);
	
}

public void method(Person e) {
	
//	String info = e.getInfo();
//	System.out.println(info);

	if(e instanceof Graduate) {
		System.out.println("a graduated student");
		System.out.println("a student");
		System.out.println("a person");
	}else if(e instanceof Student) {
		System.out.println("a student");
		System.out.println("a person");
	}else if(e instanceof Person) {
			System.out.println("a person");
		
	}
	
	

//	if(e instanceof Graduate) {
//		Graduate g1 = (Graduate)e;
//		g1.getInfo();
//		System.out.println("a graduated student");
//		System.out.println("a student");
//		System.out.println("a person");
//	}else if(e instanceof Student) {
//		Student s1 = (Student)e;
//		s1.getInfo();
//		System.out.println("a student");
//		System.out.println("a person");
//		
//	}else if(e instanceof Person) {
//			e.getInfo();
//			System.out.println("a person");
//		
//	}
//	
  }
}
class Person{
	protected String name= "person";
	protected int age = 50;
	public String getInfo() {
		return "Name:"+name+"\n"+"age:"+age;
	}
}
class Student extends Person{
	protected String school="pku";
	public String getInfo() {
		return "Name:"+name+"\nage"+age+"\nschool:"+school;
	}
}

class Graduate extends Student{
	public String major="IT";
	public String getInfo() {
		return "Name:"+name+"\nage"+age+"\nmajor:"+major;
	}
}




