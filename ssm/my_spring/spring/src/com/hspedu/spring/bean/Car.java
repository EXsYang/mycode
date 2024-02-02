package com.hspedu.spring.bean;

import java.math.BigDecimal;

/**
 * @author yangda
 * @description:
 * @create 2023-08-31-20:50
 */
public class Car {
    private Integer id;
    private String name;
    private BigDecimal price;

    public Car() {

        // 这句话被输出 说明Spring 在底层 进行反射的时候默认使用的是无参构造器！！
        System.out.println("Car 无参构造器被调用...");
    }

    public Car(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
