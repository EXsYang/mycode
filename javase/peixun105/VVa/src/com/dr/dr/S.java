package com.dr.dr;

public class S {
	int k = 3;

	public static void main(String[] args) {
		A x1 = new A();
		x1.k++;
		x1.k++;
		A x2 = new A();
		x2.k++;
		System.out.println(x1.k);
	}
}