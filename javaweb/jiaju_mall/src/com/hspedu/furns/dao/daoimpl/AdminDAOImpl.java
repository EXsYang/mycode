package com.hspedu.furns.dao.daoimpl;

import com.hspedu.furns.dao.AdminDAO;
import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.entity.Admin;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:25
 */
public class AdminDAOImpl extends BasicDAO<Admin> implements AdminDAO {

    /**
     * 根据用户名和密码 返回Admin对象
     * @param adminName
     * @param adminPwd
     * @return  返回null 说明输入的用户名或密码不正确
     */
    @Override
    public Admin queryAdminByNameAndPwd(String adminName, String adminPwd) {

        //构建sql
        //String sql = "SELECT * FROM `admin` WHERE admin_name = ? AND admin_pwd = MD5(?)";
        String sql = "SELECT `id`,`admin_name` `adminName`,`admin_pwd` `adminPwd` FROM `admin` WHERE admin_name = ? AND admin_pwd = MD5(?)";
        Admin admin = querySingle(sql, Admin.class, adminName, adminPwd);
        return admin;
    }


    // 细节补充：重写的方法的参数列表的 形参名 和被重新的方法 形参名 不同也视为重写
    @Override
    public Admin queryMemberByAdminName(String admin_name1) {
        //构建sql
        String sql = "SELECT * FROM `admin` WHERE admin_name = ?";
        return querySingle(sql, Admin.class, admin_name1);
    }
}
