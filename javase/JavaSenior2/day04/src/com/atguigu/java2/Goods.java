package com.atguigu.java2;

/**
 * @author yangda
 * @create 2022-06-05-16:44
 */
public class Goods implements Comparable {

    private String name;

    private double price;

    public Goods() {
    }

    public Goods(String name, double price) {
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


    @Override
    public int compareTo(Object o) {
        if (o instanceof Goods) {
            Goods goods = (Goods) o;
            //方式一；
            if (this.price > goods.price) {
                return 1;
            } else if (this.price < goods.price) {
                return -1;
            } else {
//                return 0;
                //方式二：
                // return Double.compare(this.price,goods.price);

                return -this.name.compareTo(goods.name);
            }


        }
        throw new RuntimeException("传入的数据类型不一致");

        //return 0;//遥不可及的声明
    }
}
