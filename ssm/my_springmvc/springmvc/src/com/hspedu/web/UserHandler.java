package com.hspedu.web;

import org.aopalliance.intercept.Invocation;
import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author yangda
 * @description:
 * @create 2023-09-25-19:28
 */
/*在多个类上标注'/user' 没事，不会像方法一样会报错 但是不建议   */
@RequestMapping(value = "/user")
@Controller
public class UserHandler {

    /**
     * 老韩解读 RequestMapping 可以修饰类和方法
     *   RequestMapping 可以指定请求方式method
     * 1. method=RequestMethod.POST: 表示请求buy目标方法必须是 post
     * 2. RequestMethod 四个常用选项 POST, GET, PUT, DELETE[后面我们会详解]
     * 3. SpringMVC 控制器  @RequestMapping默认支持GET和POST两种方式
     * <p>
     * 4. buy()方法请求的url: http://ip:port/工程路径/user/buy
     *
     * 5. @PostMapping(value = "/buy")等价 @RequestMapping(value = "/buy",method = RequestMethod.POST)
     * 简写方式一览: @GetMapping @PostMapping @PutMapping @DeleteMapping
     *
     * 使用Postman完成测试
     * 1)确认请求的地址 url:  http://localhost:8080/springmvc/user/buy
     * 2)请求的方式:  post
     * 3)请求的参数: 无
     * 4)确定Headers有没有特殊指定:  无
     */
    //@RequestMapping(value = "/buy",method = RequestMethod.POST)
    //@RequestMapping(value = "/buy")
    @PostMapping(value = "/buy")
    public String buy(){
        System.out.println("购买商品");
        return "success";
    }


    /**
     * 进入到商品列表页-使用Redis优化，到Redis查询,将商品列表页缓存到Redis
     * <p>
     * http://localhost:8080/goods/toList
     *
     * produces指定返回的字符串的数据格式为"text/html;charset=utf-8"，
     * sponseBody表示直接将数据写入到响应体中返回
     * 这两注解组合使用,可以直接将html模板页面以String格式 "text/html;charset=utf-8" 返回给前端
     */
    // @RequestMapping(value = "/toList",produces = "text/html;charset=utf-8")
    // @ResponseBody//不能少，否则会出问题
    // public String toList(Model model, User user,
    //                      HttpServletRequest request,
    //                      HttpServletResponse response) {
    //
    //     if (user == null) { //用户没有成功登录
    //         // 下面这两个返回语句在这里都变成了返回字符串到前端了
    //         // ，因为这里使用的注解的影响，使其没有被解析为thymeleaf模板的模板名称
    //         // return "login";
    //         // return "redirect:/login";
    //
    //         //返回登录页面的 HTML 内容
    //         // 如果您想通过 AJAX 调用这些服务，
    //         // 并需要在用户未登录时加载登录页面，
    //         // 可以在服务器端渲染登录页面的 HTML 并返回它。
    //         // 例如，您可以使用 ThymeleafViewResolver 来获取登录页面的 HTML 字符串，
    //         // 然后返回这个字符串。这需要您确保能够在这种情况下正确处理 HTML 内容：
    //         WebContext webContext = new WebContext(request, response, request.getServletContext(), request.getLocale());
    //         String html = thymeleafViewResolver.getTemplateEngine().process("login", webContext);
    //         return html;
    //         // Local variable 'html' is redundant 局部变量“html”是多余的
    //     }
    //
    //     //先到Redis获取页面-如果有，直接返回页面
    //     ValueOperations valueOperations = redisTemplate.opsForValue();
    //     String html = (String) valueOperations.get("goodsList");
    //     if (StringUtils.hasText(html)) {
    //         //如果html有内容就直接返回
    //         return html;
    //     }
    //
    //     //如果html没有内容继续往下走
    //
    //     //登录过, 将user信息放入到model,携带到下一个模板使用
    //     model.addAttribute("user", user);
    //     //将商品列表信息，放入到model,携带到下一个模板使用
    //     //这里会到DB获取商品列表需要的数据
    //     model.addAttribute("goodsList", goodsService.findGoodsVo());
    //
    //     //如果从redis没有获取到html页面，就手动渲染页面，并存入到redis
    //     //model.asMap() 就是取出前面放入到model中的数据"user","goodsList", 进行渲染需要
    //     //这里是一个常规的用法,直接拿来使用即可,获取web的上下文,用model中的数据,渲染html模板
    //     WebContext webContext =
    //             new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
    //
    //     //process:处理方法
    //     //使用thymeleaf引擎处理一个模板页,模板页的名称 名为"goodsList"，
    //     //模板页中构建页面需要的数据/内容是从webContext中获取的
    //     //上面从redis中获取的html页面如果没有内容,就在这里进行赋值
    //     //"goodsList"的名称不可以乱写,是已经存在的,
    //     // 或者你需要对其进行手动渲染的thymeleaf模板的名称!!!
    //     html = thymeleafViewResolver.getTemplateEngine().process("goodsList", webContext);
    //     if (StringUtils.hasText(html)) {
    //         //如果此时html模板有内容,就说明渲染成功了
    //         //将页面保存到redis,设置每60s更新一次,该页面60s失效,redis会清除该页面
    //         //因为也有可能会更新这个商品列表页面,对redis中的缓存页面进行更新
    //         valueOperations.set("goodsList", html, 60, TimeUnit.SECONDS);
    //     }
    //
    //     return html;
    // }


