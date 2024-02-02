package com.lxlx.x;

public class TicketsHouse implements Runnable{
	int fiveAmount = 1,twentyAmount = 0;
	public void run(){
		if(Thread.currentThread().getName().equals("张飞")){
			saleTicket(20);
		}else if(Thread.currentThread().getName().equals("李逵")){
			saleTicket(5);
		}else if(Thread.currentThread().getName().equals("刘备")){
			saleTicket(5);
		}
	}
	
	private synchronized void saleTicket(int money){
		if(money == 5){
			fiveAmount = fiveAmount + 1;
			System.out.println("给"+Thread.currentThread().getName()+"入场券，"+Thread.currentThread().getName()+"的钱正好");
		}else if(money == 20){
			while(fiveAmount<3){
				try{
					System.out.println("\n"+Thread.currentThread().getName()+"靠边等.....");
					wait();
					System.out.println("\n"+Thread.currentThread().getName()+"继续买票");
			}catch(InterruptedException e){
			}
			}
			fiveAmount = fiveAmount - 3;
			twentyAmount = twentyAmount+1;
			System.out.println("给"+Thread.currentThread().getName()+"入场券，"+Thread.currentThread().getName()+"给20，找零15");
		}
		notifyAll();
	}
}
