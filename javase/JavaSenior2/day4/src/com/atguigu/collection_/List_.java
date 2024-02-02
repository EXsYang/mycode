package com.atguigu.collection_;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2022-11-14-11:00
 */
public class List_ {

    @Test
    public void testlistmethod(){

        List list = new ArrayList();

        list.add("jack");
        list.add("tom");
        list.add("hsp");

        list.add(1,"yangda");//在index位置插入ele元素
        list.add("yangda");//在index位置插入ele元素
        System.out.println(list);

        ArrayList list2 = new ArrayList();
        list2.add("jack");
        list2.add("tom");
        list2.add("hsp");

//        list.add(list2);//将一个集合当成一个元素插入
        list.addAll(3, list2);//将集合里的多个元素插入index位置(从index位置开始)
        System.out.println(list);

        Object o = list.get(0);
        System.out.println(o);

        System.out.println(list.indexOf("yangda"));

        System.out.println(list.lastIndexOf("yangda"));


        list.remove("yangda");//删除从左往右第一次找到的元素
        System.out.println(list);
        list.add(3);
        list.add(5);
        System.out.println(list);
        list.remove(3);//索引位置

        System.out.println(list);

        list.set(5,"东野");//索引位置
        System.out.println(list);

        List subList = list.subList(0, 3);//左闭右开的
        System.out.println("subList：" + subList );


    }

    @Test
    public void testexer(){

        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }

        arrayList.add(2,"韩顺平教育");//在索引2，插入
        System.out.println(arrayList);
        System.out.println("第五个位置上的元素:" + arrayList.get(4));
        //get(4) 索引位置
        System.out.println(arrayList);
        arrayList.remove(5);//remove(5) 索引位置
        System.out.println("remove(5)后:" + arrayList);

        arrayList.set(2,"郭靖");//set() 索引位置
        System.out.println("修改第七个元素后(set(2,郭靖))：" + arrayList);
        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next);
        }

    }
}
