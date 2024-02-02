package com.hspedu.furns.test;

import com.hspedu.furns.service.AdminService;
import com.hspedu.furns.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-07-17-1:14
 */
public class AdminServiceImplTest {

    private AdminService adminService = new AdminServiceImpl();
    @Test
    public void queryMemberByAdminName(){

        if(adminService.isExistsAdminName("adsmin")){
            System.out.println("该管理员存在");
        }else{
            System.out.println("该管理员不存在");
        }


    }
}
