package com.hspedu.web.json.entity;

/**
 * @author yangda
 * @create 2023-10-12-10:53
 * @description:
 */
public class Dog {

    private String name;
    private String address;

    public Dog() {
    }

    public Dog(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
