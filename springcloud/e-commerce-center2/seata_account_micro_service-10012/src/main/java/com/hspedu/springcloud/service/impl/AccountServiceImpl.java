package com.hspedu.springcloud.service.impl;

import com.hspedu.springcloud.dao.AccountDao;
import com.hspedu.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-10-0:31
 * @description:
 */
@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Resource
    AccountDao accountDao;

    @Override
    public void reduce(Long userId, Integer money) {

        log.info("========seata_account_micro_service-10012 扣减账户余额 start======");
        accountDao.reduce(userId,money);
        log.info("========seata_account_micro_service-10012 扣减账户余额 end======");

    }
}
