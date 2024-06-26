package com.hspedu.furns.entity;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:27
 */
public class Admin {

    private Integer id;
    private String adminName;
    private String adminPwd;

    public Admin() {
    }

    public Admin(Integer id, String adminName, String adminPwd) {
        this.id = id;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                '}';
    }
}
