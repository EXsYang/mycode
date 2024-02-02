package com.hsp.generic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author yangda
 * @description:
 * @create 2022-11-18-15:53
 */
public class GenericExercise {
    public static void main(String[] args) {
        //创建三个学生对象，放入HashMap中，要求key是String name value 就是学生对象，使用两种方式遍历
        HashMap<String,Student> map = new HashMap<String,Student>();
        map.put("jack",new Student("jack"));
        map.put("tom",new Student("tom"));
        map.put("hsp",new Student("hsp"));

        Set<String> keySet = map.keySet();
        for (Object key : keySet) {
            System.out.println(key + "---" + map.get(key));
        }
        System.out.println("***************");

        Set<Map.Entry<String, Student>> entries = map.entrySet();
//        public Set<Map.Entry<K,V>> entrySet() {
//            Set<Map.Entry<K,V>> es;
//            return (es = entrySet) == null ? (entrySet = new HashMap.EntrySet()) : es;
//        }


        Iterator<Map.Entry<String, Student>> iterator = entries.iterator();
//            String, Student 替换 K,V
//        public final Iterator<Map.Entry<K,V>> iterator() {
//            return new HashMap.EntryIterator();
//        }
        while (iterator.hasNext()) {
//            Iterator<Map.Entry<String, Student>> 里面存的就是Map.Entry<String, Student>(编译类型) 运行类型：HashMap$Node
            Map.Entry<String, Student> entry =  iterator.next();
            System.out.println(entry.getKey() + "---" + entry.getValue());

            //            System.out.println(entry.getClass());//HashMap$Node
        }


    }
}
class Student{
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}