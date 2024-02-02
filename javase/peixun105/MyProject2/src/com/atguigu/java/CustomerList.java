package com.atguigu.java;

public class CustomerList {

	private Customer[] customers;// 用来保存客户对象的数组
	private int total = 0; // 记录已保存客户对象的数量

	public CustomerList() {
		
	}
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}

	public Customer[] getCustomers() {
		return customers;
	}
	
	
	
	
	
	/*
	 * 用途：将参数customer添加到数组中最后一个客户对象记录之后 参数：customer指定要添加的客户对象
	 * 返回：添加成功返回true；false表示数组已满，无法添加
	 */
	public boolean addCustomer(Customer customer) {
		//Customer cust = new Customer(String name,char sex,int old,String phone,String mail);
		//cust = customer;
		//CustomerList customerlist = new CustomerList(10);
		//this.customers = customerlist;
		if (total < customers.length) {
			customers[total++] = customer;
			//System.out.println(new Customer().getDetails());
			return true;
		} else {
			return false;
		}
		
	}

	/*
	 * 用途： 用参数customer替换数组中由index指定的对象 参数：customer指定替换的新客户对象
	 * ,index指定所替换对象在数组中的位置，从0开始 返回：替换成功返回true；false表示索引无效，无法替换
	 */
	public boolean replaceCustomer(int index, Customer cust) {
		//if (index >= 0 && index < this.customers.length) {	如果在total和数组长度之间也不行啊
			if (index > 0 && index <= total) {
			this.customers[index-1] = cust;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 从数组中删除参数index指定索引位置的客户对象记录 参数： index指定所删除对象在数组中的索引位置，从0开始
	 * 返回：删除成功返回true；false表示索引无效，无法删除
	 */
	public boolean deleteCustomer(int index) {
		
		
		if (index > 0 && index <= total) {
			for(int i = index ;i<total;i++) {
				customers[i-1] = customers[i];
			}
			this.customers[--total] = null;
			//total--;
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 用途：返回数组中记录的所有客户对象 返回： Customer[] 数组中包含了当前所有客户对象，该数组长度与对象个数相同。
	 */
	public Customer[] getAllCustomers() {

		// for(int i = 0 ;i <= this.total; i++) {
		
		Customer[] custs = new Customer[total];
		for(int i = 0 ;i < total ;i++) {
			custs[i] = customers[i];
		}
		return custs;
		// }

	}

	/*
	 * 返回参数index指定索引位置的客户对象记录 参数： index指定所要获取的客户在数组中的索引位置，从0开始 返回：封装了客户信息的Customer对象
	 */
	public Customer getCustomer(int index) {
		
		if (index > 0 && index <= total) {//空指针原因：传过来的参数是1，条件判断1 < 1 过不了，返回null
			return this.customers[index-1];//空指针
		} else {
			return null;
		}
	}

	public int getTotal() {
		return total;
	}

}
