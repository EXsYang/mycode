package com.atguigu.map_;

import org.junit.Test;

import java.util.*;

/**
 * @author yangda
 * @description:Map集合遍历的六种方式
 * @create 2022-11-16-15:35
 */
public class MapFor {
    public static void main(String[] args) {


        HashMap map = new HashMap();
//        map.put("No1","唐伯虎");
//        map.put("No2","秋香");
//        map.put("No3","马云");
//        map.put("No4","马化腾");

        Set set = map.keySet();
        int size = set.size();

        System.out.println(set);

        System.out.println(size);

        System.out.println(map);


    }
    @Test
    public void test1(){
        HashMap map = new HashMap();
        map.put("No1","唐伯虎");
        map.put("No2","秋香");
        map.put("No3","马云");
        map.put("No4","马化腾");

        Set set = map.keySet();
        System.out.println("**********map.keySet();增强for******************");
        for (Object key : set) {
            System.out.println(key + "-" + map.get(key));
        }

        System.out.println("**********map.keySet();迭代器******************");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            System.out.println(key + "-" + map.get(key));
        }

    }
    @Test
    public void test2(){
        HashMap map = new HashMap();
        map.put("No1","唐伯虎");
        map.put("No2","秋香");
        map.put("No3","马云");
        map.put("No4","马化腾");

        Collection values = map.values();
        System.out.println("**********map.values();增强for******************");
        for (Object val : values) {
            System.out.println("value:" + val);
        }

        System.out.println("**********map.values();迭代器******************");
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            Object val = iterator.next();
            System.out.println("value:" + val);
        }

    }
    @Test
    public void test3(){
        HashMap map = new HashMap();
        map.put("No1","唐伯虎");
        map.put("No2","秋香");
        map.put("No3","马云");
        map.put("No4","马化腾");

        Set set = map.entrySet();


        System.out.println("**********map.entrySet(); 增强for******************");
        for (Object obj : set) {
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

        System.out.println("**********map.entrySet(); 迭代器******************");
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            Map.Entry entry = (Map.Entry)obj;
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }

    }
}
