package com.hspedu.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yangda
 * @create 2023-12-12-13:32
 * @description:
 * 自定义异常 访问异常
 * //自定义异常，也会根据状态码，匹配到对应的错误页面显示
 *
 * 自定义异常在javaSE讲过 一般情况下，我们自定义异常是继承 RuntimeException
 * value = HttpStatus.FORBIDDEN: 表示发生AccessException异常，我们通过http协议返回状态码403
 * FORBIDDEN(403, Series.CLIENT_ERROR, "Forbidden"),
 * 这个状态码和自定义异常的对应关系是由程序员来决定的【尽量合理来设置】
 *
 * BAD_GATEWAY(502, Series.SERVER_ERROR, "Bad Gateway"),
 *
 *
 * 1. 基本用途
 * 注解 @ResponseStatus 主要用于指定一个HTTP状态码，
 * 该状态码会在抛出使用此注解标记的异常时返回给客户端。
 * 这种方法尤其适合于RESTful服务中，当你需要根据不同的业务逻辑错误返回不同的HTTP状态码时。
 *
 * 2. 工作原理
 * 当Spring的DispatcherServlet捕捉到一个异常时，
 * 它会检查该异常是否带有@ResponseStatus注解。
 * 如果有，Spring则使用注解指定的HTTP状态码作为响应的状态码。
 * 这样，你就不需要在每个控制器方法中手动检查错误并设置响应状态了，
 * 可以把业务逻辑错误的处理逻辑集中在自定义的异常类中。
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
// @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class AccessException extends RuntimeException{

    //显示的声明一下无参构造器
    public AccessException() {
    }

    //可以指定错误信息的构造器
    public AccessException(String message) {
        super(message);
    }
}
