package com.lx.lx;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ListArrays {
	static List<User> users;
	static {
//		Arrays.asList()方法创建出来的List是一个固定长度的List集合
		users=Arrays.asList(new User("rock","admin"),
				new User("andy","123123"),
				new User("ruby","123456"),
				new User("games","jqk"));
	}
	
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("请输入登录ID");
		String userid=in.next();
		System.out.println("请输入登录密码");
		String password=in.next();
		
		boolean flag=login(userid,password);
		if(flag)
			System.out.println("登录成功");
		else 
			System.out.println("非法打印用户名或密码错误");
		
	}
	
}