package com.hspedu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangda
 * @create 2023-12-02-23:11
 * @description:
 */
@RestController
public class HiController {

    // 既可以匹配get又可以匹配post
    @RequestMapping("/hi")
    // @RequestMapping("/hello")
    // @RequestMapping("/1.jpg")
    public String hi(){
        return "hi:):)";
    }
}
