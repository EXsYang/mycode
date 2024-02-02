package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangda
 * @create 2023-11-21-15:45
 * @description:
 */
@Controller
public class HelloController {

    /**
     * @responseBody 注解的作用是将controller的方法返回的对象
     * 通过适当的转换器转换为指定的格式之后，写入到response对象的body区，
     * 通常用来返回JSON数据或者是XML
     * 数据，需要注意的呢，在使用此注解之后不会再走视图解析器，
     * 而是直接将数据写入到输入流中，他的效果等同于通过response对象
     * 输出指定格式的数据。
     */

    //返回hello,springboot字符串
    @RequestMapping("/hello")
    @ResponseBody //加了该注解 将返回的结果按照合适的格式 直接写入响应体中
    public String hello() {
        System.out.println("hello");
        return "hello!!,springboot!!";
    }


}
