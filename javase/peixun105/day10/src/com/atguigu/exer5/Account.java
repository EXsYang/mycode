package com.atguigu.exer5;

public class Account {
private int id;
private double balance;
private double annuallnterestRate;

public Account() {
	
}


public Account(int id,double balance,double annuallnterestRate) {
	this.id = id;
	this.balance = balance;
	this.annuallnterestRate = annuallnterestRate;
}





public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public double getBalance() {
	return balance;
}

public void setBalance(double balance) {
	this.balance = balance;
}

public double getAnnuallnterestRate() {
	return annuallnterestRate;
}

public void setAnnuallnterestRate(double annuallnterestRate) {
	this.annuallnterestRate = annuallnterestRate;
}

public void withdraw(double amount) {//取钱
	
	if(amount>this.balance) {
		
		System.out.println("余额不足，取款失败");
	}else {
		this.balance -= amount;
		System.out.println("成功取出："+amount);
	}
}

public void deposit(double amount) {//存钱
	this.balance += amount;
	System.out.println("成功存入："+amount);
}






}
