package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author yangda
 * @create 2024-01-01-20:08
 * @description:  openfeign + Eureka 调用远程方法，注意这里需要@Component注解
 */
@Component
@FeignClient(value = "MEMBER-SERVICE-PROVIDER")
public interface MemberFeignService {

    //这里定义方法-就是远程调用的接口

    /**
     * 1.远程调用方式是get
     * 2.远程调用的url http://MEMBER-SERVICE-PROVIDER/member/get/{id}
     * 3.MEMBER-SERVICE-PROVIDER 就是服务提供方在Eureka-Server注册的服务 (服务列表=> localhost:member-service-provider:10000 , localhost:member-service-provider:10002)
     * 4.openfeign 会根据负载均衡来决定调用10000/10002端口-默认是轮询算法
     * 5.这里的方法名getMemberById由程序员指定,可以和远程要调用的方法的方法名不一样,但是这里一样更清晰
     * 6.openfeign好处：支持springmvc注解 + 接口解耦
     */
    @GetMapping("/member/get/{id}")
    public Result getMemberById(@PathVariable("id") Long id);

}
