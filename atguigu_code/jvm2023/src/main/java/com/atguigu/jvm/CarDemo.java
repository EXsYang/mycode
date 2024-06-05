package com.atguigu.jvm;

/**
 * 理解：栈、堆、方法区
 */
public class CarDemo {
    public static void main(String[] args) {
        Car car = new Car(1,"宝马");
        car.run();
    }
}

class Car extends Object{
    private int id;
    private String name;

    public Car() {
    }

    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void run() {
        System.out.println("car running ...");
    }
}
