package com.atguigu.exer;

class StudentTest2 {

	public static void main(String[] args) {

	
		
		// StudentTest2 test = new StudentTest2();
		Student2[] stus = new Student2[20];
		for (int i = 0; i < stus.length; i++) {
			// 给数组元素赋值
			stus[i] = new Student2();
			// 给Student对象的属性赋值
			stus[i].number = i + 1;
			// 年级 [1,6]
			stus[i].state = (int) (Math.random() * (6 - 1 + 1)) + 1;
			// 成绩 [0,100]
			stus[i].score = (int) (Math.random() * (100 - 0 + 1));
		}
		
          StudentTest2 test = new StudentTest2();//实例化对象
		
          // 遍历学生数组
          test.print(stus);
          
          System.out.println("*************************");	
		
      	  // 问题1 打印出3年级(state值为3)的学生信息
          test.searchState(stus, 3);
          
          System.out.println("*************************");	
          
          // 问题2 使用冒泡排序按学生成绩排序，并遍历所有学生信息
          test.sort(stus);
          
          // 遍历学生数组
          test.print(stus);
          
          
		
}//这是main方法的}，下面的方法和main方法并列
	/**
	 * 
	 * @Description	遍历Student2[]数组的操作
	 * @author	yangda
	 * @date 2021年10月31日下午1:55:16
	 * @param stus
	 */
	public void print(Student2[] stus) {
		for(int i = 0;i<stus.length;i++) {
			System.out.println(stus[i].info());
		}
	}
	
	// 问题1 打印出三年级的学生信息
	/**
	 * 
	 * @Description	查找Student2数组中指定年级学生信息
	 * @author	yangda
	 * @date 2021年10月31日下午1:57:30
	 * @param stus	要查找的数组
	 * @param state	要找的年级
	 */
	public void searchState(Student2[] stus,int state) {
		for(int i = 0;i<stus.length;i++){
			if (stus[i].state == state) {
				System.out.println(stus[i].info());
			}
		}
	}
	/**
	 * 
	 * @Description	给Student2数组排序
	 * @author	yangda
	 * @date 2021年10月31日下午2:00:04
	 * @param stus
	 */
	public void sort(Student2[] stus) {
		for (int i = 0; i < stus.length - 1; i++) {// 最后一次冒泡排序排完还是自己
			for (int j = 0; j < stus.length - 1 - i; j++) {
				if (stus[j].score > stus[j + 1].score) {
					// 如果需要换序，交换的是数组的元素，Student对象！！！
					Student2 temp = stus[j];
					stus[j] = stus[j + 1];
					stus[j + 1] = temp;
				}
			}
	     }
	}
}

class Student2{

	int number;
	int state;
	int score;
	
	public String info() {
		return "学号：" + number + ",年级:" + state + ",成绩是：" + score;
	}
}