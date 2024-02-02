package com.hspedu.springcloud.service;

import com.hspedu.springcloud.entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangda
 * @create 2024-01-10-1:34
 * @description:
 */
// @FeignClient(value = "seata_account_micro_service")
@FeignClient(value = "seata-account-micro-service")
public interface AccountService {


    /**
     * 解读：
     * 1. 远程调用方式是 post
     * 2. 远程调用url 为 http://seata_account_micro_service/account/reduce
     * 3. seata_account_micro_service是nacos注册中心服务名
     * 4.openfeign是通过接口方式调用服务的
     */
    @PostMapping("/account/reduce")
    public Result reduce(@RequestParam("userId") Long userId, @RequestParam("money") Integer money);
    // 这里因为是在service层 形参参数的注解可以删掉
    // ，但是如果是在controller层,这里的形参参数注解需要带上 (不对)
    // 下面不加会报错
    // public Result reduce(Long userId, Integer money);
}
