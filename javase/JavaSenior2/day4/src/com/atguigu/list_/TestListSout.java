package com.atguigu.list_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2024-04-04-15:02
 * @description:
 */
public class TestListSout {
    public static void main(String[] args) {

       List<Person> personArrayList = new ArrayList<>();

        Car car = new Car();

        personArrayList.add(new Person(200,"小黄"));
        personArrayList.add(new Person(200,"小蓝"));
        personArrayList.add(new Person(300,"小红"));

        car.setPersons(personArrayList);

        car.setCarId(10);

        car.setColor("red");

        // list类型的属性输出格式如下
        // persons=[Person{personId=200, name='小黄'},Person{personId=200, name='小蓝'}, Person{personId=300, name='小红'}]
        System.out.println("car-> " + car);
        // car-> Car{carId=10, color='red', persons=[Person{personId=200, name='小黄'}, Person{personId=200, name='小蓝'}, Person{personId=300, name='小红'}]}


    }
}
