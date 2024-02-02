package com.atguigu.exer;

public class CircleTest {
public static void main(String[] args) {
	Circle p1 = new Circle();
	p1.radis = 2;
	//double a = p1.area();
	System.out.println(p1.area());
}
}

class Circle{
	
	double radis;
	//double radis;
	/*
	public void area() {
		
		double area;
		area = Math.PI*radis*radis;
		System.out.println(area);
	}
	*/
	//正确情况1
	public double area() {
		double area = Math.PI * radis*radis;
		return area;
	}
	/*正确情况2
	public void area() {
		double area = Math.PI * radis*radis;
		System.out.println(area);
	}*/
	/*
	public double area(double i) {
		double area;
		return area = Math.PI * i* i;
	}
	*/
	
	
}