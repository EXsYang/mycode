package com.hspedu.web.interceptor;

import com.hspedu.web.json.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangda
 * @create 2023-10-14-17:06
 * @description: 用于测试拦截器 拦截目标方法的handler
 */
@Controller
public class FurnHandler {


    @RequestMapping(value = "/hi")
    public String hi(User user){
        System.out.println("--FurnHandler--hi--");
        //System.out.println("user= " + user);
        return "success";
    }


    @RequestMapping(value = "/hello")
    public String hello(){
        System.out.println("--FurnHandler--hello--");
        return "success";
    }


    @RequestMapping(value = "/ok")
    public String ok(){
        System.out.println("--FurnHandler--ok--");
        return "success";
    }



}
