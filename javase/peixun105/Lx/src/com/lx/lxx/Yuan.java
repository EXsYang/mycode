package com.lx.lxx;
import java.util.Scanner;

public class Yuan {

	public static void main(String[] args) {
		Mainji a=new Mainji();
		Scanner in=new Scanner(System.in);
		int  c=in.nextInt();
		a.setR(c);
		double b=3.14*c*c;
		System.out.println(b);
	}
}
