package com.atguigu.exer;

public class CheckAccountTest {
public static void main(String[] args) {
	
	CheckAccount ca = new CheckAccount();
		ca.withdraw(5000);
		System.out.println("账户余额："+ca.getBalance()+"可透支额度："+ca.overdraft);
		ca.withdraw(18000);
		System.out.println("账户余额："+ca.getBalance()+"可透支额度："+ca.overdraft);
		ca.withdraw(3000);
		System.out.println("账户余额："+ca.getBalance()+"可透支额度："+ca.overdraft);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
