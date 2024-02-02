// package com.hspedu.springcloud.config;
//
// import com.netflix.loadbalancer.IRule;
// import com.netflix.loadbalancer.RandomRule;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @author yangda
//  * @create 2024-01-03-13:49
//  * @description:
//  * RibbonRule：配置自己的负载均衡算法
//  */
// @Configuration
// public class RibbonRule {
//
//     //配置注入自己的负载均衡算法
//     @Bean
//     public IRule myRibbonRule(){
//         //这里返回的是RandomRule 随机的负载均衡算法 即随机选择一个server,可以根据业务自己指定
//         //这里配置的RibbonRule是全局的
//         return new RandomRule();
//         // return new WeightedResponseTimeRule();
//     }
//
// }
//
