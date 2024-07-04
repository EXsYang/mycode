package com.atguigu.java;

/**
 * 测试 Object 类中 finalize() 方法，即对象的 finalization 机制。
 * 通过 finalize() 方法，尝试演示对象能否在垃圾回收前“自救”。
 * 注意：finalize() 对每个对象只能被调用一次。
 *
 * @author shkstart
 * @create 2020 下午 2:57
 */
public class CanReliveObj {
    public static CanReliveObj obj; // 类变量，属于 GC Root

    /**
     * 此方法只能被调用一次
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用当前类重写的finalize()方法");
        obj = this; // 当前待回收的对象在 finalize() 方法中与引用链上的一个对象 obj 建立了联系，实现自救
    }

    public static void main(String[] args) {
        try {
            obj = new CanReliveObj();
            // 对象第一次成功拯救自己
            obj = null;
            System.gc(); // 显式请求执行垃圾回收GC。这个调用增加了进行垃圾回收的概率，从而也增加了触发对象 finalize() 方法的机会。
            // 即使没有显式调用 System.gc()，Java虚拟机（JVM）在执行垃圾回收（GC）过程中
            // 仍然可能会触发 finalize() 方法。
            // 显式调用 System.gc() 的目的通常是为了增加触发垃圾回收（GC）的概率，
            // 从而更高概率地引发对象的 finalize() 方法的执行。不保证立即执行GC

            // 因为 Finalizer 线程优先级很低，可能需要一些时间来处理所有待终结的对象（即执行它们的 finalize() 方法）。
            // 这里暂停2秒是为了给 Finalizer 线程足够的时间处理 finalize() 队列中的对象，确保所有已经触发 finalize() 的对象
            // 都有机会执行它们的终结逻辑。这样做有助于在进行任何后续检查或操作之前，完成对象的资源清理和状态更新。
            System.out.println("第1次 gc");
            // 因为 Finalizer 线程优先级很低，暂停2秒，以等待它。
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }

            // 下面这段代码与上面的完全相同，但这次自救却失败了
            obj = null;
            System.gc(); // 再次调用垃圾回收器
            System.out.println("第2次 gc");
            Thread.sleep(2000);
            if (obj == null) {
                System.out.println("obj is dead");
            } else {
                System.out.println("obj is still alive");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
