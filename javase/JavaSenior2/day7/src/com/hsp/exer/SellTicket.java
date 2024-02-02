package com.hsp.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-19:41
 */
public class SellTicket {
    public static void main(String[] args) {
//        Window window1 = new Window();
//        Window window2 = new Window();
//        Window window3 = new Window();
//
//        window1.setName("窗口一");
//        window2.setName("窗口二");
//        window3.setName("窗口三");
//
//        window1.start();
//        window2.start();
//        window3.start();

            Window1 window1 = new Window1();
            Thread t1 = new Thread(window1);
            Thread t2 = new Thread(window1);
            Thread t3 = new Thread(window1);

            t1.setName("窗口一");
            t2.setName("窗口二");
            t3.setName("窗口三");

            t1.start();
            t2.start();
            t3.start();


    }
}

class Window extends Thread {
    static int  ticket = 100;
    @Override
    public void run() {

        while (true) {
            System.out.println(currentThread().getName() + "卖第" + ticket + "张票");
            ticket--;
            if (ticket == 0) {
                break;
            }
        }

    }
}

class Window1 implements Runnable {
    int ticket = 100;
    @Override
    public void run() {

        while (true) {
//            sell();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {
                System.out.println(this.getClass());
                if (ticket > 0){

                System.out.println(Thread.currentThread().getName() + "卖第" + ticket + "张票");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticket--;
    //        System.out.println(ticket);
            }
            }

            if (ticket <= 0) {
                break;
            }
        }
    }

//    public synchronized void sell(){
//        if (ticket > 0){
//
//            System.out.println(Thread.currentThread().getName() + "卖第" + ticket + "张票");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            ticket--;
////        System.out.println(ticket);
//        }
//    }
}