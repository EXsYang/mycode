package com.lx.lxx;

public class Cylinder {
	private double radius;
//	���췽��,��radius���Գ�ʼ��Ϊ1
	double findArea;
	public void Circle() {
		radius=1;
		
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public double findArea() {
		
		return findArea=radius*radius*3.14;
	}

}
