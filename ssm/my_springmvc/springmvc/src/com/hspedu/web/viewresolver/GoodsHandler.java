package com.hspedu.web.viewresolver;

import com.hspedu.web.homework.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yangda
 * @description: 演示视图解析器
 * @create 2023-09-28-13:03
 *
 */
@RequestMapping("/goods")
@Controller
public class GoodsHandler {


    // @RequestMapping("/hi")
    @RequestMapping("/hello")
    // @RequestMapping("/1.jpg")
    @ResponseBody
    public String hi(){
        return "hi:):)";
    }

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


    @RequestMapping(value = "/login1")
    public void doLogin(User user){
        System.out.println("doLogin...");

        // 如果没有返回值 默认视图解析器会 默认按照 前缀 goods/login1 后缀 进行拼接
        // HTTP Status 404 - /springmvc/WEB-INF/pages/goods/login1.jsp

    }

    @RequestMapping(value = "/buy")
    public String buy() {

        System.out.println("=======buy()=====");

        //默认返回方式是请求转发
        return "hspView";
    }

    /**
     * 演示直接指定要请求转发的或者是重定向的页面
     * @return
     */
    @RequestMapping(value = "/order")
    public String order() {
        System.out.println("=======order()=====");
        //默认返回方式是请求转发
        //return "hspView";

        //在目标方法直接指定重定向或转发的 url 地址
        // 要求此次请求使用/走的是默认的视图解析器 InternalResourceViewResolver
        // 使用自定义的视图解析器 会解析不成功 找不到该视图,但是如果容器中还配置了默认的
        // 视图解析器 就会再走一圈默认的视图解析器 ，最后可成功进行转发/重定向
        // 结论：该种方式适合 容器中配置了默认的视图解析器时才会调用

        // 多个视图解析器执行流程注意事项：
        // 如果先走的自定义的视图解析器，解析失败了，会再走默认的视图解析器
        // 如果先走的默认的视图解析器，解析失败了，就不会再走自定义的视图解析器了
        // 而是直接给浏览器返回404

        // 使用关键字直接请求转发或是重定向 ,写的是路径 而不是视图名
        //请求转发到 /WEB-INF/pages/my_view.jsp
        //下面的 /WEB-INF/pages/my_view.jsp 被解析成 /springmvc/WEB-INF/pages/my_view.jsp
        return "forward:/WEB-INF/pages/my_view.jsp";
        //return "forward:/aaa/bbb/ok.jsp";

        //直接指定要重定向的页面
        //1. 对于重定向来说，不能重定向到 /WEB-INF/ 目录下
        //2. redirect 关键字，表示进行重定向
        //3. /login.jsp 在服务器解析 /springmvc/login.jsp
        //return "redirect:/login.jsp";
        //return "redirect:/aaa/bbb/ok.jsp";

        // /WEB-INF/pages/my_view.jsp 被解析 /springmvc/WEB-INF/pages/my_view.jsp
        //return "redirect:/WEB-INF/pages/my_view.jsp";
    }




}