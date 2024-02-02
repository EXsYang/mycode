package com.hspedu.entity;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-14:08
 * @description: 双向映射 一对多，多对一
 */
public class User {

    private Integer id;
    private String name;
    // 一个主人对应多个宠物, mybatis使用集合List<Pet>体现一对多的关系
    private List<Pet> pets;


    public User() {
    }

    public User(Integer id, String name, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.pets = pets;
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

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    // 会出现栈溢出 StackOverflowError
    // 原因:
    // 在UserMapper.xml 的 resultMap 中使用  collection 标签 如果没有指定 集合的javaType
    // mybatis底层会 进行类型推断 默认封装为'class java.util.ArrayList'
    // ArrayList 直接输出 也会调用集合中元素的toString 方法
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                // 下面打印pets时 会调用pet.toString
                // 通过输出pets的运行类型 可以发现声明为List类型的属性
                // mybatis底层会 进行类型推断 封装为'class java.util.ArrayList'
                // 因为ArrayList 重写了 toString方法 因此
                // 又会调用ArrayList集合中元素的toString方法 即调用pet.toString
                // 而pet.toString 中 直接输出了user对象 就又会调用user.toString...
                // 因此造成了栈溢出
                ", pets=" + pets + ", pets.getClass='" + pets.getClass() + '\'' +
                '}';
    }
}
