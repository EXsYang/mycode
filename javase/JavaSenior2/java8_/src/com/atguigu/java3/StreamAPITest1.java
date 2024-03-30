package com.atguigu.java3;

import com.atguigu.java2.Employee;
import com.atguigu.java2.EmployeeData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 测试Stream的中间操作
 *
 * @author shkstart
 * @create 2019 下午 4:42
 */
public class StreamAPITest1 {

    //1-筛选与切片
    @Test
    public void test1(){
        List<Employee> list = EmployeeData.getEmployees();
//        filter(Predicate p)——接收 Lambda ， 从流中排除某些元素。
        Stream<Employee> stream = list.stream();
        //练习：查询员工表中薪资大于7000的员工信息
        stream.filter(e -> e.getSalary() > 7000).forEach(System.out::println);

        System.out.println();
//        limit(n)——截断流，使其元素不超过给定数量。
        list.stream().limit(3).forEach(System.out::println);
        System.out.println();

//        skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
        list.stream().skip(3).forEach(System.out::println);

        System.out.println();
//        distinct()——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素

        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",41,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));
        list.add(new Employee(1010,"刘强东",40,8000));

//        System.out.println(list);

        list.stream().distinct().forEach(System.out::println);
    }

    //映射
    @Test
    public void test2(){
//        map(Function f)——接收一个函数作为参数，将元素转换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素。
        /**
         * ## stream的map可以返回任意类型的对象并使用.collect(Collectors.toList());收集到集合中对吗？
         *
         * 是的，您理解得完全正确。在 Java Stream API 中，`map` 操作是一个中间操作，
         * 它可以接受一个函数作为参数。这个函数被应用到流中的每一个元素上，并将其映射（转换）成一个新的值。
         * 因此，`map` 操作的作用就是对流中的每个元素进行转换。
         */
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);

//        练习1：获取员工姓名长度大于3的员工的姓名。
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<String> namesStream = employees.stream().map(Employee::getName);
        namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
        System.out.println();
        //练习2：
        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        streamStream.forEach(s ->{
            s.forEach(System.out::println);
        });
        System.out.println();
//        flatMap(Function f)——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
        Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        characterStream.forEach(System.out::println);

        /**
         * flatMap(Function f)方法扁平化的解释
         * “扁平化”（Flattening）在这个上下文中是一个将多层结构（如流中的流、数组中的数组或列表中的列表）转换成单一层结构的过程。在Java Stream API中，`flatMap`方法用于这种转换。
         *
         * 当你使用`flatMap`方法时，你开始时可能有一个由多个集合或流组成的流。每个这些集合或流本身可能包含多个元素。如果你直接使用`map`方法，你将结束于一个“流的流”（例如，`Stream<Stream<T>>`），其中每个原始元素现在都被转换成了一个流。
         *
         * 使用`flatMap`的“扁平化”操作，这些内部流不再被视为独立的流，而是他们的内容被合并或“扁平化”成一个单一的流。因此，不是有一个流中包含多个流，你得到一个流，其中包含所有内部流中的所有元素，从而消除了层次结构。
         *
         * 例如，假设你有一个流，其中每个元素是一个列表：
         *
         * - 元素 1: [a, b, c]
         * - 元素 2: [d, e, f]
         * - 元素 3: [g, h, i]
         *
         * 如果你对这个流应用`flatMap`，使用一个简单的函数将每个列表转换成一个流，扁平化过程会将这三个列表中的所有元素合并到一个单一的流中，结果就是：
         *
         * - [a, b, c, d, e, f, g, h, i]
         *
         * 现在，这个结果是一个单一的流，其中包含所有原始子列表的所有元素。这就是`flatMap`实现的“扁平化”效果。
         */

