package com.hspedu.web.requestparam.entity;

/**
 * @author yangda
 * @description:
 * @create 2023-09-27-19:20
 */
public class Pet {

    private Integer id;
    private String name;

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
        return "Pet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
