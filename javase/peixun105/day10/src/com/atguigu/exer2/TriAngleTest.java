package com.atguigu.exer2;

public class TriAngleTest {
public static void main(String[] args) {
	TriAngle p = new TriAngle();
	p.setBase(2);
	p.setHeight(3);
	double area1 = p.getBase()*p.getHeight()/2;
	System.out.println("三角形的面积是："+area1);
	
	TriAngle p1 = new TriAngle(3.0,2.0);
	double area2 = p1.getBase()*p1.getHeight()/2;
	System.out.println("三角形p2的面积是："+area2);
	
}
}
