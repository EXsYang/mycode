package com.atguigu.java;

import org.omg.Messaging.SyncScopeHelper;

public class CustomerView {

	// 启动程序需要在CustomerView的main方法中
	// 就需要用到CustomerView的实例化对象cust
	// 由于CustomerView的构造方法中包含一个
	// CustomerList customerList = new CustomerList(10);
	// 所以在new CustomerView的对象cust时，默认new了一个
	// CustomerList 的对象customerList
	// 同时在CustomerList的构造方法中形参为一个int型
	// 指定了Customer[]数组的长度，确定了对象数组中存放对象元素的个数
	// 也就是说实例化CustomerView对象的同时确定了对象数组的长度
	// 对象数组作为CustomerList的属性，每当new一次带参数的CustomerList(10)
	// 就在堆中开辟一个空间，其其中的属性对象数组customers初始化为一个长度为10的对象数组
	// 结论：每new一次CustomerView就会生成一个对象cust
	// 同时就会生成一个CustomerList对象customerList
	// 又因为CustomerList的带参构造器对对象数组进行了初始化
	// 就会new一个长度为10的对象数组
	// CustomerList带参构造器规定了CustomerList类中this对象的Customer[]的长度
	//

	// private CustomerView cust;
	private CustomerList customerlist = new CustomerList(10);

	

	public CustomerView() {
		Customer cust =new Customer("杨撒" , '男' , 23 ,"22222" , "322222");
		
		customerlist.addCustomer(cust);
		
	}  

	public CustomerList getCustomerList() {
		return customerlist;	
	}

	// 用途：显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法（如addNewCustomer），以完成客户信息处理。
	public void enterMainMenu() {

		//CustomerView cust = new CustomerView();
		CMUtility util = new CMUtility();

		// Scanner scan = new Scanner(System.in);
		// int a = scan.nextInt(); //util.readMenuSelection();
		boolean isFlag = true;
		while (isFlag) {
			System.out.print("---------------------客户信息管理软件---------------------");
			System.out.println();
			System.out.println();
			System.out.println("			1添加客户			");
			System.out.println("			2修改客户			");
			System.out.println("			3删除客户			");
			System.out.println("			4客户列表			");
			System.out.println("			5退        出			");
			System.out.println();
			System.out.print("			请选择(1-5)：_	");

			char a = util.readMenuSelection();

			if (a == '1') {
				addNewCustomer();
			} else if (a == '2') {
				modifyCustomer();
			} else if (a == '3') {
				deleteCustomer();
			} else if (a == '4') {
				listAllCustomers();
			} else {
				isFlag = true;
			}
		}

	}

	private void addNewCustomer() {
		// CustomerView cust= new CustomerView();
//		boolean isFlag = true;
//		do {
		System.out.println("---------------------添加客户---------------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(100);
		System.out.print("姓别：");
		char gender = CMUtility.readChar();
		System.out.print("年龄：");
		int age = CMUtility.readInt(2);
		System.out.print("电话：");
		String phone = CMUtility.readString(100);
		System.out.print("邮箱：");
		String email = CMUtility.readString(100);
		// CustomerView cust= new CustomerView() ;
		 Customer cus = new Customer(name, gender, age, phone,email);
		// addCustomer(cus);
		// cust.addNewCustomer();
			//Customer[] cust1 = new Customer[customers.getTotal()];
			
		 boolean v = customerlist.addCustomer(cus);
//		 if(customerlist.addCustomer(cus) ) {添加并返回
		 if(v) {
			 System.out.println("---------------------添加完成---------------------");
			 enterMainMenu();
		 }else {
			
			 System.out.println("---------------------添加失败---------------------");
			 enterMainMenu();
		 }
//		 while(isFlag ) {
//		
	}

	private void modifyCustomer() {
		System.out.println("---------------------修改客户---------------------");
		System.out.println("请输入待修改客户编号(-1退出)");
		int b = CMUtility.readInt(3);
		if (b == -1) {
			enterMainMenu();
		}
		System.out.print("姓名：" + "(" + customerlist.getCustomer(b).getName() + ")");
		String name = CMUtility.readString(155, customerlist.getCustomer(b).getName());
		System.out.print("姓别：" + "(" + customerlist.getCustomer(b).getGender() + ")");
		char gender = CMUtility.readChar();
		System.out.print("年龄：" + "(" + customerlist.getCustomer(b).getAge() + ")");
		int age = CMUtility.readInt(2);
		System.out.print("电话：" + "(" + customerlist.getCustomer(b).getPhone() + ")");
		String phone = CMUtility.readString(100,customerlist.getCustomer(b).getPhone());
		System.out.print("邮箱：" + "(" + customerlist.getCustomer(b).getEmail() + ")");
		String email = CMUtility.readString(100,customerlist.getCustomer(b).getPhone());
		Customer cus = new Customer(name, gender, age, phone, email);

		customerlist.replaceCustomer(b, cus);
		System.out.println("---------------------修改完成---------------------");

	}

	private void deleteCustomer() {
		System.out.println("---------------------删除客户---------------------");
		System.out.println("请选择待删除客户编号(-1退出)：");
		int a = CMUtility.readInt();
		if (a == -1) {
			enterMainMenu();
		}
		System.out.println("确认是否删除(Y/N)：");
		char b = CMUtility.readConfirmSelection();
		if (b == 'Y' || b == 'y') {

			customerlist.deleteCustomer(a);
			System.out.println("---------------------删除完成---------------------");
		} else {
			deleteCustomer();
		}

	}

	private void listAllCustomers() {
		System.out.println("---------------------客户列表---------------------");
		Customer[] custs = customerlist.getAllCustomers();
		//System.out.println(customerlist.getCustomers().length);
		int Total = customerlist.getTotal();
		if(Total == 0) {
			System.out.println(Total+"没有客户记录");
			
		}else {
		
		System.out.println("编号	姓名	性别	年龄	电话	邮箱");
		
		for (int i = 0; i <Total; i++) {
			Customer cust = custs[i];
					//System.out.println(cust.getName());
			System.out.println((i+1) + "\t" + custs[i].getDetails());
		}
		}
		// for (int i = 0; i < customers.getAllCustomers().length; i++) {
		// if (i <= customers.getTotal()) {
		// System.out.println(customers.getAllCustomers()[i]);
		// }
		// }
		System.out.println("---------------------客户列表完成---------------------");

	}


public static void main(String[] args) {
	// 用途：创建CustomerView实例，并调用 enterMainMenu()方法以执行程序。

	CustomerView cu = new CustomerView();

	/*
	 * CustomerList customerList = new CustomerList();
	 * System.out.println(customerList.getCustomer());//通过new出对象，未指定长度
	 * 默认初始化null，引用类型默认初始化值
	 * 
	 * cu.setcustomerList(10);
	 * System.out.println(cu.getcustomerList().getCustomer());//new之后对象数组名存的是一个地址值[
	 * Lcom.atguigu.java.Customer;@7852e922
	 */

	cu.enterMainMenu();
	
}
}