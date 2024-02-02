package com.atguigu.java1;

public class ThreadTest1 {
	public static void main(String[] args) {
		
		//3.创建Thread类的子类对象
		MyThread m = new MyThread();
		MyThread m1 = new MyThread();
		MyThread m2 = new MyThread();
		
		//4.通过此对象调用start():①启动当前线程	②调用当前线程的run();
//		m.start();
//		m1.start();
//		m2.start();
		m.run();
		 for(int i = 0;i<=100;i++) {
			 if(i % 2 != 0) {
				 System.out.println(Thread.currentThread().getName()+":"+i);
			 }
		 }
	}
	
	
}
//创建线程的方式一：extends
//1.创建一个继承于Thread类的子类
class MyThread extends Thread{
	
	
	//2.重写Thread类的run(),将此线程执行的操作声明在run()中
	 public void run() {//输出100以内的偶数
		 for(int i = 0;i<=100;i++) {
			 if(i % 2 == 0) {
		//System.out.println(getName()+"::::::::"+i);//相当于this.getName(),this是当前对象，即MyThread的对象
		//这里不是一个新的线程，只是相当于直接调用的方法而已！！！
		System.out.println(Thread.currentThread().getName()+"::::::::"+i);//这里才是获取当前线程名字
			 }
		 }
		
	}
	
}