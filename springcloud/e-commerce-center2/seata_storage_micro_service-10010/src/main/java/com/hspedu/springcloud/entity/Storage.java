package com.hspedu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangda
 * @create 2024-01-09-21:48
 * @description: Storage实体类 对应 storage表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private Long id;
    private Long productId;
    private Integer amount;
}
