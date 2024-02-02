package com.hspedu.homework;

public class Monster  {
    private Integer id;
    private String name;
    private String job;
    private double sal;

    public Monster(Integer id, String name, String job, double sal) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.sal = sal;
    }

    public Monster(Integer id, String name, String job) {
        this.id = id;
        this.name = name;
        this.job = job;
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

    public String getjob() {
        return job;
    }

    public void setjob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", sal=" + sal +
                '}';
    }
}
