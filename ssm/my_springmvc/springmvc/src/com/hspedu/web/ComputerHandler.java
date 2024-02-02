package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * @author yangda
 * @description:
 * @create 2023-09-26-14:22
 */
@RequestMapping("/computer")
@Controller
public class ComputerHandler {

    // 注意 这里computerInfo(String brand,String price,String count) 形参名
    // 一定要和请求来的url的参数名保持一致，否则为null
    @PostMapping("/info")
    public String computerInfo(String brand,String price,String count){

        System.out.println("提交信息：" + "brand= " + brand +" price= " + price + " count= " + count);
        return "success";
    }

}
