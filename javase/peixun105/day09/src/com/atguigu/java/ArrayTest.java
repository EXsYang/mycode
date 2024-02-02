package com.atguigu.java;

public class ArrayTest {


/*
 * 求数值型数组中元素的最大值、最小值、平均值、总和。
 * 
 * 定义一个int型的一维数组，包含10个元素，分别赋一些随机整数，
 * 然后求出所有元素的最大值，最小值，和值，平均值，并输出出来。
 * 要求：所有随机数都是两位数。
 * 
 * [10,99]
 * 公式：（int)(Math.random() * (99 - 10 +1) + 10)
 * 
 */

public static void main(String[] args) {
	int[] arr = new int[10];
	//遍历数组
	for(int i = 0;i < arr.length;i++) {
		arr[i] =(int)(Math.random()*(99 - 10 + 1)) + 10;
		System.out.print(arr[i]+"  ");
	}
	System.out.println();
	//最大值
	 int maxValue = arr[0];
	for(int i = 0;i < arr.length;i++) {
		if(maxValue<arr[i]) {
			maxValue = arr[i];
		}
	}
	System.out.println("最大值是："+maxValue);
	System.out.println();
	//最小值
	 int minValue = arr[0];
	for(int i = 0;i < arr.length;i++) {
		if(minValue>arr[i]) {
			minValue = arr[i];
		}
	}
	System.out.println("最小值是："+minValue);
	System.out.println();
	//总和
		 int sum = 0;
		for(int i = 0;i < arr.length;i++) {
			sum += arr[i];
		}
		System.out.println("总和是："+sum);
		System.out.println();
	//平均值
	int avgValue;
	
	   avgValue = sum / arr.length;
		
	
	System.out.println("平均值是："+avgValue);
	System.out.println();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}