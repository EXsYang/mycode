package com.atguigu.exer4;

public class MattyTest {
public static void main(String[] args) {
	
	Boy boy3 = new Boy("唐伯虎",34);
	
	Girl girl4 = new Girl("秋香",18);		
	
	
	
	
	Boy boy1 = new Boy();
	Girl girl2 = new Girl();
	girl2.setAge(21);
	Girl girl3 = new Girl();
	girl3.setAge(22);
	girl2.compare(girl3);
	System.out.println(girl2.compare(girl3));
	
}
}
