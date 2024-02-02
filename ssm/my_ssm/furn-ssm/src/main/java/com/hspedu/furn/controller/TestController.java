package com.hspedu.furn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangda
 * @create 2023-11-07-16:33
 * @description:
 */
@Controller
public class TestController {

    @RequestMapping("/hi")
    public String hi(){
        System.out.println("TestController-hi");

        return "hi";
    }

}
