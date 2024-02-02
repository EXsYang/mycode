package com.atguigu.extends_thread;

import java.util.ArrayList;

/**
 * @author yangda
 * @create 2022-08-04-13:44
 */
public class ArrayListTest {

    public static void main(String[] args) {

//        使用无参构造器创建ArrayList对象
        ArrayList arrayList = new ArrayList();
        //ArrayList arrayList = new ArrayList();
        //使用for给arrayList集合添加1~10数据
        for (int i = 1; i <= 10; i++) {
            arrayList.add(i);
        }
        //使用for给集合添加 11-15
        for (int i = 11; i <=15 ; i++) {
            arrayList.add(i);

        }

        arrayList.add(100);
        arrayList.add(200);
        arrayList.add(null);

        arrayList.add(new User(111,"11娘"));

        // 由此可见 ArrayList 直接输出 也会调用集合中元素的toString 方法
        System.out.println("arrayList= " + arrayList);

    }
    
}
