package com.hspedu.web.homework.entity;

/**
 * @author yangda
 * @description:
 * @create 2023-09-28-21:40
 */
public class User {

    private String username;
    //private Integer pwd;
    private String pwd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //public Integer getPwd() {
    //    return pwd;
    //}
    //
    //public void setPwd(Integer pwd) {
    //    this.pwd = pwd;
    //}


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", pwd=" + pwd +
                '}';
    }
}
