package com.zixue.at;

public class ArrayExer1 {
	
	public static void main(String[] args) {
		int i;
		int j;
		int[][] arr1 =new int[][]{{3,5,8},{12,9},{7,0,6,4}};
		for(i=0;i<arr1.length;i++) {
			for(j=0;j<arr1[i].length;j++) {
				System.out.print(arr1[i][j]+" ");
			}
			System.out.println();
			
		}
		
		
	}
	

}
