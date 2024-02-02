package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangda
 * @create 2023-12-04-16:52
 * @description: springboot Rest风格请求 使用测试
 */
// @RestController
@Controller
public class MonsterController {

    /*
    老韩解读：因为@ResController 是一个复合注解, 含有@ResponseBody, 所以springboot 底层(springmvc), 在处理
    return "xxx" 时, 会以@ResponseBody 注解进行解析处理, 即返回字符串 "xxx", 而不会使用视图解析器来处理.
    同学们, 可以试一下, 如果我们把 @RestController 改成 @Controller , 当你访问getMonster() 时, 如果你有 xxx.html
    就会转发到 xxx.html , 如果没有 xxx.html , 就会报 404
    提示: 在测试时, 讲 xxx.html 放在 main\resources\public\xxx.html 进行测试, 并在application.yml 配置视图解析器
    */
    //等价的写法
    // @RequestMapping(value = "/monster",method = RequestMethod.GET)
    @GetMapping("/monster")
    public String getMonster() {
        return "GET-查询妖怪";

    }

    /**
     * 这里也使用 "/monster" ,不会报错 因为  @GetMapping("/monster")
     * 和 @PostMapping("/monster") 请求方式不同, 也可以区分开来
     */
    //等价的写法
    // @RequestMapping(value = "/monster",method = RequestMethod.POST)
    @PostMapping("/monster")
    public String postMonster() {
        return "POST-保存妖怪";
    }

    //等价的写法
    // @RequestMapping(value = "/monster",method = RequestMethod.PUT)
    @PutMapping("/monster")
    public String putMonster() {
        return "PUT-修改妖怪";
    }

    //等价的写法
    // @RequestMapping(value = "/monster",method = RequestMethod.DELETE)
    @DeleteMapping("/monster")
    public String deleteMonster() {
        return "DELETE-删除妖怪";
    }


    /**
     * HiddenHttpMethodFilter：浏览器 form 表单只支持 GET 与 POST 请求，而 DELETE、PUT
     * 等 method 并不支持，Spring 添加了一个过滤器，可以将这些请求转换为标准的 http 方
     * 法，使得支持 GET、POST、PUT 与 DELETE 请求
     */


    @RequestMapping("/go")
    // @ResponseBody
    public String go(){
        // 匹配顺序说明：
        // 当没有 配置视图解析器 & @ResponseBody 注解时的 匹配的顺序
        // 1. 先看Controller有没有映射路径配置为 "/hello" 的控制器目标方法
        //    , 找到了就执行"/hello"目标方法,没找到就报404错误,
        //    这个404指的是找不到 "/hello" 映射路径[没有配置视图解析器]

        // 2. 如果配置了视图解析器, 但没有配置@ResponseBody
        //    就直接到视图解析器根据配置的前缀和后缀找对应的视图 定位页面
        //    ，就不去匹配Controller 目标方法的映射路径了

        // 如果同时配置了 Controller目标方法映射路径 @RequestMapping("/hello") & 视图解析器 & @ResponseBody
        // 这3种特殊情况 这时 @ResponseBody优先级最高 会给页面返回字符串"hello"
        // 结论：优先级排序 @RequestMapping("/hello") < 视图解析器 < @ResponseBody
        return "hello";


    }

}
