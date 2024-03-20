package com.atguigu.exer1;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangda
 * @create 2024-03-19-16:41
 * @description:
 */
public class HashMapTest01 {

    @Test
    public void test01(){


        Map map = new HashMap();
        // ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        // HashMap key可以放null , ConcurrentHashMap key不可以放null,会抛出空指针异常 java.lang.NullPointerException
        map.put("page",2); //key="page"	value=2
        map.put("page",3); //key="page"	value=3
        map.put("page",4); //key="page"	value=4

        System.out.println("map.size=" + map.size());
        System.out.println("map=" + map);
    }
}
