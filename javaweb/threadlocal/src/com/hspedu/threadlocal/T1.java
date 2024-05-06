package com.hspedu.threadlocal;

public class T1 {

    //创建ThreadLocal对象, 做成public static.
    public static ThreadLocal<Object> threadLocal1 = new ThreadLocal<>();
    public static ThreadLocal<Object> threadLocal2 = new ThreadLocal<>();

    //Task 是线程类 -> 内部类 / 线程
    public static class Task implements Runnable {
        @Override
        public void run() {
            Dog dog = new Dog();
            Pig pig = new Pig();
            //给threadLocal1 对象放入set dog , 隔山打牛
            System.out.println("Task 放入了 dog= " + dog);
            /*
                老韩解读
                public void set(T value) {
                    //1. 获取当前线程, 关联到当前线程!
                    Thread t = Thread.currentThread();
                    //2. 通过线程对象, 获取到ThreadLocalMap
                    //   ThreadLocalMap 类型 ThreadLocal.ThreadLocalMap
                    ThreadLocalMap map = getMap(t);
                    //3. 如果map不为null, 将数据(dog,pig..) 放入map -key:threadLocal value:存放的数据
                    //   从这个源码我们已然看出一个threadlocal只能关联一个数据，如果你set, 就会替换
                    //4. 如果map为null, 就创建一个和当前线程关联的ThreadLocalMap, 并且该数据放入
                    if (map != null)
                        map.set(this, value);
                    else
                        createMap(t, value);
                }

             */
            // 【注意这里其实是放入到当前线程的threadLocals属性ThreadLocalMap中了】
            //每个线程（处理一个请求）都在其自己的`ThreadLocalMap`中操作数据，保证了数据的隔离性。
            //`ThreadLocal`利用每个线程持有自己的`ThreadLocalMap`来实现数据隔离。
            // 虽然`ThreadLocal`变量是静态的（即类级别的，所有实例共享），
            // 每个线程通过`ThreadLocal`实例存取的数据实际上存储在它自己的`ThreadLocalMap`中。
            // 这保证了即使多个线程访问同一个`ThreadLocal`对象，
            // 它们也只能访问各自独立存储的数据。
            threadLocal1.set(dog);
            //threadLocal1.set(pig);//替换
            threadLocal2.set(pig);//这个数据就会threadLocal2关联，并且都被当前Thread管理
            System.out.println("Task 在run 方法中 线程=" + Thread.currentThread().getName());
            new T1Service().update();
        }
    }

    public static void main(String[] args) {
        new Thread(new Task()).start();//主线程启动一个新的线程,注意不是主线程
    }
}
