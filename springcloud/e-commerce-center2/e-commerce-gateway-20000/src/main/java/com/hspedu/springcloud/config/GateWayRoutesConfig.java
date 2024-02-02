// package com.hspedu.springcloud.config;
//
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * @author yangda
//  * @create 2024-01-02-22:37
//  * @description:
//  * 注意事项：配置Gateway 要么使用application.yml,要么使用配置类,不要两个都用,容易产生混淆
//  */
// @Configuration
// public class GateWayRoutesConfig {
//     //配置/注入路由
//     /**
//      * 对比application.yml配置路由来理解
//      * cloud:
//      *     gateway:
//      *       routes: #配置路由,可以配置多个路由,List<RouteDefinition> routes
//      *         - id: member_route01 #路由的id,程序员自己配置,要求唯一
//      *           # gateway 最终访问的url 是 url=uri + Path
//      *           # 匹配后提供服务的路由地址:也可以是外网 http://www.baidu.com
//      *           # 比如 客户端/浏览器请求的url http://localhost:20000/member/get/1
//      *           # 如果根据Path匹配成功 最终访问的url/转发的url 就是http://localhost:10000/member/get/1
//      *           # 如果匹配失败,则有gateway返回404信息
//      *           # 疑惑：这里的uri这里暂时配置的是固定的，在当前的情况可以不是Eureka Server[服务发现]
//      *           # ,后面会使用灵活的方式配置,就会使用Eureka Server[服务发现]
//      *           uri: http://localhost:10000
//      *           predicates: #断言，可以有多种形式
//      *             - Path=/member/get/**
//      */
//     @Bean
//     public RouteLocator myRouteLocator04(RouteLocatorBuilder routeLocatorBuilder){
//
//
//         RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//
//         //1. 下面的方法我们分别指定了id path uri
//         //2. Function<PredicateSpec, Route.AsyncBuilder> fn
//         //(1) 是一个函数式接口
//         //(2) 接收的类型是PredicateSpec,返回的类型是Route.AsyncBuilder
//         //(3) r -> r.path("/member/get/**")
//         //                 .uri("http://localhost:10000") 是lambda表达式
//         //这里可以理解为是一个规定的写法
//         return routes.route("member_route04",r -> r.path("/member/get/**")
//                 .uri("http://localhost:10000"))
//                 .build();
//     }
//     @Bean
//     public RouteLocator myRouteLocator05(RouteLocatorBuilder routeLocatorBuilder){
//
//         RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
//
//         return routes.route("member_route05",r -> r.path("/member/save")
//                 .uri("http://localhost:10000"))
//                 .build();
//     }
//
// }
