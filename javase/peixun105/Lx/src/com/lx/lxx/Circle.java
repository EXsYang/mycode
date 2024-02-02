package com.lx.lxx;
public class Circle {
	private double radius;

	public double getRadius() {
		return radius*radius*3.14;
	}

	public void setRodius(double rodius) {
		this.radius = rodius;
	}
	public void findArea(){
		System.out.println("圆的面积是:"+getRadius());
	}
	
}
