package com.hspedu.springboot.controller;

import com.hspedu.springboot.bean.Monster;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-12-04-20:47
 * @description: 接收参数相关注解应用实例 演示各种方式提交数据/参数给服务器，服务器如何使用注解接收
 */
@RestController
// @Slf4j //用该注解进行输出打印信息也可以
public class ParameterController {
    /**
     * /monster/{id}/{name} 解读
     * 1. /monster/{id}/{name} 构成完整请求路径
     * 2. {id} {name} 就是占位变量
     * 3. @PathVariable("name"): 这里 name 和{name} 命名保持一致
     * 4. String name_ 这里自定义，老师故意这样写下
     * 5. @PathVariable Map<String, String> map 把所有传递的值传入 map
     * 6. 可以看下@PathVariable 源码
     * 7. 路径变量默认必须提供参数   required = true(default)
     * 即url中必须提供对应的参数
     */


    // @GetMapping("/monster/{name}")
    // href="/monster/king" 前端是这样提供的参数, 并没有提供id 可以不会报错 只不过id=null
    // public String pathVariable(@PathVariable(value = "id",required = false) Integer id,
    @GetMapping("/monster/{id}/{name}")
    public String pathVariable(@PathVariable(value = "id", required = false) Integer id,
                               @PathVariable(value = "name", required = false) String name_,
                               @PathVariable Map<String, String> map) {

        System.out.println("id= " + id);
        // id= 100
        System.out.println("name_= " + name_);
        // name_= king
        System.out.println("map= " + map);
        // map= {id=100, name=king}  //map 的 key 取决于=》 @PathVariable(value = "id")
        return "success";
    }


    /**
     * @RequestHeader("HOST") String host: 获取http请求头的host信息
     * @RequestHeader("host") "host" 这里value的值大小写无所谓
     * @RequestHeader Map<String, String> header: 获取http请求的所有信息
     * @RequestHeader("accept") String accept: 形参的顺序无所谓 回忆springmvc底层实现机制 按照类型匹配的 同时根据形参列表的顺序依次填入实参的
     */
    @GetMapping("/requestHeader")
    public String requestHeader(@RequestHeader("HOST") String host,
                                @RequestHeader Map<String, String> header,
                                @RequestHeader("accept") String accept) {

        System.out.println("host= " + host); // ip+port
        //host= localhost:8080
        System.out.println("header= " + header);
        // header= {host=localhost:8080, user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:120.0) Gecko/20100101 Firefox/120.0, accept=text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8, accept-language=zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2, accept-encoding=gzip, deflate, br, connection=keep-alive, referer=http://localhost:8080/index.html, upgrade-insecure-requests=1, sec-fetch-dest=document, sec-fetch-mode=navigate, sec-fetch-site=same-origin, sec-fetch-user=?1}
        System.out.println("accept= " + accept);
        // accept= text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8

        return "success";
    }


    /**
     * 按照字段名获取请求参数：@RequestParam
     * @param username @RequestParam(value = "name")  对应表单提交数据的字段的名字保持一致 name
     * @param fruits   如果同一个参数名有多个参数值,可以使用List集合接收
     * @param params   获取到全部的参数使用Map集合  @RequestParam Map<String,String> params,
     *                 但是 fruits 有多个参数值的参数 只可以获取到第一个值,
     *                 原因是Map集合的key是唯一的
     */
    @GetMapping("/hi")
    // @PostMapping("/hi2")
    public String hi(@RequestParam(value = "name", required = false) String username,
                     @RequestParam("fruit") List<String> fruits,
                     @RequestParam Map<String, String> params) {


        System.out.println("username= " + username);
        // username= 韩顺平
        System.out.println("fruits= " + fruits);
        // fruits= [apple, pear]
        System.out.println("params= " + params);
        // params= {name=韩顺平, fruit=apple}

        return "success";
    }


