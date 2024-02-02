package com.hspedu.springboot.mybatis.service;

import com.hspedu.springboot.mybatis.bean.Monster;

/**
 * @author yangda
 * @create 2023-12-17-19:37
 * @description:
 */
public interface MonsterService {

    //根据id返回Monster对象
    public Monster getMonsterById(Integer id);
}
