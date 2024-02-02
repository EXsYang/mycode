package com.atguigu.map_;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-20:41
 */
public class TreeMap_ {
    public static void main(String[] args) {
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
//                return ((String)o1).compareTo((String)o2);//key 按照字符串大小，阿斯克码从小到大排列
                return ((String)o1).length() - ((String)o2).length();//key 按照字符串长度从小到大排列
            }
        });

        treeMap.put("jack","克里斯提娜");
        treeMap.put("a","凤凰院凶真");
        treeMap.put("b3","嘟嘟噜");
        treeMap.put("co4","女装");

        System.out.println(treeMap);
    }
}
