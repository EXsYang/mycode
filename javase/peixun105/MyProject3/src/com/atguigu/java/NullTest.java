package com.atguigu.java;

public class NullTest {
public static void main(String[] args) {
	int array[] = new int[2];
	array = null;
	System.out.println(array.length);//数组为null时.length空指针
}
}
