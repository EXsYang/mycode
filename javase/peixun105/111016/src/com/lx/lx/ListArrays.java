package com.lx.lx;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ListArrays {
	static List<User> users;
	static {
//		Arrays.asList()��������������List��һ���̶����ȵ�List����
		users=Arrays.asList(new User("rock","admin"),
				new User("andy","123123"),
				new User("ruby","123456"),
				new User("games","jqk"));
	}
	
	
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		System.out.println("�������¼ID");
		String userid=in.next();
		System.out.println("�������¼����");
		String password=in.next();
		
		boolean flag=login(userid,password);
		if(flag)
			System.out.println("��¼�ɹ�");
		else 
			System.out.println("�Ƿ���ӡ�û������������");
		
	}
	
}