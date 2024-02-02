package com.atguigu.exer;

public class StringTest {
public static void main(String[] args) {
	StringTest test = new 	StringTest();
	test.show(5,3,4,5);
	test.show(new int[] {5,2,4,6,6,5});//也可以是其它类型的
	//也能传一个数组
}
	
	public void show(int ... i) {
		
		for(int j=0;j<i.length;j++) {
			System.out.print(i[j]+"  ");
		}
		System.out.println();
	}
	
	
}
