package com.hspedu.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yangda
 * @create 2023-12-12-13:32
 * @description:
 * 自定义异常 访问异常
 * 自定义异常在javaSE讲过 一般情况下，我们自定义异常是继承 RuntimeException
 * value = HttpStatus.FORBIDDEN: 表示发生AccessException异常，我们通过http协议返回状态码403
 * FORBIDDEN(403, Series.CLIENT_ERROR, "Forbidden"),
 * 这个状态码和自定义异常的对应关系是由程序员来决定的【尽量合理来设置】
 *
 * BAD_GATEWAY(502, Series.SERVER_ERROR, "Bad Gateway"),
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
