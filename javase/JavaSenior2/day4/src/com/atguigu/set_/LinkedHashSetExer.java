package com.atguigu.set_;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * @author yangda
 * @description:
 * @create 2022-11-16-11:18
 */
public class LinkedHashSetExer {
    public static void main(String[] args) {

        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new Car("奥迪",1000));
        linkedHashSet.add(new Car("宝马",100022));
        linkedHashSet.add(new Car("本田",1000));
        linkedHashSet.add(new Car("劳斯莱斯",10004444));
        linkedHashSet.add(new Car("宝马",100022));
        linkedHashSet.add(new Car("奥拓",100));

        System.out.println(linkedHashSet);








    }
}
class Car{

    String name;
    int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return price == car.price &&
                Objects.equals(name, car.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    public Car(String name, int price) {
        this.name = name;
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