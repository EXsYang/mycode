package com.atguigu.map_;

import java.util.Hashtable;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-18:08
 */
public class Hashtable_ {
    public static void main(String[] args) {
        Hashtable hashtable = new Hashtable();
        hashtable.put("No1","张三丰");
        hashtable.put("No2","张三");
        hashtable.put("No3","马化腾");
        hashtable.put("No1","赵敏");

        //为什么Entry 可以放到集合里面去？

        System.out.println(hashtable);

    }
}
