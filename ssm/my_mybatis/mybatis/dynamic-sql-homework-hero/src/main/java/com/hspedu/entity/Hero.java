package com.hspedu.entity;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-10-31-17:15
 * @description:
 */
public class Hero {

    private Integer id;
    //外号
    private String nickname;
    //技能
    private String skill;
    // 排行
    private Integer rank;
    // 薪水
    private Double salary;
    // 入伙时间
    // private Date date;
    private String date;

    public Hero() {
    }

    public Hero(Integer id, String nickname, String skill, Integer rank, Double salary, String date) {
        this.id = id;
        this.nickname = nickname;
        this.skill = skill;
        this.rank = rank;
        this.salary = salary;
        this.date = date;
    }

    // public Hero(Integer id, String nickname, String skill, Integer rank, Double salary, Date date) {
    //     this.id = id;
    //     this.nickname = nickname;
    //     this.skill = skill;
    //     this.rank = rank;
    //     this.salary = salary;
    //     this.date = date;
    // }

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    // public Date getDate() {
    //     return date;
    // }
    //
    // public void setDate(Date date) {
    //     this.date = date;
    // }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", skill='" + skill + '\'' +
                ", rank=" + rank +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }
}
