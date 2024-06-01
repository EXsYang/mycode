package com.atguigu.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_order_item") //逻辑表名
public class OrderItem {
    @TableId(type = IdType.ASSIGN_ID) //分布式id
    private Long id;
    private Long userId;
    private Long orderId;
    private BigDecimal price;
    private Integer count;
}
