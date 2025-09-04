package com.atguigu;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BiFunction;

//演示用户线程和守护线程
public class Main {

    public static void main(String[] args) {
        Thread aa = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa");

        Thread aa2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {

            }
        }, "aa2");
        //设置守护线程
        aa.setDaemon(true);
        aa.start();

        System.out.println(Thread.currentThread().getName()+" over");





        // 1. 这里是使用的构造器引用吗？
        // 不是。
        //
        // 您代码中的 new Thread(...) 是一个直接的构造器调用（Direct Constructor Invocation）。您正在明确地、直接地调用 Thread 类的构造函数来创建一个新的 Thread 对象。
        //
        // 构造器引用（Constructor Reference）是一种特殊的方法引用，它有特定的语法：类名::new。它本身并不创建对象，而是创建一个指向特定构造函数的“配方”或“蓝图”，这个“配方”可以用来实现某个函数式接口。
        //
        // 简单来说：
        //
        // 直接调用: new Thread(...) -> 马上创建一个对象。
        //
        // 构造器引用: Thread::new -> 创建一个“如何创建Thread对象”的指令，但不立刻执行。
        //
        // 2. 可以改写成 类名::new 的形式吗？
        // 可以，但这需要一个合适的函数式接口来承载这个引用。
        //
        // 类名::new 这种形式必须赋值给一个函数式接口变量。编译器会根据这个接口的抽象方法的签名，去匹配 Thread 类中对应的构造函数。
        //
        // 在您的例子中，您调用的构造函数是 Thread(Runnable target, String name)，它接收一个 Runnable 和一个 String 参数。
        //
        // 为了能够引用这个构造函数，我们需要一个函数式接口，它的抽象方法需要满足：
        //
        // 接收一个 Runnable 和一个 String 作为参数。
        //
        // 返回一个 Thread 对象。
        //
        // Java 内置的 java.util.function.BiFunction<T, U, R> 接口正好符合这个要求。我们可以这样设置它的泛型：
        //
        // T: Runnable (第一个参数)
        //
        // U: String (第二个参数)
        //
        // R: Thread (返回值)
        //
        // 下面是使用 Thread::new 构造器引用的改写方式：

        // 这是原始的 Runnable 任务
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
            while (true) {
                // Infinite loop
            }
        };

        // 1. 创建一个指向 Thread(Runnable, String) 构造函数的引用
        //    这个引用由 BiFunction 接口来承载
        BiFunction<Runnable, String, Thread> threadConstructor = Thread::new;

        // 2. 使用这个引用来创建 Thread 实例，效果等同于 new Thread(task, "aa3")
        Thread aa3 = threadConstructor.apply(task, "aa3");

        //设置守护线程
        aa3.setDaemon(true);
        aa3.start();

        System.out.println(Thread.currentThread().getName() + " over");

        // 对比	直接构造器调用 (您的原代码)	构造器引用 (改写后的代码)
        // 语法	new Thread(runnable, name)	BiFunction<Runnable, String, Thread> ref = Thread::new;<br>ref.apply(runnable, name);
        // 本质	立即创建一个对象。	        创建一个实现了 BiFunction 接口的对象，该对象的 apply 方法会去调用 Thread 的构造函数。
        // 简洁性	在这个特定场景下，直接调用更简单明了。	   代码更长，显得有点绕。
        // 使用场景	当你只需要当场创建一个对象时。	当你需要将“创建对象的能力”作为参数传递给另一个方法，或者存储起来稍后使用时（例如在工厂模式或依赖注入中）。





        // // Create a thread pool with 2 worker threads.
        // // This is a more robust and manageable approach.
        // ExecutorService executor = Executors.newFixedThreadPool(2);
        //
        // // Submit a task to the thread pool.
        // // This task will be picked up by one of the threads in the pool.
        // executor.submit(() -> {
        //     // Note: In a real app, this infinite loop would be a finite task.
        //     System.out.println(Thread.currentThread().getName() + "::" + Thread.currentThread().isDaemon());
        //     while (!Thread.currentThread().isInterrupted()) {
        //         // Do some work...
        //     }
        // });
        //
        // System.out.println(Thread.currentThread().getName() + " over");

        // It's crucial to shut down the executor service when the application is done.
        // Otherwise, the JVM will not exit because the pool's threads are still running.
        // executor.shutdown(); // Gracefully shuts down after current tasks are finished.

        // In this specific example with an infinite loop, you might need shutdownNow()
        // or a proper task cancellation mechanism to stop the program.
    }


}
