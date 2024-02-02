package com.atguigu.exer;

public class Exer3Test {

	public static void main(String[] args) {
		Exer3Test a = new Exer3Test();
		//a.method(2,3);
		double area2 = a.method(2,3);
		System.out.println(area2);
	}
	
/*	public double method() {
		for(int i = 0;i<10 ;i++) {
			for(int j= 0;j<8;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		//double area  = 10*8;
		//return area;
		 return 10*8;
	}
	*/
	public double method(double m,double n) {
		for(int i = 0;i<m ;i++) {
			for(int j= 0;j<n;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		double area = m*n;
		return area;
	}
	
	
}
