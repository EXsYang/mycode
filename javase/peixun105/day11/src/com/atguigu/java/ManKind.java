package com.atguigu.java;

public class ManKind {
	private int sex;
	private int salary;

	
	public ManKind() {
		
	}
	
	
public void manOrWoman() {
	
	if(sex == 1) {
		System.out.println("man");
	}else if(sex == 0) {
		System.out.println("woman");
	}
	
}

	public void employeed() {
		String a = (salary == 0) ? "no job" : "job";
		System.out.println(a);

	}


	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}
	

}
