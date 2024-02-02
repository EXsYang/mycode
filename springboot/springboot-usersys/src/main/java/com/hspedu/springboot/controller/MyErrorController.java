package com.hspedu.springboot.controller;

import com.hspedu.springboot.exception.AccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author yangda
 * @create 2023-12-11-20:41
 * @description: 测试自定义异常页面机制 是按照状态码进行解析然后再进行匹配xxx.html的
 *
 * 处理自定义异常时如果不作任何配置，仍然也会按照默认机制走 即按照状态码进行匹配
 * 先走
 * ExceptionHandlerExceptionResolver#doResolveHandlerMethodException()
 * 再走
 * DefaultErrorViewResolver#resolve(String viewName, Map<String, Object> model)
 *
 * 如果将自定义异常加入到全局异常处理机制中， @ExceptionHandler({ArithmeticException.class,NullPointerException.class,AccessException.class})
 * 先走
 * ExceptionHandlerExceptionResolver#doResolveHandlerMethodException()
 * 再走 全局异常处理这时不会走默认的异常处理机制即不会走DefaultErrorViewResolver按照状态码进行匹配
 * 而是按照java的匹配机制，看是否有处理该异常的方法，有就会走该方法，如下所示:
 * GlobalExceptionHandler#handlerAritException(Exception e, Model model, HandlerMethod handlerMethod)
 *
 *
 */
@Controller
public class MyErrorController {

    //模拟一个服务器内部错误500
    //错误信息可以在500.html 取出来
    @GetMapping("/err")
    public String err(){

        //测试 这里发生错误默认会找 /error 路径吗？还是会找 error/404 或者 error/4xx
        //这里不会找 /error ,因为在全局异常处理了这个算数异常，会走全局异常处理机制，
        //在GlobalExceptionHandler#handlerAritException()中，最后会转发到error/global视图，
        //注意:可以理解成这里的全局异常处理过程也进行了请求转发，但是不是传统请求转发，因此不会被拦截器拦截到

        //这里服务器抛出500服务器内部错误 springboot底层会请求转发到 /error 进行异常机制的处理
        int i = 10 / 0;


        return "manage";

    }

    //这里配置的是Post方式请求 /err2 ，会进入到默认的异常处理机制，springboot底层进行请求转发到/error
    // 如果使用get方式来请求 /err2 , 这样就会出现405客户端错误
    @PostMapping("/err2")
    public String err2(){
        // 走默认的异常处理机制才会被springboot底层进行请求转发到/error
        // 这个/error会被拦截器拦截到preHandle拦截到请求的URI=/error

        // 如果在全局异常处理中处理过这个异常，则pringboot底层不会进行请求转发到/error
        // 而是走自己配置的处理异常的方法
        return "manage";

    }

    /**
     * 编写方法 模拟AccessException
     * 请求地址 http://localhost:8080/err3?name=xxx
     */
    @GetMapping("/err3")
    public String err3(String name){
        //如果用户不是tom就禁止访问-模拟
        if (!"tom".equals(name)){
            //如果只是自定义了一个异常，没有进行任何配置，还是会走
            //默认的异常处理机制 DefaultErrorViewResolver，会根据 AccessException 注解的
            // @ResponseStatus(value = HttpStatus.FORBIDDEN) FORBIDDEN的状态码403
            // 匹配404.html、4xx.html ,如果value是HttpStatus.BAD_GATEWAY
            // 则会匹配500.html、5xx.html
            // throw new AccessException();
            //如果走的是全局异常，会把这个信息取出来
            throw new AccessException("自定义的AccessException...");
        }
        System.out.println("name=tom 进行Thymeleaf视图解析(请求转发,不会被拦截器拦截)");

        return "manage"; //视图地址,Thymeleaf请求转发(不会被拦截器拦截),转发到的页面没有数据
        // return "redirect:/manage.html"; //这个重定向会显示数据
        // return "forward:/manage.html"; //这个会被拦截,请求的是Controller的映射路径

        //preHandle拦截到请求的URL=http://localhost:8080/templates/error/manage.html
        //之所以访问不到是因为，该路径并不是默认的静态资源访问路径
        // return "forward:/templates/error/manage.html";

        /**
         * return "forward:/manage.html"; 这个请求转发为什么会被拦截，而直接return "manage";不会被拦截?
         * ChatGPT
         * return "forward:/manage.html"; 与 return "manage"; 之所以表现不同，是因为它们分别使用了不同的方式进行请求转发。
         *
         * return "manage";： 这是 Thymeleaf 的默认请求转发方式。Thymeleaf 会将这个视图名解析为模板路径，并在服务端将模板渲染成 HTML，然后将 HTML 直接返回给客户端。这个过程是在异常处理的上下文中进行的，不会经过拦截器，因为异常处理是一个特殊的流程。
         *
         * return "forward:/manage.html";： 这是 Spring MVC 的传统请求转发方式。它会将请求转发到指定的 URL（而不是 Thymeleaf 的模板路径），在这里是 /manage.html。在正常的请求处理流程中，拦截器可能会拦截请求并进行处理。如果你的拦截器配置了路径匹配规则，可能会阻止这个请求转发。
         *
         * 由于异常处理是一个特殊的上下文，与正常请求处理流程有所不同，return "forward:/manage.html"; 在异常处理的上下文中会被拦截器拦截。如果你想要在异常处理流程中进行请求转发，你可以使用 Thymeleaf 的方式，即 return "manage";，这样就不会被拦截器拦截。
         */
    }
}
