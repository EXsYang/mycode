package com.hspedu.springcloud.service;

/**
 * @author yangda
 * @create 2024-01-09-22:29
 * @description:
 */
public interface StorageService {
    // 扣减库存
    void reduce(Long productId, Integer nums);
}
