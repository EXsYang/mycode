package com.mj.san;

import java.util.Scanner;

public class Test1 {
public static void main(String[] args) {
	Yuan s=new Yuan();
	Scanner in = new Scanner(System.in);
	int r=in.nextInt();
	s.setR(r);
    float b=3.14f*r*r;
    System.out.println(b);
	
}
}
