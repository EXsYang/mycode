package com.hspedu.web;

import org.aopalliance.intercept.Invocation;
import org.jboss.logging.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
