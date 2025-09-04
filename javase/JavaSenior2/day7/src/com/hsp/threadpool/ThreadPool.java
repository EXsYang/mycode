package com.hsp.threadpool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yangda
 * @create 2025-09-04-3:18
 * @description:
 * 新增方式二：使用线程池
 *
说明：
 * 好处：
 * 1.提高响应速度（减少了创建新线程的时间）
 * 2.降低资源消耗（重复利用线程池中线程，不需要每次都创建）
 * 3.便于线程管理
 *      corePoolSize：核心池的大小
 *      maximumPoolSize：最大线程数
 *      keepAliveTime：线程没任务时最多保持多长时间后会终止

面试题：Java中多线程的创建有几种方式？四种。
 */
public class ThreadPool {

    class NumberThread implements Runnable{

        @Override
        public void run() {
            for(int i = 0;i <= 100;i++){
                if(i % 2 == 0){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        }
    }

    class NumberThread1 implements Runnable{

        @Override
        public void run() {
            for(int i = 0;i <= 100;i++){
                if(i % 2 != 0){
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        }
    }

    @Test
    public void testThreadPool() {
        //1. 提供指定线程数量的线程池
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
        //设置线程池的属性
//        System.out.println(service.getClass());
//        service1.setCorePoolSize(15);
//        service1.setKeepAliveTime();


        //2.执行指定的线程的操作。需要提供实现Runnable接口或Callable接口实现类的对象
        service.execute(new NumberThread());//适合适用于Runnable
        service.execute(new NumberThread1());//适合适用于Runnable

//        service.submit(Callable callable);//适合使用于Callable
        //3.关闭连接池
        service.shutdown();







        // Create a thread pool with 2 worker threads.
        // This is a more robust and manageable approach.
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Submit a task to the thread pool.
        // This task will be picked up by one of the threads in the pool.
        executor.submit(() -> {
            // Note: In a real app, this infinite loop would be a finite task.
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (!Thread.currentThread().isInterrupted()) {
                // Do some work...
            }
        });

        System.out.println(Thread.currentThread().getName() + " over");

        // It's crucial to shut down the executor service when the application is done.
        // Otherwise, the JVM will not exit because the pool's threads are still running.
        // executor.shutdown(); // Gracefully shuts down after current tasks are finished.

        // In this specific example with an infinite loop, you might need shutdownNow()
        // or a proper task cancellation mechanism to stop the program.

    }



}
