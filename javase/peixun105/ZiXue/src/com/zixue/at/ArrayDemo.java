package com.zixue.at;

import java.util.Scanner;

public class ArrayDemo {
	public static void main(String[] args) {
		Scanner score = new Scanner(System.in);
		System.out.print("请输入学生人数：");
		int number = score.nextInt();
		System.out.println("请输入" + number + "个成绩：");
		int i;
		int j;
		int max = 0;
		int[] score2 = new int[number];//这里是数组的长度，即数组元素个数，而不是数组下标！！！
		for (i = 0; i < score2.length; i++) {//遍历数组
			score2[i] = score.nextInt();//挨个填入数据
			//System.out.println(score2[i]);	
			if(max<score2[i]) {
				max=score2[i];
			}
			
			
		}
		
		//for (j = 0; j < score2.length; j++) {// 遍历数组
			// 获取数组中的最大值，假设0是最大值，用数组中的数和
			// 0比较大小，如果比0大，那么把这个数当做最大值，把这个数赋给前一个数
		//	if (max < score2[j]) {
		//		max = score2[j];
		//	}
		//}
		System.out.println("最高分是" + max);
        String lever;
		for (i=0;i<score2.length;i++) {
			if(score2[i]>=(max-10)) {
				lever = "A";
			}else if(score2[i]>=(max-20)){
				lever = "B";
			}else if(score2[i]>=(max-30)){
				lever = "C";
			}else{	
				lever = "D";
		}
			System.out.println("student " + i +" score is "+ score2[i]+" grade is "+lever);
	}
	}
}
