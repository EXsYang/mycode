package com.hspedu.web.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yangda
 * @create 2023-10-14-22:46
 * @description:
 * 异常处理的优先级
 * 局部异常 > 全局异常 > SimpleMappingExceptionResolver > tomcat 默认机制
 */
@Controller
public class MyExceptionHandler {

    /**
     * 1. 编写方法，模拟异常, 算术异常
     * 2. 如果我们不做异常处理，是由tomcat默认页面显示
     * @param num
     * @return
     */
    @RequestMapping(value = "/testException01")
    public String test01(Integer num){
        int i = 9/num;
        return "success";
    }

    /**
     * 1.localException 方法处理局部异常
     * 2.这里老师处理ArithmeticException.class,NullPointerException.class
     * 3.Exception ex: 生成的异常对象，会传递给ex,通过ex可以得到相关的新信息
     *   ,这里程序员可以加入自己的业务逻辑
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String localException(Exception ex, HttpServletRequest request){
        // 如果局部异常匹配到该方法@ExceptionHandler中的异常类型，
        // 会进入到ExceptionHandlerMethodResolver.java
        // 的resolveMethodByExceptionType() 方法
        System.out.println("局部异常信息是="+ex.getMessage());
        //如何将异常信息带到下一个页面
        request.setAttribute("reason",ex.getMessage());
        return "exception_mes";
    }

    @RequestMapping(value = "/testGlobalException")
    public String global(){
        //老韩解读
        //1. 这里我们模拟了一个异常 NumberFormatException
        //2. 该异常没有在局部异常处理，按照异常处理机制，就会交给全局异常处理类处理
        int num = Integer.parseInt("hello");
        return "success";
    }


    @RequestMapping(value = "/testGlobalException02")
    public void test02(){
        //老韩解读
        //1. 这里我们直接抛出一个异常 AgeException
        throw new AgeException("年龄必须在1-120之间!!!");
    }

    @RequestMapping(value = "/testException03")
    public String test03(){
        int[] arr = new int[]{3,9,10,190};
        //抛出一个数组越界的异常 ArrayIndexOutOfBoundsException
        System.out.println(arr[90]);
        return "success";
    }

    //如果发生了没有归类的异常， 可以给出统一提示页面
    @RequestMapping(value = "/testException04")
    public String test04(){
        String str = "hello";
        //这里会抛出 StringIndexOutOfBoundsException
        // str.charAt(10)：返回 char指定索引处的值。
        char c = str.charAt(10);
        return "success";
    }


}
