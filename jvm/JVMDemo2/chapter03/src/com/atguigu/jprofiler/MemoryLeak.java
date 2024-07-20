package com.atguigu.jprofiler;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * 演示内存泄漏
 * @author shkstart
 * @create 12:57
 */
public class MemoryLeak {

    public static void main(String[] args) {
        while (true) {
            //每次循环都会创建一个新的 ArrayList 对象 beanList。
            // 然后，循环创建 500 个 Bean 对象，并在每个 Bean 对象的静态 list 中添加一个 10KB 的 byte 数组。
            // beanList 中添加了这些 Bean 实例，但每次循环结束时，beanList 会被丢弃，并在下一次循环中重新创建。
            ArrayList beanList = new ArrayList();
            for (int i = 0; i < 500; i++) {
                Bean data = new Bean();
                data.list.add(new byte[1024 * 10]);//10kb

                // 添加到 beanList 的 Bean 对象
                // `beanList` 是局部变量，它在每次循环结束时会被丢弃
                // 循环中的 `beanList` 和其中的 `Bean` 对象
                // 会被 GC 回收，因为没有活动引用指向它们
                beanList.add(data);
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Bean {
    int size = 10;
    String info = "hello,atguigu";

    //在 Bean 类中，有一个静态的 ArrayList 变量 list。这个静态变量 list 属于 Bean 类本身，而不是 Bean 的任何特定实例。静态变量在类加载时被创建，并且在整个程序运行期间都存在。由于 list 是静态的，它的生命周期与 Bean 类本身相同，而不是 Bean 实例的生命周期。
    // 静态 ArrayList
    // 由于 `list` 是静态的，它属于类 `Bean` 本身
    // 静态 `list` 会持有对所有 `byte` 数组的引用
    // 这些 `byte` 数组不会被 GC 回收，即使 `beanList` 中的 `Bean` 对象被回收了
    // 这会导致内存泄漏，因为静态 `list` 会持续增加而无法释放内存
    // static ArrayList list = new ArrayList();

    ArrayList list = new ArrayList();// 去掉static，解决了内存泄露问题
}
