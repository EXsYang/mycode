package com.hspedu.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.hspedu.springcloud.entity.Result;

/**
 * @author yangda
 * @create 2024-01-07-22:06
 * @description:
 * 1. CustomGlobalBlockHandler：全局限流处理类
 * 2. 在CustomGlobalBlockHandler类中，可以编写限流处理方法，但是要求方法是static的，否则调用会出问题
 *
 */
public class CustomGlobalBlockHandler {

    /**
     * blockHandler / blockHandlerClass: blockHandler 对应处理 BlockException 的函数名称，可选项。
     * blockHandler 函数访问范围需要是 public，返回类型需要与原方法相匹配
     * ，参数类型需要和原方法相匹配并且最后加一个额外的参数，类型为 BlockException。
     * blockHandler 函数默认需要和原方法在同一个类中。若希望使用其他类的函数
     * ，则可以指定 blockHandlerClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     */
    //该方法在对应的目标方法上含有注解
    // @SentinelResource(blockHandlerClass = CustomGlobalBlockHandler.class,blockHandler = "handlerMethod1")
    //时被调用
    public static Result handlerMethod1(BlockException blockException){
    // 如果这里的方法被设置为private的，会报500状态码
    // ，说明这里的方法调用失败
    // private static Result handlerMethod1(BlockException blockException){
        return Result.error("400","sentinel控制台配置违规 客户自定义异常/限流处理方法handlerMethod1()");
    }


    public static Result handlerMethod2(BlockException blockException){
        return Result.error("401","sentinel控制台配置违规 客户自定义异常/限流处理方法handlerMethod2()");
    }
}
