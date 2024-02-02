package com.atguigu.exer2;

public class WindowTest4 {

	// 创建三个窗口卖票，票数100张，使用实现Runnable接口的方式
	public static void main(String[] args) {

		Window4 w = new Window4();

		Thread t1 = new Thread(w);
		t1.setName("窗口一");
		t1.start();

		Thread t2 = new Thread(w);
		t2.setName("窗口二");
		t2.start();

		Thread t3 = new Thread(w);
		t3.setName("窗口三");
		t3.start();

	}

}

class Window4 implements Runnable {

	private int ticket = 100;
	Object obj = new Object();

	@Override
		public void run() {
			//Object obj = new Object();这里三个锁，一个线程一个，错误的
			for (int i = 0; i < 100; i++) {
//				synchronized(obj) {//方式一
			synchronized(this) {//方式一			可以，只有一个当前对象w
					
					//synchronized(new Object()) {//每跑一次新建一个锁，错误的
				if (ticket > 0) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":" + ticket);
					ticket--;
				}
				}
			}
		}

	
}
