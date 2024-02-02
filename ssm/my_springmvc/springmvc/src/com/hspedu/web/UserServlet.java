package com.hspedu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author yangda
 * @description:
 * @create 2023-09-24-20:46
 *
 * 说明：
 * 1. 如果我们使用了SpringMVC，在一个类上标识@Controller
 * 2. 表示将该类视为一个控制器，注入到容器
 * 3. 比原生servlet开发要简化很多
 * 4. 在SpringMVC 里面Controller就是所谓的Handler(处理器)，它是一个概念
 */
@Controller
public class UserServlet {

    /**
     * 编写方法 相应用户请求
     *
     * 解读：
     * 1. login() 方法是用于响应用户的登录请求
     * 2. @RequestMapping(value = "/login") 类似于我们以前在原生的Servlet配置的url-pattern
     * 3. 即当用户在浏览器输入 http://localhost:8080/web工程路径/login 就能访问到login()
     * 4. return "login_ok"; 表示返回结果给视图解析器(InternalResourceViewResolver)
     *    , 视图解析器会根据配置，来决定跳转到哪个页面
     *
     *      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     *         <property name="prefix" value="/WEB-INF/pages/"/>
     *         <property name="suffix" value=".jsp"/>
     *     </bean>
     *
     *     根据上面的配置  return "login_ok"; 就会转发到 /WEB-INF/pages/login_ok.jsp
     *     如果返回的 return "login_okx"; 会找不到文件/页面 报错
     */

    //@RequestMapping("/login") // 可以省略 "value = " 看个人习惯
    @RequestMapping(value = "/login")
    public String login(){

        System.out.println("login ok....");
        return "login_ok";
    }

}
