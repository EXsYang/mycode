package com.atguigu.java2;

/**
 * @author yangda
 * @create 2022-06-05-19:22
 */

/**
 * @author yangda
 * @create 2022-06-05-16:44
 */
public class Goods1 {

    private String name;

    private double price;

    public Goods1() {
    }

    public Goods1(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