    /**
     * 我们自己在在浏览器创建/设置对应的 cookie 测试 F12 存储-本地存储-添加+cookie
     * 说明
     * 1. value = "cookie_key" 表示接收名字为 cookie_key 的 cookie
     * 2. 如果浏览器携带来对应的 cookie , 那么 后面的参数是 String ,则接收到的是对应对 value
     * 3. 后面的参数是 Cookie ,则接收到的是封装好的对应的 cookie
     * 4. 目标方法之所以可以 传入参数  HttpServletRequest request, 是因为在springmvc底层
     *    进行分发处理时, 会根据参数类型进行解析, 然后填入对应的实参，同时参数的顺序无所谓
     */
    @GetMapping("/cookie")
    public String cookie(@CookieValue(value = "cookie_key",required = false) String cookie_value,
                         HttpServletRequest request,
                         @CookieValue(value = "username",required = false) Cookie cookie ) {
        System.out.println("cookie_value= " + cookie_value);
        if (cookie != null){
            System.out.println("cookie=" + cookie.getName() + "--" + cookie.getValue());
            System.out.println("cookie=" + cookie);
        }

        System.out.println("------------");

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie1 : cookies) {
            System.out.println("原生servlet api 获取 cookie:" +
                    "" + " cookie.getName=" + cookie1.getName() + " |  cookie.getValue()=" + cookie1.getValue());
        }
        return "success";
    }


    /**
     * @RequestBody 是整体取出 Post 请求内容
     *
     *  @RequestBody注解,用来接收json格式数据
     *  1.我们的前端如果是以json格式来发送添加信息furn,那么我们后端需要使用@RequestBody注解
     *  ,才能将数据封装到对应的bean中
     *  2.如果前端是以表单形式提交,则不需要使用@RequestBody注解
     */
    @PostMapping("/save")
    public String postMethod(@RequestBody String content){
        System.out.println("content= " + content);
        return "success";
    }


    /**
     * 自定义参数封装
     * 演示自定义对象参数使用，完成自动封装，类型转换  从save.html发送请求进行测试
     * 形参位置直接写 Monster monster, 就可以，底层会自动完成封装
     * 注意前端提交参数的参数名 必须和对象的属性名保持一致 否则封装失败
     * 如果有级联 就按照 car.name car.price 的方式进行数据的提交 否则封装失败
     *
     *  注意:1.如果前端使用`Postman的Post方式提交数据，同时指定params提交`
     *  ，在不使用@RequestBody的情况下，即形参位置没有使用任何注解，
     *  也可以将params正常的封装到下面方法形参的Monster对象中
     // 下面这种方式接收参数是接收的表单提交的数据,完成自动封装
     // 在springboot 接收参数相关注解 自定义对象参数-自动封装时讲过
     // 下面这种方式接收参数  2.如果前端是在`地址栏的参数位置`提交的数据,如:Post请求 http://localhost:9090/save?id=101&name=喜喜台灯&maker=xx之家&price=30.3&sales=3&stock=200
     // 也可以完成封装
     *  即：如果前端是以form-data表单形式/或者是以请求参数parameters提交了,则不需要@RequestBody,才会进行对象封装，
     *  同时保证http的请求头的content-type是对应的
     *
     * 注意：这种封装对象的方式，不需要把相关的对象注入到容器中，会自动地完成封装
     *

     * 自定义参数封装前端可以使用的请求方式
     * 1.表单提交 (form-data):
     * 当数据以表单形式提交时，Spring MVC会将表单的字段映射到 Monster 对象的属性上。这需要表单的字段名称和 Monster 类的属性名称相匹配。
     * 例如，如果表单有 name 和 age 字段，Spring MVC会尝试将这些字段的值分别设置到 Monster 对象的 name 和 age 属性上。
     *
     * 2.URL请求参数:
     * (1)类似地，当数据通过URL参数（如 http://localhost:8080/saveMonster?name=牛魔王&age=22）传递时，参数的名称应与 Monster 类的属性名称相匹配。
     * Spring MVC会自动解析URL中的查询参数，并将它们映射到 Monster 对象的相应属性上。
     * (2)基本数据类型也可以完成封装 如:http://localhost:10004/member/get?id=5
     *     @GetMapping("/member/get")
     *     public Result getMemberById(Long id){}
     * 上面(2)中的形式等价于指定params
     *
     * 3.ajax请求的data中,要求前后端属性名一致
     *  $.ajax({
     *             url: "/login/doLogin",
     *             type: "POST",
     *             data: {
     *                 mobile: $("#mobile").val(),
     *                 password: password
     *             },
     *
     *
     * @Data
     * public class LoginVo {
     *
     *
     *     private String mobile;
     *     private String password;
     *
     * }
     *
     *     @RequestMapping("/doLogin")
     *     @ResponseBody //加上该注解，就不会被解析为视图了。即不会按照templates的视图，而是返回json数据
     *     public RespBean doLogin(LoginVo loginVo){}
     *-------------------------
     * 与自定义类型参数封装(方法上不使用任何注解)的对比
     * Spring 框架中的 @RequestParam 注解和不使用任何注解的方法形参都可以用来接收 URL 参数和 application/x-www-form-urlencoded 格式的 form 表单提交的参数。但这两种方式在功能和使用场景上有所区别：
     *
     * 使用 @RequestParam 注解：
     *
     * 明确指定：你可以明确指定要获取的参数名称。例如，@RequestParam("name") String userName 会获取名为 name 的参数并将其值绑定到 userName 变量。
     * 可选和默认值：@RequestParam 允许你设置参数为可选（required=false），并为参数指定默认值（defaultValue="someValue"）。
     * 更多控制权：@RequestParam 提供了更多的控制权，比如处理多值参数（如同一个参数名出现多次）。
     * 不使用注解的方法形参：
     *
     * 隐式名称匹配：如果方法的参数名和请求参数名相同，Spring 会自动将请求参数的值绑定到对应的方法参数。例如，方法参数为 String name 会自动匹配请求中的 name 参数。
     * 简洁：对于简单的情况，不使用注解可以使代码更简洁。
     * 灵活性较低：不使用注解意味着你不能指定默认值，也不能处理可选参数的情况。
     * 补充说明：
     *
     * 在使用不带注解的形参接收数据时，Spring 依赖于参数名。但是，如果你的项目经过了混淆或使用了不保存参数名的编译器设置，这可能会导致问题。为了确保准确性，建议使用 @RequestParam。
     * @RequestParam 适用于处理简单类型的数据（如 String, int, Enum 等），而对于复杂类型（如自定义的 Java Bean），通常推荐使用 @ModelAttribute 来处理。
     * 在选择使用哪种方式时，应该考虑你的具体需求。如果需要更多的控制或处理更复杂的情况，@RequestParam 是更好的选择。如果只是简单地接收数据，直接使用方法参数可能更方便。
     *
     *
     */
    @PostMapping("/saveMonster")
    public String saveMonster(Monster monster){

        System.out.println("monster= " + monster);
        return "success";
    }


}
