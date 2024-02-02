package com.mj.san;

import java.util.Scanner;

public class test {
public static void main(String[] args) {
	Mianji j=new Mianji();
	Scanner in=new Scanner(System.in);
	int a=in.nextInt();
	int b=in.nextInt();
        j.setA(a);
        j.setB(b);
        int sum=a*b;
        System.out.println(sum);
}
}
