package com.atguigu.exer4;

public class Boy {
	private String name;
	private int age;
	
	public Boy() {
		
	}
	public Boy(String name,int age) {
		this.name = name;
		this.age = age;
	}
	
	
	
	public void setName(String i) {
		name = i;
	}
	public String getName() {
		return name;
	}
	public void setAge(int i) {
		age = i;
	}
	public  int getAge(){
		return age;
	}
	
	public void marry(Girl girl) {
		System.out.println("我想娶"+girl.getName());
	}
	public void shout() {
		if(this.age>=22) {
			System.out.println("合法了，去结婚");
		}else {
			System.out.println("不合法，不能领证");
		}
	}
	
	
	
	
}
