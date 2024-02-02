package com.atguigu.exer;

public class StudentTest {

	public static void main(String[] args) {
		Student[] stus = new Student[20];
		for (int i = 0; i < stus.length; i++) {
			// 给数组元素赋值
			stus[i] = new Student();
			// 给Student对象的属性赋值
			stus[i].number = i + 1;
			// 年级 [1,6]
			stus[i].state = (int) (Math.random() * (6 - 1 + 1)) + 1;
			// 成绩 [0,100]
			stus[i].score = (int) (Math.random() * (100 - 0 + 1));
		}
		// 问题2 使用冒泡排序按学生成绩排序，并遍历所有学生信息，     遍历对象数组
		for (int i = 0; i < stus.length - 1; i++) {// 最后一次冒泡排序排完还是自己
			for (int j = 0; j < stus.length - 1 - i; j++)
				if (stus[j].score > stus[j + 1].score) {
					// 如果需要换序，交换的是数组的元素，Student对象！！！
					Student temp = stus[j];
					stus[j] = stus[j + 1];
					stus[j + 1] = temp;
				}
			// System.out.println(stus[i]);这里是对象数组的地址值
		}
		for (int i = 0; i < stus.length; i++) {
			System.out.println(stus[i].info());
			// System.out.println(stus[i].state + "年级 " + "学号是:" + stus[i].number + "成绩是:" +
			// stus[i].score);
		}

		System.out.println("*************************");
		// 问题1 打印出三年级的学生信息
		/*
		 * for (int i = 0; i < stus.length; i++) { if (stus[i].state == 3) {
		 * System.out.println(stus[i].state+"年级
		 * "+"学号是:"+stus[i].number+"成绩是:"+stus[i].score); }
		 * 
		 * }
		 */

	}

	
}
class Student{
	int number;
	int state;
	int score;

	public String info(){

		return "学号：" + number + ",年级:" + state + ",成绩是：" + score;

	}

}
