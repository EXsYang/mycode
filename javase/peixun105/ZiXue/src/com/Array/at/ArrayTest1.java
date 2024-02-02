package com.Array.at;

public class ArrayTest1 {
	public static void main(String[] args) {
		String[] arr = new String[] { "aa", "bb", "cc", "dd", "ee", "ff" };

		
		//线性查找
		String dest = "bb";
		dest = "cd";
		boolean flag = false;
		for (int i = 0; i < arr.length; i++) {
			if (dest.equals(arr[i])) {//equals比的是内容是否相等
				System.out.println("找到了是：" + i);
				flag = true;
				break;//找到以后就可以不往下找了
				
			}
		}
		if(flag == false) {
			System.out.print("没有找到");
		}
		System.out.println();
		//二分法查找，针对的是有序
		int[] arr1 = new int[] {-23,-4,4,33,44,53,542,3432};
//		int dest1 = 33;
		int dest1 = 332;
		int head = 0;
		int end = arr1.length-1;
		boolean isFlag = true;
		while(head<=end) {
			int middle = (head+end)/2;
			if(dest1 == arr1[middle]) {
				System.out.print("位置是："+middle);
				isFlag = false;
				break;
			}else if(arr1[middle]>dest1) {
				 end = middle - 1;
			}else {//arr1[middle]<dest1
				head = middle + 1;
			}
			
		}
		if(isFlag == true) {
			System.out.println("很遗憾，没有找到！");
		}
		
		
		
		
		
	
	}
}
