package com.atguigu.list_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @description: List集合有序可重复
 * ArrayList：作为List接口的主要实现类；可以存放空值null；
 * 线程不安全的，效率高；底层使用Object[] elementData存储
 * @create 2022-11-14-17:20
 */
public class ArrayListSource {
    public static void main(String[] args) {

        //使用无参构造器创建ArrayList对象
        ArrayList list = new ArrayList();
        // List list = new ArrayList();
//        ArrayList arrayList1 = new ArrayList(8);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        for (int i = 11; i <= 15 ; i++) {
            list.add(i);
        }

        list.add(100);
        list.add(100);
        list.add(100);
        list.add(200);
        list.add(null);
        list.add(null);
        list.add(null);

        //list.get(0);

        System.out.println("list" + list);
        //list[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 100, 100, 100, 200, null, null, null]


    }
}
