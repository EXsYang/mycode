package at.zixue.com;

public class ArrayTest2 {
	/*
	 * 二维数组的使用
	 * 
	 * 1.理解： 对于二维数组的理解，我们可以看成是一维数组array1有作为另一个数组array2的元素而存在。
	 * 其实，从数组底层的运行机制来看，其实没有多维数组。
	 * 
	 * 2.二维数组的使用： ① 二维数组的声明和初始化 ② 如何调用数组的指定位置的元素 ③ 如何获取数组的长度 ④ 如何遍历数组 ⑤ 数组元素的默认初始化 ⑥
	 * 数组的内存解析
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		
		
		// ① 一维数组的声明和初始化
				int[] ids = new int[] { 123, 124, 125 };
				
		
		// ① 二维数组的声明和初始化
		int[] arr = new int[] { 12, 12, 12 };
		// 静态初始化
		int[][] arr1 = new int[][] { { 10, 22, 44 }, { 0, 345 }, { 6, 5, 54 } };
		// 动态初始化1
		String[][] arr2 = new String[3][3];
		// 动态初始化2
		String[][] arr3 = new String[3][];
		// 错误的情况 {}里附上值了，[]里就不要写长度了，或是后面的[]写长度了，{}就不要有了
		// String[][] arr4 = new String[][3];
		// String[3][4] arr5 = new String[3][];
		// int[][] arr6 = new int[4][3]{{10,22,44},{0,345},{6,5,54}};
		// 也是正确的情况
		int arr4[][] = new int[][] { { 10, 22, 44 }, { 0, 345 }, { 6, 5, 54 } };
		int[] arr5[] = { { 10, 22, 44 }, { 0, 345 }, { 6, 5, 54 } };
		int[] arr6 = {1,2,3,4,5,6};//类型推断
//如何调用指定位置上的元素
		System.out.println(arr1[0][1]);
		arr3[1] = new String[4];//附上值解决下面的空指针   null
		System.out.println(arr3[1][0]);//空指针,这样写运行报错
		//Exception in thread "main" 22
		//java.lang.NullPointerException
		//at at.zixue.com.ArrayTest2.main(ArrayTest2.java:40)
		//

		int[] arr12 = new int[]{1,2,3};

		int[] arr22 = new int[4];
		//
		int[][] arr32 = new int[][]{{1,4,5,6},{2,6,55,4},{54,22,56,333}};
		System.out.println(arr32[0][2]);
		int[][] arr42 = new int[3][4];
		
		
		int[][] arr43 = new int[3][];
		System.out.println(arr43[0]);//第一列第一个位置上是一个数组，引用类型的默认初始化值是null
		System.out.println(arr43[1][0]);//第二列第一个元素还没有初始化，空指针
//		int i;
//		int j;
//		for(i=0;i<arr32.length;i++) {
//			
//			for(j=0;j<arr32[i].length;j++) {
//				System.out.print(arr32[i][j]+"\t");			
//			}
//			System.out.println('a');
//		}
//		
//		
	}

}
