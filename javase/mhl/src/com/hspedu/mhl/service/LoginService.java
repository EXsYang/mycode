package com.hspedu.mhl.service;

import com.hspedu.mhl.dao.LoginDAO;
import com.hspedu.mhl.domain.Login;

import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-05-08-23:06
 */
public class LoginService {
    private LoginDAO loginDAO = new LoginDAO();

    public List<Login> loginList(){
        return loginDAO.queryMulti("select * from login",Login.class);
    }


    //登录验证：对用户名和密码进行验证
    public boolean checkEmpIdAndPwd(String empId,String pwd){
        Login login = loginDAO.querySingle("select * from login where empId=? and pwd=MD5(?)", Login.class, empId, pwd);
        if (login == null){
            return false;
        }
        return true;

    }
    //添加用户名和密码的方法,不可以直接添加，还有其他信息呢
    public boolean addEmpIdAndPwd(String empId,String pwd){
        int update = loginDAO.update("insert into login values(?,MD5(?))", empId, pwd);
        return update > 0;
    }
    //更新用户名和密码的方法
    public boolean updateEmpIdAndPwdToLogin(String empId,String pwd){
        int update = loginDAO.update("insert into login values(?,?)", empId, pwd);
        return update > 0;
    }

    //根据empId删除，用户名和密码
    public boolean deleteEmp(String empId){
        int update = loginDAO.update("delete from login where empId=?", empId);
        return update > 0;
    }

    //用户名验证 检查Login表中用户名是否存在 存在返回true
    public boolean checkEmpId(String empId) {
        Object loginEmpId = loginDAO.queryScalar("select * from login where empId=?", empId);
        if (loginEmpId == null){
            System.out.println("用户名不存在");
            return false;
        }
        return true;
    }

    //登录验证 没有MD5的方法
    public boolean checkNOMD5EmpIdAndPwd(String empId, String pwd) {
        Login login = loginDAO.querySingle("select * from login where empId=? and pwd=?", Login.class, empId, pwd);
        if (login == null){
            return false;
        }
        return true;

    }
}
