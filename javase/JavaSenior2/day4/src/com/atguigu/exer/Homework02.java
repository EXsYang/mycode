package com.atguigu.exer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yangda
 * @description:
 * @create 2022-11-17-11:54
 */
public class Homework02 {
    public static void main(String[] args) {
        ArrayList arr = new ArrayList();
        Car baoma = new Car("宝马", 299999);

        arr.add(baoma);
        arr.add(new Car("奔驰",399999));
        arr.add(new Car("大众",499999));
        arr.add(new Car("红旗",599999));

        System.out.println(arr);
        arr.remove(2);
        System.out.println(arr);
        boolean contains = arr.contains(baoma);
        System.out.println(contains);
        System.out.println(arr.size());
        System.out.println(arr.isEmpty());
//        arr.clear();
        ArrayList list = new ArrayList();

        list.add(baoma);
        list.add(new Car("奔驰",399999));
        list.add(new Car("大众",499999));
        list.add(new Car("红旗",599999));

        for (Object car : arr) {
            System.out.println(car);
        }

        Iterator iterator = arr.iterator();
        while (iterator.hasNext()){
            System.out.println("iterator  " + iterator.next());
        }

        System.out.println("addAll  " + arr.addAll(list));
        System.out.println(arr.containsAll(list));
        System.out.println(arr.removeAll(list));
        System.out.println(arr);




    }
}
class Car{
    String name;
    int price;

    public Car(String name,int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}