package com.hspedu.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangda
 * @create 2023-10-15-13:49
 * @description:
 * 异常处理的优先级
 * 局部异常 > 全局异常 > SimpleMappingExceptionResolver > tomcat 默认机制
 *
 * 如果类上标注了@ControllerAdvice,就是一个全局异常处理类
 * advice:建议;意见;忠告;劝告
 */
@ControllerAdvice
public class MyGlobalException {


    /**
     * 老师解读
     * 1. 全局异常就不管是哪个Handler抛出的异常，都可以捕获 , @ExceptionHandler({异常类型})
     * 2. 这里老师处理的全局异常是NumberFormatException.class,ClassCastException.class
     * 3. Exception ex 接收抛出的异常对象
     *
     * @return
     */
    @ExceptionHandler({NumberFormatException.class,ClassCastException.class,AgeException.class})
    public String globalException(Exception ex, HttpServletRequest request){
        System.out.println("全局异常处理-" + ex.getMessage());
        //如何将异常的信息带到下一个页面.
        request.setAttribute("reason", ex.getMessage());
        return "exception_mes";
    }

}
