package com.hspedu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangda
 * @create 2024-01-09-22:11
 * @description:
 */
@Mapper
public interface StorageDao {
    //如果在对应的xml文件中没有声明对应的方法就会报错: Statement with id="reduce" not defined in mapper xml
    //扣减库存信息
    void reduce(@Param("productId") Long productId, @Param("nums") Integer nums);
}
