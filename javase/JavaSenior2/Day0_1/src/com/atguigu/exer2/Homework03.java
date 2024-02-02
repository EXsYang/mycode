package com.atguigu.exer2;

import java.util.*;

/**
 * @author yangda
 * @create 2022-08-22-13:12
 */
public class Homework03 {
    public static void main(String[] args) {
        HashMap m = new HashMap();

        m.put("jack",650);
        m.put("Tom",1200);
        m.put("Smith",2900);

        m.put("jack",2900);

        Set set2 = m.keySet();
        for (Object o : set2) {
            m.put(o,((int)m.get(o)+100));
//            System.out.println((int)m.get(o)+100);
        }
        System.out.println("Map.keySet()_foreach修改后：" + m);

        Collection values1 = m.values();
        Iterator iterator1 = values1.iterator();
        while (iterator1.hasNext()) {
            Object entry =  iterator1.next();
//            iterator1.remove();
           // Map.Entry entry1 = (Map.Entry)entry;//java.lang.Integer cannot be cast to java.util.Map$Entry
            System.out.println("Map.values()_iterator()修改后:\t" + entry);
        }

        Set set3 = m.entrySet();
        for (Object entry : set3) {
            Map.Entry m1 = (Map.Entry)entry;
            m.put(m1.getKey(),(((int)(m1.getValue()))+100));


        }
        System.out.println("Map.entrySet()_foreach()修改后:\t" + m);


        Set set = m.keySet();
        for (Object key : set) {

            System.out.println("m.keySet()_for()_key:"+key.getClass() + "\tvalue:"+m.get(key));
                                                    //java.lang.String,此时的key是"jack",是String
        }
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object key =  iterator.next();
            //Set key1 = (Set) key;//java.lang.String cannot be cast to java.util.Set
            System.out.println("m.keySet()_foreach()_key:"+key + "\tvalue:"+m.get(key));

        }

        Collection values = m.values();
        Set set1 = m.entrySet();

        for (Object key : set) {

            System.out.println(m.get(key));

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