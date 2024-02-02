package com.atguigu.map_;

import java.util.HashMap;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-14:25
 */
public class Map_ {
    public static void main(String[] args) {
        HashMap map = new HashMap();

        map.put("No1","张三丰");
        map.put("No2","张三");
        map.put("No3","张");
        map.put("No4","猪");
        map.put(null,null);
        map.put(null,234);//Map的key 可以为null,value 也可以为null,注意 key 为null,
        //只能有一个，value 为null ,可以多个
        map.put("No5",null);
        map.put(null,"张三丰");
        map.put(3,"张三丰");

        System.out.println("map.get(No1)= " + map.get("No1"));//张三丰
        System.out.println("map.get(4)= " + map.get(4));//null

        System.out.println(map);




    }
}
