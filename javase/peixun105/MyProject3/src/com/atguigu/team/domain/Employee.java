package com.atguigu.team.domain;

import com.atguigu.team.service.Status;
import com.atguigu.team.service.TeamService;
import com.atguigu.team.view.TeamView;

public class Employee {
	private int id;
	private String name;
	private int age;
	private double salary;
	private String Position;
	private Status status;
	public Employee() {
	}
	
	public Employee(int id, String name, int age, double salary) {
		super();
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

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public String getDetails() {
		return id  + "	"+name + "	" + age + "	" +  salary + "	";
	}
	
	@Override
	public String toString() {
		return getDetails() + getPosition() ;
	}
	
	



}
