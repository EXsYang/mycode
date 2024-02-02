package com.hspedu.furns.dao;

import com.hspedu.furns.entity.Admin;
import com.hspedu.furns.entity.Member;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:25
 */
public interface AdminDAO{

    // 提供一个方法 根据用户名和密码 返回Admin对象
    //public Admin queryAdminByNameAndPwd(String name,String pwd);

    // 提供一个方法 根据用户名和密码 返回Admin对象
    public Admin queryAdminByNameAndPwd(String name,String pwd);

    public Admin queryMemberByAdminName(String adminName);
}
