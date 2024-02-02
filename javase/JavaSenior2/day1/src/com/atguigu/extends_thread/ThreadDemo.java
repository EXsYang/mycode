package com.atguigu.extends_thread;

/**
 * 1.start()
 * 2.run()
 * 3.currentThread()
 * 4.getName()
 * 5.setName()
 * 6.yield():释放当前cpu的执行权
 * 7.join:在线程a中调用线程b的join(),此时线程a就进入阻塞状态，直到线程b完全执行完以后
 * ，线程a才结束阻塞状态
 * 8.stop() 已过时，强制结束当前线程
 * 9.sleep(long millis)
 * 10.isAlive()
 *
 **/

public class ThreadDemo {
    //创建两个分线程，一个遍历100以内的奇数，一个遍历一百以内的偶数
    //测试方法

    
    
    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();
        HelloThread2 helloThread2 = new HelloThread2();

        helloThread.setName("线程1");
        helloThread2.setName("线程2");
        helloThread.start();
        helloThread2.start();
        Thread.currentThread().setName("主线程");
        for (int i = 0; i < 100; i++) {
            if (i  == 30) {
                try {
                    helloThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class HelloThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(currentThread().getName() + ":" + i);
            }
        }
    }
}

class HelloThread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(currentThread().getName() + ":" + i);
            }

        }
    }
}