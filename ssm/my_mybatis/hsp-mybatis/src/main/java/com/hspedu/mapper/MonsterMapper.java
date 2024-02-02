package com.hspedu.mapper;

import com.hspedu.entity.Monster;

/**
 * @author yangda
 * @create 2023-10-19-23:17
 * @description: 声明对DB的crud方法
 */
public interface MonsterMapper {

    //查询方法
    public Monster getMonsterById(Integer id);

}
