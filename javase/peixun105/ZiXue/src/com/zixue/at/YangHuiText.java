package com.zixue.at;

public class YangHuiText {
public static void main(String[] args) {
	
	
	int i;
	int j;
	int[][] yangHui = new int[10][];
	for(i=0;i<yangHui.length;i++) {
		yangHui[i]	= new int[i+1];
				yangHui[i][0] = 1;
				yangHui[i][i] = 1;
				if(i>1) {
					for(j=1;j<yangHui[i].length-1;j++) {
						yangHui[i][j] = yangHui[i-1][j-1] + yangHui[i-1][j];
				
					}			
				}
				
	}
	for(i=0;i<yangHui.length;i++) {
		for(j=0;j<yangHui[i].length;j++){
			System.out.print(yangHui[i][j]+"  ");
		}
		System.out.println();
	}
	
		
	
	
	}
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	

