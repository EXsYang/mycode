package com.hspedu.springboot.controller;

import com.hspedu.springboot.bean.Admin;
import com.hspedu.springboot.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author yangda
 * @create 2023-12-07-20:54
 * @description: 处理登录请求 完成测试
 */
@Controller
@Slf4j
public class AdminController {

    /**
     * 响应用户的登录请求
     * 这里因为请求类型不同因此可以区分开 @PostMapping("/login")
     * @param admin 用于接收浏览器提交过来的用户名和密码 springboot底层会自动完成封装
     * @param session 如果登录成功,将用户信息保存到session中 因此提供该参数
     * @param model 如果需要往request域中放入一些数据，需要用到该参数，比如可能会出现验证失败，
     *              这时就需要，往request域中放入一些错误信息msg,返回给浏览器进行显示
     */
    @PostMapping("/login")
    public String login(Admin admin, HttpSession session, Model model) {
        // 处理登录请求
        // 如果密码是"666",则登录成功，否则请求转发到登陆页面
        if (null != admin.getName() && !"".equals(admin.getName()) && StringUtils.hasText(admin.getPassword())) {
            if ("666".equals(admin.getPassword())) {
                //登录成功，重定向到manage.html
                System.out.println("登录成功,重定向到manage.html");

                //将登录用户保存到session
                session.setAttribute("loginAdmin",admin);

                //合法, 重定向到manage.html
                //请小伙伴回忆,java web, 不使用请求转发是防止刷新页面会重复提交
                //这里老师为什么是写的 manage.html, 因为这样可以更加明确的表示到哪个页面
                //manage.html表示要去找 方法的映射路径为 manage.html


                //"redirect:templates/manage.html"这个重定向相当于让浏览器
                // 直接访问 http://localhost:8080/templates/manage.html
                // ,但是这里访问不到,因为该thymeleaf模板文件 manage.html
                // ，不在静态资源访问路径下(静态资源访问路径指的是: 类路径下的 /static 、 /public 、 /resources 、 /META-INF/resources)
                // 所以访问不到
                // 需要进行服务器端来进行处理

                // return "redirect:templates/manage.html";

                // 这里进行重定向 注意 "/manage.html" 只是一个Controller的映射路径
                // ,并不是 templates/manage.html  template模板文件
                // 这里只是更加明确，下面的重定向要找的是manage.html模板文件而已
                // 重定向到浏览器地址栏再次发出请求，浏览器根据这里返回的
                // Location: http://localhost:8080/manage.html 信息
                // ,再次发出请求(get请求)找目标资源 重定向发出的请求是get请求
                return "redirect:/manage.html";


            } else {
                //登录失败，请求转发到adminLogin.html 重新登陆
                System.out.println("密码不正确,请求转发到登录页面");

                //不合法，就重新登录, 请求转发
                model.addAttribute("msg", "密码不正确");

                //注意:这里不用写所在的包，可以直接写里面的thymeleaf文件 adminLogin.html 的文件名而不用写包名 回顾视图解析器 有前缀和后缀
                return "adminLogin";
            }
        } else {
            //登录失败，用户名为空或者密码没内容 请求转发到adminLogin.html 重新登陆
            System.out.println("登录失败，用户名为空或者密码没内容,请求转发到登录页面");
            //不合法，就重新登录, 请求转发
            model.addAttribute("msg", "账号/用户错误");
            //注意:这里不用写所在的包，可以直接写里面的thymeleaf文件 adminLogin.html  的文件名而不用写包名 回顾视图解析器 有前缀和后缀
            return "adminLogin";
        }

    }

    //处理用户的请求到 manage.html
    @GetMapping("/manage.html")
    public String mainPage(Model model,HttpSession session){
        //这里老师暂时使用在方法验证，后面我们统一使用拦截器来验证

            log.info("进入mainPage()");
            //可以这里集合-模拟用户数据, 放入到request域中，并显示
            System.out.println("找到啦 manage.html");

            //模拟DB数据
            ArrayList<User> users = new ArrayList<>();
            users.add(new User(1, "关羽~", "666666", 20, "gy@sohu.com"));
            users.add(new User(2, "张飞", "666666", 30, "zf@sohu.com"));
            users.add(new User(3, "赵云", "666666", 22, "zy@sohu.com"));
            users.add(new User(4, "马超", "666666", 28, "mc@sohu.com"));
            users.add(new User(5, "黄忠", "666666", 50, "hz@sohu.com"));

            // 将数据放入到model中, 默认会放入到request域中
            model.addAttribute("users", users);

            //进行视图解析 (Thymeleaf请求转发,不会被拦截器拦截)
            //在这里thymeleaf进行视图解析
            //注意:这里不用写所在的包，可以直接写里面的thymeleaf文件 manage.html 的文件名manage 而不用写包名 回顾视图解析器 有前缀和后缀
            return "manage";  //这里才是我们的视图解析到 /templates/manage.html



        // if(null != session.getAttribute("loginAdmin")){
        //     // log.info("进入mainPage()");
        //     //可以这里集合-模拟用户数据, 放入到request域中，并显示
        //     System.out.println("找到啦 manage.html");
        //
        //     //模拟DB数据
        //     ArrayList<User> users = new ArrayList<>();
        //     users.add(new User(1, "关羽~", "666666", 20, "gy@sohu.com"));
        //     users.add(new User(2, "张飞", "666666", 30, "zf@sohu.com"));
        //     users.add(new User(3, "赵云", "666666", 22, "zy@sohu.com"));
        //     users.add(new User(4, "马超", "666666", 28, "mc@sohu.com"));
        //     users.add(new User(5, "黄忠", "666666", 50, "hz@sohu.com"));
        //
        //     // 将数据放入到model中, 默认会放入到request域中
        //     model.addAttribute("users", users);
        //
        //     //进行视图解析 (请求转发)
        //     //在这里thymeleaf进行视图解析
        //     //注意:这里不用写所在的包，可以直接写里面的thymeleaf文件 manage.html 的文件名manage 而不用写包名 回顾视图解析器 有前缀和后缀
        //     return "manage";  //这里才是我们的视图解析到 /templates/manage.html
        // }else {
        //     // 没有登陆过，返回登录页面
        //     model.addAttribute("msg","没有登陆过，请进行登录");
        //     return "adminLogin"; //请求转发到 adminLogin.html
        // }

    }


    @GetMapping("/test2")
    public String test2(){

        return "test2";
    }

}
