package com.atguigu.exer;

public class Account {

	private int id;// 账号
	private double balance;// 余额
	private double annuallnterestRate;// 年利率


	//父类中没有无参构造器
	public Account(int id, double balance, double annuallnterestRate) {
		this.id = id;
		this.balance = balance;
		this.annuallnterestRate = annuallnterestRate;

	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public double getAnnuallnterestRate() {
		return annuallnterestRate;
	}

	public void setAnnuallnterestRate(double annuallnterestRate) {
		this.annuallnterestRate = annuallnterestRate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getMonthlylnterest() {// 返回月利率的方法

		return annuallnterestRate / 12;	}

	public void withdraw(double amount) {// 取款方法
		if (amount > balance) {
		System.out.println("余额不足");
		} else {
		balance -= amount;
		}
	}

	public void deposit(double amount) {// 存款方法
		balance += amount;
		
	}

}
