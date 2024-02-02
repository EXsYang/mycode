package com.atguigu.java2;

public class RunnableTest1 {
public static void main(String[] args) {
	//3.创建实现类的对象
	MThread m = new MThread();
	//4.将此对象作为参数传递到Thread类的构造器中，创建Thread类的对象
	Thread t = new Thread(m);
	Thread t1 = new Thread(m);
	//5.通过Thread类的对象调用start();
	t.setName("线程一");
	t.start();
	
	t1.setName("线程二");
	t1.start();
	
	
}
}

//1.创建一个实现了Runnable接口的类
class MThread implements Runnable{

	//2.实现类去实现Runnable中的抽象方法：run();
	@Override
	public void run() {
		for(int i = 1;i<100;i++) {
			if(i % 2 == 0) {
				System.out.println(Thread.currentThread().getName()+":"+i);//这里不能直接写getName()
				//因为Runnable不继承于Thread
			}
		}
		
	}
	
}