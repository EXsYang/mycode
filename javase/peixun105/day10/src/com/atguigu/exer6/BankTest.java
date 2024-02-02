package com.atguigu.exer6;

public class BankTest {
public static void main(String[] args) {
	
	Bank bank = new Bank();

	
	System.out.println(bank.getCustomer(0));
	//引用类型的对象数组，初始化之后，数组默认初始化值是null
	
	bank.addCustomer("yang", "da");
	System.out.println();
	
	
	
	
	bank.getCustomer(0).setAccount(new Account(2000));
	bank.getCustomer(0).getAccount().deposit(200);
	bank.getCustomer(0).getAccount().withdraw(1000);
	
	bank.addCustomer("y", "da");
	bank.getCustomer(1).setAccount(new Account(2000));
	bank.getCustomer(1).getAccount().deposit(2000);
	bank.getCustomer(1).getAccount().withdraw(100);
	
	bank.addCustomer("ang", "da");
	bank.getCustomer(2).setAccount(new Account(2000));
	bank.getCustomer(2).getAccount().deposit(20);
	bank.getCustomer(2).getAccount().withdraw(1000);
	
	bank.addCustomer("g", "da");
	bank.getCustomer(3).setAccount(new Account(2000));
//	bank.getCustomer(0).getAccount().deposit(2000);
//	bank.getCustomer(0).getAccount().withdraw(10);
	//改的是该账户里的钱数，和第几个客户没关系
	
	
	
	//bank.addCustomer("yang", "d");
	//Account acc = new Account(3000);
	//bank.getCustomer(4).setAccount(acc);、、。。。。Exception in thread "main" java.lang.NullPointerException
	//创建账户但是没有把它的地址赋给Customer的属性
	
	bank.addCustomer("yang", "d");
	Account acc = new Account(3000);
	bank.getCustomer(4).setAccount(acc);
	
	//bank.getCustomer(4).getAccount().deposit(20);
	for(int i = 0 ; i < bank.customerlength();i++) {
		if(i == bank.getNumOfCustomers()) {
			break;
		}else {
			//System.out.println("客户"+i+"的账户余额为："+bank.getCustomer(i));
			//客户0的账户余额为：com.atguigu.exer6.Customer@7852e922
			//Bank类构造器创建对象时构造器中new的对象数组，表示的是对象数组中的第i个位置上的地址值
			//System.out.println("客户"+i+"的账户余额为："+bank.getCustomer(i).getAccount());
			//客户0的账户余额为：com.atguigu.exer6.Account@7852e922
			//获取对象数组中，第i个位置上对象的账户地址值，有一个Customer对象就会有一个account地址值
			//在Bank类中每添加一个用户addCustomer，同时他也会连带生成一个account地址值，因为account是作为
			//Customer类中的属性
			System.out.println("客户"+i+"的账户余额为："+bank.getCustomer(i).getAccount().getBalance());
			//客户0的账户余额为：2200.0
			//空指针原因是没有set余额Balance()，直接get余额，获取不到
		}
		
		
		
		
	}
	
	
	
	System.out.println(bank.getNumOfCustomers());
	
	
	
	
	
	
}
}
