package com.hspedu.springboot.mybatis.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-12-16-22:38
 * @description:
 */
@Data
public class Monster {
    private Integer id;
    private Integer age;
    //这里通过注解解决时区问题，同时指定时间格式
    // , 如果不设置前端会显示为birthday	"2000-11-10T16:00:00.000+00:00"
    //GMT 就是格里尼治标准时间  东八区 加八个小时
    //当标注注解@JsonFormat后=》 birthday	"2000-11-11 00:00:00"
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;
}
