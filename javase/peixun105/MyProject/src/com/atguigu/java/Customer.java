package com.atguigu.java;

public class Customer {

	private String name;// 客户姓名
	private char gender;// 性别
	private int age;// 年龄
	private String phone;// 电话号码
	private String email;// 电子邮箱

	
	public Customer() {
		
	}
	public Customer(String name,char gender,int age,String phone,String email) {
		this.name = name;//只写了形参，没写结构体内容
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.email = email;
	}
	
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	public String getDetails() {
		return name + "\t" + gender + "\t" + age + "\t\t" + phone + "\t" + email;
	}
}
