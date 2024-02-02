package com.hsp.method;

/**
 * @author yangda
 * @description:
 * @create 2022-11-21-20:58
 */
public class Method_ {
    public static void main(String[] args) throws InterruptedException {
        T t = new T();
        t.setName("杨达");
        t.setPriority(Thread.NORM_PRIORITY);
//        System.out.println(t.getName());

        t.start();

        while (true){

            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                System.out.println("主线程打印" + i);
                if (i == 4){
                    t.interrupt();
                }
            }
            System.out.println("杨达优先级为：" + t.getPriority());
        }

    }
}
class T extends Thread{
    @Override
    public void run() {
        while (true){
            for (int i = 0; i < 100; i++) {
                System.out.println(currentThread().getName() + "吃包子~~~~" + i);
            }
            try {
                System.out.println("休眠中~~~");
                sleep(300000);
            } catch (InterruptedException e) {
                System.out.println(currentThread().getName() +"被interrupt，线程中断");

            }


        }
        }


}