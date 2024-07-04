package com.atguigu.java1;

import java.util.WeakHashMap;

/**
 * @author yangda
 * @create 2024-07-02-19:33
 * @description:
 *
 * 为了测试使用 WeakHashMap 来存放图片，
 * 并观察弱引用在 Java 中如何工作，我们首先需要定义一个 Image 类，
 * 然后创建一个 WeakHashMap 实例来存放这些 Image 对象。
 * 下面是一个简单的示例，包括图片类的定义和测试代码，
 * 以演示 WeakHashMap 中的对象何时被垃圾回收器回收。
 */
public class WeakHashMapTest {

    /**
     * public static class Image 静态嵌套类
     * 独立性
     * 非依赖外部实例：静态嵌套类不依赖于其外部类的实例。
     * 这意味着你可以创建一个内部类的实例，而不必创建外部类的实例。
     * 这在外部类的实例化成本较高或不需要外部类实例的情况下非常有用。
     *
     *
     * 在 Java 中，将内部类声明为 `static` 是一种常见的做法，
     * 尤其是当你希望内部类的实例不依赖于外部类的实例时。
     * 这种称为**静态嵌套类**的做法有几个重要的优点和用途：
     *
     * ### 1. **独立性**
     * - **非依赖外部实例**：静态嵌套类不依赖于其外部类的实例。这意味着你可以创建一个内部类的实例，而不必创建外部类的实例。这在外部类的实例化成本较高或不需要外部类实例的情况下非常有用。
     *
     * ### 2. **内存效率**
     * - **减少内存开销**：如果内部类被声明为非静态（即内部类），那么每个内部类的实例都会保持一个隐式引用到其外部类的实例。这意味着，即使你不需要访问外部类的成员，内存中也会存在一个外部类的引用，增加了额外的内存开销。
     * - **避免内存泄漏**：在某些情况下，非静态内部类可能会导致意外的内存泄漏，特别是当内部类实例的生命周期超过外部类实例时。
     *
     * ### 3. **封装性和组织性**
     * - **代码组织**：将类放在与它们紧密相关的类内部可以提高代码的组织性和可读性。当一个类仅在另一个类中使用时，将其作为静态嵌套类放在外部类中可以逻辑上将它们组织在一起，同时保持封装。
     * - **减少命名空间污染**：静态嵌套类在其外部类的命名空间内部，这有助于避免在全局命名空间中引入过多的类名，减少了命名冲突的可能性。
     *
     * ### 4. **访问控制和权限**
     * - **访问外部类的私有成员**：静态嵌套类可以访问其外部类的所有静态成员，包括私有的静态字段和方法。这对于实现一些封装的辅助功能非常有用，而不必公开外部类的私有静态成员。
     *
     * ### 结论
     * 在你的示例中，如果 `Image` 类不需要访问 `WeakHashMapExample` 类的实例变量，将 `Image` 声明为静态是有意义的。这使得 `Image` 类可以作为一个独立的实体存在，不受外部类实例的生命周期影响，同时保持了代码的整洁和组织性。这种做法在设计不需要直接访问封闭类实例状态的工具类或辅助类时尤其常见。
     */
    public static class Image {
        private byte[] imageData;

        public Image(byte[] imageData) {
            this.imageData = imageData;
        }

        // 模拟图片数据占用的内存大小
        public int size() {
            return imageData.length;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("Image finalized with size: " + size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<Image, String> weakHashMap = new WeakHashMap<>();

        // 创建一个较大的图片数据，以便容易观察到内存的变化
        byte[] imageData = new byte[1024 * 1024 * 10]; // 10 MB
        Image image = new Image(imageData);

        // 将图片存放到 WeakHashMap 中
        weakHashMap.put(image, "This is an image");

        // 检查图片是否还在 WeakHashMap 中
        System.out.println("Image in map: " + weakHashMap.containsValue("This is an image"));

        // 移除强引用
        image = null;
        System.gc(); // 建议 JVM 执行垃圾回收

        // 等待一小段时间，以确保 GC 已执行
        Thread.sleep(1000);

        // 再次检查 WeakHashMap，看看对象是否被回收
        System.out.println("Image in map after GC: " + weakHashMap.containsValue("This is an image"));

        // 输出 WeakHashMap 的大小，预期是 0 如果 Image 被垃圾回收器回收了
        System.out.println("Size of map: " + weakHashMap.size());
    }
}
