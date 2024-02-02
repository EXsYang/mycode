package com.hspedu.springboot.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-12-06-15:16
 * @description: 演示自定义对象参数使用，完成自动封装，类型转换
 */

@Data //这个注解必须要给，否则没有getter/setter方法，没有办法完成封装的任务
public class Monster {

    private Integer id;
    private String name;
    private Integer age;
    private Boolean isMarried;
    private Date birth;
    private Car car;
}
