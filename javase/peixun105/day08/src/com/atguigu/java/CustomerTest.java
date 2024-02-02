package com.atguigu.java;

public class CustomerTest {
public static void main(String[] args) {
	
	Customer p1 = new Customer();
	
	int[] arr = new int[] {1,4,3,6,7,9};
	int[] arr1 = new int[] {3,4,6,7,5};
	p1.sort(arr1);
	
}
}
class Customer{
	//属性
	String name;
	int age;
	boolean isMale;
	
	
	public void sort(int[] arr) {
		System.out.println(arr);
	}
	
	
}