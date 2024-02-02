package com.hspedu.springcloud.controller;

import com.hspedu.springcloud.entity.Result;
import com.hspedu.springcloud.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangda
 * @create 2024-01-09-22:33
 * @description:
 */
@RestController
public class StorageController {

    @Resource
    private StorageService storageService;

    //扣减库存
    @PostMapping("/storage/reduce")
    // public Result reduce(Long productId, Integer nums) {
    public Result reduce(@RequestParam("productId") Long productId, @RequestParam("nums") Integer nums) {
        storageService.reduce(productId, nums);
        return Result.success("扣减库存成功 ok", null);
    }
}
