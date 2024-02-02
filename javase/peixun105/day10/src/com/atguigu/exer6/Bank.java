package com.atguigu.exer6;

public class Bank {

	private Customer[] customer;
	private int numberOfCustomer;//成员变量
	
	public Bank() {
			//int numberOfCustomer;//局部变量
		//Customer[] customer = new Customer[10]; 错误形式，这里不需要声明类型了，声明之后是一个局部变量，和上面的
			//属性customer不是一个
			customer = new Customer[10];
			
	}
	public int customerlength() {
		return customer.length;
	}
	public Customer[] getCustomer() {
		return customer;
	}
	
	public void addCustomer(String f,String l) {
	
		Customer cust = new Customer(f,l);
		this.customer[numberOfCustomer++] = cust;//后++，运算完之后再加1
		//让new出来的cust对象作为Bank类中Customer[]数组中第numberOfCustomer个元素
		
		//this.customer[numberOfCustomer] = customer;
		
	}
	
	public int getNumOfCustomers() {
		return numberOfCustomer;
	}
	
	public Customer getCustomer(int index) {
		if(index>=0 &&index < numberOfCustomer)	{//注意等号，从0开始的
		return  this.customer[index];
	}
		return null;
	}
	
	
}
