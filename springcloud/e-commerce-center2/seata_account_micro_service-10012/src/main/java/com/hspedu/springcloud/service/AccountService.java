package com.hspedu.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yangda
 * @create 2024-01-10-0:29
 * @description:
 */
public interface AccountService {
    //扣减用户的money
    void reduce(Long userId,Integer money);
}
