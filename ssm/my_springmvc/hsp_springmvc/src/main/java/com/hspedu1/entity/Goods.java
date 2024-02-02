package com.hspedu1.entity;

/**
 * @author yangda
 * @create 2023-10-07-20:20
 * @description:
 */
public class Goods {

    private Integer id;
    private String name;

    public Goods() {
    }

    public Goods(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
