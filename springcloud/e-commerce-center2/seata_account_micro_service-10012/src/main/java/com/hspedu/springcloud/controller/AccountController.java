package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yangda
 * @create 2024-01-10-0:35
 * @description:
 */
@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额 reduce:减少
     */
    @PostMapping("/account/reduce")
    public Result reduce(@RequestParam("userId") Long userId, @RequestParam("money")
            Integer money) {
        //模拟异常, 超时 注意：openfeign默认超时时间为1s
        // try {
        // TimeUnit.SECONDS.sleep(12);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        accountService.reduce(userId, money);
        return Result.success("200", "扣减账户余额 OK");
    }
}
