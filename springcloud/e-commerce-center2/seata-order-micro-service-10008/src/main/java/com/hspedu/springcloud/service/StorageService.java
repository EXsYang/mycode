package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangda
 * @create 2024-01-10-1:26
 * @description: 使用openfeign远程调用 seata_storage_micro_service-10010 的接口方法
 * 注意，找的是seata_storage_micro_service-10010 的Controller中的方法
 */
// @FeignClient(value = "seata_storage_micro_service")
@FeignClient(value = "seata-storage-micro-service")
public interface StorageService {

    /**
     * 解读：
     * 1. 远程调用方式是 post
     * 2. 远程调用url 为 http://seata_storage_micro_service/storage/reduce
     * 3. seata_storage_micro_service是nacos注册中心服务名
     * 4.openfeign是通过接口方式调用服务的
     */
    //扣减库存
    @PostMapping("/storage/reduce")
    // public Result reduce(Long productId, Integer nums); //StorageController不写@RequestParam,这里也不写@RequestParam会报错 org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'orderController': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'orderServiceImpl': Injection of resource dependencies failed; nested exception is org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'com.hspedu.springcloud.service.StorageService': FactoryBean threw exception on object creation; nested exception is java.lang.IllegalStateException: Method has too many Body parameters: public abstract com.hspedu.springcloud.entity.Result com.hspedu.springcloud.service.StorageService.reduce(java.lang.Long,java.lang.Integer)
    public Result reduce(@RequestParam("productId") Long productId, @RequestParam("nums") Integer nums);

}
