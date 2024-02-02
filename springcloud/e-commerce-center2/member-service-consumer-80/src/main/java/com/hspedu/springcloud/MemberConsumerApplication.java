package com.hspedu.springcloud;

import com.hspedu.springcloud.config.RibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangda
 * @create 2023-12-29-21:17
 * @description: http://localhost/member/consumer/get/5
 * 注解@EnableEurekaClient:将程序标识为EurekaClient
 */
@EnableEurekaClient
@SpringBootApplication
@EnableDiscoveryClient //启用服务发现
//指定Ribbon的负载均衡算法,更加明确
@RibbonClient(name = "MEMBER_SERVICE_PROVIDER_URL",configuration = RibbonRule.class)
public class MemberConsumerApplication {
    public static void main(String[] args) {
        ApplicationContext ioc = SpringApplication.run(MemberConsumerApplication.class, args);
        System.out.println("MemberConsumerApplication starting...");
    }
}
