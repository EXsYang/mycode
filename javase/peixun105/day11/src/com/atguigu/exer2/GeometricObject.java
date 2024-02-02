package com.atguigu.exer2;

public class GeometricObject {

	protected String color;
	protected double Weight;
	
	protected  GeometricObject(String color, double weight) {
		this.color = color;
		this.Weight = weight;
	}
	
	public String getColor() {
		return this.color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}
	
	public double findArea() {
		return 0;
	}
	
	
	
	
	
	
	
}
