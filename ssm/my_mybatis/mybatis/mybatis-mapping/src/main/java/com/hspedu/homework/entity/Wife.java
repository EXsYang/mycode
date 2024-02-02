package com.hspedu.homework.entity;

/**
 * @author yangda
 * @create 2023-11-02-20:35
 * @description:
 */
public class Wife {

    private Integer id;
    private String name;

    public Wife() {
    }

    public Wife(Integer id, String name) {
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
        return "Wife{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
