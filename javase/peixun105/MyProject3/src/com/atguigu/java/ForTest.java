package com.atguigu.java;

import com.atguigu.team.service.TeamException;

public class ForTest {
	public static void main(String[] args) {

		for (int i = 0; i < 4; i++) {

			if (i > 5) {
				System.out.println("进入if语句");
			}else {
				System.out.println("else");
			}
			// System.out.println("跳过if语句");

		}
		String[] array = new String[] { "1", "3", "17", "43" };
		array[0] = null;// 引用类型的数组元素可以为null
		for (int i = 0; i < array.length; i++) {

			// System.out.println(array[i]);
		}

		Integer a = new Integer(127);
		Integer b = new Integer(128);
		int c = 127;
		int d = 128;

		Integer e = 127;
		Integer g = 127;
		Integer f = 128;
		Integer h = 128;

		long k = 127l;
		long l = 128l;
		Long m = 127l;
		Long n = 128l;
		System.out.println("小于128时，使用==比较包装类和对应基本数据类型:比较的是数值" + (a == c));// true
		System.out.println("大于127时，使用==比较包装类和对应基本数据类型:比较的是数值" + (b == d));// true
		System.out.println("小于128时，使用==比较包装类和对应包装类:==比较的是地址值" + (a == e));// false
		System.out.println("大于127时，使用==比较包装类和对应包装类:==比较的是地址值" + (b == f));// false
		System.out.println("小于128时，使用==比较包装类和其他基本数据类型:" + (e == k));// true，使用==比较包装类和其他基本数据类型
		System.out.println("大于127时，使用==比较包装类和其他基本数据类型:" + (f == l));// ture 比较的是保存的数据
		// System.out.println(f == m);//使用 == 比较两个不同引用类型数据编译不通过

		System.out.println("小于128时，通过装箱的方式，包装类和对应包装类:==比较的是IntegerCache数组中的地址值" + (e == g));// ture
		System.out.println("大于127时，通过new的方式，包装类和对应包装类:==比较new出来的Integer类型的地址值:" + (f == h));// false 比较的是保存的数据

		System.out.println("小于128时，包装类.equals(其他基本数据类型):" + f.equals(m));// 使用equals比较两个不同引用类型数据编译通过,结果为false
		System.out.println("大于127时，包装类.equals(其他基本数据类型):" + f.equals(n));// 使用equals比较两个不同引用类型数据编译通过,结果为false
		// 如下为Integer类中重写的equals()源码，如果obj和Integer没有关系，不属于子父类关系就直接返回false
		// public boolean equals(Object obj) {
		// if (obj instanceof Integer) {
		// return value == ((Integer)obj).intValue();
		// }
		// return false;
		// }

		System.out.println("小于128时，包装类.equals(对应基本数据类型):" + e.equals(c));// 使用包装类.equals(对应基本数据类型)比较的是保存的数据内容
		System.out.println("超过127后，包装类.equals(对应基本数据类型):" + f.equals(d));// 使用包装类.equals(对应基本数据类型)比较的也是保存的数据内容
		System.out.println("小于128时，包装类.equals(对应包装类):" + a.equals(e));// 使用包装类.equals(对应基本数据类型)比较的是保存的数据内容
		System.out.println("超过127后，包装类.equals(对应包装类):" + b.equals(f));// 使用包装类.equals(对应基本数据类型)比较的也是保存的数据内容

		System.out.println(a);// 127
		System.out.println(b);// 地址值

		System.out.println(e);
		System.out.println(f);
		/* 结论： Integer e = 127; 
		 *		Integer a = new Integer(127);
		 *		Integer f = 128;
		 *		Integer b = new Integer(128);
		 *如上两种方式使用==时返回false
		 *Integer e = 127;
		 *Integer g = 127;
		 *Integer f = 128;
		 *Integer h = 128;
		 * 如上两种eg方式使用==时返回true,fh方式使用==时返回false
		 * 
		 * 
		 */
		for(int i = 1;;)//该循环可以正常运行，死循环
		System.out.println(i++);
	}
}
