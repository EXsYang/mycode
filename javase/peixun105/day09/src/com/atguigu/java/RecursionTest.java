package com.atguigu.java;

public class RecursionTest {
public static void main(String[] args) {
	
	RecursionTest test = new RecursionTest();
	
	int sum1 = test.getSum(100);
	System.out.println(sum1);
	int sum2 = test.getSum1(3);
	System.out.println(sum2);
	
	

	//计算1-100之间的和
	//方式一
	int sum = 0;
	for(int i = 1;i <= 100;i++) {
		sum += i;
		
	}
	System.out.println(sum);
}

public int getSum(int n) {//递归方法
	if(n == 1) {
		return 1;
	}else {
		return n + getSum(n - 1);
	}
}
	//计算n!
	public int getSum1(int n) {
		if(n == 1) {
			return 1;
		}else {
			return n * getSum1(n - 1);
		}
		
}






}
