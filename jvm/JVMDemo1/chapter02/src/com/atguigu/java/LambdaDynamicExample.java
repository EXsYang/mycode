package com.atguigu.java;

import java.lang.invoke.*;
import java.util.Random;
import java.util.function.BiFunction;

/**
 * 该类用来展示 `invokedynamic` 的动态性
 * <p>
 * ### 字节码中的 `invokedynamic`
 * <p>
 * 在这个例子中，我们使用Lambda表达式来定义加法和乘法操作。
 * 当这段代码被编译后，每个Lambda表达式的实例化部分将通过 `invokedynamic` 指令实现。
 * 这是因为Lambda表达式在Java中是基于 `invokedynamic` 来动态绑定的，
 * 允许JVM在运行时进行方法绑定，以便更高效地执行。
 */

/**
 * 在上述示例中，使用 Lambda 表达式展示了 `invokedynamic` 的动态性主要是通过以下几个方面：
 *
 * ### 1. **Lambda 表达式的动态绑定**
 *
 * Lambda 表达式本质上是一个匿名函数，它不绑定到传统的方法调用机制上，如 `invokevirtual` 或 `invokeinterface`。相反，每个 Lambda 表达式在编译时被转换成一个称为“函数接口”的特殊类型的对象。在运行时，这些表达式的具体行为（即实际的方法体）通过 `invokedynamic` 指令动态绑定。这种绑定方式允许 JVM 在运行时根据上下文决定如何最优化执行这些表达式，而不是在编译时固定下来。
 *
 * ### 2. **根据输入动态选择行为**
 *
 * 示例中的程序根据用户输入动态选择使用加法还是乘法操作。这种行为选择虽然在代码中通过 `if-else` 逻辑实现，但每个分支的操作 —— 加法或乘法 —— 是通过 Lambda 表达式定义的。这些 Lambda 表达式在运行时通过 `invokedynamic` 被解析和绑定，这表示即使是在同一位置的代码（如 Lambda 表达式的定义点），JVM 也可以在不同情况下将其解析为不同的方法实现。
 *
 * ### 3. **JVM 的优化机会**
 *
 * 由于 `invokedynamic` 的使用，JVM 在执行 Lambda 表达式时有更多的优化机会。例如，JVM 可以延迟绑定函数的具体实现，甚至可以在程序运行期间改变绑定的目标，以响应可能的运行时信息（如调用频率、参数类型变化等）。这种优化是传统静态方法调用（如 `invokevirtual` 调用的方法）所不能实现的。
 *
 * ### 4. **代码简洁和维护性**
 *
 * 虽然这一点不直接关联到 `invokedynamic` 的技术细节，但使用 Lambda 表达式可以使代码更加简洁和易于维护。开发者可以专注于要执行的操作的逻辑，而将如何绑定和优化这些操作的决策留给 JVM，利用 `invokedynamic` 提供的动态性能。
 *
 * ### 总结
 *
 * 通过这个 Lambda 表达式的例子，我们可以看到 `invokedynamic` 提供的动态性不仅仅是在语法层面上的简化，更重要的是在运行时行为的灵活性和性能优化上。它让 JVM 有机会在运行时根据实际情况来优化和调整方法调用，这在传统的 Java 方法调用中是很难实现的。
 */
public class LambdaDynamicExample {

    public static void main(String[] args) {
        // 根据输入选择操作
        // BiFunction<Integer, Integer, Integer> operation;
        // if (args.length > 0 && "multiply".equals(args[0])) {
        //     operation = (a, b) -> a * b;  // Lambda表达式,对应字节码: 16 invokedynamic #4 <apply, BootstrapMethods #0>
        // } else {
        //     operation = (a, b) -> a + b;  // Lambda表达式,对应字节码:  25 invokedynamic #5 <apply, BootstrapMethods #1>
        // }
        //
        // // 执行操作
        // System.out.println("Result: " + operation.apply(5, 3));


        /*
          使用随机数来控制行为的选择可以更直观地展示 invokedynamic 的动态性，
          因为它更明显地体现了运行时行为的不确定性和灵活性。
         */
        // 创建随机数生成器
        Random random = new Random();

        // 随机选择操作
        BiFunction<Integer, Integer, Integer> operation;
        if (random.nextBoolean()) {
            operation = (a, b) -> a + b;  // Lambda表达式实现加法
            System.out.println("Using addition");
        } else {
            operation = (a, b) -> a * b;  // Lambda表达式实现乘法
            System.out.println("Using multiplication");
        }

        // 执行操作并打印结果
        System.out.println("Result: " + operation.apply(5, 3));

        //字节码中的 invokedynamic
        // 编译上述代码后，每个 Lambda 表达式的实例化部分将通过 invokedynamic 指令实现。这意味着对于每次程序运行，invokedynamic 可能会根据 Random 对象生成的布尔值绑定到不同的方法实现。这种行为在程序运行前是未确定的，由 JVM 在运行时决定。
        //
        // 解释动态性
        // 在这个例子中，每次程序运行时都可能表现不同的行为，这取决于 Random 对象生成的布尔值。这种模式使得程序的行为具有高度的不确定性，展示了 invokedynamic 的能力，即在运行时动态解决方法调用，而非编译时固定方法调用目标。
        //
        // 运行时决策：invokedynamic 允许 JVM 在每次执行时基于当前的上下文（本例中是随机数）动态选择最适合的方法实现。
        // 性能优化：JVM 可以利用 invokedynamic 的灵活性进行方法调用的优化，如延迟绑定、内联等，这可能会根据实际运行时的使用模式调整。
        // 总结
        // 通过随机数控制 Lambda 表达式的选择，我们不仅展示了代码的简洁性，还直观地展示了 invokedynamic 的动态方法绑定功能。这种方式体现了 Java 平台对动态语言特性的支持，允许开发者编写更灵活和高效的代码。
    }
}
