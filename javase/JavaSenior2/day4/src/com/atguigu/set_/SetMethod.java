package com.atguigu.set_;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 * @author yangda
 * @description:
 * @create 2022-11-15-10:06
 */
public class SetMethod {
    public static void main(String[] args) {

        HashSet hashSet = new HashSet();

        hashSet.add("jack");
        hashSet.add("tom");
        hashSet.add("jack");
        hashSet.add("hsp");
        hashSet.add("w");
        hashSet.add(null);
        hashSet.add(null);

        hashSet.remove(null);
//        for(int i = 0;i < 10 ; i++) {
            System.out.println(hashSet);//[null, tom, hsp, w, jack]
            //Set接口对象   Set集合无序，即添加顺序和取出顺序不一致，但是取出的顺序是固定的
//        }


        System.out.println("迭代器：");
        Iterator iterator = hashSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println();
        System.out.println("增强for:");
        for (Object o : hashSet) {
            System.out.println("o:" + o);

        }


    }
}
