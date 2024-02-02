package com.hspedu.springboot.bean;

/**
 * @author yangda
 * @create 2023-11-25-21:33
 * @description: 演示在 SpringBoot, 如何通过@Configuration 创建配置类来注入组件
 */

public class Monster {

    private Integer id;
    private String name;
    private Integer age;
    private String skill;

    public Monster() {
    }

    public Monster(Integer id, String name, Integer age, String skill) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skill = skill;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", skill='" + skill + '\'' +
                '}';
    }
}
