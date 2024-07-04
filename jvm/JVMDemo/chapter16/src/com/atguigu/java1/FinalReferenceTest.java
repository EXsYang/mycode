package com.atguigu.java1;

/**
 * @author yangda
 * @create 2024-07-02-19:58
 * @description:
 *
 * 在 Java 中，`FinalReference` 类是用来实现对象的终结过程，即对象的**终结器**或**finalization**机制。终结器引用属于 `java.lang.ref` 包，这个包主要负责实现 Java 的引用对象，包括软引用（SoftReference）、弱引用（WeakReference）、虚引用（PhantomReference），以及用于终结器的特殊引用类型 `FinalReference`。
 *
 * ### 终结器引用的目的：
 *
 * - **终结化（Finalization）的实现**：当没有任何强引用指向对象时，垃圾收集器会判断该对象是否有必要执行终结化。如果对象重写了 `finalize()` 方法，JVM 会在垃圾回收对象之前调用这个方法，允许对象执行清理资源的操作（如关闭文件或网络连接）。`FinalReference` 是这个机制的一部分，它帮助管理那些需要终结化的对象。
 * - **与引用队列的协作**：`FinalReference` 对象可以被注册到一个 `ReferenceQueue`。当垃圾收集器准备回收一个对象时，这个对象的 `FinalReference` 会被加入到它所注册的队列中。这允许应用程序在对象被销毁前进行一些后处理操作，尽管这通常是不推荐的做法，因为它会导致延迟和不可预测的行为。
 *
 * ### 代码解析：
 *
 * ```java
 * class FinalReference<T> extends Reference<T> {
 *
 *     public FinalReference(T referent, ReferenceQueue<? super T> q) {
 *         super(referent, q);
 *     }
 * }
 * ```
 *
 * - **构造器**：`FinalReference` 的构造器接收两个参数：`referent`（引用的对象）和 `q`（引用队列）。构造器调用其父类 `Reference` 的构造器，将引用对象和引用队列传递给父类。
 * - **继承 `Reference` 类**：`FinalReference` 继承自 `Reference` 类，这意味着它继承了处理 Java 引用的所有基础功能。
 *
 * ### 使用场景和注意事项：
 *
 * 虽然 Java 提供了终结器的机制，但自 Java 9 开始，Oracle 强烈推荐避免使用 `finalize()` 方法，因为它会导致多种问题，包括但不限于性能问题、安全问题和内存管理问题。相反，建议使用 `java.lang.ref.Cleaner` 或 `java.lang.AutoCloseable` 和 `try-with-resources` 语句来管理资源。
 *
 * 综上所述，`FinalReference` 是 Java 内部使用的一个工具，用于处理对象的终结化。它通常不应直接用于常规应用程序开发中，开发者应关注更现代的资源管理技术。
 */
public class FinalReferenceTest {
    public static void main(String[] args) {
        //由于 FinalReference 类是 java.lang.ref 包的一部分，
        // 且是包级私有的（package-private），它不能被直接在包外使用。
        // 这意味着在标准 Java 应用程序中不能直接创建 FinalReference 对象或直接访问这个类。

        // FinalReference 是 Java 虚拟机（JVM）内部使用的一个工具类，
        // 专门用于处理对象的终结化过程。这个类是 java.lang.ref 包的一部分，
        // 但它并不是为了直接被开发者使用而设计的，而是作为 JVM 垃圾回收和资源管理机制的一部分。

        /**
         * 角色和作用
         * 实现终结化：
         *
         * FinalReference 类是用来管理那些重写了 finalize() 方法的对象的终结化过程。
         * 当一个对象即将被垃圾回收器回收时，如果这个对象的类重写了 finalize() 方法，
         * FinalReference 会帮助确保这个方法被调用。
         *
         * 与引用队列协作：
         *
         * FinalReference 对象通常与一个 ReferenceQueue 关联。
         * 当包含有 finalize() 方法的对象成为垃圾收集的候选时，
         * 它的 FinalReference 可以被添加到一个引用队列。
         * 这使得应用程序或 JVM 可以在对象被销毁前执行某些操作，
         * 尽管这通常是不推荐的，因为终结器的运行会导致性能下降和行为不可预测。
         *
         * 不直接使用
         * FinalReference 类在 java.lang.ref 包中定义为包内私有，
         *
         * 如果 FinalReference 类在 java.lang.ref 包中定义为默认（包级）可见性，而不是明确声明为 private，那么我的描述需要更正。默认访问级别（没有明确的 public, private, 或 protected 修饰符）确实意味着类或成员只在定义它们的包内可见。这被称为包私有访问级别。
         *
         * 包私有访问控制
         * 在 Java 中，如果一个类或类成员没有明确的访问修饰符，它默认具有包私有的访问级别。这意味着它们只能被同一个包内的其他类访问：
         *
         * 包私有：这是类和类成员的默认访问级别（如果没有指定任何访问修饰符）。只有同一个包内的其他类可以访问这些类或成员。
         * 设计意图：通常，包私有的访问级别被用于那些不需要对包外部公开，但包内多个类之间需要共享的类和成员。
         *
         * 这意味着它不能被包外的代码直接访问或使用。
         * 这种设计强调了它主要用于 JVM 内部管理对象的终结化，
         * 而不是作为一般应用程序编程的一部分。
         * 替代方案
         * 由于 finalize() 方法本身从 Java 9 开始已被弃用（deprecated），
         * 推荐使用更现代的资源管理技术，例如：
         * try-with-resources 语句：自动管理实现了 AutoCloseable 或 Closeable 接口的资源。
         * Cleaner 和 PhantomReference：提供了一种更为安全和可预测的方式来处理需要在对象
         * 回收时清理资源的情况。
         * 总结
         * FinalReference 是 JVM 内部用于管理对象终结化过程的工具类。
         * 它帮助确保 finalize() 方法在对象被垃圾收集前被调用，
         * 但由于 finalize() 方法的问题和限制，开发者应当避免依赖终结器来管理资源，
         * 而是采用更现代、更可靠的资源管理方法。
         */

    }
}
