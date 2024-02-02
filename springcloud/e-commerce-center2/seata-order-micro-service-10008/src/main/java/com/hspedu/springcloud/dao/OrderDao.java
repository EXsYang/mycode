package com.hspedu.springcloud.dao;

import com.hspedu.springcloud.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangda
 * @create 2024-01-10-1:12
 * @description:
 */
@Mapper
public interface OrderDao {
    //新建订单
    void save(Order order);
    //修改订单状态
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
