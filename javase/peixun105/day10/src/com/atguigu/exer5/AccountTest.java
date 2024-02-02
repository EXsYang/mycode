package com.atguigu.exer5;

public class AccountTest {

	public static void main(String[] args) {
//		Account acc1 = new Account(3,400.5,0.3);
//		acc1.deposit(600);
//		acc1.withdraw(101);
		Account acc = new Account(1000,2000,0.0123);
		
		Customer j = new Customer("Jane","Smith");
		j.setAccount(acc);
		j.getAccount().deposit(100);
		j.getAccount().withdraw(960);
		
		//j.getAccount().deposit(2000);
		j.getAccount().withdraw(2000);
		System.out.println("Customer ["+j.getFirstName()+","+j.getLastName()+"] has a account: id is "+j.getAccount().getId()+",annualinterestRate is "+j.getAccount().getAnnuallnterestRate()*100+"%,balance is "+j.getAccount().getBalance());
		
		
		
		
		
		
		
	}
}
