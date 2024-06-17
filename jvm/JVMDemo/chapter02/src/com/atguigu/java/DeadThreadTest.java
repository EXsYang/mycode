package com.atguigu.java;

/**
 * ### clinit()
 *
 * 1.  初始化阶段就是执行类构造器方法`<clinit>()`的过程
 *
 * 2.  此方法不需定义，是javac编译器自动收集类中的所有**类变量**的赋值动作和静态代码块中的语句合并而来。也就是说，当我们代码中包含static变量的时候，就会有clinit方法
 *
 * 3.  `<clinit>()`方法中的指令按语句在源文件中出现的顺序执行
 *
 * 4.  `<clinit>()`不同于类的构造器。（关联：构造器是虚拟机视角下的`<init>()`）
 *
 * 5.  若该类具有父类，JVM会保证子类的`<clinit>()`执行前，父类的`<clinit>()`已经执行完毕
 *
 * 6.  虚拟机必须保证一个类的`<clinit>()`方法在多线程下被同步加锁
 *
 *
 * 本类是解释上面第6条的测试代码
 *
 * 在Java中，类的初始化阶段涉及执行类构造器<clinit>()方法，
 * 该方法是由编译器自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的。
 * 重要的是，这个过程必须被保证为线程安全的，即<clinit>()方法在多线程环境中被同步加锁。
 *
 * 为什么需要同步？
 * JVM规范要求类的初始化阶段（即执行<clinit>()方法）必须在一个类被首次主动使用时发生，
 * 而且要保证这个过程是线程安全的。这意味着如果多个线程同时去初始化一个类，
 * 只有一个线程会去执行这个类的<clinit>()方法，其他线程需要阻塞等待，
 * 直到活动线程执行<clinit>()方法完成。这是通过对类对象加锁实现的。
 *
 * 代码解释
 * 在您提供的DeadThreadTest类中，有一个静态代码块在DeadThread类的初始化过程中无限循环。
 * 这个无限循环代表了类的初始化过程，它阻塞了执行这个初始化的第一个线程。
 *
 * 发生了什么？
 * 当线程1试图创建DeadThread的一个实例时，它首先触发了DeadThread类的加载和初始化。
 * 线程1进入了静态代码块，并开始执行无限循环，输出“线程1初始化当前类”。
 * 线程1因为无限循环而停留在静态代码块中，未能完成类的初始化，也就未能退出<clinit>()方法。
 * 当线程2同样尝试创建DeadThread的实例时，它也需要等待DeadThread类完成初始化。
 * 但由于线程1已经在执行初始化并被无限循环阻塞，线程2也将被无限阻塞，等待线程1完成初始化。
 *
 * 结果
 * 因此，这个代码示例演示了一个典型的死锁情况，
 * 其中一个类的初始化过程被一个线程长时间占据（或永久占据），
 * 导致任何其他尝试初始化这个类的线程都无法继续执行。
 * 这也体现了Java类加载机制中关于<clinit>()方法的同步和线程安全的重要性。
 */
public class DeadThreadTest {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + "开始");
            DeadThread dead = new DeadThread();// 当尝试创建DeadThread类的实例时，JVM将加载并初始化此类
            System.out.println(Thread.currentThread().getName() + "结束");
        };

        Thread t1 = new Thread(r,"线程1");
        Thread t2 = new Thread(r,"线程2");

        t1.start();
        t2.start();
    }
}

class DeadThread{
    static{
        // 静态初始化器，包含一个无限循环
        if(true){
            System.out.println(Thread.currentThread().getName() + "初始化当前类");
            while(true){
                // 无限循环模拟长时间的初始化过程
            }
        }
    }
}