package com.atguigu.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-15:49
 */
public class NumberTest {
    //线程通信；两个线程交替打印1~100   【下面这段代码存在虚假唤醒问题。】

    /**
     * 是否存在虚假唤醒问题？ 是。因为它没有遵循将 wait() 放入 while 条件检查循环的标准范式，无法正确处理虚假唤醒。
     * 代码健壮吗？ 不健壮。它的正确性依赖于两个线程间非常理想化的执行时序，这在复杂的真实环境中是不可靠的。
     * 如何修正？ 必须引入一个状态变量来判断轮到哪个线程执行，并把 wait() 放入 while 循环中检查这个状态变量。例如，可以使用 number % 2 来判断，或者引入一个 boolean 标志位。
     */
    public static void main(String[] args) {
        Number number = new Number();

        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程一");
        t2.setName("线程二");

        t1.start();
        t2.start();

    }
}

class Number implements Runnable {
    private int number = 0;

    @Override
    public void run() {
//        for (int i = 0; i < 100; i++) {//多线程时，这里的局部变量，一人一份
//        for (; number < 100; ) {//多线程时，这里的局部变量，一人一份
            while (true){

            synchronized (this) {
                notify();
                if (number <= 100){
                    try {
                        Thread.sleep(10);//线程进入阻塞状态，不会释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;
//                System.out.println(Thread.currentThread().getName() + "局部变量 i：:" + i);

                    try {
                        wait();//执行wait()的线程进入阻塞状态，释放锁
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}