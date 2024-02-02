package com.atguigu.exer3;

public class ClerkTest {
public static void main(String[] args) {
	
	Clerk cl = new Clerk();
	
	Productor p = new Productor(cl);
	p.setName("生产者线程");
	p.start();
	
//	Customer c = new Customer(cl);
//	c.setName("消费者线程");
//	c.start();
	
	
	
	
	
}
}
