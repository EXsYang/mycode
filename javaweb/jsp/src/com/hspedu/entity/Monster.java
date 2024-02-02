package com.hspedu.entity;

public class Monster  {
    private Integer id;
    private String name;
    private String skill;
    private double sal;

    public Monster(Integer id, String name, String skill, double sal) {
        this.id = id;
        this.name = name;
        this.skill = skill;
        this.sal = sal;
    }

    public Monster(Integer id, String name, String skill) {
        this.id = id;
        this.name = name;
        this.skill = skill;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
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
                ", skill='" + skill + '\'' +
                ", sal=" + sal +
                '}';
    }
}
