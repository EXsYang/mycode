package com.hspedu.mhl.domain;

/**
 * @author yangda
 * @description:
 * @create 2023-04-29-22:50
 */
public class Employee { //Javabean, POJO, Domain ,entity对象
/*    CREATE TABLE employee (
            id INT PRIMARY KEY AUTO_INCREMENT, #自增
            empId VARCHAR(50) UNIQUE NOT NULL DEFAULT '',#员工号
    pwd CHAR(32) NOT NULL DEFAULT '',#密码md5
    NAME VARCHAR(50) NOT NULL DEFAULT '',#姓名
    job VARCHAR(50) NOT NULL DEFAULT '' #岗位
)CHARSET=utf8;*/
    private Integer id;
    private String empId;
    private String pwd;
    private String name;
    private String job;

    public Employee() {//一定要给一个无参构造器[反射需要]
    }

    public Employee(Integer id, String empId, String pwd, String name, String job) {
        this.id = id;
        this.empId = empId;
        this.pwd = pwd;
        this.name = name;
        this.job = job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        return  id +
                "\t\t" + empId +
                "\t\t" + pwd +
                "\t\t" + name +
                "\t\t" + job ;
    }
}
