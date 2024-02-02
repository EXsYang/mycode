package com.atguigu.com;

import java.util.Arrays;
import java.util.Comparator;

public class Stringtest {

	public static void main(String[] args) {
		int[] i1 = { 3, 2, 6, -1, 0 };// 一个数组
		maopao2(i1, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				int n1 = (Integer) o1;
				int n2 = (Integer) o2;
				return n1 - n2;
			}
		});
			System.out.println(Arrays.toString(i1));
	}

	public static void maopao2(int[] arr, Comparator c) {// 传进来一个数组放在形参，
		int temp;// 定义临时变量
		for (int i = 0; i < arr.length - 1; i++) {// 冒泡排序，i控制外层循环次数
			for (int j = 0; j < arr.length - i - 1; j++) {// j控制内层循环比较次数，越来越少，外层循环走一次内层循环减一
				if (c.compare(arr[j], arr[j + 1]) > 0) {// 定制排序
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}

			}
		}

	}

}
