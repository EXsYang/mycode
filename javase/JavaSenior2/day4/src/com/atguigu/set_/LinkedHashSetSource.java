package com.atguigu.set_;

import java.util.LinkedHashSet;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-11:14
 */
public class LinkedHashSetSource {
    public static void main(String[] args) {

        LinkedHashSet linkedHashSet = new LinkedHashSet();

        linkedHashSet.add("yangda");
        linkedHashSet.add("jack");
        linkedHashSet.add("tom");
        linkedHashSet.add("yangda");
        linkedHashSet.add("hsp");
        linkedHashSet.add(new Customer("刘备"));

        System.out.println(linkedHashSet);//[yangda, jack, tom, hsp, Customer{name='刘备'}]






    }
}
class Customer{
    String name;

    public Customer(String name) {
        this.name = name;
    }




    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }
}