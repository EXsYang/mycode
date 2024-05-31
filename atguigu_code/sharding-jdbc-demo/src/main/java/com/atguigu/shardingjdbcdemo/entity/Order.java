package com.atguigu.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public class Order {

    //@TableId(type = IdType.AUTO) //自增id
    @TableId(type = IdType.ASSIGN_ID) //分布式id
    private Long id;
    private String orderNo;
    private Long userId;
}