    //    在Java中，`Stream`接口提供了一种高级迭代机制，允许你以声明式方式处理数据集合。`map`和`flatMap`方法是`Stream`接口中用于转换数据的两个重要方法。这里的例子展示了如何在实践中使用这些方法。
        //
        // ### map 方法
        //
        // `map(Function f)` 方法接收一个函数作为参数。这个函数应用于流中的每个元素，将每个元素转换成另一种形式或提取信息，并最终生成一个新的流，其中包含已应用函数的结果。
        //
        // 在给定的例子中：
        //
        // ```java
        // list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        // ```
        //
        // 这里，`list` 是一个包含字符串的列表。调用 `stream()` 方法将列表转换成流。然后，`map` 方法应用于流中的每个字符串元素。在 `map` 方法中，通过 lambda 表达式 `str -> str.toUpperCase()` 定义了一个函数，该函数将每个字符串转换为其大写形式。结果是一个新的流，其中包含原始字符串的大写版本。最后，`forEach` 方法用于遍历流并打印每个元素。
        //
        // ### 练习1
        //
        // ```java
        // Stream<String> namesStream = employees.stream().map(Employee::getName);
        // namesStream.filter(name -> name.length() > 3).forEach(System.out::println);
        // ```
        //
        // 在这个练习中，首先通过 `employees.stream().map(Employee::getName)` 获取一个包含所有员工姓名的流。然后，使用 `filter` 方法筛选出长度大于3的那些姓名。最后，`forEach` 方法用于遍历这些名称并将它们打印出来。
        //
        // ### 练习2
        //
        // ```java
        // Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
        // streamStream.forEach(s ->{
        //     s.forEach(System.out::println);
        // });
        // ```
        //
        // 这个例子展示了 `map` 方法的另一种用法。这里，`map` 方法将每个字符串转换成一个字符流（通过 `StreamAPITest1::fromStringToStream` 方法）。结果是一个“流的流”，即每个元素本身是一个流（在这种情况下，是一个字符流）。然后，它使用两层 `forEach` 方法来遍历这些流，首先遍历外部流，然后遍历每个内部字符流，并打印出每个字符。
        //
        // ### flatMap 方法
        //
        // 最后一个例子展示了 `flatMap` 方法：
        //
        // ```java
        // Stream<Character> characterStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        // characterStream.forEach(System.out::println);
        // ```
        //
        // `flatMap(Function f)` 方法也接收一个函数作为参数，这个函数应用于原始流中的每个元素。不同于 `map`，每个元素转换后的结果应该是一个流，然后 `flatMap` 方法将所有这些新生成的流“扁平化”为一个新的单一流。这样，如果原始流中的元素是集合或数组，`flatMap` 可以用来将多个集合或数组合并成一个流，从而使其成为单一的序列。
        //
        // 在这个例子中，`flatMap` 用于将列表中的每个字符串转换成一个字符流，然后将所有的字符流合并成一个单一的字符流，最后遍历这个流并打印出每个字符。这是处理嵌套集合或转换结果为多个值的常用技术。
    }

    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){//aa
        ArrayList<Character> list = new ArrayList<>();
        for(Character c : str.toCharArray()){
            list.add(c);
        }
       return list.stream();

    }



    @Test
    public void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);

//        list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);

    }

    //3-排序
    @Test
    public void test4(){
//        sorted()——自然排序
        List<Integer> list = Arrays.asList(12, 43, 65, 34, 87, 0, -98, 7);
        list.stream().sorted().forEach(System.out::println);
        //抛异常，原因:Employee没有实现Comparable接口
//        List<Employee> employees = EmployeeData.getEmployees();
//        employees.stream().sorted().forEach(System.out::println);


//        sorted(Comparator com)——定制排序

        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted( (e1,e2) -> {

           int ageValue = Integer.compare(e1.getAge(),e2.getAge());
           if(ageValue != 0){
               return ageValue;
           }else{
               return -Double.compare(e1.getSalary(),e2.getSalary());
           }

        }).forEach(System.out::println);
    }

}
