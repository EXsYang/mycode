package com.atguigu.java1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2024-07-10-19:25
 * @description: 举一个泛型擦除的例子，
 * 即在源代码层面不好看出执行的细节，
 * 可能会导致误判的例子，
 * 但是可以通过观察字节码的执行细节，
 * 可以理解泛型擦除的机制
 *
 * Generic: Java中指的是**泛型**; 通用的	/dʒəˈnerɪk/
 */
public class GenericTest {
    public static void main(String[] args) {

        /**
         * Java的泛型擦除是泛型引入到Java语言中的一种折衷实现方式，
         * 以保持向后兼容性。在编译时，泛型类型参数会被擦除，
         * 替换为它们的边界（如果指定了边界），或者替换为`Object`。
         * 这种方式在源代码层面可能不易被察觉，但在字节码层面变得明显。
         * 下面举一个示例来展示泛型擦除，及其可能导致的误判情况。
         *
         */
        // 示例代码
        List<Integer> intList = new ArrayList<>();
        intList.add(1);
        // printList(intList);

        List<String> stringList = new ArrayList<>();
        stringList.add("hello");
        printList(stringList);

/*
    问题分析:

    在这个例子中，printList 方法的参数被定义为一个原始类型的 List，
    而不是一个泛型 List。这样的定义在源码层面可能会导致误判：

    - 当调用 printList(intList) 和 printList(stringList) 时，看似没有任何问题，
      因为 Java 泛型提供了类型擦除，所以这两个调用在字节码层面实际上都是传递 List 类型。
    - 在源代码中，看上去我们好像可以向 printList 传递任何类型的 List，
      而不关心其元素类型。

    字节码观察:

    通过观察编译后的字节码，可以看到泛型擦除的影响。使用 javap -c GenericTest 命令，
    可以查看到如下的关键输出：

        public static void printList(java.util.List);
            Code:
               0: aload_0
               1: invokeinterface #2,  1            // InterfaceMethod java/util/List.iterator:()Ljava/util/Iterator;
               6: astore_1
               7: goto          23
              10: aload_1
              11: invokeinterface #3,  1            // InterfaceMethod java/util/Iterator.next:()Ljava/lang/Object;
              16: astore_2
              17: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
              20: aload_2
              21: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/Object;)V
              24: goto          10
              27: return

    从这个字节码中可以看到，printList 方法在处理列表时，对于列表元素完全按照 Object 类型来处理，
    没有任何关于元素实际类型的信息。这是因为泛型信息在编译时被擦除了。

    结论:

    这个例子展示了如何通过观察字节码来理解Java泛型擦除的机制。在运行时，所有的泛型类型信息都丢失了，
    剩下的只有原始类型。这可能导致类型安全问题，因为在源代码中看起来类型安全的代码
    （如上面的 printList 方法调用），在运行时实际上可能接受任何类型的 List，
    从而引发 ClassCastException 等错误。这也是为什么建议即使是在使用泛型的环境中，
    也要尽可能保留泛型类型参数，以便编译器可以帮助保证类型安全。
*/


    }

    // public static void printList(List list) { // 故意未使用泛型
    public static void printList(List<String> list) { // 故意未使用泛型
        for (Object obj : list) {
            System.out.println(obj); // 这里会打印出列表中的元素
        }
    }


    //-------------------------泛型擦除对方法重载的影响-------------------------------

    // public void print(List<String> list) {
    //     //报错：'print(List<String>)' clashes with 'print(List<Integer>)'; both methods have same erasure
    //     //报错： 'print(List<String>)' 与 'print(List<Integer>)' 冲突；两种方法具有相同的擦除
    //
    //     for (String s : list) {
    //         System.out.println(s);
    //     }
    // }
    //
    public void print(List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
        }
    }

    // 这段代码看起来像是合法的重载，因为它试图根据列表的内容类型（String或Integer）定义不同的行为。
    // 然而，这段代码实际上是不能通过编译的。原因在于泛型擦除后，两个print方法都会变成相同的原始方法签名：
    // public void print(List list){}

    // 因为泛型类型参数在编译时都被擦除了，
    // 两个方法在擦除后的类型上没有区别，
    // 所以它们在Java的方法重载机制中被视为相同的方法，导致编译错误。

//-------------------------泛型擦除对方法重载的影响-------------------------------

    // 解决方法
    // 如果你需要根据泛型类型重载方法，通常需要采取不同的方法命名或者不使用泛型重载。例如，可以将方法命名为printStringList和printIntegerList，以此来区分：
    // 注意:  Signature:签名
    // 虽然泛型类型在编译后的字节码中的方法签名中被擦除（即，
    // 在方法的调用和执行层面不再保留具体的泛型类型信息），
    // 但这些信息并非完全丢失。Java使用`Signature`属性在类文件中
    // 保留了原始的泛型类型信息。这个属性允许保持泛型的完整性，
    // 主要用于反射操作中，使得可以在运行时通过反射访问类、
    // 方法和字段的泛型类型信息。
    public void printStringList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    public void printIntegerList(List<Integer> list) {
        for (Integer i : list) {
            System.out.println(i);
        }
    }


}

