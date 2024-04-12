package com.atguigu.set_;

import java.util.HashSet;

/**
 * @author yangda
 * @description: Set接口：存储无序的、不可重复的数据
 * HashSet：作为Set接口的主要实现类；线程不安全的；可以存储null值
 * @create 2022-11-15-11:03
 */
public class HashSet01 {
    public static void main(String[] args) {

        HashSet set = new HashSet();

        System.out.println(set.add("jack"));
        System.out.println(set.add("tom"));
        System.out.println(set.add("jack"));
        System.out.println(set.add("rose"));
        System.out.println(set.add("lucy"));
        set.add(null);
        set.add(null);
        set.add(null);



        set.remove("jack");
        //可以存放null值，但是只能有一个null
        System.out.println("set: " + set);//[null, tom, rose, lucy]


        set = new HashSet();

        set.add(new Dog("tom"));
        set.add(new Dog("tom"));//两个不同的对象

        set.add("hsp");
        set.add("hsp");//常量池中指向同一个对象

        set.add(new String("yangda"));
        set.add(new String("yangda"));//只添加进去一个

        //HashSet 不保证元素是有序的，取决于hash后，再确定索引的结果
        //即，不保证存放元素的顺序和取出的顺序一致
        System.out.println("set:" + set);//[hsp, Dog{name='tom'}, yangda, Dog{name='tom'}]








    }
}
class Dog{
    String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }
}