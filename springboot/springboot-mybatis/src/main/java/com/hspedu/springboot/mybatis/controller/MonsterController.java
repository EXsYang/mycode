package com.hspedu.springboot.mybatis.controller;

import com.hspedu.springboot.mybatis.bean.Monster;
import com.hspedu.springboot.mybatis.service.MonsterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2023-12-17-19:53
 * @description:
 */
@Controller
public class MonsterController {

    //这里虽然是使用的接口进行注入的，但是实际上是装配的实现类的对象
    @Resource
    private MonsterService monsterService;

    @ResponseBody
    @GetMapping("/monster")
    public Monster getMonsterById(@RequestParam(value = "id",required = false) Integer id){

        System.out.println("MonsterController getMonsterById() 被调用...");

        return monsterService.getMonsterById(id);
    }

}
