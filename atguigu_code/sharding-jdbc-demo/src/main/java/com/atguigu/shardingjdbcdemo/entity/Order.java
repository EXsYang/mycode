package com.atguigu.shardingjdbcdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_order")
public class Order {

    // @TableId(type = IdType.AUTO) //主键自增id，单库单表可以使用
    @TableId(type = IdType.ASSIGN_ID) //对于分布式数据库,采用 分布式ID。 MyBatisPlus提供的雪花算法 生成19位唯一的整数值
    private Long id;
    private String orderNo;
    private Long userId;
}
