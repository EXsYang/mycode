package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.MemberFeignService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-01-20:19
 * @description:
 */
@RestController
public class MemberConsumerFeignController {

    //装配MemberFeignService,虽然这里是一个接口,但装配的是代理对象
    @Resource
    private MemberFeignService memberFeignService;


    @GetMapping("/member/consumer/openfeign/get/{id}")
    public Result getMemberById(@PathVariable("id") Long id){

        return memberFeignService.getMemberById(id);

    }


}
