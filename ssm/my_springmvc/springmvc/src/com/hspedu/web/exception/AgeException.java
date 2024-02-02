package com.hspedu.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yangda
 * @create 2023-10-15-16:39
 * @description: 自定义异常
 * 关于自定义异常，在javaSE讲过
 * reason = "年龄必须在1-120之间",value = HttpStatus.BAD_REQUEST
 * 参数是给原生的tomcat 返回的提示页面使用的/给http协议看的
 * 写了 reason = "年龄必须在1-120之间" 参数 ,该信息并不会放入到ex.message中
 * 需要手动传入message 通过构造器传入
 */
@ResponseStatus(reason = "年龄必须在1-120之间",value = HttpStatus.BAD_REQUEST)
public class AgeException extends RuntimeException{

    public AgeException() {
    }

    public AgeException(String message) {
        super(message);
    }
}
