package com.atguigu.exer;

public class SalariedEmployee extends Employee{

	private int monthlySalary;
	
	
	public SalariedEmployee() {
		
	}
	
	
	public SalariedEmployee(String name, int number, MyDate birthday,int monthlySalary) {
		super(name,number,birthday);
		this.monthlySalary = monthlySalary;
	}


	public int getMonthlySalary() {
		return monthlySalary;
	}


	public void setMonthlySalary(int monthlySalary) {
		this.monthlySalary = monthlySalary;
	}


	public  int earnings() {
		
		
		return monthlySalary;
	};
	
	public int geMonthlySalary() {
		return monthlySalary;
	}
	
	public String toString() {
		return "[SalariedEmployee"+super.toString()+"]";
	}
	
	
	
}
