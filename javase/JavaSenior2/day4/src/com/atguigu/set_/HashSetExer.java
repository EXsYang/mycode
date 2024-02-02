package com.atguigu.set_;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author yangda
 * @description:
 * @create 2022-11-15-17:20
 */
public class HashSetExer {
    public static void main(String[] args) {

        HashSet hashSet = new HashSet();

        hashSet.add(new Employee("yangda",22));
        hashSet.add(new Employee("yangda",22));
        hashSet.add(new Employee("yanda",22));

        Employee ysd = new Employee("ysd", 22);
        Employee ysd1 = new Employee("ysd", 22);
        System.out.println();
        int i = ysd.hashCode();//重写之后，如果名字和年龄相同，返回相同的hashCode值，因为里面进行运算的程序(计算方法)是一样的 hashCode:3719309
        int i1 = ysd1.hashCode();// hashCode:3719309    如果不重写，使用Object默认的hashCode()，是不相同的hashCode   和重写不重写equals()没有关系


        System.out.println(hashSet);
    }
}
class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age &&
                Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}