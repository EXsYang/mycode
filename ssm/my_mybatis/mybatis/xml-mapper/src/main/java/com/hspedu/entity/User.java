package com.hspedu.entity;

/**
 * @author yangda
 * @create 2023-10-29-22:36
 * @description:
 */
public class User {

    private Integer user_id;
    private String username;
    private String useremail;

    public User() {
    }

    public User(Integer user_id, String username, String useremail) {
        this.user_id = user_id;
        this.username = username;
        this.useremail = useremail;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                '}';
    }
}
