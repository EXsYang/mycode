package com.atguigu.java;

/**
 * @author shkstart  shkstart@126.com
 * @create 2020  14:49
 */
public class SystemGCTest {
    public static void main(String[] args) {
        new SystemGCTest();
        System.gc();//提醒jvm的垃圾回收器执行gc,但是不确定是否马上执行gc
        //与Runtime.getRuntime().gc();的作用一样。

        System.runFinalization();//强制调用失去引用的对象的finalize()方法【这里代码简单，
        // 所以每次执行都可以看到finalize()方法中的输出语句"SystemGCTest 重写了finalize()",
        // 但是这里如果环境复杂，则也不是一定都会马上执行finalize()方法】
        /**
         * 在实际应用中，当你调用 System.runFinalization() 后看到 finalize() 方法
         * 总是被执行的现象，这并不意味着你的测试有误，而是因为在你的测试环境和条件下，
         * JVM 的行为恰好符合了你的操作期望。但这并不保证在所有环境或条件下，
         * 或在不同版本的 JVM 中都会有相同的结果。
         *
         * 关于 System.runFinalization() 行为的几点考虑：
         * JVM实现的不同：不同的 JVM 实现（如 Oracle HotSpot、OpenJDK、IBM J9 等）
         * 可能在细节上有所差异，特别是在垃圾回收和终结策略方面。
         * 资源压力和可用性：在资源不紧张的情况下，JVM 可能更频繁地响应 System.gc() 和
         * System.runFinalization() 的调用。在内存压力较大或 CPU 资源紧张的情况下，
         * 相同的调用可能不会立即得到处理。
         * 测试环境的简单性：在简单的测试环境中（例如你的示例代码），垃圾回收器和终结器线程
         * 有更少的并发任务，因此可能会更快地响应这些方法调用。
         * 理解 finalize() 方法调用：
         * 虽然 System.runFinalization() 请求 JVM 执行挂起的终结过程，这并不意味着 JVM
         * 必须立即执行所有对象的 finalize() 方法。在大多数实际应用程序中，尤其是那些复杂的、
         * 长时间运行的服务中，终结过程可能会被推迟，或因为其他系统活动而被推迟。
         *
         * 结论
         * 在你的测试用例中，每次都能看到 finalize() 方法被调用可能是因为：
         *
         * 测试代码相对简单，没有复杂的内存或执行路径。
         * 测试环境资源充足，JVM 能够迅速响应垃圾收集和终结请求。
         * 测试时的 JVM 实现可能对 System.runFinalization() 有即时响应的优化。
         * 因此，虽然你的测试结果是可靠的，它表明了在特定条件下 JVM 的行为，但这些行为
         * 不应被视为在所有情况下都会发生。编写依赖于 finalize() 方法及其调用时机的代码
         * 通常不是一个好的做法，因为它可能在不同的环境和条件下带来不可预测的结果。
         */
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("SystemGCTest 重写了finalize()");
    }


class Car{

}
public class Car2{

}

}

//
class Car3{

}

//Class 'Car4' is public, should be declared in a file named 'Car4.java'
// public class Car4{
//
// }