package com.hspedu.hspspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangda
 * @create 2023-12-01-18:58
 * @description:
 */
@RestController
public class HspHiController {

    @RequestMapping("/hsphi")
    public String hi() {
        return "hi is HspHiController2";
    }

}
