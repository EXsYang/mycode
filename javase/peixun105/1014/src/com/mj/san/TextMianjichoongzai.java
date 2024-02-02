package com.mj.san;

public class TextMianjichoongzai {

	public static void main(String[] args) {
		Mianjichoongzai s = new Mianjichoongzai();
//		矩形面积
		double d = s.square(5.5,3.62);
		System.out.println("矩形面积："+d);
		
//		圆形面积
		s.square(3.2f);
		
		//三角形面积
		double sj = s.square(5.5, 3.62f);
		System.out.println("三角形面积："+sj);
	}


}
