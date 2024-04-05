package com.atguigu.exer1;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yangda
 * @create 2022-08-19-20:52
 */
public class HashMapExer {

    public static void main(String[] args) {
        Map map = new HashMap();
        // ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();
        Employee e1 = new Employee("杨达", 18000, 1);
        Employee e2 = new Employee("杨晓彤", 14000, 2);
        Employee e3 = new Employee("张无忌", 31000, 3);
        map.put(e1.getId(), e1);//  public Set<Map.Entry<K,V>> entrySet() {
        map.put(e2.getId(), e2);
        map.put(e3.getId(), e3);
        // HashMap key可以放null , ConcurrentHashMap key不可以放null,会抛出空指针异常 java.lang.NullPointerException
        map.put(null,2); //key=null	value=2
        map.put(null,3); //key=null	value=2
        map.put(null,4); //key=null	value=2
        // HashMap key和value都可以放null
        // map.put(null,null); //key=null	value=2


        //ConcurrentHashMap 类型的集合不可以存储key=null,会报空指针异常 NullPointerException
        //Exception in thread "main" java.lang.NullPointerException
        // 	at java.util.concurrent.ConcurrentHashMap.putVal(ConcurrentHashMap.java:1011)
        // 	at java.util.concurrent.ConcurrentHashMap.put(ConcurrentHashMap.java:1006)
        // 	at com.atguigu.exer1.HashMapExer.main(HashMapExer.java:28)

        ConcurrentHashMap<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();
        // concurrentHashMap.put(null,1111);
        // concurrentHashMap.put(null,null);

        System.out.println("concurrentHashMap= " + concurrentHashMap);



        //第一组：先取出所有的 key ,通过key取出对应的 value
        Set keySet = map.keySet();
        //Collection values = hashMap.values();
        //(1)增强for
        System.out.println("-------第一种方式------");
        for (Object key : keySet) {
            //Map.Entry entry = (Map.Entry)obj;
            System.out.println("key=" + key + '\t' + "value=" + map.get(key));
        }
        //练习:使用keySet -> 增强for
        Set keySet1 = map.keySet();
        System.out.println("-------练习题：使用keySet -> 增强for------");
        for (Object key : keySet1) {
            if (key instanceof String){
                Employee e = (Employee)map.get(key);
                if (e.getSalary() > 18000){

                    System.out.println("工资大于18000:\t"+ e);

                }
            }else {
                System.out.println();
            }


        }


        //(2)迭代器
        System.out.println("-------第二种方式------");
        Iterator iterator1 = keySet.iterator();
        while (iterator1.hasNext()) {
            Object key = iterator1.next();
            System.out.println("key=" + key + '\t' + "value=" + map.get(key));
        }

        //第二组：把所有的value取出
        Collection values = map.values();
        //这里可以使用所有的Collection使用的方法
        //(1)增强for
        System.out.println("-----第二组：取出所有的value，增强for------");
        for (Object value : values) {
            System.out.println(value);
        }

        //(2)iterator迭代器
        System.out.println("-----第二组：取出所有的value，迭代器------");
        Iterator iterator2 = values.iterator();
        while (iterator2.hasNext()) {
            Object value = iterator2.next();
            System.out.println(value);
        }
        //普通的for循环用不了，没有get()方法

        //第三组：通过EntrySet  来获取 key-value;
        Set entrySet = map.entrySet();
        //(1)增强for
        System.out.println("------使用EntrySet 的 for增强（第3种）------");
        for (Object entry : entrySet) {
            //将entry转成Map.Entry
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }

        //(2)迭代器
        System.out.println("------使用EntrySet 的 迭代器（第4种）------");
        Iterator iterator3 = entrySet.iterator();
        while (iterator3.hasNext()) {
            Object entry = iterator3.next();
//            System.out.println(entry.getClass());//HashMap$Node -实现Map.Entry(getKey,getValue)
            //向下转型 Map.Entry
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }

        //练习：使用EntrySet -> 迭代器
        Set entrySet1 = map.entrySet();
        Iterator iterator = entrySet1.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            Map.Entry m = (Map.Entry)next;
            Employee employee = (Employee) m.getValue();
            if (employee.getSalary() > 18000){
                System.out.println("工资大于18000：\t"+employee);
            }
        }
    }
}

class Employee {
    private String name;
    private double salary;
    private int id;

    public Employee(String name, double salary, int id) {
        this.name = name;
        this.salary = salary;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", id=" + id +
                '}';
    }
}