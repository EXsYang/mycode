package com.atguigu.exer1;

public class InterfaceTest {
public static void main(String[] args) {
	ComparableCircle com1 = new ComparableCircle(2);
	ComparableCircle com2 = new ComparableCircle(3);
	ComparableRectangle com3 = new ComparableRectangle(3,44);
	ComparableRectangle com4 = new ComparableRectangle(3,5);
	
	System.out.println(com1.compareTo(com2));
	System.out.println(com3.compareTo(com4));
	
	
	
	
	
}
}
