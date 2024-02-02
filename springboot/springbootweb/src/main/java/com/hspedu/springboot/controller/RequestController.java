package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-12-05-15:44
 * @description: 演示@RequestAttribute注解 -获取 request 域属性
 */
@Controller
public class RequestController {

    @GetMapping("/login")
    public String login(HttpServletRequest request) {

        //向request域中添加数据
        request.setAttribute("user", "老韩");

        //向session中添加数据
        request.getSession().setAttribute("website","https://www.baidu.com/");

        return "forward:/ok"; //转发到 /ok
        // return "ok"; // 如果没有配置视图解析器, 默认找Controller 映射路径"/ok"
                     // 且默认是请求转发到 /ok
    }


    @ResponseBody
    @GetMapping("/ok")
    public String ok(@RequestAttribute(value = "user", required = false) String user
                     , HttpServletRequest request,
                     @SessionAttribute(value = "website",required = false) String website
                    ) {
        System.out.println("request 域中 user= " + user);
        System.out.println("servlet api request 域中 user= " + request.getAttribute("user"));
        System.out.println("-----------------");
        System.out.println("session 中的 website= " + website);
        System.out.println("servlet api session 中的 website= " + request.getSession().getAttribute("website"));
        return "success";
    }



    /**
     * 1. 在开发中，SpringBoot 在响应客户端请求时，也支持复杂参数
     * 2. Map、Model、Errors/BindingResult、RedirectAttributes、ServletResponse、SessionStatus、
     * UriComponentsBuilder、ServletUriComponentsBuilder、HttpSession
     * 3. Map、Model 数据会被放在 request 域， 底层 request.setAttribute()
     * 4. RedirectAttributes 重定向携带数据
     *
     * 演示复杂参数的使用，重点: Map、Model、HttpServletResponse
     * 在浏览器地址栏测试 http://localhost:8080/register
     */
    @GetMapping("/register")
    public String register(Map<String,Object> map,
                           Model model,
                           HttpServletResponse response){
        //如果一个注册请求，会将注册数据封装到map/model
        //map/model中的数据会被放入到request域中

        map.put("username","reborn");
        map.put("fruit","apple");
        model.addAttribute("price",80000);

        //请求转发
        return "forward:/registerOk";
    }


    @GetMapping("/registerOk")
    @ResponseBody
    // public String registerOk(@RequestAttribute(value = "username") String username,
    //                          @RequestAttribute(value = "fruit") String fruit,
    //                          @RequestAttribute(value = "price") String price,
    public String registerOk(HttpServletRequest request,
                             HttpServletResponse response){

        System.out.println("-----------使用注解 @RequestAttribute 获取request域中的参数-----------");
        // System.out.println("username= " + username);
        // System.out.println("fruit= " + fruit);
        // System.out.println("price= " + price);

        System.out.println("-----------使用原生 servlet api 获取request域中的参数------------");
        System.out.println("servlet api username= " + request.getAttribute("username"));
        System.out.println("servlet api fruit= " + request.getAttribute("fruit"));
        // Object price = request.getAttribute("price");
        System.out.println("servlet api price= " + request.getAttribute("price"));

        System.out.println("----------给浏览器/客户端通过response 设置/添加cookie------------");
        Cookie cookie = new Cookie("name", "tom2");
        response.addCookie(cookie);

        return "success";
    }






}