    /**
     * 老韩解读
     * 1. params="bookId" 表示请求该目标方法时，必须给一个bookId参数, 值没有限定
     * 2. search(String bookId): 表示请求目标方法时, 携带的bookId=100, 就会将请求携带的 bookId对应的
     * 值 100, 赋给 String bookId
     * 3. params = "bookId=100" 表示必须给一个bookId参数, 而且值必须是100
     *
     * 使用浏览器测试
     * http://localhost:8080/springmvc/user/find?bookId=100
     *
     * 使用Postman完成测试
     * 1)确认请求的地址 url:   http://localhost:8080/springmvc/user/find
     * 2)请求的方式: get/post
     * 3)请求的参数: bookId=100
     * 4)确定Headers有没有特殊指定:  无
     */
    @RequestMapping(value = "/find", params = "bookId=100", method = RequestMethod.GET)
    public String search(String bookId) {
    //public String search(String bookIdx) { //这里形参名要和params = "bookId=100"
    // 设置的bookId名称保持一致才可以取到值 否则为null
    //    System.out.println("查询书籍 bookId= " + bookIdx);

        System.out.println("查询书籍 bookId= " + bookId);
        return "success";
    }


    /**
     * 1. ?：匹配文件名中的一个字符
     * 2. *：匹配文件名中的任意字符
     * 3. **:匹配多层路径
     *
     * 要求: 可以配置  /user/message/aa, /user/message/aa/bb/cc
     * 1. @RequestMapping(value = "/message/**") /** 可以匹配多层路径
     *
     *
     * 使用Postman完成测试
     * 1)确认请求的地址 url:   http://localhost:8080/springmvc/user/message/aa/bb/cc
     * 2)请求的方式: get/post
     * 3)请求的参数: 无
     * 4)确定Headers有没有特殊指定: 无
     */

    //@RequestMapping(value = "/message/ind?")
    //@RequestMapping(value = "/message/*")
    @RequestMapping(value = "/message/**")
    public String im() {
        System.out.println("发送消息");
        return "success";
    }

    /**
     * Variable:变量
     * 路径变量 @PathVariable 用法
     * 1、若方法参数名称和需要绑定的url中变量名称一致时,可以简写:
     * @RequestMapping("/getUser/{name}")       {name} 表示传过来的路径变量/路径占位符 里面的值是程序员指定的
     *     public User getUser(@PathVariable String name){
     *         return userService.selectUser(name);
     *     }
     * 2、若方法参数名称和需要绑定的url中变量名称不一致时，写成:
     * @RequestMapping("/getUserById/{name}")
     *     public User getUser(@PathVariable("name") String userName){
     *         return userService.selectUser(userName);
     *     }
     */

    /**
     * 使用Postman完成测试
     * 1)确认请求的地址 url:   http://localhost:8080/springmvc/user/reg/yangda/1000
     * 注意：路径参数直接使用的是斜杠分割的 没有使用问号 ?
     * 2)请求的方式: get/post
     * 3)请求的参数: 已通过路径参数指定 不用再带参数
     * 4)确定Headers有没有特殊指定:  无
     */
    //要求： 我们希望目标方法获取到 username 和 userid, value="/xx/{username}" - @PathVariable("username")..
    //前端页面: <a href="user/reg/kristina/300">占位符的演示</a>
    //(value = "/reg/{username}/{userid}"): 表示kristina->{username} 300=>{userid}
    // 最终路径上的变量{username} 会赋给String name;{userid} 会赋给String id
    @RequestMapping(value = "/reg/{username}/{userid}")
    //RequestMapping(value 里的 {username} 和 形参 PathVariable("username")
    // 一定要保持一致，否则是不能匹配成功的
    public String register(@PathVariable("username") String name,
                           @PathVariable("userid") String id) {
        System.out.println("接收到参数: " + "username= " + name + "   " + "usreid= " + id);
        return "success";
    }



    /**
     *
     * 使用Postman完成测试
     * 1)确认请求的地址 url:   http://localhost:8080/springmvc/user/hi
     * 2)请求的方式: get/post
     * 3)请求的参数: 无
     * 4)确定Headers有没有特殊指定:  无
     */
    @RequestMapping(value = "/hi")
    public String hi() {
        System.out.println("hi");
        return "success";
    }

    //映射的 URL, 不能重复
    // 否则在重新发布项目时就会报错 already:已经存在
    // to { [/user/hi]}: There is already 'userHandler' bean method
    //@RequestMapping(value = "/hi")
    //public String hi2() {
    //    System.out.println("hi");
    //    return "success";
    //}

    /**
     * hello3(String email)： 如果我们的请求参数有 email=xx, 就会将传递的值,赋给String email
     * ,要求名称保持一致, 如果不一致，那么接收不到数据, 但不会报错，而是null
     * @param email
     *
     * 使用Postman完成测试
     * 1)确认请求的地址 url:   http://localhost:8080/springmvc/user/hello3
     * 2)请求的方式: get
     * 3)请求的参数: email=xx@sohu.com
     * 4)确定Headers有没有特殊指定:  无
     */
    @GetMapping(value = "/hello3")
    public String hello3(String email) {
        System.out.println("hello3 " + email);
        return "success";
    }

    /*
        注意 1.即使是在不同的类中定义了相同的路径也不可以 启动tomcat时会报错
                Invocation of init method failed; nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'bookHandler' method
                com.hspedu.web.rest.BookHandler#successGenecal()
                to { [/user/success]}: There is already 'userHandler' bean method
                com.hspedu.web.UserHandler#successGenecal() mapped.
            2. 设置的value 中的路径如果相同 但是 method 不同,也可以，不会报错 如下面这两种设置
                 //@PutMapping(value = "/book/{id}")
                 //@DeleteMapping(value = "/book/{id}")
    */

    //下面这个路径在com.hspedu.web.rest.BookHandler 中也定义了 会报错
    //@RequestMapping(value = "/success")
    //public String successGenecal() {
    //    return "success"; //由该方法 转发到 success.jsp 页面
    //}
}
