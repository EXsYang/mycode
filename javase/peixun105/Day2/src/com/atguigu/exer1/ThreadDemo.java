package com.atguigu.exer1;

public class ThreadDemo {
//创建两个分线程，其中一个遍历100以内的奇数，一个遍历100以内的偶数
	public static void main(String[] args) {
		MyThread m = new MyThread();
		MyThread1 m1 = new MyThread1();
		
		m.start();
		m1.start();
		
		
	}
	
	
}

class MyThread extends Thread{
	
	public void run() {
		for(int i = 1;i<100; i++) {
			if(i%2 == 0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		}
	}
	
	
}
class MyThread1 extends Thread{
	
	public void run() {
		for(int i = 1;i<100; i++) {
			if(i%2 != 0) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		}
	}
	
	
}