package com.atguigu.java1;

import java.lang.ref.SoftReference;

/**
 * 软引用的测试：内存不足即回收
 * 注意: 不一定会报OOM时才会回收软引用
 * 软引用和垃圾回收
 * 软引用（SoftReference） 在 Java 中被设计为具有一定的弹性和记忆性质，
 * 即在系统将要发生 OutOfMemoryError 之前，才会清理这些引用指向的对象。
 * 这意味着只有在 JVM 真正即将耗尽内存且没有其他方法可清理内存时，才会考虑回收软引用对象。
 *
 * -Xms10m -Xmx10m
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:06
 */
public class SoftReferenceTest {
    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int id;
        public String name;

        @Override
        public String toString() {
            return "[id=" + id + ", name=" + name + "] ";
        }

        // 增加finalize方法观察对象回收
        @Override
        protected void finalize() throws Throwable {
            System.out.println("User [" + id + ", " + name + "] is being finalized.");
        }
    }

    public static void main(String[] args) {
        //创建对象，建立软引用
//        SoftReference<User> userSoftRef = new SoftReference<User>(new User(1, "songhk"));
        //上面的一行代码，等价于如下的三行代码。即仅保留只有一个软引用的可达对象
        User u1 = new User(1,"songhk");
        SoftReference<User> userSoftRef = new SoftReference<User>(u1);
        u1 = null;//取消强引用

        //从软引用中重新获得强引用对象
        System.out.println(userSoftRef.get());

        System.gc();
        System.out.println("After GC:");
//        //垃圾回收之后获得软引用中的对象
        System.out.println(userSoftRef.get());//由于堆空间内存足够，所有不会回收软引用的可达对象。
//

        // 尝试引起内存压力
        createMemoryPressure();

        try {
            //让系统认为内存资源紧张、不够 【因为这里使用JVM参数-Xms10m -Xmx10m 设置的堆空间总大小为10M】
           // byte[] b = new byte[1024 * 1024 * 7];

           // -Xms10m -Xmx10m -XX:+PrintGCDetails
           //  byte[] b = new byte[1024 * 1024 * 1];
           //             byte[] b = new byte[1024 * 7168 - 635 * 1024];

            // 这里是将老年代中剩余的空间全部充满
            // ParOldGen       total 7168K, used 616K
            //             byte[] b = new byte[1024 * 7168 - 617 * 1024];
                        byte[] b = new byte[1024 * 7168 - 597 * 1024];
                        // byte[] b = new byte[1024 * 7168 - 596 * 1024];
            //             byte[] b = new byte[1024 * 7168 - 635 * 1024];

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            //再次从软引用中获取数据
            System.out.println(userSoftRef.get());//在报OOM之前，垃圾回收器会回收软引用的可达对象。
        }
    }


    // public static void main(String[] args) {
    //     // 创建对象，建立软引用
    //     SoftReference<User> userSoftRef = new SoftReference<>(new User(1, "songhk"));
    //
    //     // 打印软引用持有的对象
    //     System.out.println("Before GC: " + userSoftRef.get());
    //
    //     // 尝试引起内存压力
    //     createMemoryPressure();
    //
    //     // 再次检查软引用对象
    //     System.out.println("After memory pressure: " + userSoftRef.get());
    // }

    private static void createMemoryPressure() {
        try {
            // 创建足够多的对象以占用大部分堆空间
            // 增加内存压力，使 JVM 更有可能回收软引用对象
            for (int i = 0; i < 10; i++) { // 尝试创建更多的对象以确保内存紧张
                byte[] b = new byte[1024 * 1024]; // 每次分配1MB
            }
        } catch (Throwable e) {
            // 处理 OutOfMemoryError
            e.printStackTrace();
        }
    }
}


