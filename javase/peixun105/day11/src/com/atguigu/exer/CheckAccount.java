package com.atguigu.exer;

public class CheckAccount extends Account{

	double 	overdraft;

	public CheckAccount() {//当父类中没有无参构造器时，子类只可以调用父类中的有参构造器
		super(1122,20000,0.045);//括号内写实参,可以是一个变量，也可以是实际的数值
		overdraft = 5000;
	}
	public void withdraw(double amount) {// 取款方法
		if(amount < getBalance()) {
			super.withdraw(amount);
		}else if(amount > getBalance()) {
			double overdraft = amount - getBalance();
			if(overdraft<this.overdraft) {
//				setBalance(0);
//				this.overdraft -= overdraft ;//上面余额成0了，下面计算时用的是Balance+0
				this.overdraft -= overdraft ;
				setBalance(0);
			}else {
					System.out.println("超过透支限额！");
			}
		}
	
	}
	
	
	
	
	
}
