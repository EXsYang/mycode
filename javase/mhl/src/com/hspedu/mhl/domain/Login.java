package com.hspedu.mhl.domain;

/**
 * @author yangda
 * @description:
 * @create 2023-05-08-23:03
 */
public class Login { //Javabean, POJO, Domain ,entity对象
    private String empId;
    private String pwd;

    public Login() {
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return  empId + "\t\t" + pwd ;
    }
}
