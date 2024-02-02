package com.atguigu.java1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建线程的第四种方式，使用线程池
 * @author yangda
 * @create 2021-11-26-21:03
 */
public class ThreadPool {
    public static void main(String[] args) {
        //1.提供指定线程数量的线程池    注意：ExecutorService是一个接口
        ExecutorService service = Executors.newFixedThreadPool(10);//线程池的方式
        System.out.println(service.getClass());//通过打印获取实现类对象service所在的类
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;//将接口类型的强转成实现类类型的
        //实现类中才有设置的方法
        //设置线程池的属性，在接口的实现类中
        //service1.setCorePoolSize(10);
        //service1.setKeepAliveTime();




        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口的实现类的对象
        service.execute(new RunnableThread());//适用于Runnable
        service.execute(new RunnableThread1());//适用于Runnable

        //service.submit(Callable callable);//适用于Callable

        //3.关闭连接池
        service.shutdown();
    }
}
class RunnableThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
            }
        }
    }
}
class RunnableThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if(i % 2 != 0){
                System.out.println(i);
            }
        }
    }
}