package com.hspedu.mapper;

import com.hspedu.entity.Monster;

/**
 * @author yangda
 * @create 2023-10-27-19:38
 * @description: 声明对DB的crud方法
 * 这是一个接口 用于定义操作monster表的方法
 * 这些方法可以通过注解或者xml文件来实现
 */
public interface MonsterMapper {

    //查询方法
    public Monster getMonsterById(Integer id);


}
