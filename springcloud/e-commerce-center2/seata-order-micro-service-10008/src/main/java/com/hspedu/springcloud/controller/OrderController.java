package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Order;
import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-10-1:52
 * @description:
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    //这里为了测试方便 没有加@RequestBody注解
    //@RequestBody注解,用来接收json格式数据
    //1.我们的前端如果是以json格式来发送添加信息Order,那么我们后端需要使用@RequestBody注解
    //  ,才能将数据封装到对应的bean中,同时保证http请求头 content-type是对应的 这里即为Content-Type:application/json
    //2.如果前端是以表单形式提交,则不需要使用@RequestBody注解,同时保证http请求头 content-type是对应的 这里即为Content-Type:multipart/form-data
    @GetMapping("/order/save")
    public Result save(Order order) {
        orderService.save(order);
        return Result.success("订单创建成功", null);
    }
}
