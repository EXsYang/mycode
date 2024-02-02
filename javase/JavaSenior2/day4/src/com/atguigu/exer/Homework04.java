package com.atguigu.exer;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author yangda
 * @description:
 * @create 2022-11-17-19:53
 */
public class Homework04 {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        Person2 p1 = new Person2(1001, "AA");
        Person2 p2 = new Person2(1002, "BB");

        set.add(p1);//存进去的p1的地址值不会更新，就算修改还是在原来的位置
        set.add(p2);
        p1.name = "CC";
        set.remove(p1);//按照 修改后 的hash(此时传进来的p1已经被修改的了)，计算，删除
        System.out.println(set);
        set.add(new Person2(1001,"CC"));
        System.out.println(set);
        set.add(new Person2(1001,"AA"));
        System.out.println(set);

    }
}

class Person2 {
    int id;
    String name;

    public Person2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return id == person2.id &&
                Objects.equals(name, person2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}