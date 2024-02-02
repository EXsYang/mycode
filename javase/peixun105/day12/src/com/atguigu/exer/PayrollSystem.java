package com.atguigu.exer;

import java.util.Calendar;
import java.util.Scanner;

public class PayrollSystem {
	static final int NUMBER  = 10;
	public static void main(String[] args) {
		
		//方式一
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("请输入当前月份:");
//		int month = scanner.nextInt();
	
		//方式二
		Calendar calendar = Calendar.getInstance();
		int month = calendar.get(Calendar.MONTH);
		//System.out.println(month);//一月份：0
	
	
	Employee[] e =  new  Employee[2];
	
	
		e[0] = new SalariedEmployee("yangda",1001,new MyDate(1998,3,27),12000);
		e[1] = new HourlyEmployee("yangda2",1002,new MyDate(1998,11,27),60,240);
		for(int i = 0;i < e.length;i++) {
		System.out.println(e[i]);
		double salary = e[i].earnings();
		System.out.println("工资为："+salary);
		
		if((month+1) == e[i].getBirthday().getMonth() ) {
			
			System.out.println("生日快乐，工资加100");
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	}
	
}
