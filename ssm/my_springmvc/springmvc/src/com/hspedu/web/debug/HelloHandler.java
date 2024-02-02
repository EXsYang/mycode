package com.hspedu.web.debug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangda
 * @create 2023-10-15-18:58
 * @description:
 */
@Controller
public class HelloHandler {

    //编写方法，响应请求，返回ModelAndView
    @RequestMapping(value = "/debug/springmvc")
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response){
        System.out.println("通过 /debug/springmvc请求进入到 HelloHandler-hello()");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ok");//对应到 /WEB-INF/pages/ok.jsp
        modelAndView.addObject("name","yangda"); //默认在 request 域中

        return modelAndView;
    }

}
