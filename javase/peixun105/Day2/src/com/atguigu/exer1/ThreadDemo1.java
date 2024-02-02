package com.atguigu.exer1;

public class ThreadDemo1 {
public static void main(String[] args) {
	new Thread() {
		public void run() {
			for(int i = 1;i<100; i++) {
				if(i%2 == 0) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		}
	}.start();
	new Thread() {
		public void run() {
			for(int i = 1;i<100; i++) {
				if(i%2 != 0) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
			}
		}
	}.start();
}
}
