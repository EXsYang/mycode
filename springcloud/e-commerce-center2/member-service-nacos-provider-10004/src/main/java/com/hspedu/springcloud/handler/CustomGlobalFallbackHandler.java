package com.hspedu.springcloud.handler;

import com.hspedu.springcloud.entity.Result;
import net.bytebuddy.implementation.bytecode.Throw;

/**
 * @author yangda
 * @create 2024-01-07-22:36
 * @description: CustomGlobalFallbackHandler 全局fallback处理类
 * 在CustomGlobalFallbackHandler类中，可以编写处理Java异常/业务异常方法-依然是需要声明为static的
 */
public class CustomGlobalFallbackHandler {

    /**
     * fallback / fallbackClass：fallback 函数名称，可选项，用于在抛出异常的时候提供 fallback 处理逻辑。fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。fallback 函数签名和位置要求：
     * 返回值类型必须与原函数返回值类型一致；
     * 方法参数列表需要和原函数一致，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * fallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     *
     * defaultFallback（since 1.6.0）：默认的 fallback 函数名称，可选项，通常用于通用的 fallback 逻辑（即可以用于很多服务或方法）。默认 fallback 函数可以针对所有类型的异常（除了 exceptionsToIgnore 里面排除掉的异常类型）进行处理。若同时配置了 fallback 和 defaultFallback，则只有 fallback 会生效。defaultFallback 函数签名要求：
     * 返回值类型必须与原函数返回值类型一致；
     * 方法参数列表需要为空，或者可以额外多一个 Throwable 类型的参数用于接收对应的异常。
     * defaultFallback 函数默认需要和原方法在同一个类中。若希望使用其他类的函数，则可以指定 fallbackClass 为对应的类的 Class 对象，注意对应的函数必需为 static 函数，否则无法解析。
     */
    public static Result fallbackHandlerMethod1(Throwable e) {
    // 如果这里的方法被设置为private的，会报500状态码
    // ，说明这里的方法调用失败
    // private static Result fallbackHandlerMethod1(Throwable e) {
        return Result.error("402", "java异常 信息=" + e.getMessage());
    }

    public static Result fallbackHandlerMethod2(Throwable e) {
        return Result.error("403", "java异常 信息=" + e.getMessage());
    }
}
