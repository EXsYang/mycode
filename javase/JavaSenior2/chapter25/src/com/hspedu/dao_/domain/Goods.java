package com.hspedu.dao_.domain;

/**
 * @author yangda
 * @description:
 * @create 2023-05-03-23:00
 */
public class Goods { //Javabean, POJO, Domain ,entity对象
    private int Field;
    private String goods_name;
    private Double price;

    public Goods() {
    }

    public Goods(int field, String goods_name, Double price) {
        Field = field;
        this.goods_name = goods_name;
        this.price = price;
    }

    public int getField() {
        return Field;
    }

    public void setField(int field) {
        Field = field;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "Field=" + Field +
                ", goods_name='" + goods_name + '\'' +
                ", price=" + price +
                '}';
    }
}
