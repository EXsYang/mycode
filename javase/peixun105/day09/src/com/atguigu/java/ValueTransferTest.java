package com.atguigu.java;

public class ValueTransferTest {

	public static void main(String[] args) {
		int m = 1;
		int n = 2;
		ValueTransferTest p = new ValueTransferTest();
		p.trans(m,n);
		System.out.println("m:"+m+"  n:"+n);
		int[] arr = new int[] {33,55,66,22,63,633,77,543,-8,0};
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
//					int temp = arr[j];
//					arr[j] = arr[j + 1];
//					arr[j + 1] = temp;
					p.trans1(arr[j], arr[j + 1]);//把数值传到形参，在方法里把形参数值交换了一下位置，
					//然后销毁了啥也没发生,数组中的arr[j], arr[j + 1]元素保持不变，没有交换位置
				}
		    }
	   }
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}
	public void trans1(int i,int j) {
		int temp = i;
		i = j;
		j = temp;
		System.out.println("i:"+i+"j:"+j);
	}
	
	public void trans(int m,int n) {
		int temp = m;
		m = n;
		n = temp;
		
		
		//System.out.println("m:"+m+"n:"+n);
	}
	

	
}

class Order {
	int orderId;
	
}