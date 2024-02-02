package com.hspedu.springcloud.service.impl;

import com.hspedu.springcloud.dao.StorageDao;
import com.hspedu.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-09-22:29
 * @description:
 */
@Service
@Slf4j
public class StorageServiceImpl implements StorageService {
    // private static final Logger LOGGER =
    //         LoggerFactory.getLogger(StorageServiceImpl.class);
    @Resource
    private StorageDao storageDao;
    @Override
    public void reduce(Long productId, Integer nums) {
        log.info("==========seata_storage_micro_service-10010 扣 减 库 存 start==========");
        storageDao.reduce(productId, nums);
        log.info("==========seata_storage_micro_service-10010 扣 减 库 存 end==========");
    }
}
