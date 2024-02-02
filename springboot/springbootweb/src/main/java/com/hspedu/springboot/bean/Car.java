package com.hspedu.springboot.bean;

import lombok.Data;

/**
 * @author yangda
 * @create 2023-12-06-15:15
 * @description: 演示自定义对象参数使用，完成自动封装，类型转换
 */
@Data
public class Car {
    private String name;
    private Double price;
}
