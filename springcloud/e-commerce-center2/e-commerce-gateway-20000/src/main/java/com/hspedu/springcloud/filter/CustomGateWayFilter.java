// package com.hspedu.springcloud.filter;
//
// import org.springframework.cloud.gateway.filter.GatewayFilterChain;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.Ordered;
// import org.springframework.http.HttpStatus;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;
// import reactor.core.publisher.Mono;
//
// /**
//  * @author yangda
//  * @create 2024-01-03-21:47
//  * @description: 自定义Filter
//  * 这段代码是Spring Cloud Gateway中的一个自定义过滤器，用于在请求通过网关时进行检查。
//  *  测试地址 http://localhost:20000/member/get/3?user=hspedu&pwd=123456
//  */
// @Component
// public class CustomGateWayFilter implements GlobalFilter, Ordered {
//
//     //filter方法是核心方法，将我们的过滤器业务写在该方法中
//     @Override
//     public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//         System.out.println("-----------CustomGateWayFilter-------------");
//         //先获取对应的参数
//         //比如 http://localhost:20000/member/get/1?user=hspedu&pwd=123456
//         // .get("user") 返回的是一个List集合,因为一个参数user可能会有多个值
//         // 这里统一使用List返回的，如果是单个值的情况需要.get("user").get(0);
//         // String user = exchange.getRequest().getQueryParams().get("user").get(0);
//         //.get("user").get(0)等价于下面的 .getFirst("user")
//         // 并不是获取第一个参数,而是获取参数user的第0位置上的值
//         String user = exchange.getRequest().getQueryParams().getFirst("user");
//         String pwd = exchange.getRequest().getQueryParams().getFirst("pwd");
//
//         if (!("hspedu".equals(user) && "123456".equals(pwd))){
//             //如果不满足条件
//             System.out.println("------非法用户-------");
//             //设置HTTP状态码为406 Not Acceptable
//             exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);//回应
//             // 结束请求，不再继续路由
//             //结束消息处理: 当调用 setComplete() 方法时，它表示当前HTTP请求的处理已经完成。
//             // 这个方法是对整个请求处理流程的一个结束信号。通常用于立即结束请求处理流程，
//             // 不再将请求转发给链中的下一个过滤器或路由。
//             return exchange.getResponse().setComplete();
//         }
//
//         //满足条件 验证通过放行
//         return chain.filter(exchange);
//     }
//
//     //order 表示过滤器执行顺序, 数字越小,优先级越高
//     @Override
//     public int getOrder() {
//         return 0;
//     }
// }
