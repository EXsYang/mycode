package com.atguigu.exer4;

public class Circle extends GeometricObject{

	private double radius;

	public Circle() {
		super();
		
	}

	public Circle(double radius,String color,double weight) {
		super();
		this.radius = radius;
		this.color = color;
		this.weight = weight;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double findArea() {
		return Math.PI*radius*radius;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof Circle) {
			Circle c = (Circle)obj;
			if(this.radius == c.radius) {
				return true;
			}
		}
		return false;
	}
	
	public String toString() {
		return  "半径是：" + radius;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
