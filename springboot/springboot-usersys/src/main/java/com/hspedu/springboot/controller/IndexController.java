package com.hspedu.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @create 2023-12-07-15:39
 * @description: 默认进入登录页面
 * 注意
 * 我将 html 文件放到 templates/ 目录下, 该目录, 不能直接访问
 * ,因为这个目录不是springboot默认的静态资源访问路径
 * , adminLogin.html需要在服务器端进行访问才可以访问到 请求转发，重定向可以访问到吗?
 *
 * @ConfigurationProperties(prefix = "spring.thymeleaf")
 * public class ThymeleafProperties {
 * 	private static final Charset DEFAULT_ENCODING = StandardCharsets.UTF_8;
 * 	//这里指定了要将thymeleaf文件要放在那个目录下
 * 	public static final String DEFAULT_PREFIX = "classpath:/templates/";
 * 	//这里指定了thymeleaf文件的后缀
 * 	public static final String DEFAULT_SUFFIX = ".html";
 */
@Controller
public class IndexController {

    /**
     * 1. 进入登录页
     * 2. value = {"/","/login"} 表示 GET 请求 / 和 /login 都映射到 login()
     * 3. 浏览器地址栏发出的请求默认是get请求
     */
    @GetMapping(value = {"/","/login"})
    public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("接收到了浏览器发出的请求的URI/服务器端请求转发的URI=" + request.getRequestURI());

        /**
         * 为什么这里没有配置视图解析器，可以找到 thymeleaf/adminLogin.html文件呢?
         * 1. 因为我们引入了starter-thymeleaf
         * 2. 这里就会直接使用视图解析到 thymeleaf下的模板文件adminLogin.html
         * 3. starter-thymeleaf它本身就进行了视图解析
         *
         */

        //手动进行页面转发操作,会被拦截器拦截到
        // System.out.println("手动进行页面转发到/templates/adminLogin.html的操作");
        // request.getRequestDispatcher("/templates/adminLogin.html").forward(request,response);
        // 如果配置的时SpringMVC 默认的视图解析器InternalResourceViewResolver
        // ,也会被拦截到,但是如果配置的是spring-boot-starter-thymeleaf ,Thymeleaf自带的视图解析器
        // 就不会被拦截器拦截！！
        /**
         * 为什么传统方式配置的视图解析器进行视图解析后，在底层请求转发后可以被拦截器拦截到，
         * 而通过 spring-boot-starter-thymeleaf 自动配置 ,Thymeleaf自带的视图解析器就不会被拦截器拦截！！
         * 主要的原因在于 Thymeleaf 的工作机制与传统的视图解析器有所不同。
         * 请求转发 vs 模板渲染： 传统的视图解析器（如 InternalResourceViewResolver）会使用请求转发来将请求转发到特定的 JSP 或 HTML 页面。这时，拦截器可以捕获到请求的转发并执行相应的操作。
         * 与此不同，Thymeleaf 作为一个模板引擎，它的工作方式是将模板渲染成最终的 HTML 输出，而不涉及到请求转发。Thymeleaf 视图解析器只负责解析视图名称对应的模板，并将渲染结果返回给客户端。
         * 生命周期阶段： 拦截器的执行生命周期在请求的处理前和处理后。对于传统的视图解析器，请求转发是在处理阶段进行的，因此拦截器可以捕获到。然而，Thymeleaf 的工作是在请求处理之后的渲染阶段，此时拦截器已经完成了它的生命周期。
         * 由于这些差异，拦截器在 Thymeleaf 视图渲染阶段没有机会进行拦截。Thymeleaf 的设计目标是提供灵活且强大的模板渲染功能，而不一定与传统的请求转发方式完全一致。
         * 如果你需要在 Thymeleaf 视图渲染之前或之后执行一些操作，可以考虑使用 Thymeleaf 提供的一些扩展机制，如 ITemplateResolver、IProcessor 等。这些扩展点可以在模板渲染的不同阶段添加自定义逻辑。
         */

        /**
         * 当在Spring Boot项目中引入了spring-boot-starter-thymeleaf依赖后，
         * 控制器层返回String类型的值会默认被解释为要渲染的HTML模板页面的名称。
         * 这是因为引入了Thymeleaf依赖后，Spring Boot会自动配置Thymeleaf模板引擎及其组件，
         * 如模板解析器和视图解析器。
         *
         * 自动配置机制：
         * - Spring Boot通过spring-boot-starter-thymeleaf自动配置Thymeleaf模板引擎。
         * - 当控制器返回一个字符串，如 "index"，Spring MVC将这个字符串视为视图名称。
         * - 视图解析器随后会在src/main/resources/templates目录下寻找名为index.html的Thymeleaf模板文件。
         *
         * 控制器返回字符串的处理：
         * - 在MVC架构中，控制器的职责是处理请求并返回视图名称。
         * - Thymeleaf模板引擎会将视图名称与模板文件关联，然后渲染成HTML格式响应客户端。
         *
         * 如果希望返回纯字符串而非视图：
         * - 使用@ResponseBody注解：使方法返回的字符串直接作为HTTP响应体返回，不经过视图解析器。
         * - 使用@RestController注解：结合@Controller和@ResponseBody的功能，适用于REST API，
         *   自动处理所有方法返回值作为响应体。
         */




        //这里不用写所在的包，可以直接写里面的thymeleaf文件 adminLogin.html  的文件名而不用写包名 回顾视图解析器 有前缀和后缀
        return "adminLogin";
    }



}
