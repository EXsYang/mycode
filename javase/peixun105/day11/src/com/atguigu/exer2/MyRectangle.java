package com.atguigu.exer2;

public class MyRectangle extends GeometricObject{

	private double width;
	private double height;
	
	public MyRectangle(double width,double height,String color,double weight) {
		super(color,weight);//子类形参个数或是有没有的，不一致也没事，在上面补就行
		this.width = width;
		this.height = height;
		super.color = color;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public double findArea() {
		return height * width;
	}
	
	
	
}
