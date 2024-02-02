package com.hspedu.furns.service.impl;

import com.hspedu.furns.dao.AdminDAO;
import com.hspedu.furns.dao.daoimpl.AdminDAOImpl;
import com.hspedu.furns.entity.Admin;
import com.hspedu.furns.service.AdminService;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-0:46
 */
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO = new AdminDAOImpl();

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @Override
    public Admin adminLogin(Admin admin) {
        //返回对象
        return adminDAO.queryAdminByNameAndPwd(admin.getAdminName(),admin.getAdminPwd());


    }

    /**
     * 判断管理员的用户名是否存在
     * @param adminName
     * @return 返回true 输入的管理员名存在
     */
    @Override
    public boolean isExistsAdminName(String adminName) {
        return adminDAO.queryMemberByAdminName(adminName) != null;
    }
}
