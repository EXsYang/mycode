package com.hspedu.entity;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-10-17-22:14
 * @description:
 */
public class Monk {
//`id` INT NOT NULL AUTO_INCREMENT, #id是自增长的 插入时不用管
//`nickname` VARCHAR(255) NOT NULL,
//`skill` VARCHAR(255) NOT NULL,
//`grade` INT NOT NULL,
//`salary` DOUBLE NOT NULL,
//`birthday` DATETIME DEFAULT NULL,
//`entry` DATE DEFAULT NULL,

    private Integer id;
    private String nickname;
    private String skill;
    private Integer grade;
    private Double salary;
    //数据库是datetime类型 java中可以直接传进去一个java.util.Date 类型
    // 即数据库的datetime类型 在java中可以使用Date类型进行对应
    // 虽然 java.util.Date 类型 . 默认输出的日期格式是国外的方式 但是没影响
    private Date birthday; //出生日期 年月日时分秒
    private Date entry; // 入寺庙的时间 年月日

    public Monk() {
    }

    public Monk(Integer id, String nickname, String skill, Integer grade, Double salary, Date birthday, Date entry) {
        this.id = id;
        this.nickname = nickname;
        this.skill = skill;
        this.grade = grade;
        this.salary = salary;
        this.birthday = birthday;
        this.entry = entry;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getEntry() {
        return entry;
    }

    public void setEntry(Date entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return "Monk{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", skill='" + skill + '\'' +
                ", grade=" + grade +
                ", salary=" + salary +
                ", birthday=" + birthday +
                ", entry=" + entry +
                '}';
    }
}
