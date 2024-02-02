package com.hspedu.furns.test;

import com.hspedu.furns.dao.AdminDAO;
import com.hspedu.furns.dao.daoimpl.AdminDAOImpl;
import com.hspedu.furns.entity.Admin;
import com.hspedu.furns.entity.Page;
import org.junit.jupiter.api.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:38
 */
public class AdminDAOImplTest {
    private AdminDAO adminDAO = new AdminDAOImpl();

    private String defaultVal;

    @Test
    public void queryAdminByNameAndPwd(){

        if(adminDAO.queryAdminByNameAndPwd("admin", "admin") != null){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }

        System.out.println(1/3);
        System.out.println(3/5);

    }
    @Test
    public void queryMemberByAdminName(){

        if(adminDAO.queryMemberByAdminName("admin") != null){
            System.out.println("找到 该名字的管理员");
        }else{
            System.out.println("没有找到 该名字的管理员");
        }

    }
    @Test
    public void StringDefault(){


        //System.out.println(defaultVal.getClass()); //.NullPointerException
        System.out.println( "" + defaultVal.getClass()); //.NullPointerException
        System.out.println( ("" + defaultVal).getClass()); //class java.lang.String


    }


}
