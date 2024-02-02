package com.mj.san;

public class Mianjichoongzai {
//	三个重载方法
//	矩形
	public double square(double width,double height){
		return width*height;
	}
//	圆形
	public void square(float r){
		System.out.println("圆形面积："+3.14*r*r);
	}
	
//	三角形
	public double square(double height,float bottom){
		return bottom*height/2;
	}

}
