package com.hspedu.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangda
 * @create 2023-11-29-17:22
 * @description: 注解 @RestController 就是 @ResponseBody+@Controller 的组合注解
 * //直接在类上标注该注解 相当于类中所有的目标方法 都标注了@ResponseBody注解
 * ,因此类中的方法就不能再加 @ResponseBody 注解了!
 * <p>
 * 1. HiController 被 @RestController标注，就是一个控制器
 * 2. HiController 在springboot默认扫描包/子包下，会被注入spring容器
 */
@RestController
public class HiController {


    @RequestMapping("/hi")
    public String hi() {
        System.out.println("hi i am HiController");
        return "hi i am HiController";
    }

}
