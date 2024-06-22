package com.atguigu.java;

import java.lang.reflect.Constructor;

/**
 * @author yangda
 * @create 2024-06-19-17:46
 * @description:
 * 在Java中，Constructor类的newInstance()方法
 * 提供了一种通过反射来创建对象的方式。
 * 这种方法允许你在运行时动态地创建对象，
 * 并且能够为其构造方法提供参数。
 * 这是反射（Reflection）API的一部分，
 * 特别用于那些在编写代码时还不知道具体类的情况。
 * 下面是一个简单的例子，演示如何使用Constructor的newInstance()方法创建对象：
 *
 * 示例类定义
 * 假设我们有一个简单的类Person，
 * 具有两个字段：名字（name）和年龄（age），以及一个构造方法.
 *
 * 代码解释
 * 获取Class对象：使用Person.class来获取Person类的Class对象。
 * 获取Constructor对象：调用getConstructor方法并传递参数类型，
 * 获取匹配的公开（public）构造函数。
 * 这里传递的参数是String.class和int.class，匹配Person类的构造函数。
 * 创建实例：通过newInstance方法并传递具体的参数来创建Person类的实例。
 * 这里传递的参数是字符串"John Doe"和整数30。
 * 异常处理：使用反射可能会抛出多种异常，
 * 包括但不限于NoSuchMethodException、IllegalAccessException、InstantiationException和InvocationTargetException，因此需要捕获并处理这些异常。
 * 以上就是使用Constructor的newInstance()方法创建对象的示例。这种方法非常强大，因为它可以在运行时动态地创建任何类型的对象，并且可以选择合适的构造函数。这对于编写通用代码和框架非常有用。
 */
public class NewInstanceTest {
    public static void main(String[] args) {

        try {
            // 获取Person类的Class对象
            Class<Person> clazz = Person.class;

            // 获取Person类的构造器对象
            Constructor<Person> constructor = clazz.getConstructor(String.class, int.class);

            // 使用构造器对象的newInstance方法创建Person类的实例
            Person person = constructor.newInstance("John Doe", 30);

            // 输出创建的实例
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + '\'' + ", age=" + age + '}';
        }
    }
}
