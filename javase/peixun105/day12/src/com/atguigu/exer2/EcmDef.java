package com.atguigu.exer2;

import java.util.Scanner;

public class EcmDef {
	public static void main(String[] args) {
		
		try {
			int  a = Integer.parseInt(args [0]);
			int  b = Integer.parseInt(args [1]);
			ecm(a,b);
		} catch(NumberFormatException e){
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}catch(ArithmeticException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.getMessage();
		}
//		try {
//			ecm(a,b);
//		} catch(NumberFormatException e){
//			e.printStackTrace();
//		}catch(ArrayIndexOutOfBoundsException e) {
//			e.printStackTrace();
//		}catch(ArithmeticException e) {
//			e.printStackTrace();
//		}catch (Exception e) {
//			e.getMessage();
//		}
		
	}
  
    
	public static int ecm(int a,int b) throws Exception{
		
		if(a < 0 || b < 0) {
			throw new EcDef("输入负数请重新输入！");
		}else {
			int c = a/b;
			System.out.println(c);
			return c;
		}
		
		
}
	
	
	
	
	
	
	
	
	
	
	
}