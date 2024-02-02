package com.atguigu.exer;

public class AccountTest {
	public static void main(String[] args) {
		Account cust = new Account(1122,20000,0.045);
		cust.withdraw(30000);
		System.out.println("您的账户余额为："+cust.getBalance());
		cust.withdraw(2500);
		cust.deposit(3000);
		System.out.println("您的账户余额为："+cust.getBalance());
		System.out.println(cust.getMonthlylnterest());
		
		
		
		
		
	}
	
	
	
	
	
}
