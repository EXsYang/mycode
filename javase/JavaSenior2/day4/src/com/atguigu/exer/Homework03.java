package com.atguigu.exer;

import java.util.*;

/**
 * @author yangda
 * @description:
 * @create 2022-11-17-13:19
 */
public class Homework03 {
    public static void main(String[] args) {
        Map m = new HashMap();
//        Employee jack = new Employee("jack", 650);
//        Employee tom = new Employee("tom", 1200);
//        Employee smith = new Employee("smith", 2900);
        HashSet hashSet = new HashSet();
        hashSet.add("s");

        m.put("jack",650);
        System.out.println(m.put("tom", 1200));//null,添加成功，返回null
        System.out.println(m.put("smith", 2900));
//        Object smith = m.put("smith", 2);

        System.out.println(m);

        m.put("jack",2600);

//        m.put("jack",660);

        Set set = m.keySet();
        for (Object key : set) {
            m.put(key,((int)(m.get(key))+100));
            System.out.println(key + "-" + m.get(key));
//            m.put(key,22);
//            System.out.println(m);
//            System.out.println("员工： " + key + "工资： " + m.put(key,1));


//            System.out.println("员工： " + key + "工资： " + m.put(key,((int)(m.get(key))+100)));
//            员工： tom工资： 1200       这里总是打印修改之前的值，是因为，找到相同的key时，
//            员工： smith工资： 2900     不添加新对象，为旧对象进行一个替换操作
//            员工： jack工资： 2600      这里确实修改成功了
//

//            if (e != null) { // existing mapping for key
//                V oldValue = e.value;
//                if (!onlyIfAbsent || oldValue == null)
//                    e.value = value;
//                afterNodeAccess(e);
//                return oldValue;
//            }
        }
        System.out.println("********************");

        Set set2 = m.keySet();
        System.out.println("set2.getClass(): " + set2.getClass());//HashMap$KeySet
        System.out.println("增强for keySet  ");
        for (Object key : set2) {
            System.out.println(key + "-" + m.get(key));
        }
        System.out.println();
        System.out.println("迭代器iterator  keySet");
        Iterator iterator1 = set2.iterator();
        while (iterator1.hasNext()) {
            Object key =  iterator1.next();
            System.out.println(key.getClass());//class java.lang.String
            System.out.println(key + "-" + m.get(key));

        }

        System.out.println("****************************");
        Collection values = m.values();
        System.out.println("values.getClass() :" + values.getClass());//HashMap$Values
        for (Object val : values) {
            System.out.println("增强for:values  " + val);
            System.out.println(val.getClass());//class java.lang.Integer
        }

        Iterator iterator = values.iterator();
        while (iterator.hasNext()){
            System.out.println("迭代器iterator  values  " + iterator.next());
        }

        System.out.println("***********************");
        Set set1 = m.entrySet();//
        // 编译类型：  Set<Map.Entry<K, V>> entrySet();
        //
        System.out.println("m.entrySet()：" + set1.getClass());//class java.util.HashMap$EntrySet
        System.out.println("增强for  entrySet");
        for (Object entry : set1) {
            System.out.println(entry.getClass());//HashMap$Node
//            static class Node<K,V> implements Map.Entry<K,V> {
            //接口多态
            Map.Entry entry1 = (Map.Entry)entry;

//            HashMap.Node entry1 = (HashMap.Node)entry;
//    'java.util.HashMap.Node' is not public in 'java.util.HashMap'. Cannot be accessed from outside package

            System.out.println(entry1.getKey() + "---" + entry1.getValue());
            //这里走的是HashMap$Node里的getKey(),方法
            //因为 Node<K,V> implements Map.Entry<K,V>  动态绑定
            //entrySet 里面存放的 Set<Map.Entry<K, V>> 运行类型(真正的类型)就是一个 HashMap$Node类型

//            static class Node<K,V> implements Map.Entry<K,V> {
//                final int hash;
//                final K key;
//                V value;
//                HashMap.Node<K,V> next;
//
//                Node(int hash, K key, V value, HashMap.Node<K,V> next) {
//                    this.hash = hash;
//                    this.key = key;
//                    this.value = value;
//                    this.next = next;
//                }
//
//                public final K getKey()        { return key; }
//                public final V getValue()      { return value; }
//                public final String toString() { return key + "=" + value; }

            }
        System.out.println();
        System.out.println("迭代器 iterator  entrySet");
        Iterator iterator2 = set1.iterator();
        while (iterator2.hasNext()) {
            Object entry =  iterator2.next();
            System.out.println(entry.getClass());//HashMap$Node
            Map.Entry entry1 = (Map.Entry)entry;
            System.out.println(entry1.getKey() + "-" + entry1.getValue());



        }


    }
}
class Employee{
    private String name;
    private int salary;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}