package com.hspedu.furns.service;

import com.hspedu.furns.entity.Admin;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:43
 */
public interface AdminService {

    // 提供 管理员登录方法 adminLogin
    public Admin adminLogin(Admin admin);

    // 老韩写的如下 命名方式参考 判断管理员用户名是否存在
    public boolean isExistsAdminName(String adminName);
}
