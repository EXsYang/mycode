package com.atguigu.team.domain;

import com.atguigu.team.service.Status;

public class Employee {
	 
	 private int id;
	private String name;
	private int age;
	private double salary;
	
	 //Employee  :  10, id, name, age, salary
    //Programmer:  11, id, name, age, salary
    //Designer  :  12, id, name, age, salary, bonus
    //Architect :  13, id, name, age, salary, bonus, stock
	public Employee() {
		super();
	}
	
	public Employee(int id,String name,int age,double salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return id+ "\t" + name + "\t" + age + "\t" + salary;
	}
	

}










