package com.atguigu.exer6;

public class Account {

	private double balance;
	
	public Account(double int_balance){
		balance = int_balance;
	}
	
	
	public double getBalance() {
		return balance;
	}
	public void deposit(double amt) {//存钱
		if(amt > 0) {
			this.balance += amt;
			System.out.println("存入："+amt+"余额："+this.balance);
		}else {
			System.out.println("存钱失败");
		}
		
	}
	public void withdraw(double amt) {//取钱
	
		if(amt >this.balance) {
			System.out.println("余额不足，取款失败");
		}else {
			System.out.println("取出："+amt);
		}
	}
	
	
	
	
}
