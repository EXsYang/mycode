package com.hspedu.springboot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

/**
 * @author yangda
 * @create 2023-12-11-22:39
 * @description:
 * 注意:全局异常优先级 > 默认异常处理机制
 * 所以如果配置了全局异常处理器，全局异常处理器就会优先处理
 * 如果发生了算数异常 这时不在进入到500.html 而是进入到 global.html
 *
 * @ControllerAdvice 该注解标识一个全局异常处理器/对象
 * 会被注入到spring ioc容器中

 * 全局异常底层是 =》 ExceptionHandlerExceptionResolver#doResolveHandlerMethodException() 进行支持的
 *
 * 测试
 * http://localhost:8080/err
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    //1.编写方法，处理指定异常，比如我们要处理算数异常和空指针异常，可以指定多个异常
    //2.这里要处理的异常，由程序员来指定
    //3.Exception e: 表示异常发生后传递过来的异常对象
    //4.Model model: 可以将我们的异常信息，放入到model,并传递给显示页面
    /**
     * Exceptions handled by the annotated method. If empty, will default to any
     * exceptions listed in the method argument list.
     * 如果value为空，即没有指定要处理哪些异常，就会处理所有的异常
     */
    // @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class,AccessException.class})
    public String handlerAritException(Exception e, Model model, HandlerMethod handlerMethod){

        log.info("异常信息={}",e.getMessage());
        //将发生的异常信息，放入到model(即默认会放入到request域),可以在错误页面取出显示
        model.addAttribute("msg",e.getMessage());

        //得到异常发生的位置/异常发生的方法是哪个
        log.info("异常发生的位置/方法={}",handlerMethod.getMethod());

        //可以理解成这里的全局异常处理过程也进行了请求转发，但是不是传统请求转发，因此不会被拦截器拦截到
        // gpt:你理解得非常正确。在异常处理过程中，确实进行了一种形式的请求转发，但这与传统的请求转发有所不同。
        // 在全局异常处理器中，异常处理的流程可能涉及到定向到特定的错误页面，而这个定向过程类似于请求转发。然而，这并不是传统的 Servlet 请求转发，而是在服务器端将模板渲染成 HTML，然后将 HTML 直接返回给客户端。
        // 由于这个过程不经过控制器的正常请求处理流程，拦截器不会对其进行拦截。拦截器主要作用于正常的请求处理流程，而全局异常处理器属于异常处理的特殊情况。这是为了确保异常处理过程不会被正常请求处理流程中的拦截器拦截，从而保证异常处理的顺利进行。
        // return "error/global"; //视图地址 可以
        return "/error/global"; //视图地址 也可以

        /**
         * 路径问题： 在返回视图名时，使用 return "error/global";
         * ，如果 error 文件夹是在 src/main/resources/templates 目录下，
         * 那么这个路径是正确的。如果不是，请根据实际的项目目录结构来调整。
         *
         * 在Spring Boot中，视图解析器会根据配置的前缀和后缀来解析视图名。
         * 在你的代码中，如果视图解析器的前缀是 /，那么这两个返回语句是等价的。
         * 这是因为在视图解析的时候，会自动加上前缀和后缀。
         *
         * return "error/global";
         * 这个语句表示视图名是 "error/global"，而具体的前缀和后缀会由视图解析器自动添加。
         *
         * return "/error/global";
         * 这个语句同样表示视图名是 "error/global"，因为前缀已经包含在视图名中了。
         * 在这两种情况下，Spring Boot 会根据配置的视图解析器来解析最终的视图路径。
         *
         * 通常来说，不需要在视图名前面加上 "/"，因为视图解析器会自动处理。
         * 在一些特殊情况下，可能需要手动指定前缀，但一般情况下，
         * Spring Boot的自动配置能够很好地处理这些细节。
         */
    }





}
