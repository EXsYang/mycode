package com.hspedu.testscan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangda
 * @create 2023-11-23-20:48
 * @description:
 */
@Controller
public class HiController2 {

    //返回hi,springboot字符串
    @RequestMapping("/hi2")
    @ResponseBody //加了该注解 将返回的结果按照合适的格式 直接写入响应体中
    public String hi2() {
        System.out.println("hi2");
        return "hi2!!,springboot!!";
    }
}
