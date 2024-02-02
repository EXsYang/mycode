package com.atguigu.extends_thread;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author yangda
 * @create 2022-08-15-18:23
 */
public class HashSetTest {
    public static void main(String[] args) {

        HashSet hashSet = new HashSet();
//        hashSet.add("java");
//        hashSet.add("yangda");
//        hashSet.add("java");

        Employee1 yd = new Employee1("yd", 24);
        Employee1 yxt = new Employee1("yxt", 18);
        Employee1 yd1 = new Employee1("yd", 24);
        hashSet.add(yd);
        hashSet.add(yxt);
        hashSet.add(yd1);

        System.out.println(hashSet);
    }

    }
    class Employee1{
    private String name;
    private int age;

        public Employee1(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Employee1 employee1 = (Employee1) o;
            return age == employee1.age &&
                    Objects.equals(name, employee1.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return "Employee1{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }