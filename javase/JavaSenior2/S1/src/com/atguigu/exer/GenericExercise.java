package com.atguigu.exer;

/**
 * @author yangda
 * @create 2022-10-23-10:32
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 创建三个学生对象
 * 放入到HashSet中学生对象
 * 使用
 * 放入到HashMap中，要求Key是String name,value 就是学生对象
 * 使用两种方式遍历
 */
public class GenericExercise {
    public static void main(String[] args) {

        Student s1 = new Student("yang", 25);
        Student s2 = new Student("张", 26);
        Student s3 = new Student("王", 27);

        HashSet hashSet = new HashSet();

        hashSet.add(s1);
        hashSet.add(s2);
        hashSet.add(s3);

        System.out.println(hashSet);

        HashMap hashMap = new HashMap();

        hashMap.put(s1.getName(),s1);
        hashMap.put(s2.getName(),s2);
        hashMap.put(s3.getName(),s3);

        Set set = hashMap.entrySet();

        System.out.println(hashMap);




    }


}
class Student{
    private String name;
    private int age;

    public Student(String name,int age) {
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
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}