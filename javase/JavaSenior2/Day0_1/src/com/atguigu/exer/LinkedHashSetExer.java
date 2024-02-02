package com.atguigu.exer;

import java.util.LinkedHashSet;

/**
 * @author yangda
 * @create 2022-08-18-21:39
 */
public class LinkedHashSetExer {
    public static void main(String[] args) {

        LinkedHashSet linkedHashSet = new LinkedHashSet();

        linkedHashSet.add(new Car("奥拓",1000));
        linkedHashSet.add(new Car("奥迪",300000));
        linkedHashSet.add(new Car("法拉利",10000000));
        linkedHashSet.add(new Car("奥迪",300000));
        linkedHashSet.add(new Car("保时捷",70000000));
        linkedHashSet.add(new Car("奥迪",300000));

        System.out.println(linkedHashSet);
    }

}
