package com.hspedu.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangda
 * @description: 处理rest风格的请求-增删改查
 * HiddenHttpMethodFilter 过滤器可以对以Post方式提交的delete,put,patch进行转换 因此前端一定要转成post 发送请求
 * @create 2023-09-26-21:17
 */
@RequestMapping(value = "/user")
@Controller
public class BookHandler {
    //查询[GET]
    //@RequestMapping(value = "/getBook/{id}",method = RequestMethod.GET)
    @GetMapping(value = "/getBook/{id}")
    //@GetMapping(value = "/book/{id}")  //  '/book/{id}' 和 下面配置的 '/book' 不算重复 ,即使都配置的'/book' 请求方式不同也可以区分开来
    public String getBook(@PathVariable("id") String id) {
        System.out.println("查询书籍 id=" + id );
        return "success";
    }
    //添加[POST]
    @PostMapping(value = "/addBook")
    //@PostMapping(value = "/book")
    public String addBook(String bookName) {
        // 要想接收到参数 需要保证前端传递的form表单中的参数名 name="bookName"
        // 和此方法的形参String bookName 的形参名bookName 保持一致
        System.out.println("添加书籍 bookName== " + bookName);
        return "success";
    }
    //删除[DELETE]
    //@RequestMapping(value = "/book/{id}")
    //@RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    @DeleteMapping(value = "/book/{id}")
    public String delBook(@PathVariable("id") String id) {
        System.out.println("删除书籍 id= " + id);
        //return "success"; // [如果 这样返回会报错 JSPs only permit GET POST or HEAD]
        // 这里的报错属于前端jsp渲染的一个问题 不必深究 知道如何解决就可以
        //老师解读
        //1. redirect:/user/success重定向
        //2. 在服务器端会被解析成 /springmvc/user/success
        // 作为响应头Location返回给浏览器 同时会设置重定向状态码302
        return "redirect:/user/success"; //重定向到一个没有指定 method 的 Handler 方法
    }
    //修改[PUT]
    //@RequestMapping(value = "/book/{id}")
    @RequestMapping(value = "/book/{id}",method = RequestMethod.PUT)
    //@PutMapping(value = "/book/{id}")
    //@PutMapping(value = "/put/{id}")
    public String updateBook(@PathVariable("id") String id) {
        System.out.println("修改书籍 id=" + id);
        //老师解读
        //1. redirect:/user/success重定向
        //2. 在服务器端会被解析成 /springmvc/user/success
        // 作为响应头Location返回给浏览器 同时会设置重定向状态码302
        // 注意该类BookHandler.java 配置的就是 @RequestMapping(value = "/user")
        // , 同时下面的successGeneric() 方法 配置的@RequestMapping(value = "/success")
        return "redirect:/user/success"; //重定向到一个没有指定 method 的 Handler 方法
    }

    //如果请求是 /user/success , 就转发到 success.jsp
    //successGeneric对应的url http://ip:port/springmvc/user/success
    // Generic：通用的
    @RequestMapping(value = "/success")
    public String successGeneric() {
        return "success"; //由该方法 转发到 success.jsp 页面
    }
}