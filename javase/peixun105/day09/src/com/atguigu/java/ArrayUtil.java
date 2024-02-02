package com.atguigu.java;

public class ArrayUtil {
public static void main(String[] args) {
	ArrayUtil p = new ArrayUtil();
	int[] arry = new int[] {33,55,66,22,63,633,77,543,-8,0};
	//存在栈空间中的首地址值是[I@7852e922
	
	// 数组中的最大值
	int max = p.getMax(arry);
	System.out.println("最大值是："+max);
	System.out.println();
	//System.out.println(p.getMax(arry));
	// 数组中的最小值
	int min = p.getMin(arry);
	System.out.println("最小值是："+min);
	// 数组的反转
	//p.reverse(arry);
	// 数组的复制
	//System.out.println(arry);//存在栈空间中的首地址值是[I@7852e922
	System.out.println("数组的复制：");
	int[] copy = p.copy(arry);
	p.print(copy);
//	for(int i = 0;i<copy.length;i++) {
//		System.out.print(copy[i]+"\t");
//	}
	System.out.println();
	// 数组的排序
	System.out.println("排序前是：");
	p.print(arry);
	System.out.println("排序后是：");
	p.sort(arry);
	p.print(arry);
	// 数组的遍历
	//p.print(arry);s
	// 数组的线性查找,查找指定元素
	System.out.println("查找：");
	int index = p.getIndex(arry,63);
	if(index > 0) {
		System.out.println("找到了，索引位置是："+index);
	}else {
		System.out.println("未找到");
	}
	//System.out.println(p.getIndex(arry,63));
}
	
	
	
	
	// 数组中的最大值
	public int getMax(int[] arr) {
		int maxValue = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (maxValue < arr[i]) {
				maxValue = arr[i];
			}
		}

		return maxValue;
	}

	// 数组中的最小值
	public int getMin(int[] arr) {
		int minValue = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (minValue > arr[i]) {
				minValue = arr[i];
			}
		}
		return minValue;
	}

	// 数组总和
	public int getSum(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}

		return sum;
	}

	// 数组平均值
	public int getAvg(int[] arr) {
		return getSum(arr) / arr.length;
	}

	// 数组的反转
	public void reverse(int[] arr) {
		for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
			int temp1 = arr[i];
			arr[i] = arr[j];
			arr[j] = temp1;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]+" ");
		}

	}

	// 数组的复制
	/*public int[] copy(int[] arr) {
		System.out.println(arr);//arr在main方法中传过来的数组首地址值[I@7852e922
		arr = new int[arr.length];//新new出来的数组的首地址值[I@4e25154f
		System.out.println(arr);
		for (int i = 0; i < arr.length; i++) {
			arr[i] = arr[i];
		}
		return arr;//新new出来的数组的首地址值[I@4e25154f
	}
*/
	public int[] copy(int[] arr) {
		int[] arr1 = new int[arr.length];
		for (int i = 0; i < arr1.length; i++) {
			arr1[i] = arr[i];
		}
		return arr1;
	}

	// 数组的排序
	public void sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
		    }
	   }
	}
	// 数组的遍历
	public void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// 数组的线性查找,查找指定元素
	public int getIndex(int[] arr, int dest) {
		for (int i = 0; i < arr.length; i++) {
			if (dest == arr[i]) {// equals比的是内容是否相等

				return i;
			}
		}
		return -1;
	}
}
