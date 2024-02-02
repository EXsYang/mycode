package com.atguigu.extends_thread;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author yangda
 * @create 2022-08-21-13:50
 */
public class HashMapTest {
    public static void main(String[] args) {

        HashMap hashMap = new HashMap();
        for (int i = 1; i <= 12 ; i++) {
            hashMap.put(new Car(i),i);

        }
        System.out.println(hashMap);
    }
}
class Car{
   private int num;

    public Car(int num) {
        this.num = num;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Car car = (Car) o;
//        return num == car.num;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(100);
    }

    @Override
    public String toString() {
        return "\tCar{" +
                "num=" + num +
                '}';
    }
}