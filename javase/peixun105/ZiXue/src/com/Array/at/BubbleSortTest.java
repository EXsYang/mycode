package com.Array.at;

public class BubbleSortTest {

	public static void main(String[] args) {
		
		//冒泡排序
		int[] arr = new int[] {36,6,15,89,99,44,-36,-11,87};//33,42,23,32,55,52,54,34,234,3555,2365};
		//-2 23 32 33 34 42 52 54 55 234 2365 
		for(int i = 0;i<arr.length-1 ;i++) {
			for(int j=0;j<arr.length-1-i ;j++)
				if(arr[j]>arr[j+1]) {
					int temp = arr[j] ;
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
				
		}
		for(int i = 0;i<arr.length;i++ ) {
			System.out.print(arr[i]+" ");
		}
		
		
		
	}
}
