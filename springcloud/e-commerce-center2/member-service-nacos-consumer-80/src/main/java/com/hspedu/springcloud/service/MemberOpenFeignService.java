package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yangda
 * @create 2024-01-08-21:46
 * @description: openfeign + nacos 实现远程方法调用
 * 注意：引入了openfeign后需要在主启动类中加入注解@EnableFeignClients 启用openfeign
 * , 否则openfeign不生效 启动报错
 *
 * openfeign + nacos如果要切换负载均衡算法，仍然是把RibbonRule.java 注销的代码打开即可，依然可以使用
 *
 * openfeign默认超时时间是1s 1s后没有返回响应 就超时
 *
 * 使用openfeign远程调用 member-service-nacos-provider 的接口方法
   注意，找的是smember-service-nacos-provider 的Controller中的方法
 */
//value = "member-service-nacos-provider" ,是在nacos注册中心的 将来openfeign要远程调用的提供服务的服务名
//在这里不需要加@Component注解,只用加@FeignClient即可
// 出现了Java异常/业务异常 就会由fallback中写的类进行处理
@FeignClient(value = "member-service-nacos-provider",fallback = MemberFeignFallbackService.class)
public interface MemberOpenFeignService {

    /**
     * 解读：
     * 1. 远程调用方式是 get
     * 2. 远程调用url 为 http://member-service-nacos-provider/member/get/{id}
     * 3. member-service-nacos-provider是nacos注册中心服务名
     * 4.openfeign会根据负载均衡算法来决定调用的是10004/10006,默认是轮询算法
     * 5.openfeign是通过接口方式调用服务的
     */
    @GetMapping("/member/get/{id}")
    public Result getMemberById(@PathVariable("id") Long id);
}
